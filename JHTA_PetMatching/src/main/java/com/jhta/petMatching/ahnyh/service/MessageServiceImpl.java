package com.jhta.petMatching.ahnyh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.abh.domain.Member;
import com.jhta.petMatching.ahnyh.dao.MessageDAO;
import com.jhta.petMatching.ahnyh.domain.Message;




@Service
public class MessageServiceImpl implements MessageService{
	//MessageServiceImpl의 빨간줄 표시에 마우스 커서를 올리면 나오는
	//Add unimplemented methods를 클릭하면, 오버라이딩 해야할 메소드들의 껍데기가 자동으로 삽입됨.
	
	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	
	@Autowired
	private MessageDAO dao;
	
	
	@Override
	public int insertMessage(Message message) {
		return dao.insertMessage(message);
	}


	@Override
	public int newMessageCount(String loginid) {
		return dao.newMessageCount(loginid);
	}


	@Override
	public List<Message> getSearchReceiveMessage(int page, int limit, String search_word, String index, String loginid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!index.equals("")) {
			String[] search_field = index.split(""); //빈 문자열로 나누면 한글자씩 나눠짐 예)"ABC"는 "A", "B", "C"로 나뉨
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
		map.put("loginid", loginid);
		return dao.getSearchMessage_receive(map);
	}


	@Override
	public int getSearchReceiveMessageCount(String index, String search_word, String loginid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!index.equals("")) {
			String[] search_field = index.split("");
			for (String sf : search_field) {
				logger.info(sf);
			}
			map.put("search_field", search_field);
			map.put("search_word", "%"+search_word+"%");
		}
		map.put("loginid", loginid);
		return dao.getSearchMessageCount_receive(map);
	}


	@Override
	public Message getDetail_sendMessage(int num, String loginid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("loginid", loginid);
		return dao.getDetail_sendMessage(map);
	}


	@Override
	public int isId(String receiver_id) {
		Member m = dao.isId(receiver_id);
		return (m==null) ? -1 : 1;  //-1은 아이디가 존재하지 않는 경우
	                                 //1은 아이디가 존재하는 경우
		
		// return (m==null) ? -1 : 1; 은 아래의 내용과 같다.
		//int result= -1;
		//if(m !=null) {
		//	result=1;
		//}
		//return result;	
    }


	@Override
	public int readCountUpdate(int num) {
		return dao.readCountUpdate(num);
	}


	@Override
	public Message getDetail_receiveMessage(int num, String loginid) {
		if(readCountUpdate(num)!=1) { // readCountUpdate(num)을 수행하고 그 결과가 1이 아니면 return null; 
			return null;              // 즉, readCountUpdate()은 일단 수행되므로 정상적으로 수행되면 조회수가 +1 증가한다.
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("loginid", loginid);
		return dao.getDetail_receiveMessage(map);
	}


	@Override
	public int deleteByReceiver(String[] select_delete) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("select_delete", select_delete);
		return dao.deleteByReceiver(map);
	}


	@Override
	public List<Message> getSearchSendMessage(int page, int limit, String search_word, String index, String loginid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!index.equals("")) {
			String[] search_field = index.split(""); //빈 문자열로 나누면 한글자씩 나눠짐 예)"ABC"는 "A", "B", "C"로 나뉨
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
		map.put("loginid", loginid);
		return dao.getSearchMessage_send(map);
	}


	@Override
	public int getSearchSendMessageCount(String index, String search_word, String loginid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!index.equals("")) {
			String[] search_field = index.split("");
			for (String sf : search_field) {
				logger.info(sf);
			}
			map.put("search_field", search_field);
			map.put("search_word", "%"+search_word+"%");
		}
		map.put("loginid", loginid);
		return dao.getSearchMessageCount_send(map);
	}


	@Override
	public int deleteBySender(String[] select_delete) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("select_delete", select_delete);
		return dao.deleteBySender(map);
	}


	@Override
	public int cancelBySender(int cancel_num) {
		return dao.cancelBySender(cancel_num);
	}


	@Override
	public int messageDeleteBySchedule1() {
		return dao.messageDeleteBySchedule1();
	}
	
	@Override
	public int messageDeleteBySchedule2() {
		return dao.messageDeleteBySchedule2();
	}

}
