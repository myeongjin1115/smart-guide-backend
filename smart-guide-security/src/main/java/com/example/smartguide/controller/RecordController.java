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
import com.example.smartguide.dto.LongToJson;
import com.example.smartguide.dto.RecordReq;
import com.example.smartguide.dto.RecordRes;
import com.example.smartguide.model.Building;
import com.example.smartguide.model.Record;
import com.example.smartguide.service.NoticeService;
import com.example.smartguide.service.RecordService;

@RestController
@RequestMapping("/record")
public class RecordController {

	@Autowired
	private RecordService recordService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	// 誘몄궗�슜
	@GetMapping("/user/{userId}")
	public List<Record> readUserRecords(@PathVariable("userId") Long userId) {
		return recordService.getUserRecords(userId);
	}
	
	@GetMapping("/building")
	public List<Building> readBuilding(@RequestHeader("Authorization") String token) {
		token = token.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		return recordService.getBuildingListByUsername(username);
	}
	
	@GetMapping("/building/{buildingId}")
	public List<RecordRes> readBuildingRecords(@PathVariable("buildingId") Long buildingId) {
		return recordService.getBuildingRecords(buildingId);
	}
	
	// 誘몄궗�슜
	@GetMapping("/all")
	public List<Record> readAllRecords() {
		return recordService.getAllRecords();
	}
	
	@PostMapping("")
	public LongToJson createUserRecord(@RequestHeader(value = "Authorization") String token, @RequestBody RecordReq recordReq) {
		token = token.substring(7);
		recordService.setRecord(token, recordReq);
		LongToJson longToJson = new LongToJson();
		longToJson.setLastNoticeId(noticeService.getLastNoticeId(token, recordReq));
		System.out.println(recordReq.getUuid() + "  /  " + recordReq.getMajor()+ "  /  "  + recordReq.getMinor()+ "  /  "  + recordReq.getCreatedAt());
		return longToJson;
	}
	
}
