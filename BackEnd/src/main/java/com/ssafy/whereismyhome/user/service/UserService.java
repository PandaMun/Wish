package com.ssafy.whereismyhome.user.service;

import com.ssafy.whereismyhome.config.enums.Authority;
import com.ssafy.whereismyhome.config.jwt.JwtTokenProvider;
import com.ssafy.whereismyhome.config.security.SecurityUtil;
import com.ssafy.whereismyhome.user.dto.Response;
import com.ssafy.whereismyhome.user.dto.UserRequestDto;
import com.ssafy.whereismyhome.user.dto.UserResponseDto;
import com.ssafy.whereismyhome.user.entity.User;
import com.ssafy.whereismyhome.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Response response;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate redisTemplate;
    @Transactional
    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp) {
        if (userRepository.existsById(signUp.getUserId())) {
            return response.fail("이미 회원가입된 아이디입니다.", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .Id(signUp.getUserId())
                .name(signUp.getName())
                .address(signUp.getAddress())
                .nickname(signUp.getNickname())
                .phoneNumber(signUp.getPhoneNumber())
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .roles(Collections.singletonList(Authority.ROLE_USER.name()))
                .build();
        userRepository.save(user);

        return response.success("success");
    }
    @Transactional
    public ResponseEntity<?> login(UserRequestDto.Login login) {

        if (userRepository.findById(login.getUserId()).orElse(null) == null) {
            return response.fail("해당하는 유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        // 4. RefreshToken Redis 저장 (expirationTime 설정을 통해 자동 삭제 처리)
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);
        return response.success(tokenInfo, "success", HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<?> reissue(UserRequestDto.Reissue reissue) {
        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());


        // 3. Redis 에서 User email 을 기반으로 저장된 Refresh Token 값을 가져옵니다.
        String refreshToken = (String)redisTemplate.opsForValue().get("RT:" + authentication.getName());
        // (추가) 로그아웃되어 Redis 에 RefreshToken 이 존재하지 않는 경우 처리
        if(ObjectUtils.isEmpty(refreshToken)) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
        if(!refreshToken.equals(reissue.getRefreshToken())) {
            return response.fail("Refresh Token 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 4. 새로운 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 5. RefreshToken Redis 업데이트
        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return response.success(tokenInfo, "success", HttpStatus.OK);
    }
    @Transactional
    public void userInfo(UserRequestDto.myPage myPage){
        // 1. Access Token 검증
//        if (!jwtTokenProvider.validateToken(myPage.getAccessToken())) {
//            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
//        }

        // 2. Access Token 에서 User id 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(myPage.getAccessToken());



    }

    @Transactional
    public ResponseEntity<?> logout(UserRequestDto.Logout logout) {
        // 1. Access Token 검증
        if (!jwtTokenProvider.validateToken(logout.getAccessToken())) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(logout.getAccessToken());

        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            // Refresh Token 삭제
            redisTemplate.delete("RT:" + authentication.getName());
        }

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        Long expiration = jwtTokenProvider.getExpiration(logout.getAccessToken());
        redisTemplate.opsForValue()
                .set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);
        log.info("logout");
        return response.success("success");
    }
    @Transactional
    public void modifyPassword(UserRequestDto.Login login){
        User user = userRepository.findById(login.getUserId()).get();
        user.modifyPassword(login.getUserId(),passwordEncoder.encode(login.getPassword()));
    }
    public ResponseEntity<?> authority(UserRequestDto.Authority authority) {
        if (!jwtTokenProvider.validateToken(authority.getAccessToken())) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(authority.getAccessToken());
        return response.success("success");
    }
    @Transactional
    public ResponseEntity<?> findById(String userId){
        User user = userRepository.findById(userId).get();
        UserResponseDto.MyPage userInfo= UserResponseDto.MyPage.builder()
                .userId(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .address(user.getAddress())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();

        System.out.println(userInfo.toString());
        return response.userInfo(userInfo,"success",HttpStatus.OK);
    }
}
