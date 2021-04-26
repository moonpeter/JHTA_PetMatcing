package com.jhta.petMatching.abh.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

@Service
public class UserAccessDeniedHandler implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.error("AccessDeniedHandler : ����");
		
		String url = "/WEB-INF/views/error/error.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		request.setAttribute("key", "���� ������ �����ϴ�");
		dispatcher.forward(request, response);
	}
}