package com.example.smartguide.model;

import lombok.Data;

@Data
public class Member {
	private Long id;
	private String username;
	private String password;
	private Role role;
	private Long groupId;
}
