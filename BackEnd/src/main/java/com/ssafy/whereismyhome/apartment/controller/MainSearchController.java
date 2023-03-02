package com.ssafy.whereismyhome.apartment.controller;

import com.ssafy.whereismyhome.apartment.dto.AptInfo;
import com.ssafy.whereismyhome.apartment.dto.AptNameInfo;
import com.ssafy.whereismyhome.apartment.dto.ResponseDongDto;
import com.ssafy.whereismyhome.apartment.entity.DongCode;
import com.ssafy.whereismyhome.apartment.service.ApartmentService;
import com.ssafy.whereismyhome.apartment.service.MainSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainSearchController {

    private final MainSearchService mainSearchService;

    @GetMapping("/apartments/{aptName}")
    public ResponseEntity<?> searchByAptName(@PathVariable String aptName){

        List<AptNameInfo> aptNameInfoList = new ArrayList<>();
        if(aptName.isEmpty()){
            return new ResponseEntity<List<AptNameInfo>>(new ArrayList<>(), HttpStatus.OK);
        }else{
            try{
                aptNameInfoList = mainSearchService.searchByAptName(aptName);
            }catch (Exception e){
                return new ResponseEntity<String>("조회중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<List<AptNameInfo>>(aptNameInfoList, HttpStatus.OK);
        }

    }

    @GetMapping("/dongs/{dongName}")
    public ResponseEntity<?> searchBydongName(@PathVariable String dongName){

        List<ResponseDongDto> dongCodeList = new ArrayList<>();
        if(dongName.isEmpty()){
            return new ResponseEntity<List<DongCode>>(new ArrayList<>(), HttpStatus.OK);
        }else{
            try{
                dongCodeList = mainSearchService.searchByDongName(dongName);
            }catch (Exception e){
                return new ResponseEntity<String>("조회중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<List<ResponseDongDto>>(dongCodeList, HttpStatus.OK);
        }
    }

    @GetMapping("/aptinfo/{dongCode}")
    public ResponseEntity<?> searchBydongCode(@PathVariable String dongCode){

        List<AptInfo> aptInfoList = new ArrayList<>();
        if(dongCode.isEmpty()){
            return new ResponseEntity<List<DongCode>>(new ArrayList<>(), HttpStatus.OK);
        }else{
            try{
                aptInfoList = mainSearchService.searchByDongCode(dongCode);
            }catch (Exception e){
                return new ResponseEntity<String>("조회중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<List<AptInfo>>(aptInfoList, HttpStatus.OK);
        }
    }
}
