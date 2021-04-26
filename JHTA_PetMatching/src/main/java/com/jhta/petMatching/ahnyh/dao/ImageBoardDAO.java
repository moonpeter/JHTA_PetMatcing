package com.jhta.petMatching.ahnyh.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.ahnyh.domain.ImageBoard;





@Repository
public class ImageBoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	
	public int getListCount(Map<String, Object> map) {
		return sqlSession.selectOne("ImageBoards.count", map);
	}
	
	public List<ImageBoard> getBoardList(Map<String, Object> map){
		return sqlSession.selectList("ImageBoards.list", map);
	}
	
	public void insertBoard(ImageBoard imageboard) {
		sqlSession.insert("ImageBoards.insert", imageboard);
	}
	
	public ImageBoard getDetail(int num) {
		return sqlSession.selectOne("ImageBoards.detail", num);
	}
	
	public int setReadCountUpdate(int num) {
		return sqlSession.update("ImageBoards.readCountUpdate", num);
	}
	
	public ImageBoard isBoardWriter(Map<String, Object> map) {
		return sqlSession.selectOne("ImageBoards.boardWriter", map);
	}
	
	public int boardDelete(int num) {
		return sqlSession.delete("ImageBoards.delete", num);
	}
	
	public int insert_deleteFile(String before_file) {
		return sqlSession.insert("ImageBoards.insert_deleteFile", before_file);   
	}
	
	public List<String> getDeleteFileList() {
		return sqlSession.selectList("ImageBoards.deleteFileList");
	}
	
	public int boardModify(ImageBoard imageboard) {
		return sqlSession.update("ImageBoards.modify", imageboard);
	}
	
	public void reply_countUpdate(Map<String, Object> map) {
		sqlSession.update("ImageBoards.reply_count_update", map);
	}
	
	public String getRecommend_User_List(int num) {
		return sqlSession.selectOne("ImageBoards.getRecommend_User_List", num);
	}
	
	public void insertRecommend_User_List(Map<String, Object> map) {
		sqlSession.update("ImageBoards.insertRecommend_User_List", map);
	}
	
	public void updateRecommend_Count(int board_num) {
		sqlSession.update("ImageBoards.updateRecommend_Count", board_num);
	}
	
	public List<ImageBoard> getBestBoardList(){
		return sqlSession.selectList("ImageBoards.getBestBoardList");
	}
	
	public int getBestBoardListCount() {
		return sqlSession.selectOne("ImageBoards.getBestBoardListCount");
	}
}
