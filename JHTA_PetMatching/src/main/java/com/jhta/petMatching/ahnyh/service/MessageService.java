package com.jhta.petMatching.ahnyh.service;

import java.util.List;

import com.jhta.petMatching.ahnyh.domain.Message;


public interface MessageService {

	//�޽��� ����ϱ�
	public int insertMessage(Message message);
	
	//���� ���� �޽��� �� ���ϱ�
	public int newMessageCount(String loginid);
	
	//���� �޽����� ����Ʈ
	public List<Message> getSearchReceiveMessage(int page, int limit, String search_word, String index, String loginid);  
	
	//���� �޽����� ����Ʈ ��
	public int getSearchReceiveMessageCount(String index, String search_word, String loginid);
	
	//�޽��� �󼼳��� ����
	//���� �޽����Կ� �ִ� �޽��� ����. �޽��� ��ȸ�� �������� ����.
	public Message getDetail_sendMessage(int num, String loginid);
	
	//�޽����� ���� ��, receiver_id�� ȸ�����Ե� �������� �ƴ��� Ȯ��
	public int isId(String receiver_id);
	
	//�޽��� ��ȸ�� ����. 
	//���� �޽����Կ� �ִ� �޽����� ������ ������ �����ϵ�����. �� ������ �޽����� ���� ����� �޽����� �о������� �Ǵ��ϴ� �������� ���� ���̱� ����.
	public int readCountUpdate(int num);
	
	//�޽��� �󼼳��� ����
	//���� �޽����Կ� �ִ� �޽��� ����. �޽��� ��ȸ�� ���� �޼ҵ�� ���ս�ų ��.
	public Message getDetail_receiveMessage(int num, String loginid);
	
	
	//���� �޽����Կ��� ���� �޽��� ����
	public int deleteByReceiver(String[] select_delete);
	
	
	
	//���� �޽����� ����Ʈ
	public List<Message> getSearchSendMessage(int page, int limit, String search_word, String index, String loginid);  
		
	//���� �޽����� ����Ʈ ��
	public int getSearchSendMessageCount(String index, String search_word, String loginid);
	
	//���� �޽����Կ��� ���� �޽��� ����
	public int deleteBySender(String[] select_delete);
	
	
	//���� �޽�����-�󼼺��⿡�� �޽��� �߼� ���
	public int cancelBySender(int cancel_num);
	
	
	// @Scheduled �����ٷ��� ���� �޽��� ����
	public int messageDeleteBySchedule1();
	// @Scheduled �����ٷ��� ���� �޽��� ����
	public int messageDeleteBySchedule2();
	
}
