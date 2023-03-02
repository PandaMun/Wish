package com.ssafy.whereismyhome.apartment.dto;

import com.ssafy.whereismyhome.apartment.entity.DongCode;
import lombok.Data;

@Data
public class ResponseDongDto {

    private String dongCode;
    private String sidoName;
    private String gugunName;
    private String dongName;


    public ResponseDongDto(DongCode dongCode){
        this.dongCode = dongCode.getDongCode();
        this.sidoName = dongCode.getSidoName();
        this.gugunName = dongCode.getGugunName();
        this.dongName = dongCode.getDongName();
    }

}
