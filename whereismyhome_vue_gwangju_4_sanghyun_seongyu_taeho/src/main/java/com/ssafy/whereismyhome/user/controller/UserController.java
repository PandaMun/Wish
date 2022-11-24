package com.ssafy.whereismyhome.user.controller;

import com.ssafy.whereismyhome.config.jwt.JwtTokenProvider;
import com.ssafy.whereismyhome.user.dto.Response;
import com.ssafy.whereismyhome.user.dto.UserRequestDto;
import com.ssafy.whereismyhome.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final Response response;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDto.SignUp signUp) {
        System.out.println(signUp.toString());
        // validation check
        return userService.signUp(signUp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto.Login login) {
        // validation check
        return userService.login(login);
    }
    @GetMapping("/user/info/{userId}")
    public ResponseEntity<?> userInfo(@PathVariable String userId){
        return userService.findById(userId);
    }

    @PutMapping("/user/password")
    public ResponseEntity<?> modifyPassword(@RequestBody UserRequestDto.Login login){
        try{
            System.out.println(login.toString());
            userService.modifyPassword(login);
        }catch (Exception e){
            return response.fail("오류가 발생하였습니다.",HttpStatus.BAD_REQUEST);
        }
        return response.success("success");
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request) {
        // validation check
        UserRequestDto.Reissue reissue = new UserRequestDto.Reissue();
        reissue.setAccessToken(request.getHeader("accessToken"));
        reissue.setRefreshToken(request.getHeader("refreshToken"));
        return userService.reissue(reissue);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // validation check
        UserRequestDto.Logout logout = new UserRequestDto.Logout();
        logout.setAccessToken(request.getHeader("accessToken"));

        return userService.logout(logout);
    }
    @PostMapping("/my-page")
    public void logout(@RequestBody UserRequestDto.myPage myPage) {
        // validation check


        userService.userInfo(myPage);
    }
    @PostMapping("/user/authority")
    public ResponseEntity<?> getAuthority(HttpServletRequest request){
        UserRequestDto.Authority authority= new UserRequestDto.Authority();
        authority.setAccessToken(request.getHeader("accessToken"));

        return userService.authority(authority);

    }
}