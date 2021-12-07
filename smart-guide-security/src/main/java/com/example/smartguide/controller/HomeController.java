package com.example.smartguide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartguide.config.JwtTokenUtil;
import com.example.smartguide.dto.ManagerHomeRes;
import com.example.smartguide.service.HomeService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/manager")
	public List<ManagerHomeRes> managerHomePage(@RequestHeader("Authorization") String token) {
		token = token.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return homeService.RecordCount(username);
	}
	
}
