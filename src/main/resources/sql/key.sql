--主外键 关联修改
--check sql 
select distinct c.TABLE_NAME , allcmn.column_name, 'update ' || c.TABLE_NAME || ' set ' || allcmn.column_name || ' = ''k_mer1'' where ' || allcmn.column_name || ' = ''k_mer'' ;' 
from all_constraints p, all_constraints c 
, all_cons_columns allcmn 
where p.table_name = 'MER_USER' 
and p.OWNER = SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA') 
and c.OWNER=SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA') 
and c.constraint_type = 'R' 
and p.constraint_name = c.R_CONSTRAINT_NAME 
and allcmn.constraint_name = c.constraint_name 
order by c.TABLE_NAME , allcmn.column_name; 

insert into mer_user (USER_CODE, VERSION, ORG_CODE, OWNER_ORG_CODE, FIRST_NAME, LAST_NAME, DESCRIPTION, PASSWORD, EMAIL, PHONE, 
DEFAULT_LANG, FAX, STATUS, LOCKED, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, FULL_NAME, 
PASSWORD_MODIFY_DATE, LOGIN_FAIL_TIMES, LOGIN_FAIL_DATE, IS_NEW_VENDOR, EXPIRED, LAST_LOGIN_DATE, 
LAST_LOGOUT_DATE, TOTAL_LOGIN_COUNT, NEW_PWD_ENCODED) 
select 'k_mer1', VERSION, ORG_CODE, OWNER_ORG_CODE, FIRST_NAME, LAST_NAME, DESCRIPTION, PASSWORD, EMAIL, PHONE, 
DEFAULT_LANG, FAX, STATUS, LOCKED, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, FULL_NAME, 
PASSWORD_MODIFY_DATE, LOGIN_FAIL_TIMES, LOGIN_FAIL_DATE, IS_NEW_VENDOR, EXPIRED, LAST_LOGIN_DATE, 
LAST_LOGOUT_DATE, TOTAL_LOGIN_COUNT, NEW_PWD_ENCODED 
from mer_user where user_code = 'k_mer'; 

update MER_PROJ set BUYER_CODE = 'k_mer1' where BUYER_CODE = 'k_mer' ; 
update MER_PROJ set MERCHANDISER2_CODE = 'k_mer1' where MERCHANDISER2_CODE = 'k_mer' ; 
update MER_PROJ set MERCHANDISER_CODE = 'k_mer1' where MERCHANDISER_CODE = 'k_mer' ; 
update MER_PROJ set PLANNER_CODE = 'k_mer1' where PLANNER_CODE = 'k_mer' ; 
update MER_PROJ set QA_FINAL_USER_CODE = 'k_mer1' where QA_FINAL_USER_CODE = 'k_mer' ; 
update MER_PROJ set QA_FIRST_USER_CODE = 'k_mer1' where QA_FIRST_USER_CODE = 'k_mer' ; 
update MER_PROJ_STYLE set BUYER_CODE = 'k_mer1' where BUYER_CODE = 'k_mer' ; 
update MER_PROJ_STYLE set MERCHANDISER_CODE = 'k_mer1' where MERCHANDISER_CODE = 'k_mer' ; 
update MER_PROJ_STYLE set PLANNER_CODE = 'k_mer1' where PLANNER_CODE = 'k_mer' ; 
update MER_PROJ_STYLE set QA_FINAL_USER_CODE = 'k_mer1' where QA_FINAL_USER_CODE = 'k_mer' ; 
update MER_PROJ_STYLE set QA_FIRST_USER_CODE = 'k_mer1' where QA_FIRST_USER_CODE = 'k_mer' ; 
update MER_STYLE set BUYER_USER_CODE = 'k_mer1' where BUYER_USER_CODE = 'k_mer' ; 
update MER_STYLE set MERCHANT1_USER_CODE = 'k_mer1' where MERCHANT1_USER_CODE = 'k_mer' ; 
update MER_STYLE set PLANNER_USER_CODE = 'k_mer1' where PLANNER_USER_CODE = 'k_mer' ; 
update MER_STYLE set QA_USER2_CODE = 'k_mer1' where QA_USER2_CODE = 'k_mer' ; 
update MER_STYLE set QA_USER_CODE = 'k_mer1' where QA_USER_CODE = 'k_mer' ; 
update MER_USER_OFFICE set USER_CODE = 'k_mer1' where USER_CODE = 'k_mer' ; 
update MER_USER_OPTION set USER_CODE = 'k_mer1' where USER_CODE = 'k_mer' ; 
update MER_USER_ROLE set USER_CODE = 'k_mer1' where USER_CODE = 'k_mer' ; 
update MER_USER_TEAM set USER_CODE = 'k_mer1' where USER_CODE = 'k_mer' ; 
update MER_VENDOR_QUOTE set BUYER = 'k_mer1' where BUYER = 'k_mer' ; 
update MER_VENDOR_QUOTE set MERCHANDISER = 'k_mer1' where MERCHANDISER = 'k_mer' ; 
update MER_VENDOR_QUOTE set QA_PERSON = 'k_mer1' where QA_PERSON = 'k_mer' ; 

