package com.jhta.petMatching.ahnyh.service;

import java.util.List;

import com.jhta.petMatching.ahnyh.domain.ImageBoard;





public interface ImageBoardService {
		
	//글의 갯수 구하기
	public int getListCount(String index, String search_word);
			
	//글 목록 보기
	public List<ImageBoard> getBoardList(int page, int limit, String search_word, String index);
		
	//글 등록하기
	public void insertBoard(ImageBoard imageboard);
		
	//글 내용 보기
	public ImageBoard getDetail(int num);
		
	//조회수 업데이트
	public int setReadCountUpdate(int num);
		
	//글쓴이인지 확인
	public boolean isBoardWriter(int num, String pass);
		
	//글 삭제
	public int boardDelete(int num);
		
	//스케줄 파일 삭제할 목록 삽입
	public int insert_deleteFile(String before_file);
		
	//스케줄 파일 삭제 목록 가져오기
	public List<String> getDeleteFileList();
		
	//글 수정
	public int boardModify(ImageBoard imageboard);
		
	//list 페이지의 제목 옆에 표시되는 [댓글수] 업데이트
	public void reply_countUpdate(int board_num, int reply_listcount);
		
	//게시글을 추천한 유저 목록 가져오기
	public String getRecommend_User_List(int board_num);
		
	//게시글 추천 버튼을 누른 유저의 id를 삽입
	public void insertRecommend_User_List(int board_num, String loginid);
		
	//추천수 +1 증가
	public void updateRecommend_Count(int board_num);
		
	//게시글을 추천한 유저 id 체크(이미 추천버튼을 눌렀는지 안눌렀는지를 확인)
	public String recommendCheck(String recommend_user_list, String loginid);
		
	//추천수 best3 게시글 list 가져오기
	public List<ImageBoard> getBestBoardList();
		
	//추천수 best3 게시글 list 갯수 구하기. jsp에서 <c>태그를 사용해서 리스트 수가 0인 경우 best3게시글을 보여주는 테이블을 감추는데 사용.
	public int getBestBoardListCount();
	
}
