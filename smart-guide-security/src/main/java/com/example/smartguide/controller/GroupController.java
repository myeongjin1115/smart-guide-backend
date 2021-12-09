package com.example.smartguide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartguide.config.JwtTokenUtil;
import com.example.smartguide.dto.MediumGroup;
import com.example.smartguide.dto.SmallGroup;
import com.example.smartguide.mapper.GroupMapper;

@RestController
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private GroupMapper groupMapper;

	// 해당 유저의 대그룹을 기준으로 하위 중그룹을 반환함
	@GetMapping("")
	public List<MediumGroup> getGroupMedium(@RequestHeader("Authorization") String token) {
		token = token.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return groupMapper.selectGroupMediumByLargeNameAndUsername(username);
	}

	// 해당 유저의 대그룹, URL을 통한 중그룹을 통해 하위 소그룹을 반환함
	@GetMapping("/{mediumName}")
	public List<SmallGroup> getGroupSmall(@RequestHeader("Authorization") String token,
			@PathVariable("mediumName") String mediumName) {
		token = token.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return groupMapper.selectGroupSmallByMediumNameAndLargeNameAndUsername(username, mediumName);
	}

}
