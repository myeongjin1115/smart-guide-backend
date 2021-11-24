package com.example.smartguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.smartguide.dto.SignUpReq;
import com.example.smartguide.mapper.MemberMapper;

@Service
public class SignUpService {
	
	@Autowired
	private MemberMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void signup(SignUpReq signUpReq) {
		signUpReq.setPassword(passwordEncoder.encode(signUpReq.getPassword()));
		userMapper.insertUser(signUpReq);
	}
	
}
