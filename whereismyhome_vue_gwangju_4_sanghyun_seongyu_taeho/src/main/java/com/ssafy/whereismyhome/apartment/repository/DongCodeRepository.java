package com.ssafy.whereismyhome.apartment.repository;


import com.ssafy.whereismyhome.apartment.entity.DongCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DongCodeRepository extends JpaRepository<DongCode,String> {


    List<DongCode> findByDongNameIsContaining(String dongName);

    @Query("select distinct u from DongCode u join fetch u.apartmentInfos where u.dongCode = :dongCode")
    DongCode findByDongCode(@Param("dongCode")String dongCode);
    DongCode getDongCodeByDongCode(String dongCode);

}
