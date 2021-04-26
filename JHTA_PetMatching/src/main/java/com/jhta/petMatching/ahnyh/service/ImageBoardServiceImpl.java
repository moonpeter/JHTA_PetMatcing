package com.jhta.petMatching.ahnyh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.ahnyh.dao.ImageBoardDAO;
import com.jhta.petMatching.ahnyh.domain.ImageBoard;




@Service
public class ImageBoardServiceImpl implements ImageBoardService{
	private static final Logger logger = LoggerFactory.getLogger(ImageBoardServiceImpl.class);
	
	@Autowired
	private ImageBoardDAO dao;
	
	
	
	@Override
	public int getListCount(String index, String search_word) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!index.equals("")) {
			String[] search_field = index.split("");
			for (String sf : search_field) {
				logger.info(sf);
			}
			map.put("search_field", search_field);
			map.put("search_word", "%"+search_word+"%");
		}
		return dao.getListCount(map);
	}

	@Override
	public List<ImageBoard> getBoardList(int page, int limit, String search_word, String index) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!index.equals("")) {
			String[] search_field = index.split(""); //ºó ¹®ÀÚ¿­·Î ³ª´©¸é ÇÑ±ÛÀÚ¾¿ ³ª´²Áü ¿¹)"ABC"´Â "A", "B", "C"·Î ³ª´¸
			logger.info("search_field: " + search_field.length);
			for (String sf : search_field) {
				logger.info(sf);
			}
			map.put("search_field", search_field);
			map.put("search_word", "%"+search_word+"%");
		}
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getBoardList(map);
	}

	@Override
	public void insertBoard(ImageBoard imageboard) {
		dao.insertBoard(imageboard);	
	}

	@Override
	public ImageBoard getDetail(int num) {
		if(setReadCountUpdate(num)!=1) {
			return null;		
		}
		return dao.getDetail(num);
	}

	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}

	@Override
	public boolean isBoardWriter(int num, String pass) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		ImageBoard result = dao.isBoardWriter(map);
		if(result==null) {
			return false;
		}else {
			return true;	
		}
	}

	@Override
	public int boardDelete(int num) {
		int result=0;
		ImageBoard imageboard =dao.getDetail(num);
		if(imageboard!=null) {
			if(imageboard.getBOARD_FILE1()!=null) {
				dao.insert_deleteFile(imageboard.getBOARD_FILE1());
			}
			if(imageboard.getBOARD_FILE2()!=null) {
				dao.insert_deleteFile(imageboard.getBOARD_FILE2());
			}
			if(imageboard.getBOARD_FILE3()!=null) {
				dao.insert_deleteFile(imageboard.getBOARD_FILE3());
			}
			if(imageboard.getBOARD_FILE4()!=null) {
				dao.insert_deleteFile(imageboard.getBOARD_FILE4());
			}
			result=dao.boardDelete(num);
		}
		return result;
	}

	@Override
	public int insert_deleteFile(String before_file) {
		return dao.insert_deleteFile(before_file);
	}

	@Override
	public List<String> getDeleteFileList() {
		return dao.getDeleteFileList();
	}

	@Override
	public int boardModify(ImageBoard imageboard) {
		return dao.boardModify(imageboard);
	}

	@Override
	public void reply_countUpdate(int board_num, int reply_listcount) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_num", board_num);
		map.put("reply_listcount", reply_listcount);
		
		dao.reply_countUpdate(map);
	}

	@Override
	public String getRecommend_User_List(int num) {
		return dao.getRecommend_User_List(num);
	}

	@Override
	public void insertRecommend_User_List(int board_num, String loginid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_num", board_num);
		map.put("loginid", loginid);
		
		dao.insertRecommend_User_List(map);
		
	}

	@Override
	public void updateRecommend_Count(int board_num) {
		dao.updateRecommend_Count(board_num);
	}

	@Override
	public String recommendCheck(String recommend_user_list, String loginid) {
		String[] array = recommend_user_list.split(",");
		String check="";

		if(array.length==0) {
			check="not_exist";
		}else if(array.length>0) {
			for(int i=0; i<array.length; i++) {
				if(array[i].equals(loginid)) {
					check="exist";
					break; 
				}else {
					check="not_exist";
				}
			}		
		}
		return check;
	}

	@Override
	public List<ImageBoard> getBestBoardList() {
		return dao.getBestBoardList();
	}

	@Override
	public int getBestBoardListCount() {
		return dao.getBestBoardListCount();
	}

}
