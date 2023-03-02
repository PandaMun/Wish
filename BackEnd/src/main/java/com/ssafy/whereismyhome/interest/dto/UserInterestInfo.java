package com.ssafy.whereismyhome.interest.dto;

import com.ssafy.whereismyhome.apartment.entity.ApartmentInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserInterestInfo {
	private int buildYear;

	private String roadAddress;

	private String jibunAddress;
	private String apartmentName;
	private BigDecimal lng;
	private BigDecimal lat;

	public UserInterestInfo(ApartmentInfo apartmentInfo) {
		this.buildYear = apartmentInfo.getBuildYear();
		this.roadAddress = apartmentInfo.getRoadName() + apartmentInfo.getRoadNameBonbun() + apartmentInfo.getRoadNameBubun();
		this.jibunAddress = apartmentInfo.getDong() + apartmentInfo.getJibun();
		this.apartmentName = apartmentInfo.getApartmentName();
		this.lng = apartmentInfo.getLng();
		this.lat = apartmentInfo.getLat();
	}



}
