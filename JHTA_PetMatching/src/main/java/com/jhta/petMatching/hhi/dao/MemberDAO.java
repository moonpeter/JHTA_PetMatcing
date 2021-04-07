package com.jhta.petMatching.hhi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.hhi.domain.Member;


@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public Member isId(String id) {
		return sqlSession.selectOne("Members.idcheck", id);
								// namespace.xml id 값, 파라미터 값 		                           
	}

	public int insert(Member m) {
		return sqlSession.insert("Members.insert",m);
	}

	public Member member_info(String id) {
		return sqlSession.selectOne("Members.idcheck",id);
	}

	public int update(Member m) {
		return sqlSession.update("Members.update",m);
	}
	
	public void delete(String id) {
		sqlSession.delete("Members.delete",id);
	}	

	public int getSearchListCount(Map<String, Object> map) {
		return sqlSession.selectOne("Members.searchCount",map);
	}

	public List<Member> getSearchList(Map<String, Object> map) {
		return sqlSession.selectList("Members.getSearchList",map);
	}
	

}
