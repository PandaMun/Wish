package com.ssafy.whereismyhome.interest.service;

import com.ssafy.whereismyhome.apartment.entity.ApartmentInfo;
import com.ssafy.whereismyhome.apartment.repository.ApartmentInfoRepository;
import com.ssafy.whereismyhome.apartment.repository.DongCodeRepository;
import com.ssafy.whereismyhome.interest.dto.UserInterestInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService{

    private final ApartmentInfoRepository apartmentInfoRepository;
    private final DongCodeRepository dongCodeRepository;
//    private final InterestRepository interestRepository;
//
//    @Override
//    public List<UserInterest> getUserInterestList(String userId) {
//        return interestRepository.getUserInterestList(userId);
//    }
//
    @Transactional
    public List<UserInterestInfo> getInterestInfo(String dongCode){
        List<UserInterestInfo> userInterestInfoList = new ArrayList<>();
        List<ApartmentInfo> apartmentInfoList = dongCodeRepository.getDongCodeByDongCode(dongCode).getApartmentInfos();
        for(int i = 0; i<apartmentInfoList.size(); i++){
            userInterestInfoList.add(new UserInterestInfo(apartmentInfoList.get(i)));
        }
        return userInterestInfoList;
    }
//
//    @Override
//    public int deleteUserInterest(String userId, String dongCode) {
//        return interestRepository.deleteUserInterest(userId, dongCode);
//    }
//
//    @Override
//    public int registerUserInterest(String userId, String sido, String gugun, String dong) {
//        return interestRepository.registerUserInterest(userId, sido, gugun, dong);
//    }

}
