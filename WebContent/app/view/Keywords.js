/*
 * File: app/view/Keywords.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('MyApp.view.Keywords', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.Keywords',

    autoScroll: true,
    layout: {
        type: 'border'
    },
    title: '查询主题词设置',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    region: 'center',
                    id: 'crawlkeywordsForm',
                    layout: {
                        type: 'fit'
                    },
                    bodyPadding: 10,
                    header: false,
                    title: '查询主题词列表',
                    url: 'look_keywords',
                    items: [
                        {
                            xtype: 'gridpanel',
                            height: 370,
                            width: 540,
                            collapsed: false,
                            title: '查询主题词列表',
                            forceFit: true,
                            store: 'lyjkeywordsStore',
                            columns: [
                                {
                                    xtype: 'numbercolumn',
                                    hidden: true,
                                    width: 30,
                                    dataIndex: 'id',
                                    text: '编号',
                                    format: '0'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'keywordsname',
                                    text: '主题词'
                                },
                                {
                                    xtype: 'datecolumn',
                                    dataIndex: 'adddate',
                                    text: '添加日期'
                                }
                            ],
                            selModel: Ext.create('Ext.selection.CheckboxModel', {

                            }),
                            dockedItems: [
                                {
                                    xtype: 'toolbar',
                                    dock: 'top',
                                    items: [
                                        {
                                            xtype: 'textfield',
                                            id: 'searchField_crawlkeywords',
                                            fieldLabel: ''
                                        },
                                        {
                                            xtype: 'button',
                                            handler: function(button, event) {
                                                var mystore=Ext.StoreMgr.get('lyjkeywordsStore'); //获得store对象
                                                var outSearch=Ext.getCmp('searchField_crawlkeywords').getValue();
                                                //在load事件中添加参数

                                                mystore.on('beforeload', function (store, options) {
                                                    var new_params = {  searchField: outSearch};
                                                    Ext.apply(store.proxy.extraParams, new_params);
                                                });
                                                mystore.load({ params: { start: 0, limit: 10} });
                                            },
                                            text: '查找'
                                        },
                                        {
                                            xtype: 'button',
                                            handler: function(button, event) {
                                                var win=new Ext.Window({
                                                    height: 170,
                                                    width: 400,
                                                    id:'crawlkeywordsWindow',
                                                    layout: {
                                                        type: 'absolute'
                                                    },
                                                    title: '添加',
                                                    modal:'true',
                                                    items: [
                                                    {
                                                        xtype: 'form',
                                                        height: 220,
                                                        jsonSubmit: true,
                                                        id:'crawlkeywords_add_Form',
                                                        layout: {
                                                            type: 'absolute'
                                                        },
                                                        bodyPadding: 10,
                                                        url: 'add_crawlkeywords',
                                                        title: '',
                                                        items: [

                                                        {
                                                            xtype: 'datefield',
                                                            x: 15,
                                                            y: 50,
                                                            width: 300,
                                                            id:'adddate',
                                                            name:'adddate',
                                                            fieldLabel: '日期',
                                                            labelAlign: 'right',
                                                            format: 'Y-m-d'
                                                        },

                                                        {
                                                            xtype: 'textfield',
                                                            id:'keywordsname',
                                                            name:'keywordsname',
                                                            x: 15,
                                                            y: 20,
                                                            width: 300,
                                                            fieldLabel: '关键词',
                                                            labelAlign: 'right'
                                                        },


                                                        {
                                                            xtype: 'button',
                                                            x: 115,
                                                            y: 100,
                                                            width: 50,
                                                            text: '确定',
                                                            handler:function()
                                                            {

                                                                var myform=Ext.getCmp('crawlkeywords_add_Form').getForm();    
                                                                if (myform.isValid()) { // make sure the form contains valid data before submitting
                                                                    myform.submit({
                                                                        success: function(form, action) {
                                                                            Ext.Msg.alert('提示', action.result.msg);

                                                                            var mystore=Ext.StoreMgr.get('lyjkeywordsStore'); //获得store对象
                                                                            //在load事件中添加参数
                                                                            mystore.load(
                                                                            {params:{
                                                                                start:"0",
                                                                                limit:"10",
                                                                                searchField:Ext.getCmp('searchField_crawlkeywords').getValue()
                                                                            }} );
                                                                            win.close();
                                                                        },
                                                                        failure: function(form, action) {
                                                                            Ext.Msg.alert('提示', '保存失败！');
                                                                            win.close();
                                                                        }
                                                                    });
                                                                } else { 
                                                                    Ext.Msg.alert('注意', '填写信息不能为空，请检查！');
                                                                }


                                                                return;

                                                            }
                                                        },
                                                        {
                                                            xtype: 'button',
                                                            handler: function(button, event) {
                                                                win.close();
                                                            },
                                                            x: 230,
                                                            y: 100,
                                                            width: 50,
                                                            text: '取消'
                                                        }
                                                        ]
                                                    }
                                                    ]
                                                });



                                                win.show();
                                            },
                                            text: '添加'
                                        },
                                        {
                                            xtype: 'button',
                                            handler: function(button, event) {

                                                var grid=this.up('grid');
                                                var record =grid.getSelectionModel().getSelection();

                                                if(record.length===0){
                                                    Ext.Msg.alert('提示','请先选择您要操作的行！');    
                                                    return;
                                                }
                                                else
                                                {
                                                    var ids='';
                                                    for(var i=0;i<record.length;i++)
                                                    {
                                                        ids+=record[i].get("id");
                                                        if(i<record.length-1)ids=ids+",";
                                                    }

                                                    Ext.Ajax.request({
                                                        waitMsg:'正在发送，请等待...',
                                                        url:'del_crawlkeywords',
                                                        params:{ids:ids},                          
                                                        success:function(response){
                                                            Ext.Msg.alert('success','删除成功！');                
                                                            var mystore=Ext.StoreMgr.get('lyjkeywordsStore'); //获得store对象
                                                            //在load事件中添加参数
                                                            mystore.load(
                                                            {params:{
                                                                start:"0",
                                                                limit:"10",
                                                                searchField:Ext.getCmp('searchField_crawlkeywords').getValue()
                                                            }} );
                                                        },
                                                        failure:function(){
                                                            Ext.Msg.alert('failed','删除失败！');

                                                        }
                                                    });
                                                }

                                            },
                                            text: '删除'
                                        }
                                    ]
                                },
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    displayInfo: true,
                                    displayMsg: '显示{0} - {1} 条，共计{2}条',
                                    emptyMsg: '没有数据',
                                    store: 'lyjkeywordsStore'
                                }
                            ],
                            listeners: {
                                cellclick: {
                                    fn: me.onGridpanelCellClick1,
                                    scope: me
                                }
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    },

    onGridpanelCellClick1: function(tableview, td, cellIndex, record, tr, rowIndex, e, eOpts) {
        if(cellIndex===undefined || cellIndex<2) return;

        var win=new Ext.Window({
            height: 240,
            width: 400,
            layout: {
                type: 'absolute'
            },
            title: '编辑',
            modal:'true',
            items: [
            {
                xtype: 'form',
                id:'crawlurl_edit_Form',
                height: 220,
                layout: {
                    type: 'absolute'
                },
                bodyPadding: 10,
                title: '',
                items: [
                {
                    xtype: 'label',
                    x: 37,
                    y: 20,
                    text: '原网站名称：'
                },
                {
                    xtype: 'label',
                    x: 40,
                    y: 50,
                    text: '原网站URL：'
                },
                {
                    xtype: 'label',
                    x: 11,
                    y: 80,
                    text: '修改后网站名称：'
                },
                {
                    xtype: 'label',
                    x: 41,
                    y: 110,
                    text: '修改后URL：'
                },
                {
                    xtype: 'textfield',
                    id:'webName_old',
                    x: 115,
                    y: 20,
                    width: 250,
                    fieldLabel: '',
                    disabled:'true'
                },
                {
                    xtype: 'textfield',
                    id:'webURL_old',
                    x: 115,
                    y: 50,
                    width: 250,
                    fieldLabel: '',
                    disabled:'true'
                },
                {
                    xtype: 'textfield',
                    id:'webName_new',
                    x: 115,
                    y: 80,
                    width: 250,
                    fieldLabel: ''
                },
                {
                    xtype: 'textfield',
                    id:'webURL_new',
                    x: 115,
                    y: 110,
                    width: 250,
                    fieldLabel: ''
                },
                {
                    xtype: 'button',
                    x: 115,
                    y: 150,
                    width: 50,
                    text: '确定',
                    handler:function(){                     
                        var myform=Ext.getCmp('crawlurl_edit_Form').getForm();    
                        if (myform.isValid()) { // make sure the form contains valid data before submitting
                            myform.submit({
                                success: function(form, action) {
                                    Ext.Msg.alert('提示', action.result.msg);

                                    var mystore=Ext.StoreMgr.get('lyjurlStore'); //获得store对象
                                    //在load事件中添加参数
                                    mystore.load(
                                    {params:{
                                        start:"0",
                                        limit:"10",
                                        searchField:Ext.getCmp('searchField_crawlurl').getValue()

                                    }} );
                                    win.close();
                                },
                                failure: function(form, action) {
                                    Ext.Msg.alert('提示', action.result.msg);
                                    win.close();
                                }
                            });
                        } else { 
                            Ext.Msg.alert('注意', '填写信息不能为空，请检查！');
                        }


                        return;

                    }
                },
                {
                    xtype: 'button',
                    handler: function(button, event) {
                        win.close();
                    },
                    x: 230,
                    y: 150,
                    width: 50,
                    text: '取消'
                }
                ]
            }
            ]
        });
        Ext.getCmp('webName_old').setValue(record.data.webname);
        Ext.getCmp('webURL_old').setValue(record.data.url);

        win.show();
    }

});