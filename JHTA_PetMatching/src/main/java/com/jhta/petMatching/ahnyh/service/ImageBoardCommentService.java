package com.jhta.petMatching.ahnyh.service;

import java.util.List;

import com.jhta.petMatching.ahnyh.domain.ImageBoardComment;


public interface ImageBoardCommentService {
	//글의 갯수 구하기
	public int getListCount(int board_num);
			
	//댓글 목록 가져오기
	public List<ImageBoardComment> getCommentList(int board_num, int page);   
			
	//댓글 등록하기
	public int commentsInsert(ImageBoardComment c);
			
	//댓글 삭제
	public int commentsDelete(int num);
		
	//댓글 수정
	public int commentsUpdate(ImageBoardComment co);
}
