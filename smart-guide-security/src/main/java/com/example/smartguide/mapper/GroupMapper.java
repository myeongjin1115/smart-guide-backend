package com.example.smartguide.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.smartguide.model.Group;

@Mapper
public interface GroupMapper {

	@Select("SELECT * FROM `group` WHERE id=#{id}")
	Group selectGroupById(@Param("id") Long id);
	
}
