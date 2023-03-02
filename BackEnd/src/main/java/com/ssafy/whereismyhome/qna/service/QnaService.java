package com.ssafy.whereismyhome.qna.service;


import com.ssafy.whereismyhome.qna.dto.CommentDto;
import com.ssafy.whereismyhome.qna.dto.InquiryDto;
import com.ssafy.whereismyhome.qna.entity.Comment;
import com.ssafy.whereismyhome.qna.entity.Inquiry;
import com.ssafy.whereismyhome.qna.repository.CommentRepository;
import com.ssafy.whereismyhome.qna.repository.InquiryRepository;
import com.ssafy.whereismyhome.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaService{


    private final InquiryRepository inquiryRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public List<InquiryDto> getInquiries(){
        List<Inquiry> inquiry = inquiryRepository.findAll();
        List<InquiryDto> inquiries = new ArrayList<>();
        for(int i = inquiry.size()-1; i>=0; i--){
            inquiries.add(new InquiryDto(inquiry.get(i)));
        }
        return inquiries;
    }

    @Transactional
    public Inquiry registInquiry(InquiryDto param) {

        return inquiryRepository.save(Inquiry.builder()
                .title(param.getTitle())
                .user(User.builder().Id(param.getUserId()).build())
                .content(param.getContent())
                .answer(false)
                .build());
    }

    @Transactional
    public void patchInquiry(InquiryDto param){
        Inquiry entity = inquiryRepository.findById(param.getId()).get();
        entity.update(param.getTitle(), param.getContent());
        inquiryRepository.save(entity);
    }

    @Transactional
    public InquiryDto getInquiry(Long id){
        Inquiry inquiry = inquiryRepository.findById(id).get();
        InquiryDto inquiryDto = new InquiryDto(inquiry);
        return inquiryDto;

    }

    @Transactional
    public void deleteInquiry(Long id){
        inquiryRepository.deleteById(id);
    }


    @Transactional
    public CommentDto getComment(String id) throws Exception {
        Comment comment = commentRepository.getCommentByInquiry_Id(Long.parseLong(id));
        CommentDto commentDto = new CommentDto(comment);
        return commentDto;
    }

    @Transactional
    public Comment registComment(CommentDto commentDto) throws Exception {

        return commentRepository.save(Comment.builder()
                .id(commentDto.getId())
                .inquiry(Inquiry.builder().id(commentDto.getId()).build())
                .user(User.builder().Id(commentDto.getUserId()).build())
                .content(commentDto.getContent())
                .build());
    }

    @Transactional
    public void updateAnswer(Long id) throws Exception {

        Inquiry inquiry = inquiryRepository.getInquiryById(id);
        inquiry.updateAnswer(inquiry);
    }
}
