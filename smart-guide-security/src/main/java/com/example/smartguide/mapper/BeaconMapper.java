package com.example.smartguide.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BeaconMapper {
	
	@Select("SELECT id FROM beacon WHERE uuid=#{uuid} AND major=#{major} AND minor=#{minor}")
	Long getBeaconIdByUuidAndMajorAndMinor(@Param("uuid") String uuid, @Param("major") String major, @Param("minor") String minor);
	
}
