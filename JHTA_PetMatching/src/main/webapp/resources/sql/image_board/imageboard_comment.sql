drop table imageboard_comment cascade constraints;
drop sequence imageboard_comment_seq;

create table imageboard_comment(
  num                   number  primary key, --�뙎湲� 踰덊샇
  id                    varchar2(30),
  content               varchar2(200),
  reg_date              date,
  board_num             number references image_board(board_num)
                        on delete cascade
);


create sequence imageboard_comment_seq;

select*from imageboard_comment;