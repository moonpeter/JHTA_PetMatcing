package com.jhta.petMatching.ahnyh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.ahnyh.dao.ImageBoardCommentDAO;
import com.jhta.petMatching.ahnyh.domain.ImageBoardComment;



@Service
public class ImageBoardCommentServiceImpl implements ImageBoardCommentService{
	@Autowired
	ImageBoardCommentDAO dao;

	@Override
	public int getListCount(int board_num) {
		return dao.getListCount(board_num);
	}

	@Override
	public List<ImageBoardComment> getCommentList(int board_num, int page) {
		int startrow=1;
		int endrow=page*3;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("board_num", board_num);
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getCommentList(map);
	}

	@Override
	public int commentsInsert(ImageBoardComment c) {
		return dao.commentsInsert(c);
	}

	@Override
	public int commentsDelete(int num) {
		return dao.commentsDelete(num);
	}

	@Override
	public int commentsUpdate(ImageBoardComment co) {
		return dao.commentsUpdate(co);
	}
}
