package com.example.smartguide.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.smartguide.dto.MediumGroup;
import com.example.smartguide.dto.SignUpReq;
import com.example.smartguide.dto.SmallGroup;
import com.example.smartguide.model.Group;

@Mapper
public interface GroupMapper {

	@Select("SELECT * FROM `group` WHERE id=#{id}")
	Group selectGroupById(@Param("id") Long id);

	@Select("SELECT id FROM `group` WHERE large_name=#{signUpReq.large} AND medium_name=#{signUpReq.medium} AND small_name=#{signUpReq.small}")
	Long selectGroupIdByLargeAndMediumAndSmall(@Param("signUpReq") SignUpReq signUpReq);

	@Select("SELECT * FROM `group` WHERE large_name=(" + "SELECT `group`.large_name FROM `group`, user"
			+ " WHERE user.username=#{username} AND `group`.id=user.group_id)")
	List<Group> selectGroupByLargeNameAndUsername(@Param("username") String username);

	@Select("SELECT DISTINCT medium_name FROM `group` WHERE NOT medium_name IS NULL AND large_name=("
			+ "SELECT `group`.large_name FROM `group`, user"
			+ " WHERE user.username=#{username} AND `group`.id=user.group_id)")
	List<MediumGroup> selectGroupMediumByLargeNameAndUsername(@Param("username") String username);

	@Select("SELECT id, small_name FROM `group` WHERE NOT small_name IS NULL AND large_name=("
			+ "SELECT `group`.large_name FROM `group`, user"
			+ " WHERE user.username=#{username} AND `group`.id=user.group_id)" + " AND medium_name=#{mediumName}")
	List<SmallGroup> selectGroupSmallByMediumNameAndLargeNameAndUsername(@Param("username") String username,
			@Param("mediumName") String mediumName);
}
