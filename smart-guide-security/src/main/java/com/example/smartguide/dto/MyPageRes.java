package com.example.smartguide.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyPageRes {
	private String username;
	private String phoneNumber;
	private String authority;
	private String groupName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
