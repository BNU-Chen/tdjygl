/*
 * File: app/view/wbgl.js
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

Ext.define('MyApp.view.wbgl', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.wbgl',

    requires: [
        'Ext.grid.column.Action',
        'Ext.grid.View',
        'Ext.Img',
        'Ext.form.Label',
        'Ext.form.field.Text',
        'Ext.grid.plugin.CellEditing',
        'Ext.toolbar.Paging'
    ],

    height: 572,
    width: 995,
    title: '<font size=\'2.5px\'>网备</font>',
    store: 'zbgmstore',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            columns: [
                {
                    xtype: 'gridcolumn',
                    align: 'center',
                    dataIndex: 'zbpcbh',
                    text: '批次编号'
                },
                {
                    xtype: 'gridcolumn',
                    align: 'center',
                    dataIndex: 'userid',
                    text: '用户名'
                },
                {
                    xtype: 'gridcolumn',
                    align: 'center',
                    dataIndex: 'dwed',
                    text: '单位额度'
                },
                {
                    xtype: 'gridcolumn',
                    align: 'center',
                    dataIndex: 'sgsl',
                    text: '购买数量'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return record.get('sgsl')*record.get('dwed');
                    },
                    align: 'center',
                    dataIndex: 'string',
                    text: '购买总额'
                },
                {
                    xtype: 'gridcolumn',
                    align: 'center',
                    dataIndex: 'zbdj',
                    text: '成交单价'
                },
                {
                    xtype: 'gridcolumn',
                    align: 'center',
                    dataIndex: 'yfkze',
                    text: '成交总价'
                },
                {
                    xtype: 'actioncolumn',
                    handler: function(view, rowIndex, colIndex, item, e, record, row) {
                        var qszm=Ext.widget('qszm');
                        qszm.items.get(0).setSrc(record.get('pcqt')+'/'+record.get('zbpcbh')+'.jpg');
                        //alert('http://localhost:8080/sell/'+record.get('qspzwz')+'.jpg');
                        qszm.show();
                    },
                    id: 'ckfkpz',
                    width: 85,
                    align: 'center',
                    dataIndex: 'pcqt',
                    icon: 'images/cktp.png',
                    listeners: {
                        afterrender: {
                            fn: me.onActioncolumnAfterRender,
                            scope: me
                        }
                    }
                },
                {
                    xtype: 'actioncolumn',
                    handler: function(view, rowIndex, colIndex, item, e, record, row) {
                        Ext.Ajax.request(
                        {
                            url:'scdp',
                            method:'post',
                            headers:{

                                'Content-Type':'application/json'
                            },
                            params:Ext.encode(record.getData()),
                            success:function(response){
                                Ext.MessageBox.alert('信息','指标凭证生成成功');

                                var result=Ext.decode(response.responseText);
                                //alert(result.htwz);
                                record.set('pzwz',result.dpwz);
                                //Ext.MessageBox.alert('信息','发布成功',function(btn){
                                //alert(result.zbpcbh);
                                //record.set('zbpcbh',result.zbpcbh);
                                //record.commit();
                                //});
                            },
                            failure:function(){
                                //Ext.MessageBox.alert('错误','操作失败！');
                            }

                        }
                        );
                    },
                    id: 'bzbtn',
                    width: 85,
                    align: 'center',
                    dataIndex: 'string',
                    icon: 'images/ht.png',
                    listeners: {
                        afterrender: {
                            fn: me.onActioncolumnAfterRender1,
                            scope: me
                        }
                    }
                },
                {
                    xtype: 'actioncolumn',
                    handler: function(view, rowIndex, colIndex, item, e, record, row) {
                        //alert(record.get('pzwz'));
                        window.open (record.get('pzwz'));
                    },
                    id: 'ckdp',
                    width: 85,
                    align: 'center',
                    dataIndex: 'string',
                    icon: 'images/ht.png',
                    listeners: {
                        afterrender: {
                            fn: me.onActioncolumnAfterRender111,
                            scope: me
                        }
                    }
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    height: 38,
                    items: [
                        {
                            xtype: 'image',
                            height: 35,
                            width: 540,
                            src: 'images/step6.png'
                        },
                        {
                            xtype: 'label',
                            style: {
                                fontSize: '12px',
                                cursor: 'pointer',
                                color: 'blue'
                            },
                            width: 100,
                            text: '返回管理列表',
                            listeners: {
                                click: {
                                    fn: me.onLabelClick1111,
                                    element: 'el',
                                    scope: me
                                }
                            }
                        },
                        {
                            xtype: 'textfield',
                            hidden: true,
                            id: 'wbglzbpcbh',
                            fieldLabel: 'Label'
                        }
                    ]
                },
                {
                    xtype: 'pagingtoolbar',
                    dock: 'bottom',
                    width: 360,
                    displayInfo: true,
                    store: 'zbgmstore'
                }
            ],
            plugins: [
                Ext.create('Ext.grid.plugin.CellEditing', {

                })
            ],
            listeners: {
                activate: {
                    fn: me.onGridpanelActivate,
                    scope: me
                }
            }
        });

        me.callParent(arguments);
    },

    onActioncolumnAfterRender: function(component, eOpts) {
        Ext.getCmp('ckfkpz').setText('付款凭证');
    },

    onActioncolumnAfterRender1: function(component, eOpts) {
        Ext.getCmp('bzbtn').setText('备案');
    },

    onActioncolumnAfterRender111: function(component, eOpts) {
        Ext.getCmp('ckdp').setText('查看凭证');
    },

    onLabelClick1111: function(label) {
        var myTabPanel = Ext.getCmp('htglTabPanelMain');
        myTabPanel.removeAll(true);
        myTabPanel.add(Ext.widget('jylcgl'));
    },

    onGridpanelActivate: function(component, eOpts) {
        var store=Ext.StoreMgr.get('zbgmstore');
        store.clearFilter();
        store.filter('zbpcbh',Ext.getCmp('wbglzbpcbh').getValue());
        store.filter('dqhj',5);
    }

});