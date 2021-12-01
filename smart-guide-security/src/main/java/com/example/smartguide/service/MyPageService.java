package com.example.smartguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartguide.config.JwtTokenUtil;
import com.example.smartguide.dto.MyPageRes;
import com.example.smartguide.mapper.GroupMapper;
import com.example.smartguide.mapper.MemberMapper;
import com.example.smartguide.model.Group;
import com.example.smartguide.model.Member;

@Service
public class MyPageService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private GroupMapper groupMapper;
	
	public MyPageRes getUserInfo(String token) {
		String username = jwtTokenUtil.getUsernameFromToken(token);
		
		Member user = memberMapper.selectUserByUsername(username).orElseThrow(RuntimeException::new);
		
		Group group = groupMapper.selectGroupById(user.getGroupId());
		
		MyPageRes myPageRes = new MyPageRes();
		myPageRes.setUsername(username);
		myPageRes.setAuthority(user.getRole().name());
		myPageRes.setGroupName(group.getLargeName() + " > "
							+ group.getMediumName() + " > "
							+ group.getSmallName());
		
		return myPageRes;
	}
	
}
