package com.example.smartguide.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticePostReq {
	private String title;
	private String content;
	private Boolean isPublic;
	private List<Long> groupIds;
}
