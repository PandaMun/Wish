package com.ssafy.whereismyhome.qna.controller;


import com.ssafy.whereismyhome.qna.dto.CommentDto;
import com.ssafy.whereismyhome.qna.dto.InquiryDto;
import com.ssafy.whereismyhome.qna.entity.Inquiry;
import com.ssafy.whereismyhome.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QnaController {

    private final QnaService qnaService;


    @GetMapping("/inquiries") // 게시물 목록
    public ResponseEntity<?> getInquiries(){

        List<InquiryDto> inquiries = new ArrayList<>();
        try{
            inquiries = qnaService.getInquiries();
        }catch (Exception e){
            return new ResponseEntity<String>("불러오는중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<InquiryDto>>(inquiries,HttpStatus.OK);
    }

    @GetMapping("/inquiries/{id}") //게시물 상세 검색
    public ResponseEntity<?> getInquiry(@PathVariable String id){

        InquiryDto inquiryDto = new InquiryDto();
        try{
            inquiryDto = qnaService.getInquiry(Long.parseLong(id));
        }catch (Exception e){
            return new ResponseEntity<String>("불러오는중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<InquiryDto>(inquiryDto,HttpStatus.OK);
    }

    @PostMapping("/inquiries") // 게시물 등록
    public ResponseEntity<?> registInquiry(@RequestBody InquiryDto inquiry){

        try{
            qnaService.registInquiry(inquiry);
        }catch (Exception e){
            return new ResponseEntity<String>("등록중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("등록이 성공적으로 되었습니다.", HttpStatus.OK);
    }

    @PatchMapping("/inquiries/{id}") //게시물 수정
    public ResponseEntity<?> patchInquiry(@PathVariable String id, @RequestBody InquiryDto inquiryDto){
        System.out.println("update");
        try{
            qnaService.patchInquiry(inquiryDto);
        }catch (Exception e){
            return new ResponseEntity<String>("수정중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("수정이 성공적으로 되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/inquiries/{id}")
    public ResponseEntity<?> deleteInquiry(@PathVariable String id){
        try{
            qnaService.deleteInquiry(Long.parseLong(id));
        }catch (Exception e){
            return new ResponseEntity<String>("삭제중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("삭제가 성공적으로 되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getComment(@PathVariable String id){

        CommentDto commentDto = new CommentDto();
        try{
            commentDto = qnaService.getComment(id);
            if(commentDto == null){
                return new ResponseEntity<String>("답변이 없습니다.", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<String>("불러오는중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
    }

    @PostMapping("/admin/comment")
    public ResponseEntity<?> registComment(@RequestBody CommentDto commentDto){

        try{
                qnaService.registComment(commentDto);
                qnaService.updateAnswer(commentDto.getId());

        }catch (Exception e){
            return new ResponseEntity<String>("등록중 에러가 발생했습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("등록이 성공적으로 되었습니다.", HttpStatus.OK);
    }




}
