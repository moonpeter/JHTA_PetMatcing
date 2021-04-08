package com.jhta.petMatching.abh.service;

import com.jhta.petMatching.abh.domain.Member;

public interface MemberService {
		public int isId(String id);	//아이디 중복검사
		public int insert(Member m); //회원가입(DB등록)
		public int isId(String id, String password); //로그인
}
