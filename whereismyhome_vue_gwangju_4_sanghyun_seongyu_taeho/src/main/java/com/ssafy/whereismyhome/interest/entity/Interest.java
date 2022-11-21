package com.ssafy.whereismyhome.interest.entity;

import com.ssafy.whereismyhome.apartment.entity.DongCode;
import com.ssafy.whereismyhome.user.entity.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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
