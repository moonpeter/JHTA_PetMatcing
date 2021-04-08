drop table member2 CASCADE CONSTRAINTS;
--1. index.jsp 에서 시작합니다.
--2. 관리자 계정 admin, 비번 1234를 만듭니다.
--3. 사용자 계정을 3개 만듭니다.

create table member2(
	id varchar2(15),
	password varchar2(60),
	name varchar2(15),
	age  Number(2),
	gender varchar2(3),
	email varchar2(30),
	PRIMARY KEY(id)
);

select * from member2;

insert into member(id, password, name, age, gender, email) 
values ('admin', '1234', '운영자','12', '남', '1234@naver.com');








drop table TEST CASCADE CONSTRAINTS;

CREATE TABLE TEST(
	SEND_DATE	DATE,
	TABLE_NAME	VARCHAR2(30),
	PROC_STEP	VARCHAR2(2),
	PROC_CNT	NUMBER(5),
	SUCC_CNT	NUMBER(5),
	FAIL_CNT	NUMBER(5),
	constraint TEST_PK PRIMARY KEY(SEND_DATE, TABLE_NAME)
);

 