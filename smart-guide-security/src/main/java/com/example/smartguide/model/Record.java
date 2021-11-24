package com.example.smartguide.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Record extends BaseCreatedTimeModel {
	private Long id;
	private Long userId;
	private Long beaconId;
}
