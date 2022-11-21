package com.ssafy.whereismyhome.notice.controller;


import com.ssafy.whereismyhome.notice.dto.NoticeDto;
import com.ssafy.whereismyhome.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wish/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/")
    public ResponseEntity<?> getList() throws SQLException {
        List<NoticeDto> noticeList = new ArrayList<>();
        try {
            noticeList = noticeService.getNoticeList();
        }catch(Exception e){
            return new ResponseEntity<String>("조회중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<NoticeDto>>(noticeList, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody NoticeDto noticeDto) throws SQLException {
        try {
            noticeService.addNotice(noticeDto);
        }catch(Exception e){
            return new ResponseEntity<String>("에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("등록되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOnes(@PathVariable("id") String id) throws SQLException{
        NoticeDto notice = new NoticeDto();
        try {
            notice = noticeService.getNotice(Long.parseLong(id));
        }catch (Exception e){
            return new ResponseEntity<String>("조회중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<NoticeDto>(notice,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody NoticeDto noticeDto) throws SQLException {
        try {
            noticeService.updateNotice(noticeDto);
        }catch(Exception e){
            return new ResponseEntity<String>("수정중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("수정되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws SQLException{
        try {
            noticeService.deleteNotice(Long.parseLong(id));
        }catch(Exception e){
            return new ResponseEntity<String>("삭제중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("삭제 되었습니다.", HttpStatus.OK);
    }
}
