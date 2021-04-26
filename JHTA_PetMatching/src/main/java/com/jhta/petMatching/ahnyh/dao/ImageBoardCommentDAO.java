package com.jhta.petMatching.ahnyh.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.ahnyh.domain.ImageBoardComment;





@Repository
public class ImageBoardCommentDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getListCount(int board_num) {
		return sqlSession.selectOne("ImageBoardComments.count", board_num);
	} 
	
	public int commentsInsert(ImageBoardComment c) {
		return sqlSession.insert("ImageBoardComments.insert", c);
	} 
	
	public List<ImageBoardComment> getCommentList(Map<String, Integer> map){
		return sqlSession.selectList("ImageBoardComments.getList", map);
	}
	
	public int commentsUpdate(ImageBoardComment co) {
		return sqlSession.update("ImageBoardComments.update", co);
	}  
	
	public int commentsDelete(int num) {
		return sqlSession.delete("ImageBoardComments.delete", num);
	} 
}
