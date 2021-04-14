package com.jhta.petMatching.mkh.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jhta.petMatching.mkh.domain.ShopBoard;
import com.jhta.petMatching.mkh.service.ShopService;


@Controller
@RequestMapping("/shop")
public class ShopController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	ShopService shopService;
	
	@Value("${savefoldername_shop")
	private String saveFolder;
	
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
	public String insert(ShopBoard shopBoard) throws Exception {
		MultipartFile uploadThumnail = shopBoard.getShop_upload_thumnail();
		MultipartFile uploadImgContent = shopBoard.getShop_upload_img_content();
		
		if (!uploadThumnail.isEmpty()) {
			String fileName = uploadThumnail.getOriginalFilename(); // 썸네일의 원래 파일명 
			shopBoard.setShop_thumnail_original(fileName); // 썸네일의 원래 파일명 저장
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName = " + fileDBName);
			
			uploadThumnail.transferTo(new File(saveFolder + fileDBName));
			
			shopBoard.setShop_thumnail(fileDBName);
		}
		
		if (!uploadImgContent.isEmpty()) {
			String fileName = uploadImgContent.getOriginalFilename(); // 썸네일의 원래 파일명 
			shopBoard.setShop_img_content_original(fileName); // 썸네일의 원래 파일명 저장
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName = " + fileDBName);
			
			uploadImgContent.transferTo(new File(saveFolder + fileDBName));
			
			shopBoard.setShop_img_content(fileDBName);
		}
		
		shopService.insertShopBoard(shopBoard);
		
		return "redirect:main";
	}

	private String fileDBName(String fileName, String saveFolder) {
		
		// 새로운 폴더 이름 : 오늘 + 년 + 월 + 일 
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		
		String homedir = saveFolder + year + "-" + "month" + "date";
		logger.info(homedir);
		File path1 = new File(homedir);
		if(!(path1.exists())) {	// 
			path1.mkdir();		// 새로운 폴더를 생성 
		}
		
		return null;
	}
	
}
