package com.ssafy.whereismyhome.qna.repository;

import com.ssafy.whereismyhome.qna.entity.Comment;
import com.ssafy.whereismyhome.qna.entity.Inquiry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry,Long>{

    @Query("select u from Inquiry u ")
    List<Inquiry>findAll();

    @Query("select u from Inquiry u join fetch u.comment where u.id = :id")
    Inquiry getInquiryById(@Param("id") Long id);





//        int registInquiry(Inquiry inquiry) throws Exception;
//
//        int patchInquiry(Inquiry inquiry) throws Exception;
//
//        int deleteInquiry(String id) throws Exception;
//        int registComment(Comment comment) throws Exception;
//        Comment getComment(String id) throws Exception;
//
//        int updateAnswer(int id) throws Exception;

    }