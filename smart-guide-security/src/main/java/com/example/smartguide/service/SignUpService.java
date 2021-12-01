package com.example.smartguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.smartguide.dto.SignUpReq;
import com.example.smartguide.mapper.GroupMapper;
import com.example.smartguide.mapper.MemberMapper;

@Service
public class SignUpService {
	
	//@Autowired
	//private MemberMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private GroupMapper groupMapper;

	public void signup(SignUpReq signUpReq) {
	    if(signUpReq.getPassword() == null) {
	        signUpReq.setPassword("");
	    }
	    signUpReq.setPassword(passwordEncoder.encode(signUpReq.getPassword()));
	    Long groupId = groupMapper.selectGroupIdByLargeAndMediumAndSmall(signUpReq);
	    memberMapper.insertNormalUser(signUpReq.getUsername(), signUpReq.getPassword(), groupId);
	}
	
	/*public void signup(SignUpReq signUpReq) {
		signUpReq.setPassword(passwordEncoder.encode(signUpReq.getPassword()));
		userMapper.insertUser(signUpReq);
	}*/
	
}
