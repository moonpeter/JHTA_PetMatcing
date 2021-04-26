package com.jhta.petMatching.ahnyh.controller;

import java.io.File;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jhta.petMatching.ahnyh.domain.ImageBoard;
import com.jhta.petMatching.ahnyh.service.ImageBoardService;



@Controller
@RequestMapping(value="/image_board")
public class ImageBoardController {
	private static final Logger logger = LoggerFactory.getLogger(ImageBoardController.class);              
	
	@Autowired
	private ImageBoardService imageboardservice;
	

	
	
	//이미지 게시판 리스트
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView imageBoardList(@RequestParam(value="page", defaultValue="1") int page,
                                       @RequestParam(value="search_word", defaultValue="") String search_word,
                                       @RequestParam(value="search_field", defaultValue="") String index,
                                       ModelAndView mv) {
		int limit = 10; //한 화면에 출력할 레코드 갯수
		
		List<ImageBoard> list = null;
		list = imageboardservice.getBoardList(page, limit, search_word, index);
		
		int listcount =0;
		listcount = imageboardservice.getListCount(index, search_word);
		
		int maxpage = (listcount + limit -1) / limit;
		
		int startpage = ((page-1)/10)*10+1;
		
		int endpage = startpage + 10 - 1;
		
		
		if(endpage > maxpage) 
			endpage = maxpage;
		
		
		mv.addObject("page", page);
		mv.addObject("limit", limit);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("boardlist", list);
		mv.addObject("search_field", index);
		mv.addObject("search_word", search_word);
		
		
		//추천수 best3 게시글 list 가져오기
		List<ImageBoard> bestlist = null;
		bestlist = imageboardservice.getBestBoardList();
		int bestlistcount = imageboardservice.getBestBoardListCount();
		
		mv.addObject("bestlist", bestlist);
		mv.addObject("bestlistcount", bestlistcount);
		
		mv.setViewName("imageboard/imageboard_list");
		return mv;
	}
	
	//글쓰기 페이지로 이동
	@GetMapping(value="/write")
	public String imageboard_write(Model model, Principal principal) {
		model.addAttribute("id", principal.getName());
		return "imageboard/imageboard_write";
	}
	
	//글쓰기
	@PostMapping("/add")
	public String imageboard_add(ImageBoard imageboard, HttpServletRequest request) throws Exception{
		//업로드 경로
		String saveFolder =request.getSession().getServletContext().getRealPath("resources")+"/imageboard_upload/";
		logger.info("이미지 업로드 실제 경로=> "+saveFolder);
		
		MultipartFile uploadfile1 = imageboard.getUploadfile1();	
		if(!uploadfile1.isEmpty()) {
			String fileName = uploadfile1.getOriginalFilename(); //원래 파일명
			imageboard.setBOARD_ORIGINAL1(fileName); //원래 파일명 저장
			
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("첫번째 이미지 파일 fileDBName = " + fileDBName);
			
			//transferTo(File path): 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile1.transferTo(new File(saveFolder + fileDBName));
			
			//바뀐 파일명으로 저장
			imageboard.setBOARD_FILE1(fileDBName);
		}
		
		MultipartFile uploadfile2 = imageboard.getUploadfile2();
		if(!uploadfile2.isEmpty()) {
			String fileName = uploadfile2.getOriginalFilename(); //원래 파일명
			imageboard.setBOARD_ORIGINAL2(fileName); //원래 파일명 저장
			
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("두번째 이미지 파일 fileDBName = " + fileDBName);
			
			//transferTo(File path): 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile2.transferTo(new File(saveFolder + fileDBName));
			
			//바뀐 파일명으로 저장
			imageboard.setBOARD_FILE2(fileDBName);
		}
		
		MultipartFile uploadfile3 = imageboard.getUploadfile3();
		if(!uploadfile3.isEmpty()) {
			String fileName = uploadfile3.getOriginalFilename(); //원래 파일명
			imageboard.setBOARD_ORIGINAL3(fileName); //원래 파일명 저장
			
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("세번째 이미지 파일 fileDBName = " + fileDBName);
			
			//transferTo(File path): 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile3.transferTo(new File(saveFolder + fileDBName));
			
			//바뀐 파일명으로 저장
			imageboard.setBOARD_FILE3(fileDBName);
		}
		
		MultipartFile uploadfile4 = imageboard.getUploadfile4();
		if(!uploadfile4.isEmpty()) {
			String fileName = uploadfile4.getOriginalFilename(); //원래 파일명
			imageboard.setBOARD_ORIGINAL4(fileName); //원래 파일명 저장
			
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("네번째 이미지 파일 fileDBName = " + fileDBName);
			
			//transferTo(File path): 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile4.transferTo(new File(saveFolder + fileDBName));
			
			//바뀐 파일명으로 저장
			imageboard.setBOARD_FILE4(fileDBName);
		}
		
		imageboardservice.insertBoard(imageboard);
		return "redirect:list";
	}
	
	
	
