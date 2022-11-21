package com.ssafy.whereismyhome.qna.dto;

import com.ssafy.whereismyhome.qna.entity.Comment;
import com.ssafy.whereismyhome.user.entity.User;
import lombok.Data;

@Data
public class CommentDto {

    private String content;
    private String userId;
    private Long Id;

    public CommentDto(){
    }
    public CommentDto(Comment comment){
        this.content = comment.getContent();
        this.Id = comment.getInquiry().getId();
        this.userId = comment.getUser().getUserId();
    }

}
