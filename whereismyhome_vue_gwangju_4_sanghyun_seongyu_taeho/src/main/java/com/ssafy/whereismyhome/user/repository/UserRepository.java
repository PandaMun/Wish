package com.ssafy.whereismyhome.user.repository;

import com.ssafy.whereismyhome.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select m from User m join fetch m.authorities a where m.userId = :userId")
    Optional<User> findByUserIdWithAuthority(String userId);
}
