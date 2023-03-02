package com.ssafy.whereismyhome.qna.repository;

import com.ssafy.whereismyhome.qna.entity.Comment;
import com.ssafy.whereismyhome.qna.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment getCommentByInquiry_Id(long id);
}
