package com.example.smartguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartguide.dto.SignResponse;
import com.example.smartguide.dto.SignUpReq;
import com.example.smartguide.service.SignUpService;

@RestController
@RequestMapping("/signup")
public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	@PostMapping("")
	public SignResponse singup(@RequestBody SignUpReq signUpReq) {
		SignResponse signResponse = new SignResponse();
		signUpService.signup(signUpReq);
		signResponse.setAnswer("test");
		
		return signResponse; 
	}
	
}
