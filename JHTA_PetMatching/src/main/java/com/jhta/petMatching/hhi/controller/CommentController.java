package com.jhta.petMatching.hhi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhta.petMatching.hhi.service.CommentService;
import com.jhta.petMatching.hhi.domain.Comment;

@Controller
@RequestMapping(value="/comment")
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public Map<String, Object> CommentList(int board_num, int page, String table_name) {
		
		List<Comment> list = commentService.getCommentList(board_num, page, table_name);
		int listcount = commentService.getListCount(board_num, table_name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("listcount", listcount);
		return map;
	}
	
	@PostMapping(value = "/add")
	public void CommentAdd(Comment co, HttpServletResponse response)
		throws Exception {
		logger.info("id = " + co.getId());
		System.out.print("id=" + co.getId());
		int ok = commentService.commentsInsert(co);
		response.getWriter().print(ok);
	}
	
	@PostMapping(value = "/delete")
	public void CommentDelete(Comment co, HttpServletResponse response)
		throws Exception {
		int result = commentService.commentsDelete(co);
		response.getWriter().print(result);
	}
	
	@PostMapping(value = "/update")
	public void CommentUpdate(Comment co, HttpServletResponse response)
		throws Exception {
		int ok = commentService.commentsUpdate(co);
		response.getWriter().print(ok);
	}
	
	
	
	
}
