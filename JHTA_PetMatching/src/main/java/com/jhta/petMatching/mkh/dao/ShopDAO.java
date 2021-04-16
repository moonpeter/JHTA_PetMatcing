package com.jhta.petMatching.mkh.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.mkh.domain.Shop;

@Repository
public class ShopDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	public Object shopInsert(Shop shopBoard) {
		return sqlSession.insert("Shops.insert", shopBoard);
	}

	public List<Shop> shopList() {
		return sqlSession.selectList("Shops.mainList");
	}

	public Shop shopDetail(int shop_num) {
		return sqlSession.selectOne("Shops.detail", shop_num);
	}

	public int getListCount() {
		return sqlSession.selectOne("Shops.listCount");
	}

}
