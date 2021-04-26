package com.jhta.petMatching.ahnyh.controller;

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

import com.jhta.petMatching.ahnyh.domain.ImageBoardComment;
import com.jhta.petMatching.ahnyh.service.ImageBoardCommentService;
import com.jhta.petMatching.ahnyh.service.ImageBoardService;



@Controller
@RequestMapping(value="/imageboardcomment")
public class ImageBoardCommentController {
	private static final Logger logger = LoggerFactory.getLogger(ImageBoardCommentController.class);
	
	@Autowired
	private ImageBoardCommentService imageBoardCommentService;
	
	@Autowired
	private ImageBoardService imageboardservice;
	
	
	
	@ResponseBody
	@RequestMapping(value="/list")
	public Map<String, Object> imageboard_commentListAjax(int board_num, int page){
		List<ImageBoardComment> list = imageBoardCommentService.getCommentList(board_num, page);
		int listcount = imageBoardCommentService.getListCount(board_num);
		imageboardservice.reply_countUpdate(board_num, listcount); //이지미 게시판 list 페이지에서 제목 옆에 표시된 [댓글수]를 업데이트 해준다. 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("listcount", listcount);
		return map;
	}
	
	
	@PostMapping(value="/add")
	public void imageboard_CommentAdd(ImageBoardComment c, HttpServletResponse response) throws Exception{
		String loginid = c.getId();
		logger.info("loginid= "+loginid); //로그인을 안 하면 빈문자열임을 확인할 수 있다.
		
		if(loginid.isEmpty()) { //빈 문자열인 경우
			response.getWriter().print("notLogin"); // imageBoardCommentService.commentsInsert()가 없으므로 댓글 작성 정보가 insert되지 않는다.    
		}else {
			int ok = imageBoardCommentService.commentsInsert(c);   
			response.getWriter().print(ok);              
		}
	}
	
	
	@PostMapping(value="/update")
	public void imageboard_CommentUpdate(ImageBoardComment c, HttpServletResponse response) throws Exception{
		int ok = imageBoardCommentService.commentsUpdate(c);   
		response.getWriter().print(ok);
	}
	
	
	@PostMapping(value="/delete")
	public void imageboard_CommentDelete(int num, HttpServletResponse response) throws Exception{
		int result = imageBoardCommentService.commentsDelete(num);   
		response.getWriter().print(result);
	}
	
	
}
