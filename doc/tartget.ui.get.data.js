<script type="text/javascript">
Core.form.ComboBox.loadCodelistDropdownStore('brandLcnNameId', 'BRAND_NAME', 'id,name,ref1');
Core.form.ComboBox.loadCodelistDropdownStore('licenseNameId', 'LICENSE_NAME', 'id,name');
Core.form.ComboBox.loadCodelistDropdownStore('desc1Id', 'BRIEF_SPEC_DESC1', 'id,name,code,ref1,ref2,ref3,parentCode');
Core.form.ComboBox.loadCodelistDropdownStore('desc2Id', 'BRIEF_SPEC_DESC2', 'id,name,code,ref1,ref2,ref3,parentCode');
Core.form.ComboBox.loadEntityDropdownStore('merchantOfficeId', 'AgentOffice', 'id,name,lineNo,code,enabled,isDelete,parentCodes', " select mc.id, mc.name, mc.line_no, mc.code, mc.enabled, mc.is_delete, fn_connect_by_comma(c1) parent_codes"
        + "    from mer_lookup_book mlb"
        + "    inner join mer_lookup ml on ml.parent_id = mlb.id"
        + "    inner join mer_codelist mc on ml.c2 = mc.code and mc.parent_id = (select id from mer_codelist_book where name = 'AGENT_OFFICE')"
        + "    where mlb.name = 'Sourcing Agent and Agent Office'"
        + "    group by mc.id, mc.line_no, mc.code, mc.name,  mc.parent_id, mc.parent_code, mc.ref_1, mc.ref_2, mc.ref_3, mc.ref_4, mc.ref_5, mc.enabled, mc.is_delete");
</script>
<script type="text/javascript">
Core.customize.dropdownStores['colourKeycodeId'] = Ext.applyIf({entityFields: 'id,name'}, {"maxLineNo":0,"records":[],"totalPage":1,"actualTotalRecord":0,"currentPageIndex":1,"pageSize":1,"totalRecords":1,"recordsReturned":1,"startIndex":1});
Core.customize.dropdownStores['buyerUserCode'] = Ext.applyIf({entityFields: 'userCode,name'}, {"maxLineNo":0,"records":[],"totalPage":1,"actualTotalRecord":0,"currentPageIndex":1,"pageSize":1,"totalRecords":2,"recordsReturned":2,"startIndex":1});
Core.customize.dropdownStores['plannerUserCode'] = Ext.applyIf({entityFields: 'userCode,name'}, {"maxLineNo":0,"records":[],"totalPage":1,"actualTotalRecord":0,"currentPageIndex":1,"pageSize":1,"totalRecords":2,"recordsReturned":2,"startIndex":1});
Core.customize.dropdownStores['merchant1UserCode'] = Ext.applyIf({entityFields: 'userCode,name'}, {"maxLineNo":0,"records":[],"totalPage":1,"actualTotalRecord":0,"currentPageIndex":1,"pageSize":1,"totalRecords":2,"recordsReturned":2,"startIndex":1});
Core.customize.dropdownStores['qaUserCode'] = Ext.applyIf({entityFields: 'userCode,name'}, {"maxLineNo":0,"records":[],"totalPage":1,"actualTotalRecord":0,"currentPageIndex":1,"pageSize":1,"totalRecords":2,"recordsReturned":2,"startIndex":1});
Core.customize.dropdownStores['qaUser2Code'] = Ext.applyIf({entityFields: 'userCode,name'}, {"maxLineNo":0,"records":[],"totalPage":1,"actualTotalRecord":0,"currentPageIndex":1,"pageSize":1,"totalRecords":2,"recordsReturned":2,"startIndex":1});
Core.customize.dropdownStores['assistantUserCode'] = Ext.applyIf({entityFields: 'userCode,name'}, {"maxLineNo":0,"records":[],"totalPage":1,"actualTotalRecord":0,"currentPageIndex":1,"pageSize":1,"totalRecords":2,"recordsReturned":2,"startIndex":1});
Core.customize.dropdownStores['prodMerchantUserCode'] = Ext.applyIf({entityFields: 'userCode,name'}, {"maxLineNo":0,"records":[],"totalPage":1,"actualTotalRecord":0,"currentPageIndex":1,"pageSize":1,"totalRecords":2,"recordsReturned":2,"startIndex":1});

Core.customize.dropdownStores['currentUserId'] = Ext.applyIf({entityFields: 'userCode,name'}, {"maxLineNo":0,"records":[],"totalPage":1,"actualTotalRecord":0,"currentPageIndex":1,"pageSize":1,"totalRecords":0,"recordsReturned":0,"startIndex":1});
Core.customize.dropdownStores['newUserId'] = Ext.applyIf({entityFields: 'userCode,name'}, {"maxLineNo":0,"records":[],"totalPage":1,"actualTotalRecord":0,"currentPageIndex":1,"pageSize":1,"totalRecords":0,"recordsReturned":0,"startIndex":1});

</script>
