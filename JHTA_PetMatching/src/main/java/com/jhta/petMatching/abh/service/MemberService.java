package com.jhta.petMatching.abh.service;

import com.jhta.petMatching.abh.domain.Destination;
import com.jhta.petMatching.abh.domain.Member;

public interface MemberService {
		public int isId(String id);	//아이디 중복검사
		public int insert(Member m); //회원가입(DB등록)
		public int isId(String id, String password); //로그인
		public Member member_info(String id); //회원 정보
		public void delete(String id); //회원탈퇴
		public int update(Member m); //정보수정
		public int insert(Destination d);
}
