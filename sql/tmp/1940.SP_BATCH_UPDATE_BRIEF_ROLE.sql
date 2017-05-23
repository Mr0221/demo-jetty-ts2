create or replace
PROCEDURE SP_BATCH_UPDATE_BRIEF_ROLE
(
    I_YEAR_ID               IN VARCHAR2,
    I_DEPT_ID               IN VARCHAR2,
    I_ROLE_CODE             IN VARCHAR2,
    I_CURRENT_USER          IN VARCHAR2,
    I_NEW_USER              IN VARCHAR2,
    I_UPDATE_USER           IN VARCHAR2,
    I_CURRENT_USER_NAME     IN VARCHAR2,
    I_NEW_USER_NAME         IN VARCHAR2,
    O_UPATE_COUNT           OUT NUMBER
)
AS
  v_ref2      varchar2(200);
  v_exec_sql  VARCHAR2(1000);
  v_exec_insert_dochtr_sql VARCHAR2(1000);
  v_user_full_name varchar2(50);
  TYPE arry_var IS VARRAY(10) OF VARCHAR2(50);
  arry_ref2 arry_var := arry_var('BUYER_USER_CODE', 'STYLIST_USER_CODE','ASSISTANT_USER_CODE', 'DESIGNER_USER_CODE',
                        'PLANNER_USER_CODE', 'MERCHANT1_USER_CODE', 'PROD_MERCHANT_USER_CODE', 'QA_USER_CODE',
                        'QA_USER2_CODE');
  arry_field_name arry_var := arry_var('buyerUserCode', 'stylistUserCode','assistantUserCode', 'designerUserCode',
                        'plannerUserCode', 'merchant1UserCode', 'prodMerchantUserCode', 'qaUserCode',
                        'qaUser2Code');
BEGIN

select max(full_name) into v_user_full_name from mer_user where upper(full_name) = upper(replace( ''|| I_CURRENT_USER_NAME ||'', '''', ''''''));

select ref_2 into v_ref2 from mer_codelist where parent_id = 'ADMIN_ROLE_000000000000000000000' and code = I_ROLE_CODE;

FOR i IN 1 .. arry_ref2.COUNT LOOP
      if v_ref2 = arry_ref2(i) then
        if nvl(I_YEAR_ID,' ')=' ' then
          v_exec_sql := 'update mer_style set '|| v_ref2 || ' = ''' || I_NEW_USER || ''' where dept_id = ''' || I_DEPT_ID || ''' and ' || v_ref2 || '
          in ( select  mu.user_code from mer_user mu where  mu.org_code = ''target'' and  upper(mu.full_name) =  upper(''' || v_user_full_name || '''))';

          v_exec_insert_dochtr_sql := 'Insert into mer_style_dochtr(id, version, parent_id, section, field_name, value_before, value_after, update_date, update_user)
          select sys_guid(), 1, ms.id, ''main'', '''|| arry_field_name(i) ||''','''|| v_user_full_name ||''', '''|| I_NEW_USER_NAME ||''', sysdate, '''|| I_UPDATE_USER ||'''
          from mer_style ms
          where dept_id='''|| I_DEPT_ID ||'''
          and '|| v_ref2 || ' in ( select  mu.user_code from mer_user mu where  mu.org_code = ''target'' and  upper(mu.full_name) =  upper(''' || v_user_full_name || '''))';

          EXECUTE IMMEDIATE 'SELECT count(id) FROM mer_style  where dept_id='''|| I_DEPT_ID ||''' and '|| v_ref2 || '
          in ( select  mu.user_code from mer_user mu where  mu.org_code = ''target'' and  upper(mu.full_name) =  upper(''' || v_user_full_name || '''))' INTO O_UPATE_COUNT;

        else
          v_exec_sql := 'update mer_style set '|| v_ref2 || ' = ''' || I_NEW_USER || ''' where year_id= ''' || I_YEAR_ID || ''' and dept_id = ''' || I_DEPT_ID || ''' and
          ' || v_ref2 ||' in ( select  mu.user_code from mer_user mu where  mu.org_code = ''target'' and  upper(mu.full_name) =  upper(''' || v_user_full_name || '''))';

          v_exec_insert_dochtr_sql := 'Insert into mer_style_dochtr(id, version, parent_id, section, field_name, value_before, value_after, update_date, update_user)
          select sys_guid(), 1, ms.id, ''main'', '''|| arry_field_name(i) ||''','''|| v_user_full_name ||''', '''|| I_NEW_USER_NAME ||''', sysdate, '''|| I_UPDATE_USER ||'''
          from mer_style ms
          where year_id='''|| I_YEAR_ID ||'''
          and dept_id='''|| I_DEPT_ID ||'''
          and '|| v_ref2 || '
          in ( select  mu.user_code from mer_user mu where  mu.org_code = ''target'' and  upper(mu.full_name) =  upper(''' || v_user_full_name || '''))';

        EXECUTE IMMEDIATE 'SELECT count(id) FROM mer_style  where year_id='''|| I_YEAR_ID ||''' and dept_id='''|| I_DEPT_ID ||''' and
        '|| v_ref2 || ' in ( select  mu.user_code from mer_user mu where  mu.org_code = ''target'' and  upper(mu.full_name) =  upper(''' || v_user_full_name || '''))' INTO O_UPATE_COUNT;

        end if;

      EXECUTE IMMEDIATE v_exec_insert_dochtr_sql;
      EXECUTE IMMEDIATE v_exec_sql;
      end if;

END LOOP;

END SP_BATCH_UPDATE_BRIEF_ROLE;

/
