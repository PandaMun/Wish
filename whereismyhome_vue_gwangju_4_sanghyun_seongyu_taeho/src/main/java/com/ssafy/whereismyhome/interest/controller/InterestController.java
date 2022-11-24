package com.ssafy.whereismyhome.interest.controller;


import com.ssafy.whereismyhome.interest.dto.InterestRequestDto;
import com.ssafy.whereismyhome.interest.dto.UserInterestInfo;
import com.ssafy.whereismyhome.interest.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("wish/user/interest")
@RequiredArgsConstructor
public class InterestController {

	private final InterestService interestService;

	@GetMapping("/info/{userId}")
	private ResponseEntity<?> getInterest(@PathVariable String userId) {
		return interestService.getInterest(userId);
	}

	@PostMapping("/check")
	private ResponseEntity<?> checkColumn (@RequestBody InterestRequestDto interestRequestDto) {

		return interestService.check(interestRequestDto);
	}
	@PostMapping("/")
	private ResponseEntity<?> regist (@RequestBody InterestRequestDto interestRequestDto) {

		return interestService.registerUserInterest(interestRequestDto);
	}
//
//	@DeleteMapping("/")
//	private ResponseEntity<?> delete(HttpServletRequest req, HttpServletResponse resp) {
//		HttpSession session = req.getSession();
//		MemberDto userinfo = (MemberDto) session.getAttribute("userinfo");
//
//		if (userinfo == null) {
//			return new ResponseEntity<String>("로그인 후 이용 가능합니다.",HttpStatus.BAD_REQUEST);
//		}
//
//		String userId = userinfo.getUserId();
//		String dongCode = req.getParameter("dongCode");
//		int cnt = 0;
//		try {
//			cnt = interestService.deleteUserInterest(userId, dongCode);
//			if (cnt != 1) {
//				return new ResponseEntity<String>("관심 지역 삭제 실패.",HttpStatus.BAD_REQUEST);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<String>("관심 지역 삭제 중 에러 발생.",HttpStatus.BAD_REQUEST);
//		}
//
//		return new ResponseEntity<String>("삭제가 성공적으로 되었습니다.",HttpStatus.OK);
//	}
//
	@GetMapping("/{dongCode}") // 지도에 띄울 동으로 나온 아파트
	private ResponseEntity<?> detail(@PathVariable String dongCode){
		List<UserInterestInfo> list = new ArrayList<>();
		try {
			list = interestService.getInterestInfo(dongCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<UserInterestInfo>> (list, HttpStatus.OK);
	}

}