delete from mer_user where user_code = 'k_mer'; 

--undo 
insert into mer_user (USER_CODE, VERSION, ORG_CODE, OWNER_ORG_CODE, FIRST_NAME, LAST_NAME, DESCRIPTION, PASSWORD, EMAIL, PHONE, 
DEFAULT_LANG, FAX, STATUS, LOCKED, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, FULL_NAME, 
PASSWORD_MODIFY_DATE, LOGIN_FAIL_TIMES, LOGIN_FAIL_DATE, IS_NEW_VENDOR, EXPIRED, LAST_LOGIN_DATE, 
LAST_LOGOUT_DATE, TOTAL_LOGIN_COUNT, NEW_PWD_ENCODED) 
select 'k_mer', VERSION, ORG_CODE, OWNER_ORG_CODE, FIRST_NAME, LAST_NAME, DESCRIPTION, PASSWORD, EMAIL, PHONE, 
DEFAULT_LANG, FAX, STATUS, LOCKED, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, FULL_NAME, 
PASSWORD_MODIFY_DATE, LOGIN_FAIL_TIMES, LOGIN_FAIL_DATE, IS_NEW_VENDOR, EXPIRED, LAST_LOGIN_DATE, 
LAST_LOGOUT_DATE, TOTAL_LOGIN_COUNT, NEW_PWD_ENCODED 
from mer_user where user_code = 'k_mer1'; 

--check sql2 
select distinct c.TABLE_NAME , allcmn.column_name, 'update ' || c.TABLE_NAME || ' set ' || allcmn.column_name || ' = ''k_mer'' where ' || allcmn.column_name || ' = ''k_mer1'' ;' 
from all_constraints p, all_constraints c 
, all_cons_columns allcmn 
where p.table_name = 'MER_USER' 
and p.OWNER = SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA') 
and c.OWNER=SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA') 
and c.constraint_type = 'R' 
and p.constraint_name = c.R_CONSTRAINT_NAME 
and allcmn.constraint_name = c.constraint_name 
order by c.TABLE_NAME , allcmn.column_name; 

update MER_PROJ set BUYER_CODE = 'k_mer' where BUYER_CODE = 'k_mer1' ; 
update MER_PROJ set MERCHANDISER2_CODE = 'k_mer' where MERCHANDISER2_CODE = 'k_mer1' ; 
update MER_PROJ set MERCHANDISER_CODE = 'k_mer' where MERCHANDISER_CODE = 'k_mer1' ; 
update MER_PROJ set PLANNER_CODE = 'k_mer' where PLANNER_CODE = 'k_mer1' ; 
update MER_PROJ set QA_FINAL_USER_CODE = 'k_mer' where QA_FINAL_USER_CODE = 'k_mer1' ; 
update MER_PROJ set QA_FIRST_USER_CODE = 'k_mer' where QA_FIRST_USER_CODE = 'k_mer1' ; 
update MER_PROJ_STYLE set BUYER_CODE = 'k_mer' where BUYER_CODE = 'k_mer1' ; 
update MER_PROJ_STYLE set MERCHANDISER_CODE = 'k_mer' where MERCHANDISER_CODE = 'k_mer1' ; 
update MER_PROJ_STYLE set PLANNER_CODE = 'k_mer' where PLANNER_CODE = 'k_mer1' ; 
update MER_PROJ_STYLE set QA_FINAL_USER_CODE = 'k_mer' where QA_FINAL_USER_CODE = 'k_mer1' ; 
update MER_PROJ_STYLE set QA_FIRST_USER_CODE = 'k_mer' where QA_FIRST_USER_CODE = 'k_mer1' ; 
update MER_STYLE set BUYER_USER_CODE = 'k_mer' where BUYER_USER_CODE = 'k_mer1' ; 
update MER_STYLE set MERCHANT1_USER_CODE = 'k_mer' where MERCHANT1_USER_CODE = 'k_mer1' ; 
update MER_STYLE set PLANNER_USER_CODE = 'k_mer' where PLANNER_USER_CODE = 'k_mer1' ; 
update MER_STYLE set QA_USER2_CODE = 'k_mer' where QA_USER2_CODE = 'k_mer1' ; 
update MER_STYLE set QA_USER_CODE = 'k_mer' where QA_USER_CODE = 'k_mer1' ; 
update MER_USER_OFFICE set USER_CODE = 'k_mer' where USER_CODE = 'k_mer1' ; 
update MER_USER_OPTION set USER_CODE = 'k_mer' where USER_CODE = 'k_mer1' ; 
update MER_USER_ROLE set USER_CODE = 'k_mer' where USER_CODE = 'k_mer1' ; 
update MER_USER_TEAM set USER_CODE = 'k_mer' where USER_CODE = 'k_mer1' ; 
update MER_VENDOR_QUOTE set BUYER = 'k_mer' where BUYER = 'k_mer1' ; 
update MER_VENDOR_QUOTE set MERCHANDISER = 'k_mer' where MERCHANDISER = 'k_mer1' ; 
update MER_VENDOR_QUOTE set QA_PERSON = 'k_mer' where QA_PERSON = 'k_mer1' ; 

delete from mer_user where user_code = 'k_mer1';
