package com.ssafy.whereismyhome.apartment.repository;

import com.ssafy.whereismyhome.apartment.entity.ApartmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ApartmentInfoRepository extends JpaRepository<ApartmentInfo, Long> {


    @Query("select distinct u from ApartmentInfo u join fetch u.apartmentDeals where u.lng >= :swLng and :neLng >= u.lng and u.lat >= :swLat and :neLat >= u.lat")
    List<ApartmentInfo> findLocation(@Param("swLng") BigDecimal swLng, @Param("neLng") BigDecimal neLng, @Param("swLat") BigDecimal swLat, @Param("neLat") BigDecimal neLat);

    @Query("select distinct u from ApartmentInfo u join fetch u.dongCode where u.apartmentName like %:apartmentName%")
    List<ApartmentInfo> findByApartmentName(@Param("apartmentName") String apartmentName);

    ApartmentInfo findAllByAptCode(Long aptCode);
}
