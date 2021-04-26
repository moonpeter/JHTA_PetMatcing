package com.jhta.petMatching.ahnyh.service;

import java.util.List;

import com.jhta.petMatching.ahnyh.domain.Message;


public interface MessageService {

	//메시지 등록하기
	public int insertMessage(Message message);
	
	//읽지 않은 메시지 수 구하기
	public int newMessageCount(String loginid);
	
	//받은 메시지함 리스트
	public List<Message> getSearchReceiveMessage(int page, int limit, String search_word, String index, String loginid);  
	
	//받은 메시지함 리스트 수
	public int getSearchReceiveMessageCount(String index, String search_word, String loginid);
	
	//메시지 상세내용 보기
	//보낸 메시지함에 있는 메시지 보기. 메시지 조회수 증가하지 않음.
	public Message getDetail_sendMessage(int num, String loginid);
	
	//메시지를 보낼 때, receiver_id가 회원가입된 상태인지 아닌지 확인
	public int isId(String receiver_id);
	
	//메시지 조회수 증가. 
	//받은 메시지함에 있는 메시지를 보았을 때에만 증가하도록함. 그 이유는 메시지를 받은 사람이 메시지를 읽었는지를 판단하는 기준으로 쓰는 것이기 때문.
	public int readCountUpdate(int num);
	
	//메시지 상세내용 보기
	//받은 메시지함에 있는 메시지 보기. 메시지 조회수 증가 메소드와 결합시킬 것.
	public Message getDetail_receiveMessage(int num, String loginid);
	
	
	//받은 메시지함에서 받은 메시지 삭제
	public int deleteByReceiver(String[] select_delete);
	
	
	
	//보낸 메시지함 리스트
	public List<Message> getSearchSendMessage(int page, int limit, String search_word, String index, String loginid);  
		
	//보낸 메시지함 리스트 수
	public int getSearchSendMessageCount(String index, String search_word, String loginid);
	
	//보낸 메시지함에서 보낸 메시지 삭제
	public int deleteBySender(String[] select_delete);
	
	
	//보낸 메시지함-상세보기에서 메시지 발송 취소
	public int cancelBySender(int cancel_num);
	
	
	// @Scheduled 스케줄러에 의한 메시지 삭제
	public int messageDeleteBySchedule1();
	// @Scheduled 스케줄러에 의한 메시지 삭제
	public int messageDeleteBySchedule2();
	
}
