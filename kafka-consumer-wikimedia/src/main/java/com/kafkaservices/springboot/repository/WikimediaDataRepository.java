package com.kafkaservices.springboot.repository;

import com.kafkaservices.springboot.model.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {
}
