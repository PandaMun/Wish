package com.ssafy.whereismyhome.notice.service;

import com.ssafy.whereismyhome.notice.dto.NoticeDto;
import com.ssafy.whereismyhome.notice.entity.Notice;
import com.ssafy.whereismyhome.notice.repository.NoticeRepository;
import com.ssafy.whereismyhome.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public List<NoticeDto> getNoticeList() {
        List<Notice> NoticeList = noticeRepository.findAll();
        List<NoticeDto> list = new ArrayList<>();
        for (int i = 0; i< NoticeList.size(); i++) {
            list.add(new NoticeDto(NoticeList.get(i)));
        }
        return list;
    }


    @Transactional
    public void addNotice(NoticeDto noticeDto) throws SQLException {
        noticeRepository.save(Notice.builder()
        .title(noticeDto.getTitle())
        .content(noticeDto.getContent())
        .user(User.builder().userId(noticeDto.getUserId()).build())
        .build());
    }

    @Transactional
    public NoticeDto getNotice(Long id) throws SQLException {
        return new NoticeDto(noticeRepository.getById(id));
    }

    @Transactional
    public void updateNotice(NoticeDto noticeDto) throws SQLException {
        Notice notice = noticeRepository.findById(noticeDto.getId()).orElseThrow(() -> new IllegalArgumentException("id : " + noticeDto.getId() + " 해당 공지가 없습니다."));
        notice.updateNotice(noticeDto.getTitle(),noticeDto.getContent());
    }

    @Transactional
    public void deleteNotice(Long id) throws SQLException {
        noticeRepository.deleteById(id);
    }

}