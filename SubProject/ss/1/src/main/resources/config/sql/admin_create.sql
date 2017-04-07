select * from admin_info_lai;
create table admin_info_lai(
adminID varchar2(32),
adminCode varchar2(32),
password varchar2(32),
name varchar2(20),
telephone varchar2(15),
email varchar2(32),
enrolldate timestamp
);

select * from ADMIN_INFO_LAI;
select 'role1', sys_guid(), sys_guid(), 'role1', '123', '123@123.com', sysdate from dual;
insert into ADMIN_INFO_LAI (adminID, adminCode,password, name, telephone, email, enrolldate )
(select sys_guid(), sys_guid(), sys_guid(), 'role1', '123', '123@123.com', sysdate from dual );

drop table admin_info_lai;

 select * from admin_info_lai where adminCode='1324A11D950E4BE7B11B67F976FA5872';
 /
