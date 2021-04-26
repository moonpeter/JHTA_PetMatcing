package com.jhta.petMatching.hhi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.hhi.domain.Board;
import com.jhta.petMatching.hhi.domain.DwBoard;


@Repository
public class DwBoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getListCount() {
		return sqlSession.selectOne("DwBoard.count");
	}

	public List<DwBoard> getBoardList(HashMap<String, Integer> map) {
		return sqlSession.selectList("DwBoard.list", map);
	}
	
	public void insertBoard(DwBoard board) {
		sqlSession.insert("DwBoard.insert", board);
	}

	public int setReadCountUpdate(int num) {
		return sqlSession.update("DwBoard.readCountUpdate", num);
	}

	public DwBoard getDetail(int num) {
		return sqlSession.selectOne("DwBoard.detail",num);
	}

	public DwBoard getBoardList(Map<String, Object> map) {
		return sqlSession.selectOne("DwBoard.boardWriter", map);
	}

	public int boardModify(DwBoard modifyboard) {
		return sqlSession.update("DwBoard.modify", modifyboard);
	}

	public int boardReply(DwBoard board) {
		return sqlSession.insert("DwBoard.reply_insert",board);
	}

	public int boardReplyUpdate(DwBoard board) {
		return sqlSession.update("DwBoard.reply_update", board);
	}

	public int boardDelete(DwBoard board) {
		return sqlSession.delete("DwBoard.delete", board);
	}

	public int insert_deleteFile(String before_file) {
		return sqlSession.insert("DwBoard.insert_deleteFile", before_file);
	}

	public List<String> getDeleteFileList() {
		return sqlSession.selectList("DwBoard.deleteFileList");
	}

	public List<DwBoard> getSearchList(Map<String, Object> map) {
		return sqlSession.selectList("DwBoard.getSearchList", map);
	}

	public int getSearchListCount(Map<String, Object> map) {
		return sqlSession.selectOne("DwBoard.searchCount",map);

	}
}
