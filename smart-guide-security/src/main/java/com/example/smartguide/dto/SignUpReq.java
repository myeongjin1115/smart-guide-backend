package com.example.smartguide.dto;

import lombok.Data;

@Data
public class SignUpReq {
	private String username;
	private String password;
	private String role;
	private Long groupId;
}
