package com.insurance.domain.repository;

import com.demo.user.domain.entity.ShortenUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenUserRepository extends JpaRepository<ShortenUserEntity, String> {
}
