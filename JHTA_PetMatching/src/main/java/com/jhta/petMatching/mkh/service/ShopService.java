
package com.jhta.petMatching.mkh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.mkh.dao.ShopDAO;
import com.jhta.petMatching.mkh.domain.Shop;

@Service
public class ShopService {
	
	@Autowired
	ShopDAO dao;

	public void shopInsert(Shop shopBoard) {
		dao.shopInsert(shopBoard);
	}

	public List<Shop> getShopList() {
		return dao.shopList();
	}

	public Shop getShopDetail(int shop_num) {
		return dao.shopDetail(shop_num);
	}

	public int getListCount() {
		return dao.getListCount();
	}


}
