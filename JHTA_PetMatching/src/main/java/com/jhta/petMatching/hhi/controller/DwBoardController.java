// 산책 신청게시판(산책러용)
package com.jhta.petMatching.hhi.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jhta.petMatching.hhi.domain.Board;
import com.jhta.petMatching.hhi.service.BoardService;
import com.jhta.petMatching.hhi.service.CommentService;


@Controller
@RequestMapping("/dw_board")
public class DwBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(DwBoardController.class); 
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;
	
	@Value("${savefoldername}")
	private String saveFolder;
	
	// DW_BOARD List 출력
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView dwboardList(@RequestParam(value="page", defaultValue="1", required=false) int page, ModelAndView mv) {
		
		int limit = 10; // 한 화면에 출력할 레코드 갯수
		
		int listcount = boardService.getListCount(); // 총 리스트 수를 받아옴
		
		int maxpage = (listcount + limit - 1) / limit; // 총 페이지 수
		
		int startpage = ((page - 1) / 10) * 10 + 1; // 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21 등...)
		
		int endpage = startpage + 10 - 1; // 현재 페이지에 보여줄 마지막 페이지 수 (10, 20, 30 등...)
		
		if(endpage > maxpage)
			endpage = maxpage;
		 
		List<Board> boardlist = boardService.getBoardList(page, limit); // 리스트를 받아옴
		
		mv.setViewName("dogwalker_board/dogwalkerboard_list"); // setViewName() > 경로이동
		mv.addObject("page", page);			// addObject() > 객체를 만들어 정보 전달
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("boardlist", boardlist);
		mv.addObject("limit", limit);
		
		return mv;
	}
	
   /*
	 	@ResponseBody란?
	 	메서드에 @ResponseBody Annotation이 되어 있으면 return되는 값은 View를 통해서
	 	출력되는 것이 아니라 HTTP Response Body에 직접 쓰여지게 됩니다.
	 	예) HTTP 응답 프로토콜의 구조 HTTP/1.1
	 	
	*	Message Header
	 	200OK => Start Line Content-Type:text/html => Message Header Connection :
	 	close Server : Apache Tomcat... Last-Modified : Mon, ...
	 	
	*	Message Body
	 	<html> <head> <title> Hello JSP </title> </head> <body> Hello JSP! </body> </html> =>
	 	
	* 	응답 결과를 HTML이 아닌 JSON으로 변환하여 Message Body에 저장하려면 스프링에서
	 	제공하는 변환기(Converter)를 사용해야 합니다.
	* 	이 변환기를 이용해서 자바 객체를 다양한 타입으로 변환하여 HTTP Response Body에 설정할 수 있습니다.
	 	스프링 설정 파일에 <mvc:annotation-driven>을 설정하면 변환기가 생성됩니다.
	* 	이 중에서 자바 객체를 JSON 응답 바디로 변환할 때는
	 	MappingJackson2HttpMessageConverter를 사용합니다.
	 	
	* 	@ResponseBody를 이용해서 각 메서드의 실행 결과는 JSON으로 변환되어
	 	HTTP Response BODY에 설정됩니다.
	 	
	*/
	
		@ResponseBody
		@RequestMapping(value = "/list_ajax")
		public Map<String,Object> dwboardListAjax(
				@RequestParam(value="page", defaultValue="1", required=false) int page,
				@RequestParam(value="limit", defaultValue="10", required=false) int limit
				) {
			
			int listcount = boardService.getListCount(); // 총 리스트 수를 받아옴
			
			int maxpage = (listcount + limit - 1) / limit; // 총 페이지 수
			
			int startpage = ((page - 1) / 10) * 10 + 1; // 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21 등...)
			
			int endpage = startpage + 10 - 1; // 현재 페이지에 보여줄 마지막 페이지 수 (10, 20, 30 등...)
			
			if(endpage > maxpage)
				endpage = maxpage;
			 
			List<Board> boardlist = boardService.getBoardList(page, limit); // 리스트를 받아옴
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("page", page);			
			map.put("maxpage", maxpage);
			map.put("startpage", startpage);
			map.put("endpage", endpage);
			map.put("listcount", listcount);
			map.put("boardlist", boardlist);
			map.put("limit", limit);
			
			return map;
		}
	
	
	
	    // detail?num=9 요청 시 파라미터 num의 값을 int num에 저장합니다.
		@GetMapping("/detail")
		public ModelAndView dwDetail(int num, ModelAndView mv, HttpServletRequest request) {
			
			Board board = boardService.getDetail(num);
			// board null;	// error 페이지 이동 확인하고자 임의로 지정합니다.
			if(board == null) {
				logger.info("상세보기 실패");
				mv.setViewName("error/error");
				mv.addObject("url", request.getRequestURL());
				mv.addObject("message", "상세보기 실패입니다.");
			} else {
				logger.info("상세보기 성공");
				int count = commentService.getListCount(num);
				mv.setViewName("dogwalker_board/dogwalkerboard_view");
				mv.addObject("count", count);
				mv.addObject("boarddata", board);
			}
			return mv;
		}
		
		// detail?num=9 요청 시 파라미터 num의 값을 int num에 저장합니다.
		@GetMapping("/replyView")
		public ModelAndView dwreplyView(int num, ModelAndView mv, HttpServletRequest request) {
					
		Board board = boardService.getDetail(num);
		// board null;	// error 페이지 이동 확인하고자 임의로 지정합니다.
		if(board == null) {
				mv.setViewName("error/error");
				mv.addObject("url", request.getRequestURL());
				mv.addObject("message", "게시판 답변글 가져오기 실패입니다.");
		} else {
				mv.addObject("boarddata", board);
				mv.setViewName("dogwalker_board/dogwalkerboard_reply");
			}
		return mv;
		}
		
		@PostMapping("/replyAction")
		public ModelAndView dwBoardModifyAction(Board board, ModelAndView mv, HttpServletRequest request) {
			
			int result = boardService.boardReply(board);
			if(result == 0) {
				mv.setViewName("error/error");
				mv.addObject("url", request.getRequestURL());
				mv.addObject("message","게시판 답변 처리 실패");
			} else {
				mv.setViewName("redirect:dw_board/list");
			}
			return mv;
		}
		
		
	//	private String fileDBName(String)
		
		
	
	
	//글쓰기
	//@RequestMapping(value="/write", method=RequestMethod.GET) > 간단하게 아래 문장으로 변경
	@GetMapping(value="/write")
	public String board_wirte(){
	 		return "dogwalker_board/dogwalkerboard_write";
	}

	
	//수정하기
	//@RequestMapping(value="/write", method=RequestMethod.GET) > 간단하게 아래 문장으로 변경
	@GetMapping("/modifyView")
	public ModelAndView dwBoardModifyView(int num, ModelAndView mv, HttpServletRequest request){
		Board boarddata = boardService.getDetail(num);
		
		// 글 내용 불러오기 실패한 경우입니다.
		if(boarddata == null) {
			logger.info("수정보기 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message","수정보기 실패입니다.");
			return mv;
		}
		logger.info("(수정)상세보기 성공");
		// 수정 폼 페이지로 이동할 때 원문 글 내용을 보여주기 때문에 boarddata 객체를
		// ModelAndView 객체에 저장합니다.
		mv.addObject("boarddata",boarddata);
		// 글 수정 폼 페이지로 이동하기 위해 경로를 설정합니다.
		mv.setViewName("dogwalker_board/dogwalkerboard_modify");
		return mv;
	}	
	
	
	// @RequestMapping(value="/add", method=RequestMethod.POST)
		@PostMapping("/modifyAction")
		public String BoardModifyAction(Board boarddata, String check, Model mv, HttpServletRequest request, RedirectAttributes rattr) throws Exception {
			
			// 추가합니다.
			// input type="hidden" name="BOARD_FILE" value="${boarddata.BOARD_FILE}">
			// boarddata.getBOARD_FILE()는 위 문장의 값을 가져옵니다. 즉, 이전에 선택한 파일 이름입니다.
			String before_file = boarddata.getBOARD_FILE();
			
			boolean usercheck =
					boardService.isBoardWriter(boarddata.getBOARD_NUM(), boarddata.getBOARD_PASS());
			logger.info("usercheck : " + usercheck);
			String url="";
			
			// 비밀번호가 다른 경우
			if(usercheck == false) {
				rattr.addFlashAttribute("result", "passFail");
				rattr.addAttribute("num", boarddata.getBOARD_NUM());
				return "redirect:dw_board/modifyView";
			}
			
			MultipartFile uploadfile = boarddata.getUploadfile();
			// String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/";
			
			if(check != null && !check.equals("")) { // 1. 기존 파일을 그대로 사용하는 경우입니다.
				logger.info("기존 파일을 그대로 사용합니다.");
				boarddata.setBOARD_ORIGINAL(check);
				// <input type = "hidden" name = "BOARD_FILE" value = "${boarddata.BOARD_FILE}">
				// 위 문장 때문에 board.setBOARD_FILE() 값은 자동 저장됩니다.
			} else {
				
				// if(!uploadfile.isEmpty()) { // 2. 파일 변경한 경우
				if(uploadfile!=null && uploadfile.isEmpty()) {
					logger.info("파일 변경되었습니다.");
					// 답변 글을 수정할 경우 <input type="file" id="upfile" name="uploadfile" > 엘리먼트가 존재하지 않아
					// private MultipartFile uploadfile; 에서 uploadfile은 null 입니다.
					
					String fileName = uploadfile.getOriginalFilename(); //원래 파일명
					boarddata.setBOARD_ORIGINAL(fileName);
					
					String fileDBName = fileDBName(fileName, saveFolder);
					
					// transferTO(File path) : 업로드한 파을 매개변수의 경로에 저장합니다.
					uploadfile.transferTo(new File(saveFolder + fileDBName));
					
					// 바뀐 파일명으로 저장 
					boarddata.setBOARD_FILE(fileDBName);
				} else { // uploadfile.isEmpty() 인 경우 - 3. 파일 선택하지 않은 경우
					logger.info("선택 파일 없습니다.");
					// <input type = "hidden" name = "BOARD_FILE" value = "${boarddata.BOARD_FILE}">
					// 위 태그에 파일 값이 있다면 ""로 값을 변경합니다.
					boarddata.setBOARD_FILE(""); //	 ""로 초기화 합니다.
					boarddata.setBOARD_ORIGINAL(""); //	 ""로 초기화 합니다.
				} // else end
			} // else end
			
			// DAO 에서 수정 메서드 호출하여 수정합니다.
			int result = boardService.boardModify(boarddata);
			// 수정에 실패한 경우
			if(result == 0) {
				logger.info("게시판 수정 실패");
				mv.addAttribute("url", request.getRequestURL());
				mv.addAttribute("message","게시판 수정 실패");
				url = "error/error";
			} else { // 수정 성공의 경우
				logger.info("게시판 수정 완료");
				// 수정한 글 내용을 보여주기 위해 글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
				url = "redirect:dw_board/detail";
				rattr.addAttribute("num", boarddata.getBOARD_NUM());
				
				// 파일 삭제를 위해 추가한 부분
				// 수정 전에 파일이 있고 이전 파일명과 현재 파일명이 다른 경우 삭제할 목록을 테이블에 추가합니다.
				// 예) 수정 전 파일명 "a.png", 현재 파일명 ""인 경우 - 이전 파일 삭제
				// 예) 수정 전 파일명 "a.png", 현재 파일명 "b.png"인 경우 - 이전 파일 변경
				if(!before_file.equals("") && !before_file.equals(boarddata.getBOARD_FILE())) {
					boardService.insert_deleteFile(before_file);
				}
			}
			return url;
			
		}
	
	
	
	// @RequestMapping(value="/add", method=RequestMethod.POST)
	@PostMapping("/add")
	public String add(Board board) throws Exception {
		
		MultipartFile uploadfile = board.getUploadfile();
		
		if (!uploadfile.isEmpty()) {
			String fileName = uploadfile.getOriginalFilename(); // 원래 파일 명
			board.setBOARD_ORIGINAL(fileName); // 원래 파일명 저장
			
			// String saveFolder =
			//		request.getSession().getServletContext().getRealPath("resources") + "/upload/";
			
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName = " + fileDBName);
			
			// transferTO(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile.transferTo(new File(saveFolder + fileDBName));
			
			// 바뀐 파일명으로 저장
			board.setBOARD_FILE(fileDBName);
		}
		
		boardService.insertBoard(board); // 저장메서드 호출
		
		return "redirect:dw_board/list";
		
	}
	
	private String fileDBName(String fileName, String saveFolder) {
	
		// 새로운 폴더 이름 : 오늘 년 + 월 + 일
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); // 오늘 년도 구합니다.
		int month = c.get(Calendar.MONTH) + 1; // 오늘 월 구합니다.
		int date = c.get(Calendar.DATE); // 오늘 일 구합니다.
		
		String homedir = saveFolder + year + "-" + month + "-" + date;
		logger.info(homedir);
		File path1 = new File(homedir);
		if(!(path1.exists())) {
			path1.mkdir(); // 새로운 폴더를 생성
		}
		
		// 난수를 구합니다.
		Random r = new Random();
		int random = r.nextInt(1000000000);
		
		/*** 확장자 구하기 시작 ***/
		int index = fileName.lastIndexOf(".");
		// 문자열에서 특정 문자열의 위치 값(index)를 반환한다.
		// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
		// lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
		// (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
		logger.info("index = " + index);
		
		String fileExtension = fileName.substring(index + 1);
		logger.info("fileExtension = " + fileExtension);
		/*** 확장자 구하기 끝 ***/
		
		// 새로운 파일명
		String refileName = "bbs" + year + month + date + random + "." + fileExtension;
		logger.info("refileName = " + refileName);
		
		// 오라클 디비에 저장될 파일명
		String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		logger.info("fileDBName = " + fileDBName);
		return fileDBName;
		
		}	
	
	@PostMapping("/delete")
	public String dwBoardDeleteActioin(String BOARD_PASS, int num, Model mv, RedirectAttributes rattr, HttpServletRequest request) throws Exception {
		// 글 삭제 명령을 요청한 사용자가 글을 작성한 사용자인지 판단하기 위해
		// 입력한 비밀번호와 저장된 비밀번호를 비교하여 일치하면 삭제합니다.
		boolean usercheck = boardService.isBoardWriter(num, BOARD_PASS);
		
		// 비밀번호 일치하지 않는 경우
		if(usercheck == false) {
			rattr.addFlashAttribute("result", "passFail");
			rattr.addAttribute("num", num);
			return "redirect:dw_board/detail";
		}
		
		// 비밀번호 일치하는 경우 삭제처리 합니다.
		int result = boardService.boardDelete(num);
		
		// 삭제 처리 실패한 경우
		if(result == 0) {
			logger.info("게시판 삭제 실패");
			mv.addAttribute("url", request.getRequestURL());
			mv.addAttribute("message","삭제 실패");
			return "error/error";
		}
		
		// 삭제 처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
		logger.info("게시판 삭제 성공");
		rattr.addFlashAttribute("result", "deleteSuccess");
		return "redirect:dw_board/list";
	
	}
	
	@GetMapping(value = "/down", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	
		public ResponseEntity<Resource> dwdownloadFile(
				String original, String filename, HttpServletRequest request) {
		
		// String saveFolder = 
		//		request.getSession().getServletContext().getRealPath("resources") + "/upload/";
		logger.info(saveFolder);
		Resource resource = new FileSystemResource(saveFolder + filename);
		
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		try {
			String downloadName = new String(original.getBytes("UTF-8"), "ISO-8859-1");
			headers.add("Content-Disposition", "attachment;filename=" + downloadName);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
		
}

