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
	//MessageServiceImpl�� ������ ǥ�ÿ� ���콺 Ŀ���� �ø��� ������
	//Add unimplemented methods�� Ŭ���ϸ�, �������̵� �ؾ��� �޼ҵ���� �����Ⱑ �ڵ����� ���Ե�.
	
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
			String[] search_field = index.split(""); //�� ���ڿ��� ������ �ѱ��ھ� ������ ��)"ABC"�� "A", "B", "C"�� ����
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
		return (m==null) ? -1 : 1;  //-1�� ���̵� �������� �ʴ� ���
	                                 //1�� ���̵� �����ϴ� ���
		
		// return (m==null) ? -1 : 1; �� �Ʒ��� ����� ����.
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
		if(readCountUpdate(num)!=1) { // readCountUpdate(num)�� �����ϰ� �� ����� 1�� �ƴϸ� return null; 
			return null;              // ��, readCountUpdate()�� �ϴ� ����ǹǷ� ���������� ����Ǹ� ��ȸ���� +1 �����Ѵ�.
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
			String[] search_field = index.split(""); //�� ���ڿ��� ������ �ѱ��ھ� ������ ��)"ABC"�� "A", "B", "C"�� ����
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
