package com.example.smartguide.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.smartguide.model.Notice;

@Mapper
public interface NoticeMapper {
	
	@Select("SELECT * FROM notice WHERE id IN"
			+ " (SELECT notice_id FROM notice_and_group WHERE group_id=#{groupId})")
	List<Notice> selectNoticeByGroupId(@Param("groupId") Long groupId);
	
	@Select("SELECT DISTINCT notice.* FROM notice, notice_and_group, building_and_group, user\r\n"
			+ " WHERE building_and_group.building_id=#{buildingId}\r\n"
			+ " AND notice_and_group.group_id=building_and_group.group_id\r\n"
			+ " AND (notice.is_public=1 OR user.username=#{username} AND user.group_id=notice_and_group.group_id)\r\n"
			+ " AND notice.id=notice_and_group.notice_id\r\n"
			+ " ORDER BY id ASC")
    List<Notice> selectNoticesByBuildingIdAndUsername(@Param("buildingId") Long buildingId, @Param("username") String username);
	
	@Select("SELECT notice.id FROM notice, notice_and_group, building_and_group"
			+ " WHERE building_and_group.building_id=#{buildingId}"
			+ " AND building_and_group.group_id=notice_and_group.group_id"
			+ " AND notice_and_group.notice_id=notice.id"
			+ " AND (notice.is_public=1 OR notice_and_group.group_id=#{groupId})"
			+ " ORDER BY notice.id DESC"
			+ " LIMIT 1")
	Long selectLatestNoticeIdByBuilding(@Param("groupId") Long groupId, @Param("buildingId") Long buildingId);
	
	@Insert("INSERT INTO notice(title, content, is_pubilc) VALUES(#{notice.title}, #{notice.content}, #{notice.isPublic})")
	int insertNotice(@Param("notice") Notice notice);
	
}
