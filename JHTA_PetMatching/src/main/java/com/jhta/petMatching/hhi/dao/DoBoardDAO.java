package com.jhta.petMatching.hhi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.hhi.domain.Board;
import com.jhta.petMatching.hhi.domain.DoBoard;


@Repository
public class DoBoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getListCount() {
		return sqlSession.selectOne("DoBoard.count");
	}

	public List<DoBoard> getBoardList(HashMap<String, Integer> map) {
		return sqlSession.selectList("DoBoard.list", map);
	}
	
	public void insertBoard(DoBoard board) {
		sqlSession.insert("DoBoard.insert", board);
	}

	public int setReadCountUpdate(int num) {
		return sqlSession.update("DoBoard.readCountUpdate", num);
	}

	public DoBoard getDetail(int num) {
		return sqlSession.selectOne("DoBoard.detail",num);
	}

	public DoBoard getBoardList(Map<String, Object> map) {
		return sqlSession.selectOne("DoBoard.boardWriter", map);
	}

	public int boardModify(DoBoard modifyboard) {
		return sqlSession.update("DoBoard.modify", modifyboard);
	}

	public int boardReply(DoBoard board) {
		return sqlSession.insert("DoBoard.reply_insert",board);
	}

	public int boardReplyUpdate(DoBoard board) {
		return sqlSession.update("DoBoard.reply_update", board);
	}

	public int boardDelete(DoBoard board) {
		return sqlSession.delete("DoBoard.delete", board);
	}

	public int insert_deleteFile(String before_file) {
		return sqlSession.insert("DoBoard.insert_deleteFile", before_file);
	}

	public List<String> getDeleteFileList() {
		return sqlSession.selectList("DoBoard.deleteFileList");
	}
	
	public List<DoBoard> getSearchList(Map<String, Object> map) {
		return sqlSession.selectList("DoBoard.getSearchList", map);
	}
	
	public int getSearchListCount(Map<String, Object> map) {
		return sqlSession.selectOne("DoBoard.searchCount",map);
	}
}
