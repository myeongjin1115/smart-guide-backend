package com.example.smartguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartguide.dto.LoginReq;
import com.example.smartguide.mapper.MemberMapper;

@Service
public class LoginService {

	@Autowired
	private MemberMapper userMapper;
	
	public Long login(LoginReq loginReq) {
		return userMapper.selectUserId(loginReq);
	}
	
}
