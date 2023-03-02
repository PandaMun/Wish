package com.ssafy.whereismyhome.user.entity;

import com.ssafy.whereismyhome.interest.entity.Interest;
import com.ssafy.whereismyhome.notice.entity.Notice;
import com.ssafy.whereismyhome.qna.entity.Comment;
import com.ssafy.whereismyhome.qna.entity.Inquiry;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @Column(unique = true)
    private String Id;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    private String address;

    private String phoneNumber;

    private String name;

    private String nickname;


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notice> notices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Inquiry> inquiries;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Interest> interests;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void modifyPassword(String userId, String password){
        this.Id = userId;
        this.password = password;
    }
}
