package com.ssafy.whereismyhome.apartment.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "houseinfo")
public class ApartmentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aptCode;

    private int buildYear;
    private String roadName;
    private String roadNameBonbun;
    private String roadNameBubun;
    private String roadNameSeq;
    private String roadNameBasementCode;
    private String roadNameCode;
    private String dong;
    private String bonbun;
    private String bubun;
    private String sigunguCode;
    private String eubmyundongCode;
    private String landCode;
    private String apartmentName;
    private String jibun;
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal lng;
    @Column(precision = 15, scale = 12, nullable = false)
    private BigDecimal lat;

    @OneToMany(mappedBy = "apartmentInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ApartmentDeal> apartmentDeals;

    @ManyToOne
    @JoinColumn(name="dong_code")
    private DongCode dongCode;

}