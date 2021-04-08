package com.jhta.petMatching.mkh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/shop")
public class ShopController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class); 
	
	@GetMapping("main")
	public String mainPage() {
		return "shop/shop_main";
	}

	@GetMapping("detail")
	public String detailPage() {
		return "shop/shop_detail";
	}
	
	@GetMapping("write")
	public String writePage() {
		return "shop/shop_write";
	}
}
