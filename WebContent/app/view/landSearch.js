/*
 * File: app/view/landSearch.js
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

Ext.define('MyApp.view.landSearch', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.landSearch',

    requires: [
        'Ext.grid.Panel',
        'Ext.grid.column.Number',
        'Ext.grid.column.Date',
        'Ext.grid.View',
        'Ext.toolbar.Paging',
        'Ext.form.Panel',
        'Ext.form.field.ComboBox',
        'Ext.button.Button'
    ],

    layout: 'border',
    title: '农业用地',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    region: 'center',
                    layout: 'border',
                    title: '高级检索>>农业用地检索',
                    items: [
                        {
                            xtype: 'gridpanel',
                            region: 'center',
                            title: '检索结果',
                            forceFit: true,
                            store: 'landinfo1',
                            columns: [
                                {
                                    xtype: 'numbercolumn',
                                    width: 52,
                                    dataIndex: 'landid',
                                    text: '编号',
                                    format: '0,000.'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'area',
                                    text: '区域'
                                },
                                {
                                    xtype: 'numbercolumn',
                                    dataIndex: 'acre',
                                    text: '面积'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'transtyle',
                                    text: '流转方式'
                                },
                                {
                                    xtype: 'numbercolumn',
                                    width: 68,
                                    dataIndex: 'price',
                                    text: '价格'
                                },
                                {
                                    xtype: 'numbercolumn',
                                    width: 86,
                                    dataIndex: 'year',
                                    text: '年限'
                                },
                                {
                                    xtype: 'datecolumn',
                                    width: 119,
                                    dataIndex: 'releasedate',
                                    text: '发布日期'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'trading',
                                    text: '是否可交易'
                                }
                            ],
                            dockedItems: [
                                {
                                    xtype: 'pagingtoolbar',
                                    dock: 'bottom',
                                    width: 360,
                                    displayInfo: true,
                                    store: 'landinfo1'
                                }
                            ]
                        },
                        {
                            xtype: 'form',
                            region: 'north',
                            height: 116,
                            layout: 'absolute',
                            bodyPadding: 10,
                            title: '输入检索条件',
                            jsonSubmit: true,
                            url: 'find_transinfo',
                            items: [
                                {
                                    xtype: 'combobox',
                                    x: 10,
                                    y: 0,
                                    id: 'areaID',
                                    margin: 15,
                                    width: 200,
                                    fieldLabel: '区域',
                                    labelAlign: 'right',
                                    labelWidth: 60,
                                    name: 'area',
                                    selectOnFocus: true,
                                    displayField: 'areaText',
                                    queryMode: 'local',
                                    store: 'areaStore',
                                    valueField: 'area'
                                },
                                {
                                    xtype: 'combobox',
                                    x: 250,
                                    y: 0,
                                    id: 'acreID',
                                    margin: 15,
                                    width: 200,
                                    fieldLabel: '面积',
                                    labelAlign: 'right',
                                    labelWidth: 60,
                                    name: 'acre',
                                    selectOnFocus: true,
                                    queryMode: 'local'
                                },
                                {
                                    xtype: 'combobox',
                                    x: 10,
                                    y: 40,
                                    id: 'transtyleID',
                                    margin: 15,
                                    width: 200,
                                    fieldLabel: '流转方式',
                                    labelAlign: 'right',
                                    labelWidth: 60,
                                    name: 'transtyle',
                                    selectOnFocus: true,
                                    displayField: 'styleText',
                                    queryMode: 'local',
                                    store: 'transtyleStore',
                                    valueField: 'style'
                                },
                                {
                                    xtype: 'combobox',
                                    x: 250,
                                    y: 40,
                                    id: 'yearID',
                                    margin: 15,
                                    width: 200,
                                    fieldLabel: '年限',
                                    labelAlign: 'right',
                                    labelWidth: 60,
                                    name: 'year',
                                    selectOnFocus: true,
                                    displayField: 'yearText',
                                    queryMode: 'local',
                                    store: 'yearStore',
                                    valueField: 'year'
                                },
                                {
                                    xtype: 'button',
                                    handler: function(button, event) {

                                        var mystore = Ext.StoreMgr.get('landinfo1');

                                        mystore.load(
                                        {params:{
                                            start:"0",
                                            limit:"10",
                                            outArea:Ext.getCmp('areaID').getValue(),
                                            outAcre:Ext.getCmp('acreID').getValue(),
                                            outTranstyle:Ext.getCmp('transtyleID').getValue(),
                                        outYear:Ext.getCmp('yearID').getValue()}
                                    }
                                    );
                                    },
                                    x: 470,
                                    y: 0,
                                    height: 30,
                                    margin: 40,
                                    width: 72,
                                    text: '检索'
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});