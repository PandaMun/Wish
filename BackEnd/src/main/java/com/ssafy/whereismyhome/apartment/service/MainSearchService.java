package com.ssafy.whereismyhome.apartment.service;

import com.ssafy.whereismyhome.apartment.dto.AptInfo;
import com.ssafy.whereismyhome.apartment.dto.AptNameInfo;
import com.ssafy.whereismyhome.apartment.dto.ResponseDongDto;
import com.ssafy.whereismyhome.apartment.entity.ApartmentInfo;
import com.ssafy.whereismyhome.apartment.entity.DongCode;
import com.ssafy.whereismyhome.apartment.repository.ApartmentInfoRepository;
import com.ssafy.whereismyhome.apartment.repository.DongCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainSearchService{
//
    private final DongCodeRepository dongCodeRepository;
    private final ApartmentInfoRepository apartmentInfoRepository;

    @Transactional
    public List<AptNameInfo> searchByAptName(String apartmentName) throws SQLException {
        List<AptNameInfo> responseAptInfoList = new ArrayList<>();
        List<ApartmentInfo> apartmentInfoList =  apartmentInfoRepository.findByApartmentName(apartmentName);
        for(int i = 0; i< apartmentInfoList.size(); i++ ){
            responseAptInfoList.add(new AptNameInfo(apartmentInfoList.get(i)));
        }
        return responseAptInfoList;
    }

    @Transactional
    public List<ResponseDongDto> searchByDongName(String dongName) throws SQLException {
        List<ResponseDongDto> responseDongDtoList = new ArrayList<>();
        List<DongCode> dongCodeList =  dongCodeRepository.findByDongNameIsContaining(dongName);
        for(int i = 0; i< dongCodeList.size(); i++ ){
            responseDongDtoList.add(new ResponseDongDto(dongCodeList.get(i)));
        }
        return responseDongDtoList;
    }

    @Transactional
    public List<AptInfo> searchByDongCode(String dongCode) throws SQLException {
        List<AptInfo> aptInfoList= new ArrayList<>();
        DongCode dong =  dongCodeRepository.findByDongCode(dongCode);
        if(dong == null) {
            return aptInfoList;
        }else{
        for(int i = 0; i< dong.getApartmentInfos().size(); i++ ){
            aptInfoList.add(new AptInfo(dong.getApartmentInfos().get(i)));
        }
        return aptInfoList;
    }}
}
