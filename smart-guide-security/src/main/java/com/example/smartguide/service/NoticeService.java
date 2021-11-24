package com.example.smartguide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartguide.config.JwtTokenUtil;
import com.example.smartguide.dto.RecordReq;
import com.example.smartguide.mapper.BuildingMapper;
import com.example.smartguide.mapper.MemberMapper;
import com.example.smartguide.mapper.NoticeMapper;
import com.example.smartguide.model.Member;
import com.example.smartguide.model.Notice;

@Service
public class NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private BuildingMapper buildingMapper;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public List<Notice> getGroupNotices(Long userId) {
		Member user = memberMapper.selectUser(userId);
		
		return noticeMapper.selectNoticeByGroupId(user.getGroupId());
	}
	
	public List<Notice> getBuildingNotices(Long buildingId, Long userId) {
		return noticeMapper.selectNoticesByBuildingIdAndUserId(buildingId, userId);
	}
	
	public Long getLastNoticeId(String token, RecordReq recordReq) {
		String username = jwtTokenUtil.getUsernameFromToken(token);
		Long groupId = memberMapper.selectGroupIdByUsername(username);
		Long buildingId = buildingMapper.selectBuildingIdByUuidAndMajorAndMinor(recordReq.getUuid(), recordReq.getMajor(), recordReq.getMinor());
		Long result = noticeMapper.selectLatestNoticeIdByBuilding(groupId, buildingId);
		return result;
	}
	
	public void setNotice(Notice notice) {
		noticeMapper.insertNotice(notice);
	}
	
}
