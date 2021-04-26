drop table members;

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
set auth = 'ROLE_ADMIN'
where id = 'admin';

select *
from members

drop table destination;

create table destination(
id		varchar2(30),
name	varchar2(30),
post	number,
address	varchar2(300),
phone	varchar2(60),
request varchar2(300),
PRIMARY KEY(id)
);

select *
from destination;