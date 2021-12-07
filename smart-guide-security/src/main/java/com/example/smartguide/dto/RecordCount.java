package com.example.smartguide.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecordCount {
	private int recordCount;
	private int userCount;
}
