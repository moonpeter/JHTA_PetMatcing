package com.jhta.petMatching.mkh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhta.petMatching.mkh.domain.ShopBoard;
import com.jhta.petMatching.mkh.service.ShopService;


@Controller
@RequestMapping("/shop")
public class ShopController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	ShopService shopService;
	
	@GetMapping("main")
	public String mainPage() {
		return "shop/shop_main";
	}

	@GetMapping("detail")
	public String detailPage() {
		return "shop/shop_detail";
	}
	
	@GetMapping("writeForm")
	public String writePage() {
		return "shop/shop_write";
	}
	
	@PostMapping("write")
	public String insert(ShopBoard shopBoard) {
		shopService.insertShopBoard(shopBoard);
		
		return "redirect:main";
	}
	
}
