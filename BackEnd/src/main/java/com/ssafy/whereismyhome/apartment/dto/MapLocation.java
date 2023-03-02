package com.ssafy.whereismyhome.apartment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MapLocation {

    private BigDecimal swLat;
    private BigDecimal swLng;
    private BigDecimal neLat;
    private BigDecimal neLng;
    private int mapLevel;
}
