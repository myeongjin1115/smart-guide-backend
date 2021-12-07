package com.example.smartguide.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManagerHomeRes {
	private Long buildingId;
	private String buildingName;
	private int dailyRecordCount;
	private int dailyUserCount;
	private int weeklyRecordCount;
	private int weeklyUserCount;
	private int monthlyRecordCount;
	private int monthlyUserCount;
	private int yesterdayRecordCount;
	private int yesterdayUserCount;
	private int lastWeekRecordCount;
	private int lastWeekUserCount;
	private int lastMonthRecordCount;
	private int lastMonthUserCount;
}
