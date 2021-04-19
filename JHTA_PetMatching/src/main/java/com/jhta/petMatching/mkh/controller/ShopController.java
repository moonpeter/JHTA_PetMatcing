package com.jhta.petMatching.mkh.controller;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jhta.petMatching.mkh.domain.Shop;
import com.jhta.petMatching.mkh.service.ShopService;


@Controller
@RequestMapping("/shop")
public class ShopController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	ShopService shopService;
	
	@Value("${savefoldername_shop}")
	private String saveFolder;
	
	@GetMapping("writeForm")
	public String writePage() {
		return "shop/shop_write";
	}
	
	@PostMapping("write")
	public String insert(Shop shop) throws Exception {
		MultipartFile uploadThumnail = shop.getShop_upload_thumnail();
		MultipartFile uploadImgContent = shop.getShop_upload_img_content();
				
		if (!uploadThumnail.isEmpty()) {
			String fileName = uploadThumnail.getOriginalFilename(); // 썸네일의 원래 파일명
			shop.setShop_thumnail_original(fileName); // 썸네일의 원래 파일명 저장
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName = " + fileDBName);
			
			uploadThumnail.transferTo(new File(saveFolder + fileDBName));
			
			shop.setShop_thumnail(fileDBName);
		}
		
		if (!uploadImgContent.isEmpty()) {
			String fileName = uploadImgContent.getOriginalFilename(); // 썸네일의 원래 파일명 
			shop.setShop_img_content_original(fileName); // 썸네일의 원래 파일명 저장
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName = " + fileDBName);
			
			uploadImgContent.transferTo(new File(saveFolder + fileDBName));
			
			shop.setShop_img_content(fileDBName);
		}
		
		shopService.shopInsert(shop);
		
		return "redirect:main";
	}

	private String fileDBName(String fileName, String saveFolder) {
		
		// 새로운 폴더 이름 : 오늘 + 년 + 월 + 일 
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		
		String homedir = saveFolder + year + "-" + month + "-" + date;
		logger.info(homedir);
		File path1 = new File(homedir);
		if(!(path1.exists())) {	// 
			path1.mkdir();		// 새로운 폴더를 생성 
		}
		
		// 난수 생성 
		Random r = new Random();
		int random = r.nextInt(10000000);
		
		// 확장자 구하기 
		int index = fileName.lastIndexOf(".");
		// 문자열에서 특정 문자열의 위치값(index)를 반환
		// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
		// lastIndexOf는 마지막으로 반결되는 문자열의 index를 반환합니다.
		// (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
		logger.info("index = " + index);
		String fileExtension = fileName.substring(index + 1);
		logger.info("fileExtension = " + fileExtension);
		
		// 새로운 파일명 
		String refileName = "shop" + year + month + date + random + "." + fileExtension;
		logger.info("refileName = " + refileName);
		
		// DB에 저장될 파일명 
		String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		logger.info("fileDBName = " + fileDBName);
		
		return fileDBName;
	}
	
	@GetMapping("/list")
	public ModelAndView shopList(@RequestParam(value="page", defaultValue="1", required=false) int page, ModelAndView mv) {
		
		int limit = 9; // 한 페이지에 보여줄 게시글의 수 
		int listCount = shopService.getListCount(); // 총 게시글의 수를 받아옴  
		int maxPage = (listCount + limit -1) / limit;
		int startPage = ((page-1) / 9) * 9 + 1;
		int endPage = startPage + 9 -1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		List<Shop> shopList = shopService.getShopList(page, limit);
		
		logger.info("!!!!!!!!!!! shoptList : " + shopList.toString() );
		
		mv.setViewName("shop/shop_main");
		mv.addObject("page", page);
		mv.addObject("maxPage", maxPage);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("listCount", listCount);
		mv.addObject("limit", limit);
		mv.addObject("shopList", shopList);
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView shopDetail(int shop_num, ModelAndView mv) {
		
		Shop shopBoard = shopService.getShopDetail(shop_num);
		
		mv.setViewName("shop/shop_detail");
		mv.addObject("shop", shopBoard);
		
		return mv;
	}
	
}
