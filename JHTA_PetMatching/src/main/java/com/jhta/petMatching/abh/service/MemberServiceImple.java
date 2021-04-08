package com.jhta.petMatching.abh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.abh.dao.MemberDAO;
import com.jhta.petMatching.abh.domain.Member;

@Service
public class MemberServiceImple implements MemberService{
	
	@Autowired
	private MemberDAO dao;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;

	@Override
	public int isId(String id) {			//ȸ������ �� ���̵� �ߺ��˻�
		Member rmember = dao.isId(id);		//-1�� ���̵� �������� ����
		return (rmember==null) ? -1 : 1;	//1�� ���̵� ����
	}

	@Override
	public int insert(Member m) {	//ȸ������ �ۼ��Ϸ�
		return dao.insert(m);		//insert ó����
	}

	@Override
	public int isId(String id, String password) {	//�α��� �õ�
		Member rmember = dao.isId(id);
		int result = -1;	//���̵� �������� ���� ��� -> rmember�� null�� ���
		if(rmember != null) {//�Ƶ�� �����ϴ� ���
			if(rmember.getPassword().equals(password)) {
				result = 1;	//���̵�� ��й�ȣ�� ��ġ�ϴ� ���
			} else {
				result = 0;	//���̵�� ���������� ��й�ȣ�� ��ġ���� �ʴ� ���
			}
		}
		return result;
	}
}
