package com.jhta.petMatching.hhi.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.hhi.domain.Comment;



@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getListCount(Map<String, Object> map) {
		return sqlSession.selectOne("Comments.count", map);
	}

	public List<Comment> getCommentList(Map<String, Object> map) {
		return sqlSession.selectList("Comments.getList", map);
	}

	public int commentsInsert(Comment c) {
		return sqlSession.insert("Comments.insert", c);
	}

	public int commentsDelete(Comment c) {
		return sqlSession.delete("Comments.delete",c);
	}

	public int commentsUpdate(Comment co) {
		return sqlSession.update("Comments.update",co);
	}


}
