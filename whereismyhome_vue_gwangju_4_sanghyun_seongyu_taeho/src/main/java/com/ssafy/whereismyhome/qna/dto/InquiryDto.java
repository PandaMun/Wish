package com.ssafy.whereismyhome.qna.dto;

import com.ssafy.whereismyhome.qna.entity.Inquiry;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class InquiryDto {
    private Long id;
    private String title;
    private String userId;
    private String content;
    private Boolean answer;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public InquiryDto(){}


    public InquiryDto(Inquiry inquiry){
        this.id = inquiry.getId();
        this.title = inquiry.getTitle();
        this.userId = inquiry.getUser().getId();
        this.content = inquiry.getContent();
        this.answer = inquiry.getAnswer();
        this.createDate = inquiry.getCreateDate();
        this.updateDate = inquiry.getUpdateDate();
    }
}
