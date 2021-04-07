package com.jhta.petMatching.hhi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.hhi.dao.DwBoardDAO;
import com.jhta.petMatching.hhi.domain.DwBoard;

@Service
public class DwBoardServiceImpl implements DwBoardService {

	
	@Autowired
	private DwBoardDAO dao;
	
	@Override
	public int getListCount() {
		return dao.getListCount();
	}

	@Override
	public List<DwBoard> getBoardList(int page, int limit) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int startrow = (page-1)*limit + 1;
		int endrow = startrow + limit - 1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getBoardList(map);
	}

	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}
	
	@Override
	public int boardModify(DwBoard modifyboard) {
		return dao.boardModify(modifyboard);
	}
	
	@Override
	public DwBoard getDetail(int num) {
		if(setReadCountUpdate(num)!=1)
			return null;
		return dao.getDetail(num);
	}

	@Override
	public int boardReply(DwBoard board) {
		boardReplyUpdate(board);
		board.setBOARD_RE_LEV(board.getBOARD_RE_LEV() + 1);
		board.setBOARD_RE_SEQ(board.getBOARD_RE_SEQ() + 1);
		return dao.boardReply(board);
	}


	@Override
	public int boardDelete(int num) {
		int result = 0;
		DwBoard board = dao.getDetail(num);
		if(board != null) {
			// 추가 - 삭제할 파일 목록들을 저장하기 위한 메서드 호출
			if(board.getBOARD_FILE() != null) {
				dao.insert_deleteFile(board.getBOARD_FILE());
			}
			result = dao.boardDelete(board);
		}
		return result;
	}


	@Override
	public boolean isBoardWriter(int num, String pass) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		DwBoard result = dao.getBoardList(map);
		if(result == null)
			return false;
		else
			return true;
	}

	@Override
	public void insertBoard(DwBoard board) {
		dao.insertBoard(board);
	}

	@Override
	public int boardReplyUpdate(DwBoard board) {
		return dao.boardReplyUpdate(board);
	}

	@Override
	public int insert_deleteFile(String before_file) {
		return dao.insert_deleteFile(before_file);
	}

	@Override
	public List<String> getDeleteFileList() {
		return dao.getDeleteFileList();
	}

	
}
