package com.ssafy.whereismyhome.user.controller;

import com.ssafy.whereismyhome.config.jwt.JwtTokenUtil;
import com.ssafy.whereismyhome.user.dto.JoinDto;
import com.ssafy.whereismyhome.user.dto.LoginDto;
import com.ssafy.whereismyhome.user.dto.TokenDto;
import com.ssafy.whereismyhome.user.dto.UserInfo;
import com.ssafy.whereismyhome.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/sign-up")
    public ResponseEntity<String> join(@RequestBody JoinDto joinDto) {
        userService.join(joinDto);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/admin/sign-up")
    public String joinAdmin(@RequestBody JoinDto joinDto) {
        userService.joinAdmin(joinDto);
        return "어드민 회원 가입 완료";
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @GetMapping("/members/{userId}")
    public UserInfo getMemberInfo(@PathVariable String userId) {
        UserInfo userInfo = userService.getMemberInfo(userId);
        System.out.println(userInfo.toString());
        return userInfo;
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestHeader("RefreshToken") String refreshToken) {
        return ResponseEntity.ok(userService.reissue(refreshToken));
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String accessToken,
                       @RequestHeader("RefreshToken") String refreshToken) {
        String username = jwtTokenUtil.getUsername(resolveToken(accessToken));
        userService.logout(TokenDto.of(accessToken, refreshToken), username);
    }

    private String resolveToken(String accessToken) {
        return accessToken.substring(7);
    }
}