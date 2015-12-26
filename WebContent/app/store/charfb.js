/*
 * File: app/store/charfb.js
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

Ext.define('MyApp.store.charfb', {
    extend: 'Ext.data.Store',
    alias: 'store.charfb',

    requires: [
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json',
        'Ext.data.Field'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            storeId: 'charfb',
            data: [
                {
                    y0: 1000,
                    y1: 100,
                    y2: 100,
                    y3: 100,
                    y4: 100,
                    y5: 100,
                    y6: 100,
                    y7: 100,
                    y8: 100,
                    y9: 100,
                    y10: 100
                }
            ],
            proxy: {
                type: 'ajax',
                reader: {
                    type: 'json'
                }
            },
            fields: [
                {
                    name: 'y0'
                },
                {
                    name: 'y1'
                },
                {
                    name: 'y2'
                },
                {
                    name: 'y3'
                },
                {
                    name: 'y4'
                },
                {
                    name: 'y5'
                },
                {
                    name: 'y6'
                },
                {
                    name: 'y7'
                },
                {
                    name: 'y8'
                },
                {
                    name: 'y9'
                },
                {
                    name: 'y10'
                }
            ]
        }, cfg)]);
    }
});