	private String fileDBName(String fileName, String saveFolder) {
		//새로운 폴더 이름 : 오늘 년+월+일
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); //오늘 년도를 구합니다.
		int month = c.get(Calendar.MONTH); //오늘 월을 구합니다.
		int date = c.get(Calendar.DATE); //오늘 일을 구합니다.
		
		String homedir = saveFolder + year + "-" + month + "-" + date;
		logger.info(homedir);
		File path1 = new File(homedir);
		if(!(path1.exists())) {
			path1.mkdir(); //새로운 폴더를 생성
		}
		
		//난수를 구합니다.
		Random r = new Random();
		int random = r.nextInt(100000000);
		
		/**** 확장자 구하기 시작  ****/
		int index = fileName.lastIndexOf(".");
		//문자열에서 특정 문자열의 위치 값(index)을 반환한다.
		//indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
		//lastindexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
		//(파일명에 점이 여러개 있을 경우, 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
		logger.info("index = " + index);
		
		String fileExtension = fileName.substring(index + 1);
		logger.info("fileExtension = " +fileExtension);
		/**** 확장자 구하기 끝  ****/
		
		//새로운 파일명
		String refileName = "bbs" +year + month + date + random + "." + fileExtension;
		logger.info("refileName = " + refileName);
		
		//오라클 DB에 저장될 파일명
		String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		logger.info("fileDBName = " + fileDBName);
		return fileDBName;
		
	}
	
	
	
	//detail?num= 요청시 파라미터 num의 값을 int num에 저장합니다.
	@GetMapping("/detail")
	public ModelAndView imageboard_detail(int num, ModelAndView mv,
			                              HttpServletRequest request) {
		
		ImageBoard imageboard = imageboardservice.getDetail(num);
		
		if(imageboard == null) {
			logger.info("상세보기 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "상세보기 실패입니다.");
		}else {
			logger.info("상세보기 성공");
			//int count = commentService.getListCount(num);
			mv.setViewName("imageboard/imageboard_detail");
			//mv.addObject("count", count);
			mv.addObject("boarddata", imageboard);
		}
		return mv;
	}
	
	
	
	//detail?num= 요청시 파라미터 num의 값을 int num에 저장합니다. (name=num의 value를 int num에 저장)
	@PostMapping("/delete")
	public String BoardDeleteAction(String BOARD_PASS, int num,
			                      Model mv, RedirectAttributes rattr,
			                      HttpServletRequest request
			                      ) throws Exception{
		//글 삭제 명령을 요청한 사용자가 글을 작성한 사용자인지 판단하기 위해
		//입력한 비밀번호와 저장된 비밀번호를 비교하여 일치하면 삭제합니다.
		boolean usercheck = imageboardservice.isBoardWriter(num, BOARD_PASS);
		
		//비밀번호가 일치하지 않는 경우
		if(usercheck == false) {
			rattr.addFlashAttribute("result", "passFail");
			rattr.addAttribute("num", num); //리다이렉트할때 필요한 쿼리스트링의 'num=게시글 번호'이다.
			return "redirect:detail";
		}
		
		//비밀번호가 일치하는 경우 삭제처리 합니다.
		int result = imageboardservice.boardDelete(num);
		
		//삭제 처리 실패한 경우
		if (result == 0) {
			logger.info("게시판 삭제 실패");
			mv.addAttribute("url", request.getRequestURL());
			mv.addAttribute("message", "삭제 실패");
			return "error/error";
		}
			
		//삭제 처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
		logger.info("게시판 삭제 성공");
		rattr.addFlashAttribute("result", "deleteSuccess");
		rattr.addFlashAttribute("num", num); 
		return "redirect:list";
	}
	
	
	//수정 페이지로 이동
	@PostMapping("/modifyView")
	public ModelAndView imageboardModifyView(@RequestParam(value="modify_num") int num,
			                                 ModelAndView mv,
			                                 HttpServletRequest request) {
		ImageBoard imageboard = imageboardservice.getDetail(num);
		
		//글 내용 불러오기 실패한 경우입니다.
		if(imageboard == null) {
			logger.info("수정보기 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "수정보기 실패입니다.");
			return mv;
		}
		logger.info("(수정)상세보기 성공");
		mv.addObject("boarddata", imageboard);
		mv.setViewName("imageboard/imageboard_modify");
		return mv;
	}
	
	
	@PostMapping("/modifyProcess")
	public String imageboardModifyProcess(ImageBoard boarddata,
			                              Model mv, HttpServletRequest request,
			                              RedirectAttributes rattr,
			                              String check1, String check2, String check3, String check4) throws Exception{
		//업로드 경로
		String saveFolder =request.getSession().getServletContext().getRealPath("resources")+"/imageboard_upload/";
		
		//수정 페이지에서 맨 처음에 보여주는 변경 전 이미지 파일
		String before_file1=boarddata.getBOARD_FILE1();
		String before_file2=boarddata.getBOARD_FILE2();
		String before_file3=boarddata.getBOARD_FILE3();
		String before_file4=boarddata.getBOARD_FILE4();
		
		
		boolean usercheck = imageboardservice.isBoardWriter(boarddata.getBOARD_NUM(), boarddata.getBOARD_PASS());
		//비밀번호가 일치하지 않는 경우
		if(usercheck == false) {
			rattr.addFlashAttribute("result", "passFail");
			rattr.addAttribute("num", boarddata.getBOARD_NUM()); //리다이렉트할때 필요한 쿼리스트링의 'num=게시글 번호'이다.
			return "redirect:detail";
		}
		
		MultipartFile uploadfile1 = boarddata.getUploadfile1();
		if(check1 != null && !check1.equals("")) {
			logger.info("첫번째 이미지 파일은 기존 파일을 그대로 사용합니다.");
			boarddata.setBOARD_ORIGINAL1(check1);
			// 원래는 boarddata.setBOARD_FILE1();도 작성해야 하는데 command 객체가 자동으로 setter에 저장해주므로 작성하지 않은 것.
		}else {
			if(!uploadfile1.isEmpty()) { //업로드 파일을 변경한 경우
				logger.info("첫번째 이미지 파일이 변경되었습니다.");
				String fileName = uploadfile1.getOriginalFilename(); //원래 파일명
				boarddata.setBOARD_ORIGINAL1(fileName); //원래 파일명 저장
				
				String fileDBName = fileDBName(fileName, saveFolder);
				logger.info("첫번째 이미지 파일 fileDBName = " + fileDBName);
				
				//transferTo(File path): 업로드한 파일을 매개변수의 경로에 저장합니다.
				uploadfile1.transferTo(new File(saveFolder + fileDBName));
				
				//바뀐 파일명으로 저장
				boarddata.setBOARD_FILE1(fileDBName);
			}else {
				logger.info("첫번째 이미지-선택 파일이 없습니다.");
				//<input type="hidden" name="BOARD_FILE1" value="${boarddata.BOARD_FILE1}">
				//위 태그에 값이 있다면 ""로 값을 변경합니다. 
				boarddata.setBOARD_FILE1(""); //""로 초기화합니다.
				boarddata.setBOARD_ORIGINAL1(""); //""로 초기화합니다.
			}
		}
		
		MultipartFile uploadfile2 = boarddata.getUploadfile2();
		if(check2 != null && !check2.equals("")) {
			logger.info("두번째 이미지 파일은 기존 파일을 그대로 사용합니다.");
			boarddata.setBOARD_ORIGINAL2(check2);
		}else {
			if(!uploadfile2.isEmpty()) { //업로드 파일을 변경한 경우
				logger.info("두번째 이미지 파일이 변경되었습니다.");
				String fileName = uploadfile2.getOriginalFilename(); //원래 파일명
				boarddata.setBOARD_ORIGINAL2(fileName); //원래 파일명 저장
				
				String fileDBName = fileDBName(fileName, saveFolder);
				logger.info("두번째 이미지 파일 fileDBName = " + fileDBName);
				
				//transferTo(File path): 업로드한 파일을 매개변수의 경로에 저장합니다.
				uploadfile2.transferTo(new File(saveFolder + fileDBName));
				
				//바뀐 파일명으로 저장
				boarddata.setBOARD_FILE2(fileDBName);
			}else {
				logger.info("두번째 이미지-선택 파일이 없습니다.");
				boarddata.setBOARD_FILE2(""); //""로 초기화합니다.
				boarddata.setBOARD_ORIGINAL2(""); //""로 초기화합니다.
			}
		}
		
		MultipartFile uploadfile3 = boarddata.getUploadfile3();
		if(check3 != null && !check3.equals("")) {
			logger.info("세번째 이미지 파일은 기존 파일을 그대로 사용합니다.");
			boarddata.setBOARD_ORIGINAL3(check3);
		}else {
			if(!uploadfile3.isEmpty()) { //업로드 파일을 변경한 경우
				logger.info("세번째 이미지 파일이 변경되었습니다.");
				String fileName = uploadfile3.getOriginalFilename(); //원래 파일명
				boarddata.setBOARD_ORIGINAL3(fileName); //원래 파일명 저장
				
				String fileDBName = fileDBName(fileName, saveFolder);
				logger.info("세번째 이미지 파일 fileDBName = " + fileDBName);
				
				//transferTo(File path): 업로드한 파일을 매개변수의 경로에 저장합니다.
				uploadfile3.transferTo(new File(saveFolder + fileDBName));
				
				//바뀐 파일명으로 저장
				boarddata.setBOARD_FILE3(fileDBName);
			}else {
				logger.info("세번째 이미지-선택 파일이 없습니다.");
				boarddata.setBOARD_FILE3(""); //""로 초기화합니다.
				boarddata.setBOARD_ORIGINAL3(""); //""로 초기화합니다.
			}
		}
		
		MultipartFile uploadfile4 = boarddata.getUploadfile4();
		if(check4 != null && !check4.equals("")) {
			logger.info("네번째 이미지 파일은 기존 파일을 그대로 사용합니다.");
			boarddata.setBOARD_ORIGINAL4(check4);
		}else {
			if(!uploadfile4.isEmpty()) { //업로드 파일을 변경한 경우
				logger.info("세번째 이미지 파일이 변경되었습니다.");
				String fileName = uploadfile4.getOriginalFilename(); //원래 파일명
				boarddata.setBOARD_ORIGINAL4(fileName); //원래 파일명 저장
				
				String fileDBName = fileDBName(fileName, saveFolder);
				logger.info("네번째 이미지 파일 fileDBName = " + fileDBName);
				
				//transferTo(File path): 업로드한 파일을 매개변수의 경로에 저장합니다.
				uploadfile4.transferTo(new File(saveFolder + fileDBName));
				
				//바뀐 파일명으로 저장
				boarddata.setBOARD_FILE4(fileDBName);
			}else {
				logger.info("네번째 이미지-선택 파일이 없습니다.");
				boarddata.setBOARD_FILE4(""); //""로 초기화합니다.
				boarddata.setBOARD_ORIGINAL4(""); //""로 초기화합니다.
			}
		}
		
		// DAO에서 수정 메소드를 호출하여 수정합니다.
		int result = imageboardservice.boardModify(boarddata);
		//수정에 실패한 경우
		if(result ==0) {
			logger.info("게시판 수정 실패.");
			mv.addAttribute("url", request.getRequestURL());
			mv.addAttribute("message", "게시판 수정 실패");
			return "error/error";
		}else { //수정 성공한 경우
			logger.info("게시판 수정 완료.");
			
			//파일 삭제를 위해  추가한 부분
			//수정 전에 파일이 있고 이전 파일명과 현재 파일명이 다른 경우 삭제할 목록을 테이블에 추가합니다.
			if(!before_file1.equals("") && !before_file1.equals(boarddata.getBOARD_FILE1())) {
				imageboardservice.insert_deleteFile(before_file1);
			}
			if(!before_file2.equals("") && !before_file2.equals(boarddata.getBOARD_FILE2())) {
				imageboardservice.insert_deleteFile(before_file2);
			}
			if(!before_file3.equals("") && !before_file3.equals(boarddata.getBOARD_FILE3())) {
				imageboardservice.insert_deleteFile(before_file3);
			}
			if(!before_file4.equals("") && !before_file4.equals(boarddata.getBOARD_FILE4())) {
				imageboardservice.insert_deleteFile(before_file4);
			}
			
			//수정한 글 내용을 보여주기 위해 글 내용 보기 보기 페이지로 이동하기 위해 경로를 설정합니다.
			rattr.addAttribute("num", boarddata.getBOARD_NUM());
			return "redirect:detail";
		}
		
	}
	
	
	//추천 버튼 눌렀을 때
	@GetMapping("/recommend")
	public String recommend(int num, Principal principal,
			                RedirectAttributes rattr) throws Exception{
		//로그인 id 가져오기
		String loginid = principal.getName();
		logger.info("==================== loginid ================>"+loginid);
		
		//게시글을 추천한 유저 목록 가져오기. NullPointerException이 발생하지 않도록 칼럼의 default값을 ","로 주었음.
		String recommend_user_list = imageboardservice.getRecommend_User_List(num);
		logger.info("==================== recommend_user_list ================>"+recommend_user_list);
		
		//추천한 유저가 목록에 있는지 체크. 
		String check = imageboardservice.recommendCheck(recommend_user_list, loginid);
		logger.info("======================추천id check===================> "+check);
		
		
		//위에서 추천한 유저가 목록에 있는지 체크 후, 없으면 추천 유저 목록에 삽입하고 추천수+1 증가, 있으면 추천수 증가 없고 경고창에 사용할 FlashAttribute을 작성
		if(check=="not_exist") {
			imageboardservice.insertRecommend_User_List(num, loginid+",");
			imageboardservice.updateRecommend_Count(num);
		}else if(check=="exist") {
			rattr.addFlashAttribute("result", "Duplication");
		}
		
		//추천수 갱신된 디테일 페이지로 이동하는 경로 설정.
		rattr.addAttribute("num", num);
		return "redirect:detail";
	}
	
	
	
}
