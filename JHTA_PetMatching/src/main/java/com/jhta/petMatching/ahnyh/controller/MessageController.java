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
	
	
	//������̾�� ������ MessageService�� ��������� �־����.
    //��, MessageServiceImpl�� @Service�ֳ����̼Ǳ��� �Ǿ��־�� ������ �߻�����.
	//Spring_Layered_Architecture.jpg �̹��� ������ ���� �� �� �ֵ���
    //Controller�� Service�� ���ļ� DAO�� ����.
	@Autowired
	private MessageService messageservice;
	
	
	
	@RequestMapping(value ="/send", method=RequestMethod.GET)
	public ModelAndView send(@RequestParam(value="receiver_id", required=false) String receiver_id, // ���� �޽�����, ���� �޽����� ������ �ϴܿ� �ִ� '�޽��� ������ ��ư'�� Ŭ���� ������ receiver_id�� �Ķ���ͷ� �ѱ��� �ʴ´�. �� ��츦 ����Ͽ� required=false�� �� ��.
			                 Principal principal,                                                   // ����, �Ʒ��� �ִ� sendProcess()�޼ҵ忡�� return "redirect:send"�� ��,
			                 ModelAndView mv,                                                       // receiver_id�� �Ķ���ͷ� �ѱ��� �����Ƿ� required=false�� �־ ������ ���ش�.
			                 @RequestParam(value="re_title", required=false) String re_title        // re_title�� ���� �޽����� �󼼺��� ���������� '�亯 �޽��� ������'�� Ŭ������ ��
			                 ) {                                                                    // �亯 ����� �Ǵ� �޽����� ������ ������ ���̴�.
		                                                                                            // �� �̿ܿ��� �Ķ���͸� ������ ���� �����Ƿ� required=false�� �־ ������ ���ش�.
		//�α��� id ��������
		String loginid = principal.getName();
		logger.info("loginid = "+loginid);
		
		mv.addObject("sender_id", loginid);
		mv.addObject("receiver_id", receiver_id);
		mv.addObject("re_title", re_title);
		mv.setViewName("message/send_messageForm");  //WEB-INF/views/message/send_messageForm.jsp
		return mv; 
	}
	
	
	@RequestMapping(value ="/sendProcess", method=RequestMethod.POST)
	public String sendProcess(Message message, Model model, //Message�� command ��ü
			                  RedirectAttributes rattr, HttpServletRequest request) throws Exception{
		
		int result = messageservice.insertMessage(message);
		
		//������ �� ���
		if(result ==1) {
			rattr.addFlashAttribute("result", "sendSuccess");
			return "redirect:send"; // send�� �����̷�Ʈ�Ҷ� @RequestParam("receiver_id")���� ���� �ʾ����Ƿ� required=false�� �־ ������ ���ش�.
		}else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "�޽��� ������ ����");
			return "error/error";
		}
	}
	
	
	
	@ResponseBody 
	@RequestMapping(value ="/newMessage")
	public int getNewMessage(Principal principal) {
		//�α��� id ��������
		String loginid = principal.getName();
		
		int result = messageservice.newMessageCount(loginid);
		return result;
	}

	
	
	//���� �޽��� ����Ʈ
	@RequestMapping(value="/receiveMessageList", method=RequestMethod.GET)
	public ModelAndView receiveList(Principal principal,
			                     @RequestParam(value="page", defaultValue="1") int page,
			                     @RequestParam(value="search_word", defaultValue="") String search_word,
			                     @RequestParam(value="search_field", defaultValue="") String index,
			                     ModelAndView mv) {
		//�α��� id ��������
		String loginid = principal.getName();
		
		int limit = 10; //�� ȭ�鿡 ����� ���ڵ� ����
		
		List<Message> list = null;
		list = messageservice.getSearchReceiveMessage(page, limit, search_word, index, loginid);
		
		int listcount =0;
		listcount = messageservice.getSearchReceiveMessageCount(index, search_word, loginid);
		
		int maxpage = (listcount + limit -1) / limit;
		
		int startpage = ((page-1)/10)*10+1;
		
		//endpage: ���� ������ �׷쿡�� ������ ������ ������ ��( [10],[20],[30] ��... )
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
	
	
	// detail?num= ��û�� �Ķ���� num�� ���� int num�� �����մϴ�.
	// �α��� id�� �����ͼ� sql������ ����Ѵ�. receiver_id�� ���� ��쿡�� �󼼺��� ����.
	// �� �۾��� �����ָ� ��3�ڰ� �Ʒ��� �ּҸ� ġ�� ���� �޽��� ������ ���� ���� �����ϱ� �����̴�.
	// http://localhost:8088/myhome/message/detailReceiveMessage?num=54 
	@GetMapping("/detailReceiveMessage")
	public ModelAndView ReceiveMessageDetail(int num, ModelAndView mv,
			                                 HttpServletRequest request,
			                                 Principal principal) {
		
		//�α��� id ��������
		String loginid = principal.getName();
		
		
		Message m = messageservice.getDetail_receiveMessage(num, loginid);
		//m=null; //�ּ� Ǯ�� error�������� �̵��ϴ� ���� Ȯ���� �� �ִ�.
		if(m == null) {
			logger.info("�󼼺��� ����");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "�󼼺��� �����Դϴ�.");
		}else {
			logger.info("�󼼺��� ����");
			mv.addObject("messagedata", m);
			mv.setViewName("message/receive_messageDetail");
		}
		return mv;
	}
	
	
	//�޽����� ���� ��, receiver_id�� ȸ�����Ե� �������� �ƴ��� Ȯ��
	@ResponseBody 
	@RequestMapping(value ="/idCheck")
	public int idcheck(String receiver_id) {
		int result = messageservice.isId(receiver_id);
		return result;
	}

	
	
	//���� �޽����Կ��� �޽��� ����
	@PostMapping("/deleteByReceiver")
	public String delete_by_receiver(@RequestParam("select_delete") String[] select_delete,
			                         Model model, HttpServletRequest request,
			                         RedirectAttributes rattr) {
		int result = messageservice.deleteByReceiver(select_delete);
		int delete_count = select_delete.length;
		logger.info("���� result ��: "+result);
		
		if(result == -1) {
			rattr.addFlashAttribute("result", "deleteSuccess");
			rattr.addFlashAttribute("delete_count", delete_count);
			return "redirect:/message/receiveMessageList";
		}else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "�޽��� ���� ����");
			return "error/error";
		}
	}
	
	
	
	//���� �޽��� ����Ʈ
	@RequestMapping(value="/sendMessageList", method=RequestMethod.GET)
	public ModelAndView sendList(Principal principal,
			                     @RequestParam(value="page", defaultValue="1") int page,
			                     @RequestParam(value="search_word", defaultValue="") String search_word,
			                     @RequestParam(value="search_field", defaultValue="") String index,
			                     ModelAndView mv) {
		//�α��� id ��������
		String loginid = principal.getName();
		
		int limit = 10; //�� ȭ�鿡 ����� ���ڵ� ����
		
		List<Message> list = null;
		list = messageservice.getSearchSendMessage(page, limit, search_word, index, loginid);
		
		int listcount =0;
		listcount = messageservice.getSearchSendMessageCount(index, search_word, loginid);
		
		int maxpage = (listcount + limit -1) / limit;
		
		int startpage = ((page-1)/10)*10+1;
		
		//endpage: ���� ������ �׷쿡�� ������ ������ ������ ��( [10],[20],[30] ��... )
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
	
	
	
	//���� �޽����Կ��� �޽��� ����
	@PostMapping("/deleteBySender")
	public String delete_by_sender(@RequestParam("select_delete") String[] select_delete,
			                         Model model, HttpServletRequest request,
			                         RedirectAttributes rattr) {
		int result = messageservice.deleteBySender(select_delete);
		int delete_count = select_delete.length;
		logger.info("���� result ��: "+result);
		
		if(result == -1) {
			rattr.addFlashAttribute("result", "deleteSuccess");
			rattr.addFlashAttribute("delete_count", delete_count);
			return "redirect:/message/sendMessageList";
		}else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "�޽��� ���� ����");
			return "error/error";
		}
	}
	
	
	
	
	//detail?num= ��û�� �Ķ���� num�� ���� int num�� �����մϴ�.
	// �α��� id�� �����ͼ� sql������ ����Ѵ�. receiver_id�� ���� ��쿡�� �󼼺��� ����.
	// �� �۾��� �����ָ� ��3�ڰ� �Ʒ��� �ּҸ� ġ�� ���� �޽��� ������ ���� ���� �����ϱ� �����̴�.
	// http://localhost:8088/myhome/message/detailSendMessage?num=54 	
	@GetMapping("/detailSendMessage")
	public ModelAndView SendMessageDetail(int num, ModelAndView mv,
			                              HttpServletRequest request,
			                              Principal principal) {
		
		//�α��� id ��������
		String loginid = principal.getName();
		
		Message m = messageservice.getDetail_sendMessage(num, loginid);
		//m=null; //�ּ� Ǯ�� error�������� �̵��ϴ� ���� Ȯ���� �� �ִ�.
		if(m == null) {
			logger.info("�󼼺��� ����");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "�󼼺��� �����Դϴ�.");
		}else {
			logger.info("�󼼺��� ����");
			mv.addObject("messagedata", m);
			mv.setViewName("message/send_messageDetail");
		}
		return mv;
	}
	
	
	//���� �޽����� - �޽��� �󼼺��⿡�� �޽��� �߼� ���
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
			model.addAttribute("message", "�޽��� �߼� ��� ����");
			return "error/error";
		}
	}
	
}
