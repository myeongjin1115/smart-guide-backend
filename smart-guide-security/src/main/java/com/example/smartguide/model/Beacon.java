package com.example.smartguide.model;

import lombok.Data;

@Data
public class Beacon {
	private Long id;
	private String uuid;
	private String major;
	private String minor;
	private Long buildingId;
}
