package com.ssafy.whereismyhome.user.entity;

import com.ssafy.whereismyhome.interest.entity.Interest;
import com.ssafy.whereismyhome.notice.entity.Notice;
import com.ssafy.whereismyhome.qna.entity.Comment;
import com.ssafy.whereismyhome.qna.entity.Inquiry;
import com.ssafy.whereismyhome.user.dto.JoinDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toList;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User {

    @Id
    @Column(unique = true)
    private String userId;

    @Column(unique = true)
    private String userKey;


    private String userPassword;

    private String address;

    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private String username;

    private String nickname;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notice> notices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Inquiry> inquiries;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Interest> interests;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();

    public static User ofUser(JoinDto joinDto) {
        User user = User.builder()
                .userKey(UUID.randomUUID().toString())
                .userId(joinDto.getUserId())
                .email(joinDto.getEmail())
                .userPassword(joinDto.getPassword())
                .username(joinDto.getUsername())
                .nickname(joinDto.getNickname())
                .phoneNumber(joinDto.getPhoneNumber())
                .address(joinDto.getAddress())
                .build();
        user.addAuthority(Authority.ofUser(user));
        return user;
    }

    public static User ofAdmin(JoinDto joinDto) {
        User user = User.builder()
                .userKey(UUID.randomUUID().toString())
                .userId(joinDto.getUserId())
                .email(joinDto.getEmail())
                .userPassword(joinDto.getPassword())
                .username(joinDto.getUsername())
                .nickname(joinDto.getNickname())
                .phoneNumber(joinDto.getPhoneNumber())
                .address(joinDto.getAddress())
                .build();
        user.addAuthority(Authority.ofAdmin(user));
        return user;
    }

    private void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public List<String> getRoles() {
        return authorities.stream()
                .map(Authority::getRole)
                .collect(toList());
    }

}
