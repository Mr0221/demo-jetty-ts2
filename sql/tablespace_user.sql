
create tablespace LRN
logging
datafile 'C:\oraclexe\app\oracle\oradata\XE\Lrn.dbf'
size 32m
autoextend on
next 32m maxsize 2048m
extent management local;

CREATE USER "LRN_MR" PROFILE "DEFAULT"
IDENTIFIED BY "Lrn_ding" DEFAULT TABLESPACE "LRN"
TEMPORARY TABLESPACE "TEMP"
ACCOUNT UNLOCK;
GRANT UNLIMITED TABLESPACE TO "LRN_MR";
GRANT "RESOURCE" to "LRN_MR";
GRANT "CONNECT" TO "LRN_MR";
GRANT "DBA" TO "LRN_MR";