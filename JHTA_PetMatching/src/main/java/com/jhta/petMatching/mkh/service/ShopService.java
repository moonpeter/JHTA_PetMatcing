
package com.jhta.petMatching.mkh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.mkh.dao.ShopDAO;
import com.jhta.petMatching.mkh.domain.ShopBoard;

@Service
public class ShopService {
	
	@Autowired
	ShopDAO dao;

	public void insertShopBoard(ShopBoard shopBoard) {
		dao.insertShopBoard(shopBoard);
	}

}
