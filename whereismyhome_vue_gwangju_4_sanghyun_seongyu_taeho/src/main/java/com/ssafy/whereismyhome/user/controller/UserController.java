package com.ssafy.whereismyhome.user.controller;

import com.ssafy.whereismyhome.config.jwt.JwtTokenProvider;
import com.ssafy.whereismyhome.user.dto.UserRequestDto;
import com.ssafy.whereismyhome.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wish/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDto.SignUp signUp) {
        // validation check
        return userService.signUp(signUp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto.Login login) {
        // validation check
        return userService.login(login);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody UserRequestDto.Reissue reissue) {
        // validation check
        return userService.reissue(reissue);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody UserRequestDto.Logout logout) {
        // validation check

        return userService.logout(logout);
    }
}