package com.example.smartguide.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Notice extends BaseTimeModel {
	private Long id;
	private String title;
	private String content;
	private Boolean isPublic;
}
