package com.jhta.petMatching.ahnyh.controller;




import java.security.Principal;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jhta.petMatching.ahnyh.domain.Message;
import com.jhta.petMatching.ahnyh.service.MessageService;




@Controller
@RequestMapping(value="/message")
public class MessageController {
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	
	//오토와이어드 했으면 MessageService가 만들어져야 있어야함.
    //즉, MessageServiceImpl에 @Service애노테이션까지 되어있어야 오류가 발생안함.
	//Spring_Layered_Architecture.jpg 이미지 파일을 보면 알 수 있듯이
    //Controller가 Service를 거쳐서 DAO로 간다.
	@Autowired
	private MessageService messageservice;
	
	
	
	@RequestMapping(value ="/send", method=RequestMethod.GET)
	public ModelAndView send(@RequestParam(value="receiver_id", required=false) String receiver_id, // 받은 메시지함, 보낸 메시지함 페이지 하단에 있는 '메시지 보내기 버튼'을 클릭할 때에는 receiver_id를 파라미터로 넘기지 않는다. 그 경우를 고려하여 required=false를 준 것.
			                 Principal principal,                                                   // 또한, 아래에 있는 sendProcess()메소드에서 return "redirect:send"할 때,
			                 ModelAndView mv,                                                       // receiver_id를 파라미터로 넘기지 않으므로 required=false를 주어서 오류를 없앤다.
			                 @RequestParam(value="re_title", required=false) String re_title        // re_title은 받은 메시지함 상세보기 페이지에서 '답변 메시지 보내기'를 클릭했을 때
			                 ) {                                                                    // 답변 대상이 되는 메시지의 제목을 가져온 것이다.
		                                                                                            // 그 이외에는 파라미터를 가져올 일이 없으므로 required=false를 주어서 오류를 없앤다.
		//로그인 id 가져오기
		String loginid = principal.getName();
		logger.info("loginid = "+loginid);
		
		mv.addObject("sender_id", loginid);
		mv.addObject("receiver_id", receiver_id);
		mv.addObject("re_title", re_title);
		mv.setViewName("message/send_messageForm");  //WEB-INF/views/message/send_messageForm.jsp
		return mv; 
	}
	
	
	@RequestMapping(value ="/sendProcess", method=RequestMethod.POST)
	public String sendProcess(Message message, Model model, //Message는 command 객체
			                  RedirectAttributes rattr, HttpServletRequest request) throws Exception{
		
		int result = messageservice.insertMessage(message);
		
		//삽입이 된 경우
		if(result ==1) {
			rattr.addFlashAttribute("result", "sendSuccess");
			return "redirect:send"; // send로 리다이렉트할때 @RequestParam("receiver_id")값을 주지 않았으므로 required=false를 주어서 오류를 없앤다.
		}else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "메시지 보내기 실패");
			return "error/error";
		}
	}
	
	
	
	@ResponseBody 
	@RequestMapping(value ="/newMessage")
	public int getNewMessage(Principal principal) {
		//로그인 id 가져오기
		String loginid = principal.getName();
		
		int result = messageservice.newMessageCount(loginid);
		return result;
	}

	
	
	//받은 메시지 리스트
	@RequestMapping(value="/receiveMessageList", method=RequestMethod.GET)
	public ModelAndView receiveList(Principal principal,
			                     @RequestParam(value="page", defaultValue="1") int page,
			                     @RequestParam(value="search_word", defaultValue="") String search_word,
			                     @RequestParam(value="search_field", defaultValue="") String index,
			                     ModelAndView mv) {
		//로그인 id 가져오기
		String loginid = principal.getName();
		
		int limit = 10; //한 화면에 출력할 레코드 갯수
		
		List<Message> list = null;
		list = messageservice.getSearchReceiveMessage(page, limit, search_word, index, loginid);
		
		int listcount =0;
		listcount = messageservice.getSearchReceiveMessageCount(index, search_word, loginid);
		
		int maxpage = (listcount + limit -1) / limit;
		
		int startpage = ((page-1)/10)*10+1;
		
		//endpage: 현재 페이지 그룹에서 보여줄 마지막 페이지 수( [10],[20],[30] 등... )
		int endpage = startpage + 10 - 1;
		
		
		if(endpage > maxpage) 
			endpage = maxpage;
		
		mv.setViewName("message/receive_messageList");
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("receiveMessageCount", listcount);
		mv.addObject("receiveMessageList", list);
		mv.addObject("search_field", index);
		mv.addObject("search_word", search_word);
		return mv;
	}
	
	
	// detail?num= 요청시 파라미터 num의 값을 int num에 저장합니다.
	// 로그인 id를 가져와서 sql문에서 사용한다. receiver_id와 같은 경우에만 상세보기 성공.
	// 이 작업을 안해주면 제3자가 아래의 주소를 치고 들어가서 메시지 내용을 보는 것이 가능하기 때문이다.
	// http://localhost:8088/myhome/message/detailReceiveMessage?num=54 
	@GetMapping("/detailReceiveMessage")
	public ModelAndView ReceiveMessageDetail(int num, ModelAndView mv,
			                                 HttpServletRequest request,
			                                 Principal principal) {
		
		//로그인 id 가져오기
		String loginid = principal.getName();
		
		
		Message m = messageservice.getDetail_receiveMessage(num, loginid);
		//m=null; //주석 풀면 error페이지로 이동하는 것을 확인할 수 있다.
		if(m == null) {
			logger.info("상세보기 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "상세보기 실패입니다.");
		}else {
			logger.info("상세보기 성공");
			mv.addObject("messagedata", m);
			mv.setViewName("message/receive_messageDetail");
		}
		return mv;
	}
	
	
	//메시지를 보낼 때, receiver_id가 회원가입된 상태인지 아닌지 확인
	@ResponseBody 
	@RequestMapping(value ="/idCheck")
	public int idcheck(String receiver_id) {
		int result = messageservice.isId(receiver_id);
		return result;
	}

	
	
	//받은 메시지함에서 메시지 삭제
	@PostMapping("/deleteByReceiver")
	public String delete_by_receiver(@RequestParam("select_delete") String[] select_delete,
			                         Model model, HttpServletRequest request,
			                         RedirectAttributes rattr) {
		int result = messageservice.deleteByReceiver(select_delete);
		int delete_count = select_delete.length;
		logger.info("삭제 result 값: "+result);
		
		if(result == -1) {
			rattr.addFlashAttribute("result", "deleteSuccess");
			rattr.addFlashAttribute("delete_count", delete_count);
			return "redirect:/message/receiveMessageList";
		}else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "메시지 삭제 실패");
			return "error/error";
		}
	}
	
	
	
	//보낸 메시지 리스트
	@RequestMapping(value="/sendMessageList", method=RequestMethod.GET)
	public ModelAndView sendList(Principal principal,
			                     @RequestParam(value="page", defaultValue="1") int page,
			                     @RequestParam(value="search_word", defaultValue="") String search_word,
			                     @RequestParam(value="search_field", defaultValue="") String index,
			                     ModelAndView mv) {
		//로그인 id 가져오기
		String loginid = principal.getName();
		
		int limit = 10; //한 화면에 출력할 레코드 갯수
		
		List<Message> list = null;
		list = messageservice.getSearchSendMessage(page, limit, search_word, index, loginid);
		
		int listcount =0;
		listcount = messageservice.getSearchSendMessageCount(index, search_word, loginid);
		
		int maxpage = (listcount + limit -1) / limit;
		
		int startpage = ((page-1)/10)*10+1;
		
		//endpage: 현재 페이지 그룹에서 보여줄 마지막 페이지 수( [10],[20],[30] 등... )
		int endpage = startpage + 10 - 1;
		
		
		if(endpage > maxpage) 
			endpage = maxpage;
		
		mv.setViewName("message/send_messageList");
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("sendMessageCount", listcount);
		mv.addObject("sendMessageList", list);
		mv.addObject("search_field", index);
		mv.addObject("search_word", search_word);
		return mv;
	}
	
	
	
	//보낸 메시지함에서 메시지 삭제
	@PostMapping("/deleteBySender")
	public String delete_by_sender(@RequestParam("select_delete") String[] select_delete,
			                         Model model, HttpServletRequest request,
			                         RedirectAttributes rattr) {
		int result = messageservice.deleteBySender(select_delete);
		int delete_count = select_delete.length;
		logger.info("삭제 result 값: "+result);
		
		if(result == -1) {
			rattr.addFlashAttribute("result", "deleteSuccess");
			rattr.addFlashAttribute("delete_count", delete_count);
			return "redirect:/message/sendMessageList";
		}else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "메시지 삭제 실패");
			return "error/error";
		}
	}
	
	
	
	
	//detail?num= 요청시 파라미터 num의 값을 int num에 저장합니다.
	// 로그인 id를 가져와서 sql문에서 사용한다. receiver_id와 같은 경우에만 상세보기 성공.
	// 이 작업을 안해주면 제3자가 아래의 주소를 치고 들어가서 메시지 내용을 보는 것이 가능하기 때문이다.
	// http://localhost:8088/myhome/message/detailSendMessage?num=54 	
	@GetMapping("/detailSendMessage")
	public ModelAndView SendMessageDetail(int num, ModelAndView mv,
			                              HttpServletRequest request,
			                              Principal principal) {
		
		//로그인 id 가져오기
		String loginid = principal.getName();
		
		Message m = messageservice.getDetail_sendMessage(num, loginid);
		//m=null; //주석 풀면 error페이지로 이동하는 것을 확인할 수 있다.
		if(m == null) {
			logger.info("상세보기 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "상세보기 실패입니다.");
		}else {
			logger.info("상세보기 성공");
			mv.addObject("messagedata", m);
			mv.setViewName("message/send_messageDetail");
		}
		return mv;
	}
	
	
	//보낸 메시지함 - 메시지 상세보기에서 메시지 발송 취소
	@PostMapping("/cancelBySender")
	public String cancel_by_sender(@RequestParam("cancel_num") int cancel_num,
            						Model model, HttpServletRequest request,
            						RedirectAttributes rattr) {
		int result = messageservice.cancelBySender(cancel_num);

		if(result == 1) {
			rattr.addFlashAttribute("result", "cancelSuccess");
			return "redirect:/message/sendMessageList";
		}else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "메시지 발송 취소 실패");
			return "error/error";
		}
	}
	
}
