package com.example.smartguide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartguide.config.JwtTokenUtil;
import com.example.smartguide.model.Notice;
import com.example.smartguide.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	// 앱 미구현
	@GetMapping("/group")
	public List<Notice> getGroupNotices(@RequestParam("userId") Long userId) {
		return noticeService.getGroupNotices(userId);
	}
	
	@GetMapping("/building/{buildingId}")
    public List<Notice> getBuildingNotices(@RequestHeader("Authorization") String token, @PathVariable("buildingId") Long buildingId) {
        token = token.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return noticeService.getBuildingNotices(buildingId, username);
    }
	
	@PostMapping("")
	public void postNotice(@RequestBody Notice notice) {
		noticeService.setNotice(notice);
	}
	
}
