package com.example.smartguide.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.smartguide.dto.RecordCount;
import com.example.smartguide.dto.RecordRes;
import com.example.smartguide.model.Record;

@Mapper
public interface RecordMapper {

	@Select("SELECT * FROM record")
	List<Record> selectAllRecord();
	
	@Select("SELECT * FROM record WHERE user_id=#{userId}")
	List<Record> selectUserRecord(@Param("userId") Long userId);
	
	@Select("SELECT user.username, building.name AS building_name, record.created_at"
			+ " FROM user, building, beacon, record"
			+ " WHERE building.id=#{buildingId}"
			+ " AND beacon.building_id=building.id"
			+ " AND beacon.id=record.beacon_id"
			+ " AND record.user_id=user.id")
	List<RecordRes> selectBuildingRecord(@Param("buildingId") Long buildingId);
	
	@Insert("INSERT INTO record(user_id, beacon_id, created_at) VALUES(#{record.userId}, #{record.beaconId}, #{record.createdAt})")
	int insertRecord(@Param("record") Record record);
	
	// 해당 빌딩에 오늘 하루 동안 기록된 record 수 및 인원 수
	@Select("SELECT count(*), count(DISTINCT user_id) FROM record"
			+ " WHERE DATE(created_at)=DATE(NOW())"
			+ " AND beacon_id"
			+ " IN (SELECT id FROM beacon WHERE building_id=#{buildingId})")
	@Results({
		@Result(property="recordCount", column="count(*)"),
		@Result(property="userCount", column="count(DISTINCT user_id)")
	})
	RecordCount selectDailyRecordCountByBuildingId(@Param("buildingId") Long buildingId);
	
	// 해당 빌딩에 어제 하루 동안 기록된 record 수 및 인원 수
	@Select("SELECT count(*), count(DISTINCT user_id) FROM record"
			+ " WHERE DATE(created_at)=DATE(NOW())-1"
			+ " AND beacon_id"
			+ " IN (SELECT id FROM beacon WHERE building_id=#{buildingId})")
	@Results({
		@Result(property="recordCount", column="count(*)"),
		@Result(property="userCount", column="count(DISTINCT user_id)")
	})
	RecordCount selectYesterdayRecordCountByBuildingId(@Param("buildingId") Long buildingId);
	
	// 해당 빌딩에 이번 주 동안 기록된 record 수 및 인원 수
	@Select("SELECT count(*), count(DISTINCT user_id) FROM record"
			+ " WHERE YEARWEEK(created_at, 7)=YEARWEEK(NOW(), 7)"
			+ " AND beacon_id"
			+ " IN (SELECT id FROM beacon WHERE building_id=#{buildingId})")
	@Results({
		@Result(property="recordCount", column="count(*)"),
		@Result(property="userCount", column="count(DISTINCT user_id)")
	})
	RecordCount selectWeeklyRecordCountByBuildingId(@Param("buildingId") Long buildingId);
	
	// 해당 빌딩에 지난 주 동안 기록된 record 수 및 인원 수
	@Select("SELECT count(*), count(DISTINCT user_id) FROM record"
			+ " WHERE YEARWEEK(created_at, 7)=YEARWEEK(NOW() - INTERVAL 1 WEEK, 7)"
			+ " AND beacon_id"
			+ " IN (SELECT id FROM beacon WHERE building_id=#{buildingId})")
	@Results({
		@Result(property="recordCount", column="count(*)"),
		@Result(property="userCount", column="count(DISTINCT user_id)")
	})
	RecordCount selectLastWeekRecordCountByBuildingId(@Param("buildingId") Long buildingId);
	
	// 해당 빌딩에 이번 달 동안 기록된 record 수 및 인원 수
	@Select("SELECT count(*), count(DISTINCT user_id) FROM record"
			+ " WHERE MONTH(created_at)=MONTH(NOW())"
			+ " AND beacon_id"
			+ " IN (SELECT id FROM beacon WHERE building_id=#{buildingId})")
	@Results({
		@Result(property="recordCount", column="count(*)"),
		@Result(property="userCount", column="count(DISTINCT user_id)")
	})
	RecordCount selectMonthlyRecordCountByBuildingId(@Param("buildingId") Long buildingId);
	
	// 해당 빋링에 저번 달 동안 기록된 record 수 및 인원 수
	@Select("SELECT count(*), count(DISTINCT user_id) FROM record"
			+ " WHERE MONTH(created_at)=MONTH(NOW() - INTERVAL 1 MONTH)"
			+ " AND beacon_id"
			+ " IN (SELECT id FROM beacon WHERE building_id=#{buildingId})")
	@Results({
		@Result(property="recordCount", column="count(*)"),
		@Result(property="userCount", column="count(DISTINCT user_id)")
	})
	RecordCount selectLastMonthRecordCountByBuildingId(@Param("buildingId") Long buildingId);
	
	
}
