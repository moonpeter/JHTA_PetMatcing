package com.jhta.petMatching.abh.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.abh.dao.MemberDAO;
import com.jhta.petMatching.abh.domain.Destination;
import com.jhta.petMatching.abh.domain.Member;

@Service
public class MemberServiceImple implements MemberService{
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
			if(passwordEncoder.matches(password, rmember.getPassword())) {
				result = 1;	//���̵�� ��й�ȣ ��ġ
			}else {
				result = 0;	//���̵�� ����, ��й�ȣ ����ġ
			}
		}
		return result;
	}
	
	@Override
	public Member member_info(String id) { //ȸ������
		return dao.member_info(id);
	}
	
	@Override
	public void delete(String id) { //ȸ������
		dao.delete(id);
	}
	
	@Override
	public int update(Member m) { //��������
		return dao.update(m);
	}

	@Override
	public int insert(Destination d) { //������Է�
		return dao.insert(d);
	}

	@Override
	public Destination desti_info(String id) {
		return dao.desti_info(id);
	}

	@Override
	public int update(Destination d) {
		return dao.update(d);
	}
	
	@Override
	public void desti_delete(String id) {
		dao.desti_delete(id);
	}
}