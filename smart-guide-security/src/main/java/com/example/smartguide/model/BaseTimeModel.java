package com.example.smartguide.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BaseTimeModel extends BaseCreatedTimeModel {
	private LocalDateTime updatedAt;
}
