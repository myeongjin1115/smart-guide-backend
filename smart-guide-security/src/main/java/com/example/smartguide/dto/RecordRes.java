package com.example.smartguide.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RecordRes {
	private String username;
	private String buildingName;
	private LocalDateTime createdAt;
}
