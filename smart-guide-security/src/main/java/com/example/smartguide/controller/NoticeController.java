package com.example.smartguide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartguide.model.Notice;
import com.example.smartguide.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/group")
	public List<Notice> getGroupNotices(@RequestParam("userId") Long userId) {
		return noticeService.getGroupNotices(userId);
	}
	
	@GetMapping("/building")
	public List<Notice> getBuildingNotices(@RequestParam("buildingId") Long buildingId, @RequestParam("userId") Long userId) {
		return noticeService.getBuildingNotices(buildingId, userId);
	}
	
	@PostMapping("")
	public void postNotice(@RequestBody Notice notice) {
		noticeService.setNotice(notice);
	}
	
}
