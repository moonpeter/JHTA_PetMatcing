package com.jhta.petMatching.abh.service;

import com.jhta.petMatching.abh.domain.Destination;
import com.jhta.petMatching.abh.domain.Member;

public interface MemberService {
		public int isId(String id);	//���̵� �ߺ��˻�
		public int insert(Member m); //ȸ������(DB���)
		public int isId(String id, String password); //�α���
		public Member member_info(String id); //ȸ�� ����
		public void delete(String id); //ȸ��Ż��
		public int update(Member m); //���� ����
		public int insert(Destination d); //����� �Է�
		public Destination desti_info(String id); //����� ����
		public int update(Destination d); //����� ����
		public void desti_delete(String id);
}
