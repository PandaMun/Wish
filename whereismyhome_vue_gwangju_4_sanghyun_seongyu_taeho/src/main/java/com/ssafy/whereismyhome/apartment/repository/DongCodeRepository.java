package com.ssafy.whereismyhome.apartment.repository;


import com.ssafy.whereismyhome.apartment.entity.DongCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DongCodeRepository extends JpaRepository<DongCode,String> {


    List<DongCode> findByDongNameIsContaining(String dongName);

    DongCode getDongCodeByDongCode(String dongCode);

}
