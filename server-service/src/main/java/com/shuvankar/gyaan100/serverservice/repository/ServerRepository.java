package com.shuvankar.gyaan100.serverservice.repository;

import com.shuvankar.gyaan100.serverservice.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
}
