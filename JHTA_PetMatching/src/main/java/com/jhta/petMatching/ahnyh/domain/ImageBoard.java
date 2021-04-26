package com.jhta.petMatching.ahnyh.domain;

import org.springframework.web.multipart.MultipartFile;

public class ImageBoard {
	private int BOARD_NUM; //글번호
	private String BOARD_NAME; //글 작성자
	private String BOARD_PASS; //글 비밀번호
	private String BOARD_SUBJECT; //글 제목
	private String BOARD_CONTENT; //글 내용
	private String BOARD_FILE1; //실제 저장된 파일의 이름
	private String BOARD_ORIGINAL1; //첨부될 파일의 이름
	//ImageBoard_write.jsp에서 name 속성 확인.
	//<input type="file" id="upload" name="uploadfile"> 이 태그의 name과 일치해야한다.
	private MultipartFile uploadfile1;
	private String BOARD_FILE2;
	private String BOARD_ORIGINAL2;
	private MultipartFile uploadfile2;
	private String BOARD_FILE3;
	private String BOARD_ORIGINAL3;
	private MultipartFile uploadfile3;
	private String BOARD_FILE4;
	private String BOARD_ORIGINAL4;
	private MultipartFile uploadfile4;
	private int BOARD_READCOUNT; //글의 조회수
	private String BOARD_DATE; //글의 등록일
	private int REPLY_COUNT; //댓글의 수
	private String RECOMMEND_USER_LIST; //게시글을 추천한 유저들의 목록(한 유저가 한 게시글에 중복해서 추천하는 것을 방지하는데에 사용)
	private int RECOMMEND_COUNT; //게시글의 추천수
	
	
	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}
	public String getBOARD_NAME() {
		return BOARD_NAME;
	}
	public void setBOARD_NAME(String bOARD_NAME) {
		BOARD_NAME = bOARD_NAME;
	}
	public String getBOARD_PASS() {
		return BOARD_PASS;
	}
	public void setBOARD_PASS(String bOARD_PASS) {
		BOARD_PASS = bOARD_PASS;
	}
	public String getBOARD_SUBJECT() {
		return BOARD_SUBJECT;
	}
	public void setBOARD_SUBJECT(String bOARD_SUBJECT) {
		BOARD_SUBJECT = bOARD_SUBJECT;
	}
	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}
	public void setBOARD_CONTENT(String bOARD_CONTENT) {
		BOARD_CONTENT = bOARD_CONTENT;
	}
	public String getBOARD_FILE1() {
		return BOARD_FILE1;
	}
	public void setBOARD_FILE1(String bOARD_FILE1) {
		BOARD_FILE1 = bOARD_FILE1;
	}
	public String getBOARD_ORIGINAL1() {
		return BOARD_ORIGINAL1;
	}
	public void setBOARD_ORIGINAL1(String bOARD_ORIGINAL1) {
		BOARD_ORIGINAL1 = bOARD_ORIGINAL1;
	}
	public MultipartFile getUploadfile1() {
		return uploadfile1;
	}
	public void setUploadfile1(MultipartFile uploadfile1) {
		this.uploadfile1 = uploadfile1;
	}
	public String getBOARD_FILE2() {
		return BOARD_FILE2;
	}
	public void setBOARD_FILE2(String bOARD_FILE2) {
		BOARD_FILE2 = bOARD_FILE2;
	}
	public String getBOARD_ORIGINAL2() {
		return BOARD_ORIGINAL2;
	}
	public void setBOARD_ORIGINAL2(String bOARD_ORIGINAL2) {
		BOARD_ORIGINAL2 = bOARD_ORIGINAL2;
	}
	public MultipartFile getUploadfile2() {
		return uploadfile2;
	}
	public void setUploadfile2(MultipartFile uploadfile2) {
		this.uploadfile2 = uploadfile2;
	}
	public String getBOARD_FILE3() {
		return BOARD_FILE3;
	}
	public void setBOARD_FILE3(String bOARD_FILE3) {
		BOARD_FILE3 = bOARD_FILE3;
	}
	public String getBOARD_ORIGINAL3() {
		return BOARD_ORIGINAL3;
	}
	public void setBOARD_ORIGINAL3(String bOARD_ORIGINAL3) {
		BOARD_ORIGINAL3 = bOARD_ORIGINAL3;
	}
	public MultipartFile getUploadfile3() {
		return uploadfile3;
	}
	public void setUploadfile3(MultipartFile uploadfile3) {
		this.uploadfile3 = uploadfile3;
	}
	public String getBOARD_FILE4() {
		return BOARD_FILE4;
	}
	public void setBOARD_FILE4(String bOARD_FILE4) {
		BOARD_FILE4 = bOARD_FILE4;
	}
	public String getBOARD_ORIGINAL4() {
		return BOARD_ORIGINAL4;
	}
	public void setBOARD_ORIGINAL4(String bOARD_ORIGINAL4) {
		BOARD_ORIGINAL4 = bOARD_ORIGINAL4;
	}
	public MultipartFile getUploadfile4() {
		return uploadfile4;
	}
	public void setUploadfile4(MultipartFile uploadfile4) {
		this.uploadfile4 = uploadfile4;
	}
	public int getBOARD_READCOUNT() {
		return BOARD_READCOUNT;
	}
	public void setBOARD_READCOUNT(int bOARD_READCOUNT) {
		BOARD_READCOUNT = bOARD_READCOUNT;
	}
	public String getBOARD_DATE() {
		return BOARD_DATE;
	}
	public void setBOARD_DATE(String bOARD_DATE) {
		BOARD_DATE = bOARD_DATE;
	}
	public int getREPLY_COUNT() {
		return REPLY_COUNT;
	}
	public void setREPLY_COUNT(int rEPLY_COUNT) {
		REPLY_COUNT = rEPLY_COUNT;
	}
	public String getRECOMMEND_USER_LIST() {
		return RECOMMEND_USER_LIST;
	}
	public void setRECOMMEND_USER_LIST(String rECOMMEND_USER_LIST) {
		RECOMMEND_USER_LIST = rECOMMEND_USER_LIST;
	}
	public int getRECOMMEND_COUNT() {
		return RECOMMEND_COUNT;
	}
	public void setRECOMMEND_COUNT(int rECOMMEND_COUNT) {
		RECOMMEND_COUNT = rECOMMEND_COUNT;
	}
	
	
	
	
}
