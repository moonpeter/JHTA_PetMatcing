-- 산책신청게시판(견주)
-- DO : Dog Owner

drop table DO_BOARD cascade constraints;

CREATE TABLE DO_BOARD(
	BOARD_NUM				NUMBER,					-- 글번호
	BOARD_NAME				VARCHAR2(30)   not null,-- 작성자
	BOARD_PASS				VARCHAR2(30)   not null,-- 비밀번호
	BOARD_SUBJECT			VARCHAR2(300)  not null,-- 제목
	BOARD_CONTENT			VARCHAR2(4000) not null,-- 내용
	
	BOARD_RE_REF			NUMBER,					-- 답변 글 작성시 참조되는 글의 번호
	BOARD_RE_LEV			NUMBER,					-- 답변 글의 깊이
	BOARD_RE_SEQ			NUMBER,					-- 답변 글의 순서
	BOARD_READCOUNT			NUMBER,					-- 글의 조회수
	BOARD_DATE 				DATE,					-- 글의 작성 날짜
	DOG_NAME				VARCHAR2(20)   not null,-- 반려견 이름
	DOG_AGE					NUMBER		   not null,-- 반려견 나이
	DOG_BREED				VARCHAR2(30)   not null, -- 견종
	DOG_SPECIAL_NOTE		VARCHAR2(200)  not null,-- 반려견 특이사항
	
	BOARD_FILE				VARCHAR2(50),			-- 첨부될 파일명(가공) 
	BOARD_ORIGINAL			VARCHAR2(50),  			-- 첨부 파일명
	
	DOG_PHOTO				VARCHAR2(200),			-- 반려견 사진
	DOG_PHOTO_ORIGINAL		VARCHAR2(50),  			-- 첨부 파일명
		
	DOG_PHOTO2				VARCHAR2(200),			-- 반려견 사진
	DOG_PHOTO_ORIGINAL2			VARCHAR2(50),  		-- 첨부 파일명
	
	DOG_PHOTO3				VARCHAR2(200),			-- 반려견 사진
	DOG_PHOTO_ORIGINAL3			VARCHAR2(50),  		-- 첨부 파일명
	
	DOG_PHOTO4				VARCHAR2(200),			-- 반려견 사진
	DOG_PHOTO_ORIGINAL4		VARCHAR2(50),  			-- 첨부 파일명
	
	DOG_PHOTO5				VARCHAR2(200),			-- 반려견 사진
	DOG_PHOTO_ORIGINAL5		VARCHAR2(50),  			-- 첨부 파일명
	
	PRIMARY KEY(BOARD_NUM)
);

select * from DO_BOARD;