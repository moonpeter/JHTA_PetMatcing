create table members(
id 		 varchar2(20),
password varchar2(60),
name 	 varchar2(20),
phone 	 varchar2(60),
gender 	 varchar2(20),
position varchar2(20),
auth	 varchar2(30) not null,
PRIMARY KEY(id)
);

update members
set auth = 'SET_ADMIN'
where id = 'admin';

select *
from members

drop table members;