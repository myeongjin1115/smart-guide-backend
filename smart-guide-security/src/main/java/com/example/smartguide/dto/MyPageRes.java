package com.example.smartguide.dto;

import lombok.Data;

@Data
public class MyPageRes {
	private String username;
	private String role;
	private GroupInfo group;
}
