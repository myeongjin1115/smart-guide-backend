package com.example.smartguide.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notice {
	private Long id;
	private String title;
	private String content;
	private Boolean isPublic;
	private String createdAt;
	private String updatedAt;
	private Long userId;
}
