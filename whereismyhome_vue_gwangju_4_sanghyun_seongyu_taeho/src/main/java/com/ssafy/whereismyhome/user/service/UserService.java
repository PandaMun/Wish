package com.ssafy.whereismyhome.user.service;

import com.ssafy.whereismyhome.config.jwt.JwtExpirationEnums;
import com.ssafy.whereismyhome.config.jwt.JwtTokenUtil;
import com.ssafy.whereismyhome.config.redis.*;
import com.ssafy.whereismyhome.user.dto.JoinDto;
import com.ssafy.whereismyhome.user.dto.LoginDto;
import com.ssafy.whereismyhome.user.dto.TokenDto;
import com.ssafy.whereismyhome.user.dto.UserInfo;
import com.ssafy.whereismyhome.user.entity.User;
import com.ssafy.whereismyhome.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public void join(JoinDto joinDto) {
        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));
        memberRepository.save(User.ofUser(joinDto));
    }

    public void joinAdmin(JoinDto joinDto) {
        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));
        memberRepository.save(User.ofAdmin(joinDto));
    }

    // 1
    public TokenDto login(LoginDto loginDto) {
        User user = memberRepository.findById(loginDto.getUserId()).orElseThrow(() -> new NoSuchElementException("회원이 없습니다."));
        checkPassword(loginDto.getUserPassword(), user.getUserPassword());

        String userId = user.getUserId();
        String accessToken = jwtTokenUtil.generateAccessToken(userId);
        RefreshToken refreshToken = saveRefreshToken(userId);
        return TokenDto.of(accessToken, refreshToken.getRefreshToken());
    }

    private void checkPassword(String rawPassword, String findMemberPassword) {
        if (!passwordEncoder.matches(rawPassword, findMemberPassword)) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
    }

    private RefreshToken saveRefreshToken(String userId) {
        return refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(userId,
                jwtTokenUtil.generateRefreshToken(userId), JwtExpirationEnums.REFRESH_TOKEN_EXPIRATION_TIME.getValue()));
    }

    // 2
    public UserInfo getMemberInfo(String userId) {
        User user = memberRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("회원이 없습니다."));
        if (!user.getUserId().equals(getCurrentUsername())) {
            throw new IllegalArgumentException("회원 정보가 일치하지 않습니다.");
        }
        return UserInfo.builder()
                .address(user.getAddress())
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    // 4
    @CacheEvict(value = CacheKey.USER, key = "#username")
    public void logout(TokenDto tokenDto, String userId) {
        String accessToken = resolveToken(tokenDto.getAccessToken());
        long remainMilliSeconds = jwtTokenUtil.getRemainMilliSeconds(accessToken);
        refreshTokenRedisRepository.deleteById(userId);
        logoutAccessTokenRedisRepository.save(LogoutAccessToken.of(accessToken, userId, remainMilliSeconds));
    }

    private String resolveToken(String token) {
        return token.substring(7);
    }

    // 3
    public TokenDto reissue(String refreshToken) {
        refreshToken = resolveToken(refreshToken);
        String userId = getCurrentUsername();
        RefreshToken redisRefreshToken = refreshTokenRedisRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        if (refreshToken.equals(redisRefreshToken.getRefreshToken())) {
            return reissueRefreshToken(refreshToken, userId);
        }
        throw new IllegalArgumentException("토큰이 일치하지 않습니다.");
    }

    private String getCurrentUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }

    private TokenDto reissueRefreshToken(String refreshToken, String userId) {
        if (lessThanReissueExpirationTimesLeft(refreshToken)) {
            String accessToken = jwtTokenUtil.generateAccessToken(userId);
            return TokenDto.of(accessToken, saveRefreshToken(userId).getRefreshToken());
        }
        return TokenDto.of(jwtTokenUtil.generateAccessToken(userId), refreshToken);
    }

    private boolean lessThanReissueExpirationTimesLeft(String refreshToken) {
        return jwtTokenUtil.getRemainMilliSeconds(refreshToken) < JwtExpirationEnums.REISSUE_EXPIRATION_TIME.getValue();
    }
}

