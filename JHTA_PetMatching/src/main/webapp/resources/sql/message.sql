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
--read_count ����
--���� �޽����Կ��� �޽��� �󼼺����, message_num�� select�Ͽ� �ش� �޽����� read_count�� +1 ��½�Ų��. 
--�̰��� �̿��Ͽ� ���� �޽������� new message���� ���� �� �ְ�
--������ ���� �޽����Կ��� ���� �޽����� �������� ���������� Ȯ���� �� �ִ�. 
--���� �޽����Կ��� �޽��� ��ȸ�� ������ ��ȸ���� ������� �ʴ´�.


--������ �ۼ�
create sequence message_seq;


--������ ����
drop sequence message_seq;



