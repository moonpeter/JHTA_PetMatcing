package com.jhta.petMatching.abh.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class LoginHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException exception)throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");
		String url = request.getContextPath() + "/member/login";
		response.sendRedirect(url);
	}
}
