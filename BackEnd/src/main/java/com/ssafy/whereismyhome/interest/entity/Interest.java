package com.ssafy.whereismyhome.interest.entity;

import com.ssafy.whereismyhome.apartment.entity.DongCode;
import com.ssafy.whereismyhome.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestCnt;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;



    @ManyToOne
    @JoinColumn(name = "dongcode")
    private DongCode dongCode;



}
