/*
 * File: app/view/zbsgsh.js
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

Ext.define('MyApp.view.zbsgsh', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.zbsgsh',

    requires: [
        'Ext.grid.RowNumberer',
        'Ext.grid.column.Date',
        'Ext.grid.View',
        'Ext.toolbar.Toolbar',
        'Ext.Img',
        'Ext.form.Label',
        'Ext.form.field.Text',
        'Ext.grid.column.Action',
        'Ext.grid.plugin.CellEditing'
    ],

    height: 676,
    width: 965,
    title: '<font size=\'2.5px\'>指标申购审核</font>',
    store: 'zbgmstore',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            columns: [
                {
                    xtype: 'rownumberer',
                    width: 35,
                    align: 'center',
                    text: '序号'
                },
                {
                    xtype: 'gridcolumn',
                    align: 'center',
                    dataIndex: 'zbpcbh',
                    text: '指标批次编号'
                },
                {
                    xtype: 'gridcolumn',
                    width: 77,
                    align: 'center',
                    dataIndex: 'userid',
                    text: '用户名'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return value+'公顷/张';
                    },
                    align: 'center',
                    dataIndex: 'dwed',
                    text: '单位额度'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return value+'张';
                    },
                    width: 82,
                    align: 'center',
                    dataIndex: 'sgsl',
                    text: '申购数量'
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                        return record.get('dwed')*record.get('sgsl')+'公顷';
                    },
                    width: 82,
                    align: 'center',
                    dataIndex: 'sgsl',
                    text: '申购总额度'
                },
                {
                    xtype: 'datecolumn',
                    width: 87,
                    align: 'center',
                    dataIndex: 'sqjzrq',
                    text: '申请截止日期',
                    format: 'Y-m-d'
                },
                {
                    xtype: 'actioncolumn',
                    width: 112,
                    align: 'center',
                    tooltip: '查看审核凭证',
                    items: [
                        {
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                var qszm=Ext.widget('qszm');
                                qszm.items.get(0).setSrc(record.get('yyzzwz')+'/'+record.get('zbpcbh')+'.jpg');
                                //alert(record.get('yyzzwz')+'/'+record.get('zbpcbh')+'.jpg');
                                qszm.show();
                            },
                            icon: 'images/cktp.png',
                            tooltip: '查看营业执照'
                        },
                        {
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                var qszm=Ext.widget('qszm');
                                qszm.items.get(0).setSrc(record.get('nspzwz')+'/'+record.get('zbpcbh')+'.jpg');
                                //alert('http://localhost:8080/sell/'+record.get('qspzwz')+'.jpg');
                                qszm.show();
                            },
                            icon: 'images/cktp.png',
                            tooltip: '查看组织代码'
                        },
                        {
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                var qszm=Ext.widget('qszm');
                                qszm.items.get(0).setSrc(record.get('zzdmwz')+'/'+record.get('zbpcbh')+'.jpg');
                                //alert('http://localhost:8080/sell/'+record.get('qspzwz')+'.jpg');
                                qszm.show();
                            },
                            icon: 'images/cktp.png',
                            tooltip: '查看纳税凭证'
                        },
                        {
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                var qszm=Ext.widget('qszm');
                                qszm.items.get(0).setSrc(record.get('sfzmwz')+'/'+record.get('zbpcbh')+'.jpg');
                                //alert('http://localhost:8080/sell/'+record.get('qspzwz')+'.jpg');
                                qszm.show();
                            },
                            icon: 'images/cktp.png',
                            tooltip: '查看身份证件'
                        }
                    ],
                    listeners: {
                        afterrender: {
                            fn: me.onActioncolumnAfterRender,
                            scope: me
                        }
                    }
                },
                {
                    xtype: 'actioncolumn',
                    width: 52,
                    align: 'center',
                    items: [
                        {
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                Ext.Ajax.request(
                                {
                                    url:'sgshtg',
                                    method:'post',
                                    headers:{

                                        'Content-Type':'application/json'
                                    },
                                    params:Ext.encode(record.getData()),
                                    success:function(response){
                                        Ext.MessageBox.alert('信息','审核通过');
                                        //var result=Ext.decode(response.responseText);
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
                            icon: 'images/true.png'
                        }
                    ],
                    listeners: {
                        afterrender: {
                            fn: me.onActioncolumnAfterRender1,
                            scope: me
                        }
                    }
                },
                {
                    xtype: 'actioncolumn',
                    width: 48,
                    align: 'center',
                    items: [
                        {
                            handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                Ext.Ajax.request(
                                {
                                    url:'sgshbtg',
                                    method:'post',
                                    headers:{

                                        'Content-Type':'application/json'
                                    },
                                    params:Ext.encode(record.getData()),
                                    success:function(response){
                                        Ext.MessageBox.alert('信息','审核未通过');
                                        //var result=Ext.decode(response.responseText);
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
                            icon: 'images/false.png'
                        }
                    ],
                    listeners: {
                        afterrender: {
                            fn: me.onActioncolumnAfterRender2,
                            scope: me
                        }
                    }
                },
                {
                    xtype: 'gridcolumn',
                    renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {

                        if(value==null||value=='')

                        return "双击进行编辑";
                        else
                        return value;

                    },
                    align: 'center',
                    dataIndex: 'pcbz',
                    text: '备注',
                    editor: {
                        xtype: 'textfield'
                    }
                }
            ],
            viewConfig: {
                cls: 'x-grid3-row'
            },
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    height: 39,
                    items: [
                        {
                            xtype: 'image',
                            height: 30,
                            width: 499,
                            src: 'images/step2.png'
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
                                    fn: me.onLabelClick11,
                                    element: 'el',
                                    scope: me
                                }
                            }
                        },
                        {
                            xtype: 'textfield',
                            hidden: true,
                            id: 'sgshpc',
                            fieldLabel: 'Label'
                        }
                    ]
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

    onLabelClick11: function(label) {
        var myTabPanel = Ext.getCmp('htglTabPanelMain');
        myTabPanel.removeAll(true);
        myTabPanel.add(Ext.widget('jylcgl'));
    },

    onActioncolumnAfterRender: function(component, eOpts) {
        component.setText('资质审核');
    },

    onActioncolumnAfterRender1: function(component, eOpts) {
        component.setText('通过');
    },

    onActioncolumnAfterRender2: function(component, eOpts) {
        component.setText('不通过');
    },

    onGridpanelActivate: function(component, eOpts) {
        var store=Ext.StoreMgr.get('zbgmstore');
        store.clearFilter();
        store.filter('zbpcbh',Ext.getCmp('sgshpc').getValue());
        store.filter('dqhj',1);
    }

});