DECLARE 
LISTAGG_SQL VARCHAR(700); -- THE SPLICING SQL STATEMENT FOR SPLICING THE USERNAME 
UPDATE_SQL VARCHAR(1000); -- THE UPDATING SQL STATEMNT BASED ON LISTAGG_SQL 
MAIN_VERSION VARCHAR(20); --THE VERSION OF MIAN MODULE 
MAIN_REF_NO VARCHAR(1000); --THE REF_NO OF MIAN MODULE 
MAIN_ID VARCHAR(32); --THE ID OF MIAN MODULE, LIKE AS ITEM ID 
ITEM_NUM INT; -- USING FOR CALCULATING THE RECORD OF MAIN TABLE 
TYPE arry_var IS VARRAY(5) OF VARCHAR2(30); 
LISTAGG_SQL_party_name_name arry_var; 
UPDATE_SQL_PARTY_NAME arry_var; 
BEGIN 
LISTAGG_SQL_party_name_name := arry_var('Designer', 'Design Approver', 'Buyer', 'Buyer Assitant', 'Import Coordinator'); 
UPDATE_SQL_PARTY_NAME := arry_var('PARTY_NAME1', 'PARTY_NAME2', 'PARTY_NAME3', 'PARTY_NAME4', 'PARTY_NAME5'); 
select count(*) into ITEM_NUM from cnt_item; 
FOR I IN 1..ITEM_NUM LOOP 
EXECUTE IMMEDIATE 'select id , version, ref_no from (select rownum rn, id, version, ref_no from CNT_ITEM )where rn = ' || I INTO MAIN_ID, MAIN_VERSION, MAIN_REF_NO; 
for subIndex IN 1..5 LOOP 
LISTAGG_SQL := '(select listagg((select user_name from cnt_user where id = pn.contact_user_id), '','') 
within group (order by null) from cnt_item_party pn 
inner join cnt_item ptn on pn.doc_id = ''' || MAIN_ID || ''' and ptn.ref_no ='''||MAIN_REF_NO ||'''and ptn.version = ' || MAIN_VERSION ||' and ptn.domain_id = ''LP'' 
where party_name_name ='''|| LISTAGG_SQL_party_name_name(subIndex) ||''')'; 
UPDATE_SQL := 'UPDATE CNT_ITEM_M SET '|| UPDATE_SQL_PARTY_NAME(subIndex) ||' =(' || LISTAGG_SQL || ') WHERE REF_NO = '''||MAIN_REF_NO ||''' AND VERSION = ' || MAIN_VERSION ||' AND DOMAIN_ID = ''LP'''; 
EXECUTE IMMEDIATE UPDATE_SQL; 
end loop; 
END LOOP; 
END; 
/ 