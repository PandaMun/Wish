package com.ssafy.whereismyhome.apartment.controller;

import com.ssafy.whereismyhome.apartment.dto.AptDeal;
import com.ssafy.whereismyhome.apartment.dto.AptInfo;
import com.ssafy.whereismyhome.apartment.dto.MapLocation;
import com.ssafy.whereismyhome.apartment.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/wish/apartments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApartmentController {

	private final ApartmentService apartmentService;

	@GetMapping("/list")
	public ResponseEntity<?> getAptList(@RequestBody MapLocation mapLocation) {

		List<AptInfo> list = new ArrayList<>();
		try {
			list = apartmentService.getList(mapLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<AptInfo>>(list, HttpStatus.OK);
	}
	@GetMapping("/info")
	public ResponseEntity<?> getAptDetailInfo(@RequestBody Map<String, String> aptCode) {

		List<AptDeal> list = new ArrayList<>();

		try {
			list = apartmentService.getAptDeal(Long.parseLong(aptCode.get("aptCode")));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<AptDeal>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<AptDeal>>(list, HttpStatus.OK);
	}


	@PostMapping("/deal") // 아파트 거래내역 등록
	public ResponseEntity<?> regist(@RequestBody AptDeal aptDeal) {
		try {
			apartmentService.aptDeal(aptDeal); // 요청처리 정상 처리 : 메인화면으로 가기

		} catch (Exception e) { // 처리 실패하는 경우 에러페이지로 이동 e.printStackTrace();
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	@DeleteMapping("/deal") //아파트 거래내역 삭제
	public ResponseEntity<?> delItem(@RequestBody Map<String,String> aptCode) {
		try {
			apartmentService.delList(Long.parseLong(aptCode.get(aptCode)));

		} catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}


}