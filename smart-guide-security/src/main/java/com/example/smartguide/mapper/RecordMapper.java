package com.example.smartguide.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.smartguide.dto.RecordRes;
import com.example.smartguide.model.Record;

@Mapper
public interface RecordMapper {

	@Select("SELECT * FROM record")
	List<Record> selectAllRecord();
	
	@Select("SELECT * FROM record WHERE user_id=#{userId}")
	List<Record> selectUserRecord(@Param("userId") Long userId);
	
//	@Select("SELECT * FROM record WHERE beacon_id=(SELECT beacon.id FROM beacon WHERE beacon.building_id=#{buildingId})")
//	List<Record> selectBuildingRecord(@Param("buildingId") Long buildingId);
	
	@Select("SELECT user.username, building.name AS building_name, record.created_at"
			+ " FROM user, building, beacon, record"
			+ " WHERE building.id=#{buildingId}"
			+ " AND beacon.building_id=building.id"
			+ " AND beacon.id=record.beacon_id"
			+ " AND record.user_id=user.id")
	List<RecordRes> selectBuildingRecord(@Param("buildingId") Long buildingId);
	
	@Insert("INSERT INTO record(user_id, beacon_id, created_at) VALUES(#{record.userId}, #{record.beaconId}, #{record.createdAt})")
	int insertRecord(@Param("record") Record record);
	
}
