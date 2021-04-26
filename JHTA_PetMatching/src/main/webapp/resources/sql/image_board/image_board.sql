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
BOARD_READCOUNT        NUMBER default 0, --湲��쓽 議고쉶�닔
BOARD_DATE             DATE default sysdate, --湲��쓽 �옉�꽦 �궇吏�
REPLY_COUNT            NUMBER default 0, --�뙎湲� �닔
RECOMMEND_USER_LIST    VARCHAR2(4000) default ',', --寃뚯떆湲��쓣 異붿쿇�븳 �쑀���쓽 id 紐⑸줉. varchar2�뒗 理쒕� 4000byte�씠誘�濡� �슜�웾�쓣 �솗�옣�븯怨� �떢�쑝硫� LONG (理쒕� 2GB), CLOB(理쒕� 4GB)�쓣 �궗�슜�븷 寃�.   
RECOMMEND_COUNT        NUMBER default 0,
PRIMARY KEY(BOARD_NUM)
);

--�떆���뒪 �궘�젣
drop sequence image_board_seq;

--�떆���뒪 �옉�꽦
create sequence image_board_seq;

                     
                     