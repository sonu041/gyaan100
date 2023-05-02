package com.shuvankar.gyaan100.knowledgeservice.repository;

import com.shuvankar.gyaan100.knowledgeservice.model.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {
//    public List<Knowledge> findActiveAll(String active);
    List<Knowledge> findByTitleContainingIgnoreCase(String title);
    @Query("SELECT p FROM Knowledge p WHERE p.title = :searchText")
    List<Knowledge> findByTitleOrDescription(@Param("searchText") String searchText);
}
