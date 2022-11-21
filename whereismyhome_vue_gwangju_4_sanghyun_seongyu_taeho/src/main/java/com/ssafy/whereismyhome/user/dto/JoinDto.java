package com.ssafy.whereismyhome.user.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinDto {

    private String userId;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String phoneNumber;
    private String address;

}
