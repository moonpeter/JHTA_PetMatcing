package com.jhta.petMatching.hhi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.hhi.dao.CommentDAO;
import com.jhta.petMatching.hhi.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO dao;

	@Override
	public int getListCount(int board_num, String table_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_num", board_num);
		map.put("table_name", table_name);
		return dao.getListCount(map);
	}

	@Override
	public List<Comment> getCommentList(int board_num, int page, String table_name) {
		int startrow =  1;
		int endrow = page * 3;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_num", board_num);
		map.put("table_name", table_name);
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getCommentList(map);
	}

	@Override
	public int commentsInsert(Comment c) {
		return dao.commentsInsert(c);
	}

	@Override
	public int commentsDelete(Comment c) {
		return dao.commentsDelete(c);
	}

	@Override
	public int commentsUpdate(Comment co) {
		return dao.commentsUpdate(co);
	}
	
}
