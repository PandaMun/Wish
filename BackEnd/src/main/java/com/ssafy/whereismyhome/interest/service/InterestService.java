package com.ssafy.whereismyhome.interest.service;

import com.ssafy.whereismyhome.apartment.dto.ResponseDongDto;
import com.ssafy.whereismyhome.apartment.entity.ApartmentInfo;
import com.ssafy.whereismyhome.apartment.entity.DongCode;
import com.ssafy.whereismyhome.apartment.repository.ApartmentInfoRepository;
import com.ssafy.whereismyhome.apartment.repository.DongCodeRepository;
import com.ssafy.whereismyhome.interest.dto.InterestRequestDto;
import com.ssafy.whereismyhome.interest.dto.UserInterestInfo;
import com.ssafy.whereismyhome.interest.entity.Interest;
import com.ssafy.whereismyhome.interest.repository.InterestRepository;
import com.ssafy.whereismyhome.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService{

    private final ApartmentInfoRepository apartmentInfoRepository;
    private final DongCodeRepository dongCodeRepository;
    private final InterestRepository interestRepository;
//
//    @Override
//    public List<UserInterest> getUserInterestList(String userId) {
//        return interestRepository.getUserInterestList(userId);
//    }
//
    @Transactional
    public List<UserInterestInfo> getInterestInfo(String dongCode){
        List<UserInterestInfo> userInterestInfoList = new ArrayList<>();
        List<ApartmentInfo> apartmentInfoList = new ArrayList<>();
        if(!dongCode.isEmpty()) {
            apartmentInfoList = dongCodeRepository.getDongCodeByDongCode(dongCode).getApartmentInfos();
        }for(int i = 0; i<apartmentInfoList.size(); i++){
            userInterestInfoList.add(new UserInterestInfo(apartmentInfoList.get(i)));
        }
        return userInterestInfoList;
    }
    @Transactional
    public ResponseEntity<List<ResponseDongDto>> getInterest(String userId){
        List<ResponseDongDto> responseDongDtoList = new ArrayList<>();
        List<Interest> interestsByUsers = interestRepository.findAllByUserId(userId);
        for(int i = 0; i<interestsByUsers.size(); i++){
            responseDongDtoList.add(new ResponseDongDto(interestsByUsers.get(i).getDongCode()));
        }
        return new ResponseEntity<List<ResponseDongDto>>(responseDongDtoList,HttpStatus.OK);
    }
//
//    @Override
//    public int deleteUserInterest(String userId, String dongCode) {
//        return interestRepository.deleteUserInterest(userId, dongCode);
//    }
//
    @Transactional
    public ResponseEntity<String> registerUserInterest(InterestRequestDto interestRequestDto) {
        Interest interest = Interest.builder()
                .dongCode(DongCode.builder().dongCode(interestRequestDto.getDongCode()).build())
                .user(User.builder().Id(interestRequestDto.getUserId()).build()).build();

        interestRepository.save(interest);
        return new ResponseEntity<String>("등록되었습니다.", HttpStatus.OK);

    }
    @Transactional
    public ResponseEntity<String> check(InterestRequestDto interestRequestDto) {
            if(interestRepository.CountByUserIdAndDongCode(interestRequestDto.getUserId(),interestRequestDto.getDongCode()).size() > 0){
                return new ResponseEntity<String>("존재합니다.", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("존재하지 않습니다.", HttpStatus.OK);
            }

               

    }


    public void deleteInterest(InterestRequestDto interestRequestDto){
        List<Interest> interests = interestRepository.CountByUserIdAndDongCode(interestRequestDto.getUserId(), interestRequestDto.getDongCode());
        interestRepository.deleteById(interests.get(0).getInterestCnt());
    }

}
