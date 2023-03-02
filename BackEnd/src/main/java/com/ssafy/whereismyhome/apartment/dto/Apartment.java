package com.ssafy.whereismyhome.apartment.dto;

import lombok.Data;

// sql.append("select apartmentName, dealAmount, dealDay, area, floor, dongCode, aptCode\n");
@Data
public class Apartment {
	private String apartmentName;
	private String dealAmount;
	private int dealDay;
	private double area;
	private int floor;
	private String dongCode;
	private Long aptCode;
	
}
