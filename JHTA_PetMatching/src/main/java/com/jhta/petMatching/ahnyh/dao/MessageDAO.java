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
	private SqlSessionTemplate sqlSession; //@Autowird�� ���� root-context.xml�� bean�� �ڵ� ����. SqlSessionTemplate�� root-context.xml���� bean�� ������

	
	
	public int insertMessage(Message message) {
		return sqlSession.insert("Messages.insert", message);  //message.xml�� �ִ� namespace="Messages"�� �ִ� id="insert"�� �ش��ϴ� sql�� ����.
	} //��ȯ���� int�� ���� insert(����)�� ���ڵ� ���� ��ȯ�ϴ� ���̱� �����̴�. (CommentDAO.java ����)
	
	
	public int newMessageCount(String loginid) {
		return sqlSession.selectOne("Messages.newMessageCount", loginid);
	} //��ȯ���� int�� ���� select(����)�� ���ڵ� ���� ��ȯ�ϴ� ���̱� �����̴�.
	
	
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
	
	//������ �����ϴ� ���� �ƴ϶� deleteby_receiverĮ���� ���� 'y'�� �ָ鼭 ���� �޽��� ��Ͽ� ��Ÿ���� �ʰ� �Ѵ�.
	public int deleteByReceiver(Map<String, Object> map) {
		return sqlSession.update("Messages.deleteByReceiver", map);
	}
	
	
	public List<Message> getSearchMessage_send(Map<String, Object> map){
		return sqlSession.selectList("Messages.getSearchSendMessage", map);
	}
	
	
	public int getSearchMessageCount_send(Map<String, Object> map) {
		return sqlSession.selectOne("Messages.getSearchSendMessageCount", map);
	}
	
	
	//������ �����ϴ� ���� �ƴ϶� deleteby_senderĮ���� ���� 'y'�� �ָ鼭 ���� �޽��� ��Ͽ� ��Ÿ���� �ʰ� �Ѵ�.
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
