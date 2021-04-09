package com.jhta.petMatching.mkh.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.mkh.domain.ShopBoard;

@Repository
public class ShopDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	public Object insertShopBoard(ShopBoard shopBoard) {
		return sqlSession.insert("ShopBoards.insert", shopBoard);
	}

}
