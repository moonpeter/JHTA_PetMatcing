drop table shop_board CASCADE CONSTRAINTS;

create table shop_board(
	shop_num						number(10)	primary key,	-- 판매글 번호
	shop_category					varchar2(20) not null,		-- 카테고리
	shop_title						varchar2(50) not null,		-- 제목
	shop_price						varchar2(10) not null,		-- 가격
	shop_country_of_origin			varchar2(10) not null,		-- 원산지
	shop_brand						varchar2(20) not null,		-- 브랜드
	
	shop_thumnail					varchar2(100) not null,		-- 썸네일
	shop_thumnail_original			varchar2(100) not null,
	
	shop_grade						varchar2(10), 				-- 평점
	shop_img_content				varchar2(100),				-- 본문 이미지 파일
	shop_img_content_original		varchar2(100),
	shop_img_content_2				varchar2(100),
	shop_img_content_original_2		varchar2(100),
	shop_img_content_3				varchar2(100),
	shop_img_content_original_3		varchar2(100),
	shop_img_content_4				varchar2(100),
	shop_img_content_original_4		varchar2(100),
	shop_img_content_5				varchar2(100),
	shop_img_content_original_5		varchar2(100),

	shop_text_content				varchar2(3000)				-- 본문 텍스트
);

select * from shop_board;

drop sequence shop_seq_num;

create sequence shop_seq_num
increment by 1;