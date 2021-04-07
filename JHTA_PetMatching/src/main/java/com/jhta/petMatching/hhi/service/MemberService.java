package com.jhta.petMatching.hhi.service;

import java.util.List;

import com.jhta.petMatching.hhi.domain.Member;


public interface MemberService {
	
	public int isId(String id, String password);
	public int insert(Member m);
	public int isId(String id);
	public Member member_info(String id);
	public void delete(String id);
	public int update(Member m);
	public int getSearchListCount(String index, String search_word);
	public List<Member> getSearchList(String index, String search_word, int page, int limit);
}
