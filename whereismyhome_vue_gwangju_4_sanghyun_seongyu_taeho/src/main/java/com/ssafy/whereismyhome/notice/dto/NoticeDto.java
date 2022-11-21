package com.ssafy.whereismyhome.notice.dto;

import com.ssafy.whereismyhome.notice.entity.Notice;
import com.ssafy.whereismyhome.user.entity.User;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class NoticeDto {

    private Long id;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public NoticeDto(){

    }

    public NoticeDto(Notice notice){
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.userId = notice.getUser().getUserId();
        this.createDate = notice.getCreateDate();
        this.updateDate = notice.getUpdateDate();
    }

}
