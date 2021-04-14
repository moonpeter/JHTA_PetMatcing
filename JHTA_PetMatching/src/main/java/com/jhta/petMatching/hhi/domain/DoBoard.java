package com.jhta.petMatching.hhi.domain;

import org.springframework.web.multipart.MultipartFile;

// DoBoard : Dog Owner Board
public class DoBoard {

	private int    BOARD_NUM;		// 글번호
	private String BOARD_NAME;	// 글 작성자
	private String BOARD_PASS;	// 글 비밀번호 
	private String BOARD_SUBJECT;	// 글 제목
	private String BOARD_CONTENT;	// 글 작성자
	private String BOARD_FILE;	// 실제 저장된 파일의 이름
	private int BOARD_RE_REF;		// 답변 글 작성시 참조되는 글의 번호
	private int BOARD_RE_LEV;		// 글번호
	private int BOARD_RE_SEQ;		// 글번호
	private int BOARD_READCOUNT;		// 글의 조회
	
	// board_wirte.jsp에서 name 속성 확인하세요.
	// <input type="file" id="upfile" name="uploadfile"> 확인
//	private MultipartFile uploadfile;	
	private String BOARD_ORIGINAL;		// 첨부될 파일의 이름
	
	private String BOARD_DATE;
	private String DOG_THUMBIMG;
	private String DOG_NAME;
	private int DOG_AGE;
	private String DOG_BREED;
	private String DOG_PHOTO;
	private String DOG_SPECIAL_NOTE;
	
	private MultipartFile dog_uploadfile;	
	
	
	public MultipartFile getDog_uploadfile() {
		return dog_uploadfile;
	}

	public void setDog_uploadfile(MultipartFile dog_uploadfile) {
		this.dog_uploadfile = dog_uploadfile;
	}

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
	
	public String getBOARD_FILE() {
		return BOARD_FILE;
	}
	
	public void setBOARD_FILE(String bOARD_FILE) {
		BOARD_FILE = bOARD_FILE;
	}
	
	public int getBOARD_RE_REF() {
		return BOARD_RE_REF;
	}
	
	public void setBOARD_RE_REF(int bOARD_RE_REF) {
		BOARD_RE_REF = bOARD_RE_REF;
	}
	
	public int getBOARD_RE_LEV() {
		return BOARD_RE_LEV;
	}
	
	public void setBOARD_RE_LEV(int bOARD_RE_LEV) {
		BOARD_RE_LEV = bOARD_RE_LEV;
	}
	
	public int getBOARD_RE_SEQ() {
		return BOARD_RE_SEQ;
	}
	
	public void setBOARD_RE_SEQ(int bOARD_RE_SEQ) {
		BOARD_RE_SEQ = bOARD_RE_SEQ;
	}
	
	public int getBOARD_READCOUNT() {
		return BOARD_READCOUNT;
	}
	
	public void setBOARD_READCOUNT(int bOARD_READCOUNT) {
		BOARD_READCOUNT = bOARD_READCOUNT;
	}
	
//	public MultipartFile getUploadfile() {
//		return uploadfile;
//	}
//	
//	public void setUploadfile(MultipartFile uploadfile) {
//		this.uploadfile = uploadfile;
//	}
	
	public String getBOARD_ORIGINAL() {
		return BOARD_ORIGINAL;
	}
	
	public void setBOARD_ORIGINAL(String bOARD_ORIGINAL) {
		BOARD_ORIGINAL = bOARD_ORIGINAL;
	}
	
	public String getBOARD_DATE() {
		return BOARD_DATE;
	}
	
	public void setBOARD_DATE(String bOARD_DATE) {
		BOARD_DATE = bOARD_DATE.substring(0,16);
	}
	
	public String getDOG_THUMBIMG() {
		return DOG_THUMBIMG;
	}
	
	public void setDOG_THUMBIMG(String dOG_THUMBIMG) {
		DOG_THUMBIMG = dOG_THUMBIMG;
	}
	
	public String getDOG_NAME() {
		return DOG_NAME;
	}
	
	public void setDOG_NAME(String dOG_NAME) {
		DOG_NAME = dOG_NAME;
	}
	
	public int getDOG_AGE() {
		return DOG_AGE;
	}
	
	public void setDOG_AGE(int dOG_AGE) {
		DOG_AGE = dOG_AGE;
	}
	
	public String getDOG_BREED() {
		return DOG_BREED;
	}
	
	public void setDOG_BREED(String dOG_BREED) {
		DOG_BREED = dOG_BREED;
	}
	
	public String getDOG_SPECIAL_NOTE() {
		return DOG_SPECIAL_NOTE;
	}
	
	public void setDOG_SPECIAL_NOTE(String dOG_SPECIAL_NOTE) {
		DOG_SPECIAL_NOTE = dOG_SPECIAL_NOTE;
	}
	
	public String getDOG_PHOTO() {
		return DOG_PHOTO;
	}
	
	public void setDOG_PHOTO(String dOG_PHOTO) {
		DOG_PHOTO = dOG_PHOTO;
	}
	
	
	
}
