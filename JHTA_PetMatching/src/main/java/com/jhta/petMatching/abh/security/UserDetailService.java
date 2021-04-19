package com.jhta.petMatching.abh.security;

import java.util.ArrayList;
import java.util.Collection;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.abh.domain.Member;

@Service
public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		Member users = sqlSession.selectOne("Members.idcheck", username);

		if (users == null) {	//아이디가 없는경우
			throw new UsernameNotFoundException("Username" + username + "Not Found");
		}
		
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		
		roles.add(new SimpleGrantedAuthority(users.getAuth()));
		
		UserDetails user = new User(username, users.getPassword(), roles);
		
		return user;
	}
}
