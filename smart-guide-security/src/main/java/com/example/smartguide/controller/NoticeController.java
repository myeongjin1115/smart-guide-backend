package com.example.smartguide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartguide.config.JwtTokenUtil;
import com.example.smartguide.dto.NoticePostReq;
import com.example.smartguide.model.Group;
import com.example.smartguide.model.Notice;
import com.example.smartguide.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	// 새로운 공지사항을 작성할 때 group 지정을 위해 group 반환
	@GetMapping("/create")
	public List<Group> getGroups(@RequestHeader("Authorization") String token) {
		token = token.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return noticeService.getGroups(username);
	}
	
	// 해당 유저가 속한 그룹의 공지사항을 반환
	@GetMapping("/group")
	public List<Notice> getGroupNotices(@RequestHeader("Authorization") String token) {
		token = token.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return noticeService.getGroupNotices(username);
	}
	
	// 유저가 빌딩 진입 시, 유저의 그룹을 확인하고 해당 빌딩의 공지사항을 반환함
	@GetMapping("/building/{buildingId}")
    public List<Notice> getBuildingNotices(@RequestHeader("Authorization") String token, @PathVariable("buildingId") Long buildingId) {
        token = token.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return noticeService.getBuildingNotices(buildingId, username);
    }
	
	// 공지사항 게시
	@PostMapping("")
	public void postNotice(@RequestHeader("Authorization") String token, @RequestBody NoticePostReq noticePostReq) {
		token = token.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		noticeService.setNotice(username, noticePostReq);
	}
	
}
