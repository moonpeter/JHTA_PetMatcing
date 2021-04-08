create table members(
id 		 varchar2(20) primary key,
password varchar2(60),
name 	 varchar2(20),
phone 	 varchar2(60),
gender 	 varchar2(20),
position varchar2(20)
);

select *
from members

drop table members;