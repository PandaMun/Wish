package com.ssafy.whereismyhome.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class JoinDto {

    private String userId;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phoneNumber;
    private String address;

}
