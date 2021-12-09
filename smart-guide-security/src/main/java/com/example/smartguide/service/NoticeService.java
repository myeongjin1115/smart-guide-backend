package com.example.smartguide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartguide.config.JwtTokenUtil;
import com.example.smartguide.dto.NoticePostReq;
import com.example.smartguide.dto.RecordReq;
import com.example.smartguide.mapper.BuildingMapper;
import com.example.smartguide.mapper.GroupMapper;
import com.example.smartguide.mapper.MemberMapper;
import com.example.smartguide.mapper.NoticeMapper;
import com.example.smartguide.model.Group;
import com.example.smartguide.model.Member;
import com.example.smartguide.model.Notice;
import com.example.smartguide.model.Role;

@Service
public class NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private BuildingMapper buildingMapper;
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public List<Group> getGroups(String username) {
		return groupMapper.selectGroupByLargeNameAndUsername(username);
	}
	
	public List<Notice> getGroupNotices(String username) {
		Member member = memberMapper.selectUserByUsername(username).orElseThrow(RuntimeException::new);
		if(member.getRole() == Role.ROLE_MANAGER) {
			return noticeMapper.selectNoticesByManagerGroupId(member.getGroupId());
		}
		return noticeMapper.selectNoticeByGroupId(member.getGroupId());
	}
	
	public List<Notice> getBuildingNotices(Long buildingId, String username) {
        return noticeMapper.selectNoticesByBuildingIdAndUsername(buildingId, username);
    }
	
	public Long getLastNoticeId(String token, RecordReq recordReq) {
		String username = jwtTokenUtil.getUsernameFromToken(token);
		Long groupId = memberMapper.selectGroupIdByUsername(username);
		Long buildingId = buildingMapper.selectBuildingIdByUuidAndMajorAndMinor(recordReq.getUuid(), recordReq.getMajor(), recordReq.getMinor());
		Long result = noticeMapper.selectLatestNoticeIdByBuilding(groupId, buildingId);
		return result;
	}
	
	public void setNotice(String username, NoticePostReq noticePostReq) {
		Member member = memberMapper.selectUserByUsername(username).orElseThrow(RuntimeException::new);
		List<Long> groupIds = noticePostReq.getGroupIds();
		Notice notice = Notice.builder()
				.title(noticePostReq.getTitle())
				.content(noticePostReq.getContent())
				.isPublic(noticePostReq.getIsPublic())
				.userId(member.getId())
				.build();
		
		noticeMapper.insertNotice(notice);
		if(noticePostReq.getIsPublic() == false) {
			for(Long groupId : groupIds) {
				noticeMapper.insertNoticeAndGroup(notice.getId(), groupId);
			}
		}
	}
	
}
