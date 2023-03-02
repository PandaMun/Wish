package com.ssafy.whereismyhome.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserRequestDto {

    @ToString
    @Getter
    @Setter
    public static class SignUp {

        private String userId;
        private String address;
        private String phoneNumber;
        private String nickname;
        private String name;
        private String email;
        private String password;
    }

    @ToString
    @Getter
    @Setter
    public static class Login {
        private String userId;
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(userId, password);
        }
    }

    @Getter
    @Setter
    public static class Reissue {
        private String accessToken;

        private String refreshToken;
    }

    @Getter
    @Setter
    public static class Logout {
        private String accessToken;

        private String refreshToken;
    }

    @Getter
    @Setter
    public static class myPage {
        private String accessToken;

        private String refreshToken;
    }
    @Getter
    @Setter
    public static class Authority{
        private String accessToken;
    }
}
