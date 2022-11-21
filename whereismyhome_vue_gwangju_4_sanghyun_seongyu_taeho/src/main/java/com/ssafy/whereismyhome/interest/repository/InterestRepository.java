package com.ssafy.whereismyhome.interest.repository;

import com.ssafy.whereismyhome.interest.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest,Long> {
}
