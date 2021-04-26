package com.jhta.petMatching.abh.dao;

import org.mybatis.spring.SqlSessionTemplate;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.abh.domain.Destination;
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
	
	public Member member_info(String id) {
		return sqlSession.selectOne("Members.idcheck", id);
	}

	public void delete(String id) {
		sqlSession.delete("Members.delete", id);
	}
	
	public int update(Member m) {
		return sqlSession.update("Members.update", m);
	}

	public int insert(Destination d) {
		return sqlSession.insert("Members.insert2", d);
	}
	
	public Destination desti_info(String id) {
		return sqlSession.selectOne("Members.desticheck", id);
	}

	public int update(Destination d) {
		return sqlSession.update("Members.destiupdate", d);
	}
	
	public void desti_delete(String id) {
		sqlSession.delete("Members.destidelete", id);
	}
}