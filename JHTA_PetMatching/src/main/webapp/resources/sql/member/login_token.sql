create table login_token(
username varchar(64) not null,
series 	 varchar(64) primary key,
token	 varchar(64) not null,
last_used timestamp not null
);

select *
from login_token;

delete login_token;x

drop table login_token;