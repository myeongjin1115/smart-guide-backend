package com.example.smartguide.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RecordReq {
	private String uuid;
	private String major;
	private String minor;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	//private LocalDateTime createdAt;
	private String createdAt;
}
