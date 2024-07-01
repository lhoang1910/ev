package com.insurance.domain.repository;

import com.insurance.domain.entity.EventStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventStoreRepository extends JpaRepository<EventStoreEntity, String> {

}
