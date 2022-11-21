package com.ssafy.whereismyhome.user.dto;

import lombok.Builder;

@Builder
public class UserInfo {

    private String userId;
    private String userPassword;
    private String username;
    private String nickname;
    private String email;
    private String address;

}
