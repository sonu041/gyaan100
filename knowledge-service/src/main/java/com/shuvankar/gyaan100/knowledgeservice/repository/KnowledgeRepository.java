package com.shuvankar.gyaan100.knowledgeservice.repository;

import com.shuvankar.gyaan100.knowledgeservice.model.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {
}
