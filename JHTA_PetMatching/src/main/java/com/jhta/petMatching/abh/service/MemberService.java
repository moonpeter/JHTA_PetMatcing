package com.jhta.petMatching.abh.service;

import com.jhta.petMatching.abh.domain.Member;

public interface MemberService {
		public int isId(String id);	//���̵� �ߺ��˻�
		public int insert(Member m); //ȸ������(DB���)
		public int isId(String id, String password); //�α���
}
