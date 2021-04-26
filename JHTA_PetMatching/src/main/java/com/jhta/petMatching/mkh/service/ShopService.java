
package com.jhta.petMatching.mkh.service;

import java.util.HashMap;
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

	public List<Shop> getShopList(int page, int limit) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int startRow = (page - 1) * limit + 1;
		int endRow = startRow + limit -1;
		map.put("start", startRow);
		map.put("end", endRow);
		return dao.shopList(map);
	}

	public Shop getShopDetail(int shop_num) {
		return dao.shopDetail(shop_num);
	}

	public int getListCount() {
		return dao.getListCount();
	}

	public List<Shop> getShopCategoryList(int page, int limit, String category) {
		HashMap<String, String> map = new HashMap<String, String>();
		int startRow = (page - 1) * limit + 1;
		int endRow = startRow + limit -1;
		map.put("start", String.valueOf(startRow));
		map.put("end", String.valueOf(endRow));
		map.put("category", category);
		return dao.shopCategoryList(map);
	}

	public int getCategoryListCount(String category) {
		return dao.getCategoryListCount(category);
	}

	public int getSearchListCount(String search_word) {
		return dao.getSearchListCount(search_word);
	}

	public List<Shop> getSearchList(String search_word) {
		return dao.getSearchList(search_word);
	}

	public int shopDelete(int shop_num) {
		return dao.shopDelete(shop_num);
	}


}
