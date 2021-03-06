/*
 * File: app/view/pmjjclient.js
 *
 * This file was generated by Sencha Architect version 3.1.0.
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

Ext.define('MyApp.view.pmjjclient', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.pmjjclient',

    requires: [
        'Ext.form.Panel',
        'Ext.form.field.Display',
        'Ext.form.Label',
        'Ext.form.field.Text',
        'Ext.tab.Panel',
        'Ext.grid.Panel',
        'Ext.grid.column.Column',
        'Ext.grid.View',
        'Ext.tab.Tab',
        'Ext.toolbar.Toolbar',
        'Ext.Img'
    ],

    height: 612,
    width: 950,
    autoScroll: true,
    layout: 'absolute',
    title: '<font size=\'2.5px\'>拍卖竞价</font>',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    x: 40,
                    y: 20,
                    height: 440,
                    id: 'jpxxclient',
                    itemId: 'jpxxclient',
                    width: 500,
                    layout: 'absolute',
                    bodyPadding: 10,
                    title: '竞拍信息',
                    jsonSubmit: true,
                    url: 'getjpxx',
                    items: [
                        {
                            xtype: 'displayfield',
                            x: 20,
                            y: 30,
                            id: 'jpzbpcbhclient',
                            width: 220,
                            fieldLabel: '批次编号',
                            name: 'zbpcbh',
                            submitValue: true
                        },
                        {
                            xtype: 'displayfield',
                            x: 280,
                            y: 30,
                            width: 130,
                            fieldLabel: '单位额度',
                            name: 'dwed'
                        },
                        {
                            xtype: 'displayfield',
                            x: 20,
                            y: 80,
                            width: 150,
                            fieldLabel: '本批次总数量',
                            name: 'sl'
                        },
                        {
                            xtype: 'displayfield',
                            x: 280,
                            y: 80,
                            fieldLabel: '申购总数量',
                            name: 'sgzsl'
                        },
                        {
                            xtype: 'displayfield',
                            x: 20,
                            y: 250,
                            id: 'djclient',
                            width: 200,
                            fieldLabel: '底价',
                            name: 'dj'
                        },
                        {
                            xtype: 'displayfield',
                            x: 280,
                            y: 140,
                            width: 200,
                            fieldLabel: '最高出价',
                            name: 'zgcj',
                            value: '无'
                        },
                        {
                            xtype: 'displayfield',
                            x: 20,
                            y: 135,
                            id: 'pcztclient',
                            style: {
                                fontColor: 'red'
                            },
                            width: 220,
                            fieldLabel: '状态',
                            name: 'pczt',
                            value: '正在竞拍\r',
                            fieldStyle: ''
                        },
                        {
                            xtype: 'displayfield',
                            x: 20,
                            y: 190,
                            width: 220,
                            fieldLabel: '竞价开始日期',
                            name: 'jjksrq'
                        },
                        {
                            xtype: 'displayfield',
                            x: 275,
                            y: 190,
                            width: 210,
                            fieldLabel: '开始时间',
                            msgTarget: 'under',
                            name: 'kssj'
                        },
                        {
                            xtype: 'displayfield',
                            x: 280,
                            y: 255,
                            id: 'zdjjclient',
                            width: 190,
                            fieldLabel: '最低加价',
                            msgTarget: 'under',
                            name: 'zdjj',
                            value: '1000'
                        },
                        {
                            xtype: 'displayfield',
                            x: 280,
                            y: 305,
                            id: 'wdcjclient',
                            width: 190,
                            fieldLabel: '我的出价',
                            msgTarget: 'under',
                            name: 'cj',
                            submitValue: true,
                            value: '0'
                        },
                        {
                            xtype: 'button',
                            handler: function(button, event) {
                                var wdcj=Ext.getCmp('wdcjclient');
                                var dj=Ext.getCmp('djclient').getValue();
                                var jj=Ext.getCmp('zdjjclient').getValue();
                                if(wdcj.getValue()==0)
                                {
                                    wdcj.setValue(dj*10000);
                                }
                                else
                                {
                                    wdcj.setValue(Ext.Number.from(wdcj.getValue(),10000)+Ext.Number.from(jj,1000));
                                }
                                Ext.Ajax.request(
                                {
                                    url:'addjpcj',
                                    method:'post',
                                    headers:{

                                        'Content-Type':'application/json'
                                    },

                                    success:function(response){

                                        //alert('sucdess');
                                    },


                                    failure:function(){
                                        //Ext.MessageBox.alert('错误','操作失败！');
                                    },
                                    params:Ext.encode(Ext.getCmp('jpxxclient').getValues())

                                });
                            },
                            x: 185,
                            y: 365,
                            height: 25,
                            id: 'cjbtnclient',
                            width: 100,
                            text: '出价'
                        },
                        {
                            xtype: 'label',
                            x: 430,
                            y: 35,
                            text: '公顷/张'
                        },
                        {
                            xtype: 'label',
                            x: 170,
                            y: 85,
                            height: 10,
                            text: '张'
                        },
                        {
                            xtype: 'label',
                            x: 170,
                            y: 255,
                            height: 10,
                            text: '万元'
                        },
                        {
                            xtype: 'label',
                            x: 440,
                            y: 260,
                            height: 15,
                            width: 15,
                            text: '元'
                        },
                        {
                            xtype: 'label',
                            x: 410,
                            y: 85,
                            text: '张'
                        },
                        {
                            xtype: 'displayfield',
                            x: 15,
                            y: 305,
                            id: 'pcqtclient',
                            width: 250,
                            fieldLabel: '倒计时',
                            name: 'pcqt',
                            listeners: {
                                change: {
                                    fn: me.onPcqtChange,
                                    scope: me
                                }
                            }
                        },
                        {
                            xtype: 'textfield',
                            x: 100,
                            y: 15,
                            hidden: true,
                            id: 'sgslclient',
                            fieldLabel: 'sgsl',
                            name: 'sgsl'
                        }
                    ]
                },
                {
                    xtype: 'tabpanel',
                    x: 570,
                    y: 20,
                    width: 300,
                    activeTab: 0,
                    items: [
                        {
                            xtype: 'gridpanel',
                            height: 410,
                            id: 'cjjl',
                            width: 270,
                            overflowX: 'auto',
                            overflowY: 'auto',
                            title: '出价记录',
                            store: 'cjjl',
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    width: 108,
                                    align: 'center',
                                    dataIndex: 'userid',
                                    text: '用户'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 97,
                                    align: 'center',
                                    dataIndex: 'cjsj',
                                    text: '时间'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 84,
                                    align: 'center',
                                    dataIndex: 'cj',
                                    text: '出价'
                                }
                            ],
                            viewConfig: {
                                id: 'ccclient',
                                loadMask: false,
                                loadingHeight: 100
                            },
                            tabConfig: {
                                xtype: 'tab',
                                width: 148
                            }
                        },
                        {
                            xtype: 'gridpanel',
                            height: 410,
                            title: '实时结果',
                            store: 'ssjg',
                            columns: [
                                {
                                    xtype: 'gridcolumn',
                                    width: 98,
                                    align: 'center',
                                    dataIndex: 'userid',
                                    text: '用户名'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    width: 96,
                                    align: 'center',
                                    dataIndex: 'sgsl',
                                    text: '申购数量'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                        if(value==true)
                                        {
                                            return '是';
                                        }
                                        else
                                        {
                                            return '否';
                                        }
                                    },
                                    width: 92,
                                    align: 'center',
                                    dataIndex: 'sfdb',
                                    text: '是否得标'
                                }
                            ],
                            viewConfig: {
                                getRowClass: function(record, rowIndex, rowParams, store) {
                                    if(record.get('sfdb')===true)
                                    return 'oper';
                                }
                            },
                            tabConfig: {
                                xtype: 'tab',
                                width: 146
                            }
                        }
                    ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    x: 139,
                    y: 13,
                    dock: 'top',
                    height: 40,
                    items: [
                        {
                            xtype: 'image',
                            height: 40,
                            width: 657,
                            src: 'images/lc4.png'
                        },
                        {
                            xtype: 'label',
                            height: 12,
                            style: {
                                fontSize: '12px',
                                cursor: 'pointer',
                                color: 'blue'
                            },
                            width: 84,
                            text: '返回订单列表',
                            listeners: {
                                click: {
                                    fn: me.onLabelClick11,
                                    element: 'el',
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

    onPcqtChange: function(field, newValue, oldValue, eOpts) {
        if(newValue=='正在进行')
        {
            var cjstore=Ext.StoreMgr.get('cjjl');
            var jgstore=Ext.StoreMgr.get('ssjg');
            var n=cjstore.count();

            runner.stopAll();
            var pcqt=Ext.getCmp('pcqtclient');
            var num=10;
            var jxdjs =runner.newTask({
                run: function()
                {  //alert();
                    pcqt.setValue(num);
                    num=num-1;
                },
                interval:1000
            }
            );
            jxdjs.start();

            var cjjl =runner.newTask({
                run: function()
                {
                    cjstore.load({
                        params:{zbpcbh:Ext.getCmp('jpzbpcbhclient').getValue()},
                        callback:function(){
                            if(n!=cjstore.count())
                            {
                                n=cjstore.count();
                                num=10;
                            }
                            var d = Ext.getDom('ccclient');

                            d.scrollTop = d.scrollHeight - d.offsetHeight;
                        }
                    }
                    );



                },
                interval:500
            }
            );
            cjjl.start();

            var ssjg =runner.newTask({
                run: function()
                {
                    jgstore.load({
                        params:{zbpcbh:Ext.getCmp('jpzbpcbhclient').getValue()}
                    }
                    );

                },
                interval:500
            }
            );
            ssjg.start();
        }
        if(newValue==0)
        {
            runner.stopAll();
            var btn=Ext.getCmp('cjbtnclient');
            btn.disable();
        }

    },

    onLabelClick11: function(label) {

        var panel=Ext.getCmp('contentPanel');

        var zbsg=Ext.widget('wdsglb');
        panel.removeAll();
        panel.add(zbsg);

    }

});