package com.ssafy.whereismyhome.notice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.whereismyhome.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column(name = "CREATED_DATE")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "UPDATED_DATE")
    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    private LocalDateTime updateDate = LocalDateTime.now();


    public void updateNotice(String title, String content){
        this.title = title;
        this.content = content;
    }
}
