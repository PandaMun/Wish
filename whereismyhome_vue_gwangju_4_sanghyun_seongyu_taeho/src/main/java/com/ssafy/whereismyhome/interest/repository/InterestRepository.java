package com.ssafy.whereismyhome.interest.repository;

import com.ssafy.whereismyhome.interest.entity.Interest;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest,Long> {

    @Query("select u from Interest u where u.user.Id=:userId")
    List<Interest> findAllByUserId(@Param("userId") String userId);

    @Query("select u from Interest u where u.user.Id = :userId and u.dongCode.dongCode = :dongCode")
    List<Interest> CountByUserIdAndDongCode(@Param("userId") String userId, @Param("dongCode") String dongCode);
}
