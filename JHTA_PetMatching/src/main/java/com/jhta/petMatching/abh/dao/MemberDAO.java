package com.jhta.petMatching.abh.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.abh.domain.Member;


@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public Member isId(String id) {
		return sqlSession.selectOne("Members.idcheck", id);
	}

	public int insert(Member m) {
		return sqlSession.insert("Members.insert", m);
	}
}