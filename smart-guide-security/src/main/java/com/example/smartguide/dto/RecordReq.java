package com.example.smartguide.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RecordReq {
	private String uuid;
	private String major;
	private String minor;
	private LocalDateTime createdAt;
}
