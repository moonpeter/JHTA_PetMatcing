package com.jhta.petMatching.hhi.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.hhi.domain.Comment;



@Repository
public class DoCommentDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getListCount(int board_num) {
		return sqlSession.selectOne("DoComments.count", board_num);
	}

	public List<Comment> getCommentList(Map<String, Integer> map) {
		return sqlSession.selectList("DoComments.getList", map);
	}

	public int commentsInsert(Comment c) {
		return sqlSession.insert("DoComments.insert", c);
	}

	public int commentsDelete(int num) {
		return sqlSession.delete("DoComments.delete",num);
	}

	public int commentsUpdate(Comment co) {
		return sqlSession.update("DoComments.update",co);
	}


}
