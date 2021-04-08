package com.jhta.petMatching.abh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.abh.dao.MemberDAO;
import com.jhta.petMatching.abh.domain.Member;

@Service
public class MemberServiceImple implements MemberService{
	
	@Autowired
	private MemberDAO dao;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;

	@Override
	public int isId(String id) {			//회원가입 시 아이디 중복검사
		Member rmember = dao.isId(id);		//-1은 아이디가 존재하지 않음
		return (rmember==null) ? -1 : 1;	//1은 아이디가 존재
	}

	@Override
	public int insert(Member m) {	//회원가입 작성완료
		return dao.insert(m);		//insert 처리문
	}

	@Override
	public int isId(String id, String password) {	//로그인 시도
		Member rmember = dao.isId(id);
		int result = -1;	//아이디가 존재하지 않은 경우 -> rmember가 null인 경우
		if(rmember != null) {//아디디가 존재하는 경우
			if(rmember.getPassword().equals(password)) {
				result = 1;	//아이디와 비밀번호가 일치하는 경우
			} else {
				result = 0;	//아이디는 존재하지만 비밀번호가 일치하지 않는 경우
			}
		}
		return result;
	}
}
