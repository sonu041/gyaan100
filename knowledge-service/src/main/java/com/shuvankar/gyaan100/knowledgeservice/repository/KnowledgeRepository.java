package com.shuvankar.gyaan100.knowledgeservice.repository;

import com.shuvankar.gyaan100.knowledgeservice.model.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {
//    public List<Knowledge> findActiveAll(String active);
}
