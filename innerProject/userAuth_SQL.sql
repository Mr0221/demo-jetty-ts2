
create table TModule  (
   mid                varchar2(15)                    not null,
   mname              varchar2(30),
   parentModuleno     varchar2(15),
   constraint PK_TMODULE primary key (mid)
);

alter table TModule
   add constraint FK_TMODULE_REFERENCE_TMODULE foreign key (parentModuleno)
      references TModule (mid);


create table TAuth  (
   aid                varchar2(30)                    not null,
   authName           varchar2(30),
   moduleid           varchar2(15),
   constraint PK_TAUTH primary key (aid)
);


create table TRole  (
   id                 number(9)                       not null,
   name               varchar2(30),
   constraint PK_TROLE primary key (id)
);



create table TRoleAuth  (
   id                 number(9)                       not null,
   rno                number(9)                       not null,
   ano                varchar2(30),
   constraint PK_TROLEAUTH primary key (id)
);

alter table TRoleAuth
   add constraint FK_TROLEAUT_REFERENCE_TAUTH foreign key (ano)
      references TAuth (aid);

alter table TRoleAuth
   add constraint FK_TROLEAUT_REFERENCE_TROLE foreign key (rno)
      references TRole (id);



create table TDepartment  (
   deptno             varchar2(60)                    not null,
   deptname           varchar2(30),
   parentDept         varchar2(60),
   deptdesc           varchar2(1000),
   constraint PK_TDEPARTMENT primary key (deptno)
);

alter table TDepartment
   add constraint FK_TDEPARTM_REFERENCE_TDEPARTM foreign key (parentDept)
      references TDepartment (deptno);


create table TEmploy  (
   eno                varchar2(30)                    not null,
   deptno             varchar2(20),
   ename              varchar2(30),
   sex                varchar2(2),
   constraint PK_TEMPLOY primary key (eno)
);

alter table TEmploy
   add constraint FK_TEMPLOY_REFERENCE_TDEPARTM foreign key (deptno)
      references TDepartment (deptno);


create table TUser  (
   eno                varchar2(15)                    not null,
   uname              varchar2(30),
   pwd                varchar2(30),
   constraint PK_TUSER primary key (eno),
   constraint AK_KEY_2_TUSER unique (uname)
);


alter table TUser
   add constraint FK_TUSER_REFERENCE_TEMPLOY foreign key (eno)
      references TEmploy (eno);



create table TEmployAuth  (
   id                 number(9)                       not null,
   eno                varchar2(15),
   authno             varchar2(30),
   constraint PK_TEMPLOYAUTH primary key (id)
);


alter table TEmployAuth
   add constraint FK_TEMPLOYA_REFERENCE_TAUTH foreign key (authno)
      references TAuth (aid);

alter table TEmployAuth
   add constraint FK_TEMPLOYA_REFERENCE_TUSER foreign key (eno)
      references TUser (eno);



create table TUserRole  (
   id                 number(9)                       not null,
   eno                varchar2(15)                    not null,
   rid                number(9),
   constraint PK_TUSERROLE primary key (id)
);

alter table TUserRole
   add constraint FK_TUSERROL_REFERENCE_TUSER foreign key (eno)
      references TUser (eno);

alter table TUserRole
   add constraint FK_TUSERROL_REFERENCE_TROLE foreign key (rid)
      references TRole (id);






