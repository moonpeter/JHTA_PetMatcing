package com.jhta.petMatching.ahnyh.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.petMatching.abh.domain.Member;
import com.jhta.petMatching.ahnyh.domain.Message;



@Repository
public class MessageDAO {
	@Autowired
	private SqlSessionTemplate sqlSession; //@Autowird에 의해 root-context.xml의 bean이 자동 주입. SqlSessionTemplate는 root-context.xml에서 bean이 생성됨

	
	
	public int insertMessage(Message message) {
		return sqlSession.insert("Messages.insert", message);  //message.xml에 있는 namespace="Messages"에 있는 id="insert"에 해당하는 sql문 실행.
	} //반환형이 int인 것은 insert(삽입)된 레코드 수를 반환하는 것이기 때문이다. (CommentDAO.java 참고)
	
	
	public int newMessageCount(String loginid) {
		return sqlSession.selectOne("Messages.newMessageCount", loginid);
	} //반환형이 int인 것은 select(선택)된 레코드 수를 반환하는 것이기 때문이다.
	
	
	public List<Message> getSearchMessage_receive(Map<String, Object> map){
		return sqlSession.selectList("Messages.getSearchReceiveMessage", map);
	}
	
	
	public int getSearchMessageCount_receive(Map<String, Object> map) {
		return sqlSession.selectOne("Messages.getSearchReceiveMessageCount", map);
	}	
	
	public Member isId(String receiver_id) {
		return sqlSession.selectOne("Messages.idcheck", receiver_id);
	}
	
	public int readCountUpdate(int num) {
		return sqlSession.update("Messages.readCountUpdate", num);
	}
	
	public Message getDetail_receiveMessage(Map<String, Object> map) {
		return sqlSession.selectOne("Messages.getDetail_receiveMessage", map);
	}
	
	//실제로 삭제하는 것이 아니라 deleteby_receiver칼럼의 값을 'y'로 주면서 받은 메시지 목록에 나타나지 않게 한다.
	public int deleteByReceiver(Map<String, Object> map) {
		return sqlSession.update("Messages.deleteByReceiver", map);
	}
	
	
	public List<Message> getSearchMessage_send(Map<String, Object> map){
		return sqlSession.selectList("Messages.getSearchSendMessage", map);
	}
	
	
	public int getSearchMessageCount_send(Map<String, Object> map) {
		return sqlSession.selectOne("Messages.getSearchSendMessageCount", map);
	}
	
	
	//실제로 삭제하는 것이 아니라 deleteby_sender칼럼의 값을 'y'로 주면서 받은 메시지 목록에 나타나지 않게 한다.
	public int deleteBySender(Map<String, Object> map) {
		return sqlSession.update("Messages.deleteBySender", map);
	}
	
	
	public Message getDetail_sendMessage(Map<String, Object> map) {
		return sqlSession.selectOne("Messages.getDetail_sendMessage", map);
	}
	
	public int cancelBySender(int cancel_num) {
		return sqlSession.update("Messages.cancelBySender", cancel_num);
	}
	
	
	public int messageDeleteBySchedule1() {
		return sqlSession.delete("Messages.messageDeleteBySchedule1");
	}
	
	public int messageDeleteBySchedule2() {
		return sqlSession.delete("Messages.messageDeleteBySchedule2");
	}
	
	
}
