/*
 * File: app/store/pubNewDealStore.js
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

Ext.define('MyApp.store.pubNewDealStore', {
    extend: 'Ext.data.Store',

    requires: [
        'MyApp.model.InfoArticleModel',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            autoSync: true,
            model: 'MyApp.model.InfoArticleModel',
            storeId: 'pubNewDealStore',
            pageSize: 10,
            proxy: {
                type: 'ajax',
                url: 'search_PubNewDeal',
                reader: {
                    type: 'json',
                    root: 'root'
                }
            },
            listeners: {
                beforeload: {
                    fn: me.onStoreBeforeLoad,
                    scope: me
                }
            }
        }, cfg)]);
    },

    onStoreBeforeLoad: function(store, operation, eOpts) {
        var extra_params={
            searchField:Ext.getCmp('searchField_pubNewDeal').getValue(),
            searchDate:Ext.util.Format.date(Ext.getCmp('searchDate_pubNewDeal').getValue(),'Y-m-d')
        };
        Ext.apply(store.proxy.extraParams,extra_params);


    }

});