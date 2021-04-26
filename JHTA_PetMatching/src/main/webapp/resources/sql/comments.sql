-- ////////////////////////////////////////////////////////////////////////////////////
-- 1. 자유게시판 댓글 테이블

drop table freeboard_comments cascade constraints;

CREATE TABLE freeboard_comments(
	num 		number			primary key,
	id			varchar2(30)	references members(id),
	content		varchar2(200),
	reg_date	date,
	board_num	number references free_board(board_num)
	            on delete cascade
);

drop sequence free_com_seq;
create sequence free_com_seq;

select * from freeboard_comments;

insert into freeboard_comments
values(free_com_seq.nextval, 'admin', 'jUnit테스트2', sysdate, 3);

-- ////////////////////////////////////////////////////////////////////////////////////
-- 2. 산책 신청 게시판(주인, DO_BOARD) 댓글 테이블

drop table doboard_comments cascade constraints;

CREATE TABLE doboard_comments(
	num 		number			primary key,
	id			varchar2(30)	references members(id),
	content		varchar2(200),
	reg_date	date,
	board_num	number references do_board(board_num)
	            on delete cascade
);

drop sequence do_com_seq;
create sequence do_com_seq;

select * from doboard_comments;

insert into doboard_comments
values(free_com_seq.nextval, 'admin', 'jUnit테스트2', sysdate, 3);



-- ////////////////////////////////////////////////////////////////////////////////////
-- 3. 산책 신청 게시판(산책러, DW_BOARD) 댓글 테이블

drop table dwboard_comments cascade constraints;

CREATE TABLE dwboard_comments(
	num 		number			primary key,
	id			varchar2(30)	references members(id),
	content		varchar2(200),
	reg_date	date,
	board_num	number references dw_board(board_num)
	            on delete cascade
);

drop sequence dw_com_seq;
create sequence dw_com_seq;

select * from dwboard_comments;

insert into dwboard_comments
values(dw_com_seq.nextval, 'admin', 'jUnit테스트2', sysdate, 3);



