package com.ssafy.whereismyhome.apartment.dto;

import com.ssafy.whereismyhome.apartment.entity.ApartmentInfo;
import com.ssafy.whereismyhome.apartment.entity.DongCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AptInfo {
	private Long aptCode;
	private String apartmentName;
	private String dongCode;
	private String dongName;
	private Integer buildYear;
	private BigDecimal lng;
	private BigDecimal lat;


	public AptInfo(){

	}

	public AptInfo(ApartmentInfo apartmentInfo){
		this.aptCode = apartmentInfo.getAptCode();
		this.apartmentName = apartmentInfo.getApartmentName();
		this.dongCode = apartmentInfo.getDongCode().getDongCode();
		this.dongName = apartmentInfo.getDong();
		this.buildYear = apartmentInfo.getBuildYear();
		this.lng = apartmentInfo.getLng();
		this.lat = apartmentInfo.getLat();
	}

}
