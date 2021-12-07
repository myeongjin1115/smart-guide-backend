package com.example.smartguide.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartguide.dto.ManagerHomeRes;
import com.example.smartguide.dto.RecordCount;
import com.example.smartguide.mapper.BuildingMapper;
import com.example.smartguide.mapper.RecordMapper;
import com.example.smartguide.model.Building;

@Service
public class HomeService {

	@Autowired
	private RecordMapper recordMapper;

	@Autowired
	private BuildingMapper buildingMapper;

	public List<ManagerHomeRes> RecordCount(String username) {
		List<ManagerHomeRes> result = new ArrayList<>();

		List<Building> buildings = buildingMapper.selectBuildingIdsByUsername(username);
		for (Building building : buildings) {
			Long buildingId = building.getId();

			RecordCount dailyRecordCount = recordMapper.selectDailyRecordCountByBuildingId(buildingId);
			RecordCount weeklyRecordCount = recordMapper.selectWeeklyRecordCountByBuildingId(buildingId);
			RecordCount monthlyRecordCount = recordMapper.selectMonthlyRecordCountByBuildingId(buildingId);
			RecordCount yesterdayRecordCount = recordMapper.selectYesterdayRecordCountByBuildingId(buildingId);
			RecordCount lastWeekRecordCount = recordMapper.selectLastWeekRecordCountByBuildingId(buildingId);
			RecordCount lastMonthRecordCount = recordMapper.selectLastMonthRecordCountByBuildingId(buildingId);

			result.add(ManagerHomeRes.builder().buildingId(buildingId).buildingName(building.getName())
					.dailyRecordCount(dailyRecordCount.getRecordCount()).dailyUserCount(dailyRecordCount.getUserCount())
					.weeklyRecordCount(weeklyRecordCount.getRecordCount())
					.weeklyUserCount(weeklyRecordCount.getUserCount())
					.monthlyRecordCount(monthlyRecordCount.getRecordCount())
					.monthlyUserCount(monthlyRecordCount.getUserCount())
					.yesterdayRecordCount(yesterdayRecordCount.getRecordCount())
					.yesterdayUserCount(yesterdayRecordCount.getUserCount())
					.lastWeekRecordCount(lastWeekRecordCount.getRecordCount())
					.lastWeekUserCount(lastWeekRecordCount.getUserCount())
					.lastMonthRecordCount(lastMonthRecordCount.getRecordCount())
					.lastMonthUserCount(lastMonthRecordCount.getUserCount()).build());
		}

		return result;
	}

}
