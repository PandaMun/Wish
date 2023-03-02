package com.ssafy.whereismyhome.apartment.entity;

import com.ssafy.whereismyhome.interest.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DongCode {

    @Id
    private String dongCode;
    private String sidoName;
    private String gugunName;
    private String dongName;


    @OneToMany(mappedBy = "dongCode", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ApartmentInfo> apartmentInfos;


}
