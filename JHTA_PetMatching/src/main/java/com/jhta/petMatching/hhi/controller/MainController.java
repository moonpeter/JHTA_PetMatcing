package com.jhta.petMatching.hhi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home") // http://localhost:8088/myhome4/member/ 로 시작하는 주소로 매핑
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
		@RequestMapping("/main")
		public String main() {
			logger.info("Welcome! 우리 주인이 달라졌어요 !!!");
			return "common/main";
		}

}
