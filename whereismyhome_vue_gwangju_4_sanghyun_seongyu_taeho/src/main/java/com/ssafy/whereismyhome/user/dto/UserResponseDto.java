package com.ssafy.whereismyhome.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TokenInfo {
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long refreshTokenExpirationTime;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @ToString
    public static class MyPage{
        private String userId;
        private String address;
        private String phoneNumber;
        private String nickname;
        private String name;
        private String email;
    }
}
