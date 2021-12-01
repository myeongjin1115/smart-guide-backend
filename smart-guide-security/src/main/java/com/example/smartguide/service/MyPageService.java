package com.example.smartguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartguide.config.JwtTokenUtil;
import com.example.smartguide.dto.GroupInfo;
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
		
		Member member = memberMapper.selectUserByUsername(username).orElseThrow(RuntimeException::new);
		
		Group group = groupMapper.selectGroupById(member.getGroupId());
		
		MyPageRes myPageRes = new MyPageRes();
		myPageRes.setUsername(username);
		myPageRes.setRole(member.getRole().name());
		GroupInfo groupInfo = new GroupInfo();
		groupInfo.setLarge(group.getLargeName());
		groupInfo.setMedium(group.getMediumName());
		groupInfo.setSmall(group.getSmallName());
		myPageRes.setGroup(groupInfo);
		
		return myPageRes;
	}
	
}
