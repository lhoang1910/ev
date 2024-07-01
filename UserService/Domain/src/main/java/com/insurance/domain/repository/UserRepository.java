package com.insurance.domain.repository;

import com.demo.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query(value = "SELECT user_code FROM users ORDER BY user_code DESC LIMIT 1", nativeQuery = true)
    Optional<String> findLatestUserCode();

    Optional<UserEntity> findByUsername(String username);
}