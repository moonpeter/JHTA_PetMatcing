package com.jhta.petMatching.hhi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jhta.petMatching.hhi.domain.DwBoard;
import com.jhta.petMatching.hhi.service.BoardService;
import com.jhta.petMatching.hhi.service.DoBoardService;
import com.jhta.petMatching.hhi.service.DwBoardService;

@Controller
@RequestMapping(value = "/home") 
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private DoBoardService doboardService;
	
	private DwBoardService dwboardService;
	
	private BoardService boardService;
	
	
		@RequestMapping("/main")
		public String main() {
			logger.info("Welcome! 우리 주인이 달라졌어요 !!!");
			return "common/main";
		}

//		@RequestMapping(value="/main", method=RequestMethod.GET)
//		public ModelAndView boardList(ModelAndView mv,
//				@RequestParam(value="page", defaultValue="1", required=false)int page) {
//			
//			int limit = 5;
//			
//			int listcount = dwboardService.getListCount(); // 총 리스트 수를 받아옴
//
//			int maxpage = (listcount + limit - 1) / limit; // 총 페이지 수
//
//			int startpage = ((page - 1) / 10) * 10 + 1; // 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21 등...)
//
//			int endpage = startpage + 10 - 1; // 현재 페이지에 보여줄 마지막 페이지 수 (10, 20, 30 등...)
//
//			if (endpage > maxpage)
//				endpage = maxpage;
//			
//			
//			List<DwBoard> dwboardlist = dwboardService.getBoardList(page, limit);
//			mv.setViewName("board/dogwalker_board/dogwalkerboard_list");
//			mv.addObject("page",page);
//			mv.addObject("boardlist", dwboardlist);
//			mv.addObject("limit", limit);
//			mv.addObject("maxpage", maxpage);
//			mv.addObject("startpage", startpage);
//			mv.addObject("endpage", endpage);
////			Map<String, Object> map = new HashMap<String, Object>();
//			
//			return mv;
//		}
}
