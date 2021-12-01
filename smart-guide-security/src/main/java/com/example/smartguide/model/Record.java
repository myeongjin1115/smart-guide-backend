package com.example.smartguide.model;

import lombok.Data;

@Data
public class Record {
	private Long id;
	private Long userId;
	private Long beaconId;
	private String createdAt;
}
