package com.ssafy.whereismyhome.notice.repository;


import com.ssafy.whereismyhome.notice.dto.NoticeDto;
import com.ssafy.whereismyhome.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {

}
