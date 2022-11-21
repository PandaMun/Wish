package com.ssafy.whereismyhome.apartment.service;

import com.ssafy.whereismyhome.apartment.dto.AptDeal;
import com.ssafy.whereismyhome.apartment.dto.AptInfo;
import com.ssafy.whereismyhome.apartment.dto.MapLocation;
import com.ssafy.whereismyhome.apartment.entity.ApartmentDeal;
import com.ssafy.whereismyhome.apartment.entity.ApartmentInfo;
import com.ssafy.whereismyhome.apartment.repository.ApartmentDealRepository;
import com.ssafy.whereismyhome.apartment.repository.ApartmentInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ApartmentService {

	private final ApartmentInfoRepository apartmentInfoRepository;
	private final ApartmentDealRepository apartmentDealRepository;

	@Transactional
	public List<AptInfo> getList(MapLocation mapLocation) throws Exception {
		if(mapLocation.getMapLevel() < 5) {
			List<AptInfo> aptInfoList = new ArrayList<>();
			List<ApartmentInfo> apartmentInfoList = apartmentInfoRepository.findLocation(mapLocation.getSwLng(), mapLocation.getNeLng(), mapLocation.getSwLat(), mapLocation.getNeLat());
			for(int i = 0; i< apartmentInfoList.size(); i++){
				aptInfoList.add(new AptInfo(apartmentInfoList.get(i)));
			}
			return aptInfoList;
		}
		return new ArrayList<>();
	}



	public List<AptDeal> getAptDeal(Long aptCode) throws Exception {

		List<AptDeal> aptDealList = new ArrayList<>();
		List<ApartmentDeal> apartmentDealList =  apartmentInfoRepository.findAllByAptCode(aptCode).getApartmentDeals();
		for(int i = 0; i<apartmentDealList.size(); i++){
			aptDealList.add(new AptDeal(apartmentDealList.get(i)));
		}
		return aptDealList;
	}


	@Transactional
	public ApartmentDeal aptDeal(AptDeal aptDeal) throws Exception {
		return apartmentDealRepository.save(ApartmentDeal.builder()
				.apartmentInfo(ApartmentInfo.builder().aptCode(aptDeal.getAptCode()).build())
				.dealAmount(aptDeal.getDealAmount())
				.dealMonth(aptDeal.getDealMonth())
				.dealDay(aptDeal.getDealDay())
				.area(aptDeal.getArea())
				.floor(aptDeal.getFloor())
				.build());

	}

	@Transactional
	public void delList(Long id) {
		apartmentDealRepository.deleteById(id);
	}

}
