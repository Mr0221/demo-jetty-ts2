create table SP_User  (
   uname              varchar2(30)                    not null,
   pwd                varchar2(20),
   enabled            number(1),
   constraint PK_SP_USER primary key (uname)
);


create table SP_UserRole  (
   autoid             number(9)                       not null,
   uname              varchar2(30),
   role               varchar2(30),
   constraint PK_SP_USERROLE primary key (autoid)
);

alter table SP_UserRole
   add constraint FK_SP_USERROL_REFCE_SP_USER foreign key (uname)
      references SP_User (uname);
