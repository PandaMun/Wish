package com.ssafy.whereismyhome.interest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InterestRequestDto {
    private String userId;
    private String dongCode;
}
