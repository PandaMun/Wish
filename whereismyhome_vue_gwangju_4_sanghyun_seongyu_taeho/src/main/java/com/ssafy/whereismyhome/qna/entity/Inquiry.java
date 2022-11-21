package com.ssafy.whereismyhome.qna.entity;

import com.ssafy.whereismyhome.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean answer;

    @OneToOne(mappedBy = "inquiry" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Comment comment;


    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column(name = "CREATED_DATE")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "UPDATED_DATE")
    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    private LocalDateTime updateDate = LocalDateTime.now();

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void updateAnswer(Inquiry inquiry){
        inquiry.answer = true;
    }

}
