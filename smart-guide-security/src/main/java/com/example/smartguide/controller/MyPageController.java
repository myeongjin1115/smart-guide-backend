package com.example.smartguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartguide.dto.MyPageRes;
import com.example.smartguide.service.MyPageService;

@RestController
@RequestMapping("/mypage")
public class MyPageController {

	@Autowired
	private MyPageService myPageService;

	@GetMapping("")
	public MyPageRes getMyPage(@RequestHeader("Authorization") String token) {
		token = token.substring(7);
		return myPageService.getUserInfo(token);
	}

}
