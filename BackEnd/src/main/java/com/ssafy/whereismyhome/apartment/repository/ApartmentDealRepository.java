package com.ssafy.whereismyhome.apartment.repository;

import com.ssafy.whereismyhome.apartment.entity.ApartmentDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentDealRepository extends JpaRepository<ApartmentDeal,Long> {
}
