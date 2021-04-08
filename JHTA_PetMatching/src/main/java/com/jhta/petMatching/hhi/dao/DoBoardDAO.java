package com.jhta.petMatching.hhi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.hhi.domain.DoBoard;


@Repository
public class DoBoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getListCount() {
		return sqlSession.selectOne("Boards.count");
	}

	public List<DoBoard> getBoardList(HashMap<String, Integer> map) {
		return sqlSession.selectList("Boards.list", map);
	}
	
	public void insertBoard(DoBoard board) {
		sqlSession.insert("Boards.insert", board);
	}

	public int setReadCountUpdate(int num) {
		return sqlSession.update("Boards.readCountUpdate", num);
	}

	public DoBoard getDetail(int num) {
		return sqlSession.selectOne("Boards.detail",num);
	}

	public DoBoard getBoardList(Map<String, Object> map) {
		return sqlSession.selectOne("Boards.boardWriter", map);
	}

	public int boardModify(DoBoard modifyboard) {
		return sqlSession.update("Boards.modify", modifyboard);
	}

	public int boardReply(DoBoard board) {
		return sqlSession.insert("Boards.reply_insert",board);
	}

	public int boardReplyUpdate(DoBoard board) {
		return sqlSession.update("Boards.reply_update", board);
	}

	public int boardDelete(DoBoard board) {
		return sqlSession.delete("Boards.delete", board);
	}

	public int insert_deleteFile(String before_file) {
		return sqlSession.insert("Boards.insert_deleteFile", before_file);
	}

	public List<String> getDeleteFileList() {
		return sqlSession.selectList("Boards.deleteFileList");
	}
}
