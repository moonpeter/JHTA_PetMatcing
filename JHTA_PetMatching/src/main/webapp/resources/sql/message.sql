drop table message cascade constraints;

select*from message;

create table message(
message_num        number primary key,
sender_id          varchar2(20),
receiver_id        varchar2(20),
message_title      varchar2(100),
message_content    varchar2(3000),
deleteby_sender    varchar2(1) default 'n' check(deleteby_sender in ('n', 'y')),                   
deleteby_receiver  varchar2(1) default 'n' check(deleteby_receiver in ('n', 'y')),
cancelby_sender    varchar2(1) default 'n' check(cancelby_sender in ('n', 'y')),
read_count         number default 0, 
register_date      date default sysdate  
);
--read_count 설명
--받은 메시지함에서 메시지 상세보기시, message_num로 select하여 해당 메시지의 read_count를 +1 상승시킨다. 
--이것을 이용하여 받은 메시지함의 new message수를 구할 수 있고
--상대방이 보낸 메시지함에서 보낸 메시지가 읽혔는지 안읽혔는지 확인할 수 있다. 
--보낸 메시지함에서 메시지 조회할 때에는 조회수가 상승하지 않는다.



--시퀀스 삭제
drop sequence message_seq;

--시퀀스 작성
create sequence message_seq;
