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

	@RequestMapping(value = "/login", method = RequestMethod.GET) //로그인 화면
	public String loginPage(ModelAndView mv, @CookieValue(value = "remember", required = false) Cookie readCookie,
			Model model, HttpSession session, Principal principal) {
		model.addAttribute("loginFailMsg", session.getAttribute("loginFailMsg"));
		session.removeAttribute("loginFailMsg");
		if (readCookie != null) {
			logger.info("저장된 아이디 = " + principal.getName());
		}
		return "member/member_login";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET) //회원가입 화면
	public String joinPage() {
		return "member/member_join";
	}

	@RequestMapping(value = "/joinProcess", method = RequestMethod.POST) //회원가입 프로세스
	public String joinProcess(Member member, RedirectAttributes rattr, Model model, HttpServletRequest request)
			throws Exception {
		// 비밀번호 암호화
		String encPassword = passwordEncoder.encode(member.getPassword());
		logger.info(encPassword);
		member.setPassword(encPassword);

		int result = memberService.insert(member);

		// 삽입 성공
		if (result == 1) {
			rattr.addFlashAttribute("result", "joinSuccess");
			return "redirect:login";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "회원가입 실패");
			return "error/error";
		}
	}

	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST}) //로그아웃
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

	@RequestMapping(value = "/idcheck", method = RequestMethod.GET) //아이디 중복검사
	public void idcheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		int result = memberService.isId(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET) //내정보 보기
	public ModelAndView member_info(@RequestParam("id") String id, ModelAndView mv, HttpServletRequest request) {
		Member m = memberService.member_info(id);
		if (m != null) {
			mv.setViewName("member/member_info");
			mv.addObject("memberinfo", m);
		} else {
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "해당 정보가 없습니다.");
		}
		
		Destination d = memberService.desti_info(id);
		mv.addObject("destiinfo", d);
		
		return mv;
	}

	@RequestMapping(value = "/update") //내정보 수정 화면
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

	@RequestMapping(value = "/updateProcess", method = RequestMethod.POST) //내정보 수정 프로세스
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
			model.addAttribute("message", "수정에 실패하였습니다");
			return "error/error";
		}
	}

	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST}) //회원탈퇴
	public String member_delete(Principal principal) {
		memberService.delete(principal.getName());
		memberService.desti_delete(principal.getName());
		return "redirect:logout";
	}

	@RequestMapping(value = "/destination") //배송지 입력 화면
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

	@RequestMapping(value = "/desti_Process", method = RequestMethod.POST) //배송지 입력 프로세스
	public String desti_Process(Destination d, RedirectAttributes rattr, Model model, HttpServletRequest request)
			throws Exception {
		int result = memberService.insert(d);

		if (result == 1) {
			rattr.addFlashAttribute("result", "insertSuccess");
			return "redirect:/home/main";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "배송지 입력 실패");
			return "error/error";
		}
	}

	@RequestMapping(value = "/desti_info") //배송지 정보 화면
	public ModelAndView desti_info(@RequestParam(value="id", required=false) String id, ModelAndView mv,
					Principal principal,
					HttpServletRequest request) {
		Destination d = memberService.desti_info(principal.getName());
		logger.info("d의 값 : " + d);
		if (d != null) {
			mv.setViewName("member/member_desti");
			mv.addObject("destiinfo", d);
		} else {
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "해당 정보가 없습니다.");
		}
		return mv;
	}
	
	@RequestMapping(value = "/desti_update") //배송지 수정 화면
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
	
	@RequestMapping(value = "/desti_updateProcess", method = RequestMethod.POST) //배송지 수정 프로세스
	public String desti_updateProcess(Destination d, Model model, HttpServletRequest request, RedirectAttributes rattr) {
		int result = memberService.update(d);
		
		if (result == 1) {
			rattr.addFlashAttribute("result", "desti_updateSuccess");
			return "redirect:/home/main";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "수정에 실패하였습니다");
			return "error/error";
		}
	}
}