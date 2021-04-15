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
import com.jhta.petMatching.abh.domain.Member;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class); 
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelAndView mv,
							@CookieValue(value = "remember", required = false) Cookie readCookie,
							Model model, HttpSession session, Principal principal) {
		model.addAttribute("loginFailMsg", session.getAttribute("loginFailMsg"));
		session.removeAttribute("loginFailMsg");
		if (readCookie != null) {
			logger.info("저장된 아이디 = " + principal.getName());
		}
		return "member/member_login";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinPage() {
		return "member/member_join";
	}
	
	@RequestMapping(value = "/joinProcess", method = RequestMethod.POST)
	public String joinProcess(Member member, RedirectAttributes rattr, Model model, HttpServletRequest request)
		throws Exception {
		//비밀번호 암호화
		String encPassword = passwordEncoder.encode(member.getPassword());
		logger.info(encPassword);
		member.setPassword(encPassword);
		
		int result = memberService.insert(member);
		
		//삽입 성공
		if(result == 1) {
			rattr.addFlashAttribute("result", "joinSuccess");
			return "redirect:login";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "회원가입 실패");
			return "error/error";
		}
	}
	
	//@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public String loginProcess(@RequestParam("id") String id, @RequestParam("password") String password,
			@RequestParam(value = "remember", defaultValue = "", required = false) String remember,
			HttpServletResponse response, HttpSession session, RedirectAttributes rattr) {
		int result = memberService.isId(id, password);
		logger.info("결과 값 : " + result);
		
		if (result == 1) { //로그인 성공
			session.setAttribute("id", id);
			Cookie savecookie = new Cookie("saveid", id);
			if (!remember.equals("")) {
				savecookie.setMaxAge(30* 60);
				logger.info("쿠키저장 시간 : 30*60");
			} else {
				savecookie.setMaxAge(0);
				logger.info("쿠키저장 없음");
			}
			response.addCookie(savecookie);
			return "redirect:/shop/main";
		} else {
			rattr.addFlashAttribute("result", result);
			return "redirect:login";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	
	@RequestMapping(value = "/idcheck", method = RequestMethod.GET)
	public void idcheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		int result = memberService.isId(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}