package com.example.smartguide.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.smartguide.model.Building;

@Mapper
public interface BuildingMapper {
	
	@Select("SELECT building_id FROM beacon WHERE uuid=#{uuid} AND major=#{major} AND minor=#{minor}")
	Long selectBuildingIdByUuidAndMajorAndMinor(@Param("uuid") String uuid, @Param("major") String major, @Param("minor") String minor);
	
	@Select("SELECT building.* FROM user, building_and_group, building"
			+ " WHERE user.username=#{username}"
			+ " AND user.group_id=building_and_group.group_id"
			+ " AND building_and_group.building_id=building.id")
	List<Building> selectBuildingByUsername(@Param("username") String username);
	
}
