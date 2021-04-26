drop table shop_board CASCADE CONSTRAINTS;

create table shop_board(
	shop_num						number(10)	primary key,	-- �뙋留ㅺ� 踰덊샇
	shop_category					varchar2(20) not null,		-- 移댄뀒怨좊━
	shop_title						varchar2(50) not null,		-- �젣紐�
	shop_price						varchar2(10) not null,		-- 媛�寃�
	shop_country_of_origin			varchar2(10) not null,		-- �썝�궛吏�
	shop_brand						varchar2(20) not null,		-- 釉뚮옖�뱶
	
	shop_thumnail					varchar2(100) not null,		-- �뜽�꽕�씪
	shop_thumnail_original			varchar2(100) not null,
	
	shop_grade						varchar2(10), 				-- �룊�젏
	shop_img_content				varchar2(100),				-- 蹂몃Ц �씠誘몄� �뙆�씪
	shop_img_content_original		varchar2(100),
	shop_img_content_2				varchar2(100),
	shop_img_content_original_2		varchar2(100),
	shop_img_content_3				varchar2(100),
	shop_img_content_original_3		varchar2(100),
	shop_img_content_4				varchar2(100),
	shop_img_content_original_4		varchar2(100),
	shop_img_content_5				varchar2(100),
	shop_img_content_original_5		varchar2(100),

	shop_text_content				varchar2(3000)				-- 蹂몃Ц �뀓�뒪�듃
);

select * from shop_board;

drop sequence shop_seq_num;

create sequence shop_seq_num
increment by 1;