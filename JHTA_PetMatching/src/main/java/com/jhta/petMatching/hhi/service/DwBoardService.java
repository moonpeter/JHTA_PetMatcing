package com.jhta.petMatching.hhi.service;

import java.util.List;

import com.jhta.petMatching.hhi.domain.Board;
import com.jhta.petMatching.hhi.domain.DwBoard;


public interface DwBoardService {

	
	// 글의 갯수 구하기
	public int getListCount();
	
	// 글 목록 보기
	public List<DwBoard> getBoardList(int page, int limit);
	
	// 글 내용 보기
	public DwBoard getDetail(int num);
	
	// 글 답변
	public int boardReply(DwBoard board);
	
	//글 수정
	public int boardModify(DwBoard modifyboard);
	
	// 글 삭제
	public int boardDelete(int num);
	
	// 조회수 업데이트
	public int setReadCountUpdate(int num);
	
	// 글쓴이인지 확인
	public boolean isBoardWriter(int num, String pass);
	
	// 글 등록하기
	public void insertBoard(DwBoard board);
	
	// BOARD_RE_SEQ 값 수정
	public int boardReplyUpdate(DwBoard board);

	
	public List<String> getDeleteFileList();

	public int insert_deleteFile(String before_file);

	// 서치리스트
	public List<DwBoard> getSearchList(String index, String search_word, int page, int limit);

	public int getSearchListCount(String index, String search_word);
	
}
