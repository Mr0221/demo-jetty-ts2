insert into tuser values('admin', '123', 1);
insert into tuser values('tom', 'abc', 1);

insert into TUserRole values(1, 'admin', 'ROLE_USER,ROLE_ADMIN');
insert into TuserRole values(2, 'tom', 'ROLE_USER');

select * from tuser;
select * from tuserrole;

select uname j_username,rname role from tuserrole ;