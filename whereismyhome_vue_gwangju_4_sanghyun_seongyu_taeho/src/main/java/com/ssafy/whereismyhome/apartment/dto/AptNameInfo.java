package com.ssafy.whereismyhome.apartment.dto;

import com.ssafy.whereismyhome.apartment.entity.ApartmentInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AptNameInfo {

    private String apartmentName;
    private Long aptCode;
    private String roadName;
    private int buildYear;
    private String roadNameBonbun;
    private String roadNameBubun;
    private String dongName;
    private String dongCode;
    private BigDecimal lng;
    private BigDecimal lat;



    public AptNameInfo(ApartmentInfo apartmentInfo) {
        this.apartmentName = apartmentInfo.getApartmentName();
        this.aptCode = apartmentInfo.getAptCode();
        this.roadName = apartmentInfo.getRoadName();
        this.buildYear = apartmentInfo.getBuildYear();
        this.roadNameBonbun = apartmentInfo.getRoadNameBonbun();
        this.roadNameBubun = apartmentInfo.getRoadNameBubun();
        this.dongName = apartmentInfo.getDong();
        this.lng = apartmentInfo.getLng();
        this.lat = apartmentInfo.getLat();
    }

}
