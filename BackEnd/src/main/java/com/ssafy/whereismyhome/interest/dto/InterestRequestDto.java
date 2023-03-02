package com.ssafy.whereismyhome.interest.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class InterestRequestDto {
    private String userId;
    private String dongCode;
}
