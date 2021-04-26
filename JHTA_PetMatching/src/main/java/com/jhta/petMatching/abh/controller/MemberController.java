package com.jhta.petMatching.abh.controller;

import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jhta.petMatching.abh.service.MemberService;
import com.jhta.petMatching.abh.domain.Destination;
import com.jhta.petMatching.abh.domain.Member;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/login", method = RequestMethod.GET) //�α��� ȭ��
	public String loginPage(ModelAndView mv, @CookieValue(value = "remember", required = false) Cookie readCookie,
			Model model, HttpSession session, Principal principal) {
		model.addAttribute("loginFailMsg", session.getAttribute("loginFailMsg"));
		session.removeAttribute("loginFailMsg");
		if (readCookie != null) {
			logger.info("����� ���̵� = " + principal.getName());
		}
		return "member/member_login";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET) //ȸ���� ȭ��
	public String joinPage() {
		return "member/member_join";
	}

	@RequestMapping(value = "/joinProcess", method = RequestMethod.POST) //ȸ���� ��μ���
	public String joinProcess(Member member, RedirectAttributes rattr, Model model, HttpServletRequest request)
			throws Exception {
		// ��й�ȣ ��ȣȭ
		String encPassword = passwordEncoder.encode(member.getPassword());
		logger.info(encPassword);
		member.setPassword(encPassword);

		int result = memberService.insert(member);

		// ���� ����
		if (result == 1) {
			rattr.addFlashAttribute("result", "joinSuccess");
			return "redirect:login";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "ȸ���� ����");
			return "error/error";
		}
	}

	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST}) //�α׾ƿ�
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

	@RequestMapping(value = "/idcheck", method = RequestMethod.GET) //���̵� �ߺ��˻�
	public void idcheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		int result = memberService.isId(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET) //����� ����
	public ModelAndView member_info(@RequestParam("id") String id, ModelAndView mv, HttpServletRequest request) {
		Member m = memberService.member_info(id);
		if (m != null) {
			mv.setViewName("member/member_info");
			mv.addObject("memberinfo", m);
		} else {
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "�ش� ����� ���ϴ�.");
		}
		
		Destination d = memberService.desti_info(id);
		mv.addObject("destiinfo", d);
		
		return mv;
	}

	@RequestMapping(value = "/update") //����� ��� ȭ��
	public ModelAndView member_update(Member member, ModelAndView mv, Principal principal) { 
		String id = (String) principal.getName();
		String password = member.getPassword();
		logger.info(password);
		member.setPassword(password);
		
		if (id == null) {
			mv.setViewName("redirect:login");
		} else {
			Member m = memberService.member_info(principal.getName());
			mv.setViewName("member/member_update");
			mv.addObject("memberinfo", m);
		}
		return mv;
	}

	@RequestMapping(value = "/updateProcess", method = RequestMethod.POST) //����� ��� ��μ���
	public String updateProcess(Member member, Model model, HttpServletRequest request, RedirectAttributes rattr) {
		String encPassword = passwordEncoder.encode(member.getPassword());
		logger.info(encPassword);
		member.setPassword(encPassword);

		int result = memberService.update(member);
		if (result == 1) {
			rattr.addFlashAttribute("result", "updateSuccess");
			return "redirect:/home/main";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "����� �����Ͽ���ϴ�");
			return "error/error";
		}
	}

	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST}) //ȸ��Ż��
	public String member_delete(Principal principal) {
		memberService.delete(principal.getName());
		memberService.desti_delete(principal.getName());
		return "redirect:logout";
	}

	@RequestMapping(value = "/destination") //����� �Է� ȭ��
	public ModelAndView desti_Page(ModelAndView mv, Principal principal) {
		String id = (String) principal.getName();
		if (id == null) {
			mv.setViewName("redirect:login");
		} else {
			Member m = memberService.member_info(principal.getName());
			mv.setViewName("member/member_destination");
			mv.addObject("memberinfo", m);
		}
		return mv;
	}

	@RequestMapping(value = "/desti_Process", method = RequestMethod.POST) //����� �Է� ��μ���
	public String desti_Process(Destination d, RedirectAttributes rattr, Model model, HttpServletRequest request)
			throws Exception {
		int result = memberService.insert(d);

		if (result == 1) {
			rattr.addFlashAttribute("result", "insertSuccess");
			return "redirect:/home/main";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "����� �Է� ����");
			return "error/error";
		}
	}

	@RequestMapping(value = "/desti_info") //����� ��� ȭ��
	public ModelAndView desti_info(@RequestParam(value="id", required=false) String id, ModelAndView mv,
					Principal principal,
					HttpServletRequest request) {
		Destination d = memberService.desti_info(principal.getName());
		logger.info("d�� �� : " + d);
		if (d != null) {
			mv.setViewName("member/member_desti");
			mv.addObject("destiinfo", d);
		} else {
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "�ش� ����� ���ϴ�.");
		}
		return mv;
	}
	
	@RequestMapping(value = "/desti_update") //����� ��� ȭ��
	public ModelAndView destiProcess(ModelAndView mv, Principal principal) {
		String id = (String) principal.getName();
		if (id == null) {
			mv.setViewName("redirect:login");
		} else {
			Destination d = memberService.desti_info(principal.getName());
			mv.setViewName("member/member_destiUpdate");
			mv.addObject("destiinfo", d);
		}
		return mv;
	}
	
	@RequestMapping(value = "/desti_updateProcess", method = RequestMethod.POST) //����� ��� ��μ���
	public String desti_updateProcess(Destination d, Model model, HttpServletRequest request, RedirectAttributes rattr) {
		int result = memberService.update(d);
		
		if (result == 1) {
			rattr.addFlashAttribute("result", "desti_updateSuccess");
			return "redirect:/home/main";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "����� �����Ͽ���ϴ�");
			return "error/error";
		}
	}
}