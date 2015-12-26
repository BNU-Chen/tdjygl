var map;
var mapServiceLayer;
var navigation; //导航
var infoTemplate;
var popup;
var template;
var renderer;   //设置的渲染样式
var mapSr;   // 当前地图的空间参考系
var showLayerGroupIndex;     //显示的图层组的index
var featureLayer_cyd;   //采样点的FeatureLayer
var featureLayer_ght;   //规划图的FeatureLayer
var featureLayer_xzt;   //现状图的FeatureLayer
var featureLayer_jgt;   //竣工图的FeatureLayer
var findTask, findParams;   //查询要素

require([
    "esri/map",
    "esri/graphic",
    "esri/config",
    "esri/urlUtils",
    "esri/geometry/Point",
    "esri/geometry/Extent",
    "esri/SpatialReference",
    "esri/layers/FeatureLayer",
    "esri/InfoTemplate",
    "esri/symbols/SimpleFillSymbol",
    "esri/symbols/SimpleMarkerSymbol",
    "esri/renderers/ClassBreaksRenderer",
    "esri/tasks/query",
    "esri/tasks/find",

    "esri/dijit/InfoWindowLite",  //弹出对话框
    "esri/dijit/Popup",           //弹出对话框
    "esri/dijit/PopupTemplate",   //弹出对话框模板
    "esri/dijit/HomeButton",  //Home按钮
    "esri/dijit/Scalebar",    //比例尺
    "esri/dijit/LayerSwipe",  //图层卷帘效果

    "dojo/_base/Color",
    "dojo/parser",
    "dojo/dom",
    "dojo/dom-class",
    "dojo/dom-style",
    "dojo/dom-construct",

    "dojo/on",
    "dojo/number",
    "dojo/string",

    "dojo/domReady!"
], function (Map, Graphic, esriConfig, urlUtils, Point, Extent, SpatialReference, FeatureLayer, InfoTemplate, SimpleFillSymbol, SimpleMarkerSymbol, ClassBreakRenderer, Query, InfoWindowLite, Popup, PopupTemplate, HomeButton, Scalebar, LayerSwipe, Color, parser, dom, domClass, domStyle, domConstruct, on, number, String) {

    //parser.parse();
//    urlUtils.addProxyRule({
//        urlPrefix: "http://172.16.190.35",
//        proxyUrl: "/map/proxy/"
//    });
    esriConfig.defaults.io.corsDetection = false;
//    esriConfig.defaults.io.proxyUrl = "map/proxy/proxy.jsp";
    //esriConfig.defaults.io.alwaysUseProxy = false;

    dojo.addOnLoad(initMap);

    //-------------------------------------地图初始化-------------------------------------------------------------------
    function initMap() {
        //清空地图内容，再次添加
        if (map) {
            map.destroy();
        }
        map = new Map("mapDiv", {
            //logo: false
        });
        map.removeAllLayers();
        mapServiceLayer = new esri.layers.ArcGISDynamicMapServiceLayer("http://172.16.190.35:8399/arcgis/rest/services/basemap/csqzjxzq/MapServer");
        map.addLayer(mapServiceLayer);

        infoTemplate = new esri.InfoTemplate("${项目名称}", "${*}");
        //添加项目地块图层
        featureLayer_jgt = new esri.layers.FeatureLayer("http://172.16.190.35:8399/arcgis/rest/services/fukenyanshou/fukenxiangmu/FeatureServer/3", {
            mode: esri.layers.FeatureLayer.MODE_SNAPSHOT,
            outFields: [ "*" ],
            infoTemplate: infoTemplate
        });
        map.addLayer(featureLayer_jgt);

        // dojo框架事件
        dojo.connect(map, "onLoad", function () {
            console.log("Map onLoad event.");
        });
        dojo.connect(map, "onExtentChange", ResizeMap);

//        //处理传递过来的参数
//        var findTextCmp = Ext.getCmp('fkxmbh');
//        //console.log("findTextCmp:",findTextCmp);
//        if (findTextCmp) {
//            var findText = findTextCmp.getValue();
//            console.log("findText:",findText);
//            FindFeatureByText(findText);
//        }
    }

    //缩放到图层
    ZoomToLayer = function () {
        var featLayerToZoom;
        featLayerToZoom = featureLayer_jgt;

        if (!featLayerToZoom) {
            return;
        }
        var query = new esri.tasks.Query();
        query.where = "0=1";
        featLayerToZoom.selectFeatures(query, esri.layers.FeatureLayer.SELECTION_NEW, function () {
            map.setExtent(featLayerToZoom.fullExtent);
        });
    }

    //查询要素
    FindFeatureByText = function (searchText) {
        if (searchText == "") {
            Ext.Msg.alert("请输入复垦项目编号。");
            ZoomToLayer();     //如果没有查询到结果，则缩放到整个图层
            return;
        }
        //console.log("查询关键词：",searchText);
        //create find task with url to map service
        findTask = new esri.tasks.FindTask("http://172.16.190.35:8399/arcgis/rest/services/fukenyanshou/fukenxiangmu/FeatureServer/3");

        //create find parameters and define known values
        findParams = new esri.tasks.FindParameters();
        findParams.returnGeometry = true;
        findParams.layerIds = [0, 1, 2];
        findParams.searchFields = ["项目编号", "项目名称", "片块编号", "编号", "采集地点", "xzqmc"];

        findParams.searchText = searchText;
        findTask.execute(findParams, showResults);

        function showResults(results) {
            if (results.count == 0) {
                Ext.Msg.alert("没有查询到与项目“" + searchText + "”相关的结果。");
                ZoomToLayer();     //如果没有查询到结果，则缩放到整个图层
                return;
            }

            //symbology for graphics
            var markerSymbol = new esri.symbol.SimpleMarkerSymbol(esri.symbol.SimpleMarkerSymbol.STYLE_SQUARE, 10, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([255, 0, 0]), 1), new dojo.Color([0, 255, 0, 0.25]));
            var lineSymbol = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASH, new dojo.Color([255, 0, 0]), 1);
            var polygonSymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_NONE, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT, new dojo.Color([255, 0, 0]), 2), new dojo.Color([255, 255, 0, 0.25]));

            //find results return an array of findResult.
            map.graphics.clear();
            var data = [];   //存储查询结果数据
            //Build an array of attribute information and add each found graphic to the map
            dojo.forEach(results, function (result) {
                var graphic = result.feature;
                //设置属性框
                var layerName = result.layerName;
                //console.log('layername:',layerName);
                var infoTemplate = new InfoTemplate();
                if (layerName.indexOf('竣工图') >= 0) {
                    infoTemplate.setTitle("项目竣工图属性");
                    infoTemplate.setContent(
                            "项目编号: ${项目编号} <br/>"
                            + "项目名称: ${项目名称} <br/>"
                            + "片块名称: ${片块名称} <br/>"
                    );
                }
                graphic.setInfoTemplate(infoTemplate);

                //设置样式
                switch (graphic.geometry.type) {
                    case "point":
                        graphic.setSymbol(markerSymbol);
                        break;
                    case "polyline":
                        graphic.setSymbol(lineSymbol);
                        break;
                    case "polygon":
                        graphic.setSymbol(polygonSymbol);
                        break;
                }
                map.graphics.add(graphic);
                data.push([result.layerName, result.foundFieldName, result.value, graphic]);
            });

            //缩放到graphics
            var extent = esri.graphicsExtent(map.graphics.graphics);
            map.setExtent(extent, true);
        }
    }

    //清除map上的graphics
    ClearGraphics = function () {
        map.graphics.clear();
    }

    //定位到graphic
    locateByGraphic = function (graphic) {
        if (graphic) {
            //var extent = graphic.geometry.getExtent().expand(5.0);
            var geo = graphic.geometry;
            if (geo.type == 'point') {
                var point = new esri.geometry.Point(geo.x, geo.y, geo.spatialReference); //这个点处于地图的中间
                map.centerAt(point);
            } else {
                var ext = geo._extent;
                var extent = new Extent(ext.xmin, ext.ymin, ext.xmax, ext.ymax, geo.spatialReference);
                map.setExtent(extent.expand(5.0), false);
                //console.log('extent',extent);
            }
            //map.graphics.clear();   //清除graphics图层对象
            //mapSr = geo.spatialReference;
            //console.log('mapSr:',mapSr);
//            console.log("locateGraphic,graphic:", graphic);
        }
    }

    //地图功能--------------------------------------------------------------------------
    //当界面发生变化时，重整地图的大小
    ResizeMap = function () {
        map.resize();
        //map.reposition();
    }


    function onMapZoom() {
        var mapLevel = map.getLevel();
        console.log("getmaplevel:", mapLevel);
    }

    function showExtent(extent) {
        var s = "";
        s = "xmin: " + extent.xmin.toFixed(2) + "," + "ymin: "
            + extent.ymin.toFixed(2) + "," + "xmax: "
            + extent.xmax.toFixed(2) + "," + "ymax: "
            + extent.ymax.toFixed(2) + "," + "CenterX:"
            + ((extent.xmax + extent.xmin) / 2.0).toFixed(2) + ","
            + "CenterY:"
            + ((extent.ymax + extent.ymin) / 2.0).toFixed(2);
        // dojo.byId("extentInfoDiv").innerHTML = s;
        console.log("MapExtent:", s);
//        var findTextCmp = Ext.getCmp('map_fkSampleSearchText');
//        if (findTextCmp) {
//            var xy = ((extent.xmax + extent.xmin) / 2.0).toFixed(2) + ","
//                + ((extent.ymax + extent.ymin) / 2.0).toFixed(2);
//            findTextCmp.setValue(xy);
//        }

    }


});