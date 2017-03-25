create or replace 
FUNCTION fn_connect_by_comma (input VARCHAR2) 
RETURN VARCHAR2 PARALLEL_ENABLE 
AGGREGATE USING string_agg_type;

select listagg( mc.name, ', ') WITHIN GROUP( ORDER BY mc.name) EscAuditResultRef3 from mer_codelist mc inner join mer_codelist_book mcb on mcb.id = mc.parent_id and mcb.name = 'ESC_AUDIT_RESULT' where mc.ref_3= 'ALLOW_TO_GEN'; 
desc mer_codelist;