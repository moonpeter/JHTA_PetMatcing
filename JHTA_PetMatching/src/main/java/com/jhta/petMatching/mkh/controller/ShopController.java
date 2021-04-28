package com.jhta.petMatching.mkh.controller;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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
	
	@GetMapping("writeForm")
	public String writePage() {
		return "shop/shop_write";
	}
	
	@PostMapping("write")
	public String insert(Shop shop, HttpServletRequest request) throws Exception {
		
		String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/shop/";
		logger.info("이미지 업로드 실제 경로 === " + saveFolder);
		
		MultipartFile uploadThumnail = shop.getShop_upload_thumnail();
		
//		MultipartFile img_content_array[] = null;
//		for(int i=0; i<=5; i++) {
//			img_content_array[i] = shop.getShop_upload_img_content();	
//		}
		
		MultipartFile uploadImgContent = shop.getShop_upload_img_content();
		MultipartFile uploadImgContent_2 = shop.getShop_upload_img_content_2();
		MultipartFile uploadImgContent_3 = shop.getShop_upload_img_content_3();
		MultipartFile uploadImgContent_4 = shop.getShop_upload_img_content_4();
		MultipartFile uploadImgContent_5 = shop.getShop_upload_img_content_5();

		
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
		
		if (!uploadImgContent_2.isEmpty()) {
			String fileName = uploadImgContent_2.getOriginalFilename(); // 썸네일의 원래 파일명 
			shop.setShop_img_content_original_2(fileName); // 썸네일의 원래 파일명 저장
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName = " + fileDBName);
			
			uploadImgContent_2.transferTo(new File(saveFolder + fileDBName));
			
			shop.setShop_img_content_2(fileDBName);
		}
		
		if (!uploadImgContent_3.isEmpty()) {
			String fileName = uploadImgContent_3.getOriginalFilename(); // 썸네일의 원래 파일명 
			shop.setShop_img_content_original_3(fileName); // 썸네일의 원래 파일명 저장
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName = " + fileDBName);
			
			uploadImgContent_3.transferTo(new File(saveFolder + fileDBName));
			
			shop.setShop_img_content_3(fileDBName);
		}
		
		if (!uploadImgContent_4.isEmpty()) {
			String fileName = uploadImgContent_4.getOriginalFilename(); // 썸네일의 원래 파일명 
			shop.setShop_img_content_original_4(fileName); // 썸네일의 원래 파일명 저장
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName = " + fileDBName);
			
			uploadImgContent_4.transferTo(new File(saveFolder + fileDBName));
			
			shop.setShop_img_content_4(fileDBName);
		}
		
		if (!uploadImgContent_5.isEmpty()) {
			String fileName = uploadImgContent_5.getOriginalFilename(); // 썸네일의 원래 파일명 
			shop.setShop_img_content_original_5(fileName); // 썸네일의 원래 파일명 저장
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName = " + fileDBName);
			
			uploadImgContent_5.transferTo(new File(saveFolder + fileDBName));
			
			shop.setShop_img_content_5(fileDBName);
		}
		
		shopService.shopInsert(shop);
		
		return "redirect:list";
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
	
	@GetMapping("/list_ajax")
	public ModelAndView shopListAjax(int page, ModelAndView mv) {
		
		int limit = 9; // 한 페이지에 보여줄 게시글의 수 
		int listCount = shopService.getListCount(); // 총 게시글의 수를 받아옴  
		int maxPage = (listCount + limit -1) / limit;
		int startPage = ((page-1) / 9) * 9 + 1;
		int endPage = startPage + 9 -1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		List<Shop> shopList = shopService.getShopList(page, limit);
				
		mv.setViewName("shop/shop_list_ajax");
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
	
	@GetMapping("/category_list")
	public ModelAndView shopCategoryList(@RequestParam(value="page", defaultValue="1", required=false) int page, ModelAndView mv, String category) {
		
		int limit = 9; // 한 페이지에 보여줄 게시글의 수 
		int listCount = shopService.getCategoryListCount(category); // 총 게시글의 수를 받아옴  
		int maxPage = (listCount + limit -1) / limit;
		int startPage = ((page-1) / 9) * 9 + 1;
		int endPage = startPage + 9 -1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		List<Shop> shopList = shopService.getShopCategoryList(page, limit, category);
				
		mv.setViewName("shop/shop_main");
		mv.addObject("category", category);
		mv.addObject("page", page);
		mv.addObject("maxPage", maxPage);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("listCount", listCount);
		mv.addObject("limit", limit);
		mv.addObject("shopList", shopList);
		return mv;
	}
	
	@GetMapping("/category_list_ajax")
	public ModelAndView shopCategoryListAjax(@RequestParam(value="page", defaultValue="1", required=false) int page, ModelAndView mv, String category) {
		
		page += 1;
		int limit = 9; // 한 페이지에 보여줄 게시글의 수 
		int listCount = shopService.getCategoryListCount(category); // 총 게시글의 수를 받아옴  
		int maxPage = (listCount + limit -1) / limit;
		int startPage = ((page-1) / 9) * 9 + 1;
		int endPage = startPage + 9 -1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		List<Shop> shopList = shopService.getShopCategoryList(page, limit, category);
				
		mv.setViewName("shop/shop_list_ajax");
		mv.addObject("category", category);
		mv.addObject("page", page);
		mv.addObject("maxPage", maxPage);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("listCount", listCount);
		mv.addObject("limit", limit);
		mv.addObject("shopList", shopList);
		return mv;
	}
	
	@GetMapping("/search")
	public ModelAndView searchList(ModelAndView mv, String search_word) {
		int searchListCount = shopService.getSearchListCount(search_word);
		
		List<Shop> shopList = shopService.getSearchList(search_word);
		
		mv.addObject("searchListCount", searchListCount);
		mv.addObject("shopList", shopList);
		mv.addObject("search_word", search_word);
		mv.setViewName("shop/shop_main");
		return mv;
	}
	
	@GetMapping("/delete")
	public String shopDelete(int shop_num) {
		int result = shopService.shopDelete(shop_num);
		return "redirect:list";
	}
	
	@GetMapping("/modifyForm")
	public ModelAndView shopModifyForm(ModelAndView mv, int shop_num) {
		
		Shop shopBoard = shopService.getShopDetail(shop_num);
		mv.addObject("shopBoard", shopBoard);
		mv.setViewName("shop/shop_modify");
		return mv;
	}
	
	@PostMapping("/modify")
	public String shopModify(Shop shop) {
		
		int result = shopService.shopModify(shop);

		return "redirect:list";	
	}

}
