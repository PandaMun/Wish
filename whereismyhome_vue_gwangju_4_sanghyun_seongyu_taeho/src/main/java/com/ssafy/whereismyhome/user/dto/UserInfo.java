package com.ssafy.whereismyhome.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String userId;
    private String userPassword;
    private String username;
    private String nickname;
    private String email;
    private String address;

}
