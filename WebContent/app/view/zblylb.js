/*
 * File: app/view/zblylb.js
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

Ext.define('MyApp.view.zblylb', {
    extend: 'Ext.window.Window',
    alias: 'widget.zblylb',

    requires: [
        'Ext.grid.Panel',
        'Ext.grid.column.Number',
        'Ext.toolbar.Toolbar',
        'Ext.form.field.Display',
        'Ext.toolbar.Spacer',
        'Ext.form.Label',
        'Ext.grid.View',
        'Ext.button.Button'
    ],

    height: 282,
    id: 'zblylb',
    width: 732,
    layout: 'absolute',
    closable: false,
    title: '指标来源列表',
    modal: true,

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            dockedItems: [
                {
                    xtype: 'gridpanel',
                    x: 166,
                    y: 42,
                    dock: 'top',
                    height: 180,
                    title: '',
                    store: 'zbcrdisplay',
                    columns: [
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'fkxmbh',
                            groupable: true,
                            text: '项目编号'
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'fkxmmc',
                            text: '项目名称'
                        },
                        {
                            xtype: 'numbercolumn',
                            width: 114,
                            dataIndex: 'fkxmgm',
                            text: '复垦总规模（公顷）'
                        },
                        {
                            xtype: 'numbercolumn',
                            width: 115,
                            dataIndex: 'kcrgm',
                            text: '可出让规模（公顷）'
                        },
                        {
                            xtype: 'numbercolumn',
                            width: 126,
                            dataIndex: 'bccrgm',
                            text: '本次出让规模（公顷）'
                        },
                        {
                            xtype: 'numbercolumn',
                            width: 127,
                            dataIndex: 'sykcrgm',
                            text: '剩余可出让规模（公顷）'
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'displayfield',
                                    id: 'fkxmzs',
                                    fieldLabel: '复垦项目总数',
                                    value: 'Display Field'
                                },
                                {
                                    xtype: 'tbspacer',
                                    height: 10,
                                    width: 49
                                },
                                {
                                    xtype: 'label',
                                    width: 132,
                                    text: '请核实指标信息'
                                }
                            ]
                        }
                    ],
                    viewConfig: {
                        cls: 'x-grid3-row'
                    }
                }
            ],
            items: [
                {
                    xtype: 'button',
                    handler: function(button, event) {
                        var store=Ext.StoreMgr.get('zbcrdisplay');
                        var records = [];
                        store.each(function(rec){
                            records.push(Ext.encode(rec.data));
                        });


                        //alert(records);

                        Ext.Ajax.request({
                            url:'addzbcrxx',
                            method:'post',

                            headers:{
                                'Content-Type':'application/json'
                            },


                            success:function(response){
                                var result=Ext.decode(response.responseText);
                                Ext.MessageBox.alert('信息',result.msg);
                                Ext.getCmp('zblylb').close();

                            },
                            fail:function()
                            {

                            },
                            params:'['+records+']'
                        }
                        );
                    },
                    x: 230,
                    y: 20,
                    width: 80,
                    text: '确认提交'
                },
                {
                    xtype: 'button',
                    x: 380,
                    y: 20,
                    width: 70,
                    text: '返回编辑',
                    listeners: {
                        click: {
                            fn: me.onButtonClick,
                            scope: me
                        }
                    }
                }
            ]
        });

        me.callParent(arguments);
    },

    onButtonClick: function(button, e, eOpts) {
        var store=Ext.StoreMgr.get('zbcrdisplay');
        store.removeAll();
        this.close();
    }

});