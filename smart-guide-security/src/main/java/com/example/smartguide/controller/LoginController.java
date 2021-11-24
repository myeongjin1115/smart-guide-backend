package com.example.smartguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartguide.config.JwtTokenUtil;
import com.example.smartguide.dto.LoginReq;
import com.example.smartguide.dto.LoginRes;
import com.example.smartguide.model.Member;
import com.example.smartguide.service.JwtUserDetailsService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@PostMapping("")
	public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
		final Member member = userDetailsService.authenticationByUsernameAndPassword(loginReq.getUsername(), loginReq.getPassword());
		final String token = jwtTokenUtil.generateToken(member.getUsername());
		return ResponseEntity.ok(new LoginRes(token));
	}
	
}
