package com.example.smartguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartguide.dto.SignUpResponse;
import com.example.smartguide.dto.SignUpReq;
import com.example.smartguide.service.SignUpService;

@RestController
@RequestMapping("/signup")
public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	@PostMapping("")
	public SignUpResponse singup(@RequestBody SignUpReq signUpReq) {
		SignUpResponse signResponse = new SignUpResponse();
		signUpService.signup(signUpReq);
		signResponse.setAnswer("SignUp Success!");
		
		return signResponse; 
	}
	
}
