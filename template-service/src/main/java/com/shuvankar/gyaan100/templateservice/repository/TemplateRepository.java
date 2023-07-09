package com.shuvankar.gyaan100.templateservice.repository;

import com.shuvankar.gyaan100.templateservice.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
