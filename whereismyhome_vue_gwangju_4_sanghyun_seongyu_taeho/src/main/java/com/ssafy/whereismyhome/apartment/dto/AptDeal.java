package com.ssafy.whereismyhome.apartment.dto;

import com.ssafy.whereismyhome.apartment.entity.ApartmentDeal;
import lombok.Data;

@Data
public class AptDeal {
	private Long id;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;

	private String dealDate;
	private int dealDay;
	private String area;
	private String floor;
	private Long aptCode;


	public AptDeal(ApartmentDeal apartmentDeal) {
		this.id = apartmentDeal.getId();
		this.dealAmount = apartmentDeal.getDealAmount();
		this.dealYear = apartmentDeal.getDealYear();
		this.dealMonth = apartmentDeal.getDealMonth();
		this.dealDay = apartmentDeal.getDealDay();
		this.area = apartmentDeal.getArea();
		this.floor = apartmentDeal.getFloor();
		this.aptCode = apartmentDeal.getApartmentInfo().getAptCode();
		this.dealDate = new StringBuilder().append(apartmentDeal.getDealYear()).append(".").append(apartmentDeal.getDealMonth()).append(".").append(apartmentDeal.getDealDay()).toString();
	}
}
