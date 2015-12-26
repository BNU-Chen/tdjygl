
Ext.apply(Ext.form.field.VTypes, {
    //  vtype validation function
    max: function(val, field) {
        var value=Ext.getCmp('kcrgm').getValue();
        var cr=Ext.getCmp('bccrgm').getValue();
        if(cr>value)
        {
           
        
        return false;
        }
        else
        {
            return true;
        }
    },
    // vtype Text property: The error text to display when the validation function returns false
    maxText: '输入值不能大于可出让规模'
      
});

