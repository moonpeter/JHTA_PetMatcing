drop table image_board cascade constraints;

select*from image_board;


create table image_board(
BOARD_NUM              NUMBER,  --湲� 踰덊샇
BOARD_NAME             VARCHAR2(30), --�옉�꽦�옄
BOARD_PASS             VARCHAR2(30), --鍮꾨�踰덊샇
BOARD_SUBJECT          VARCHAR2(300), --�젣紐�
BOARD_CONTENT          VARCHAR2(4000), --�궡�슜
BOARD_FILE1            VARCHAR2(50),   --泥⑤��맆 �뙆�씪紐�(媛�怨�)
BOARD_ORIGINAL1        VARCHAR2(50),   --泥⑤��맆 �뙆�씪紐�
BOARD_FILE2            VARCHAR2(50),
BOARD_ORIGINAL2        VARCHAR2(50),
BOARD_FILE3            VARCHAR2(50),
BOARD_ORIGINAL3        VARCHAR2(50),
BOARD_FILE4            VARCHAR2(50),
BOARD_ORIGINAL4        VARCHAR2(50),
BOARD_READCOUNT        NUMBER default 0, --글의 조회수
BOARD_DATE             DATE default sysdate, --글의 작성 날짜
REPLY_COUNT            NUMBER default 0, --댓글 수
RECOMMEND_USER_LIST    VARCHAR2(4000) default ',', --게시글을 추천한 유저의 id 목록. varchar2는 최대 4000byte이므로 용량을 확장하고 싶으면 LONG (최대 2GB), CLOB(최대 4GB)을 사용할 것.   
RECOMMEND_COUNT        NUMBER default 0,
PRIMARY KEY(BOARD_NUM)
);

--�떆���뒪 �궘�젣
drop sequence image_board_seq;

--�떆���뒪 �옉�꽦
create sequence image_board_seq;

                     
                     