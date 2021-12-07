package com.example.smartguide.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.smartguide.model.Building;

@Mapper
public interface BuildingMapper {
	
	// 비콘 정보를 이용하여 비콘이 속한 빌딩 id를 가져옴
	@Select("SELECT building_id FROM beacon WHERE uuid=#{uuid} AND major=#{major} AND minor=#{minor}")
	Long selectBuildingIdByUuidAndMajorAndMinor(@Param("uuid") String uuid, @Param("major") String major, @Param("minor") String minor);
	
	@Select("SELECT DISTINCT building.* FROM building_and_group, building, `group`, user"
			+ " WHERE building_and_group.building_id=building.id"
			+ " AND building_and_group.group_id=group.id"
			+ " AND group.large_name=("
			+ " SELECT group.large_name"
			+ " FROM user, `group`"
			+ " WHERE user.username=#{username}"
			+ " AND user.group_id=group.id"
			+ ")")
	List<Building> selectBuildingByUsername(@Param("username") String username);
	
	// 해당 유저가 속한 그룹의 대그룹에 속한 그룹들의 빌딩 Id 들을 가져옴
	@Select("SELECT * FROM building WHERE id"
			+ " IN (SELECT building_id FROM building_and_group WHERE group_id"
			+ " IN (SELECT id FROM `group` WHERE large_name="
			+ "(SELECT large_name FROM `group` WHERE id="
			+ "(SELECT group_id FROM user WHERE username=#{username}))))")
	List<Building> selectBuildingIdsByUsername(@Param("username") String username);
	
}
