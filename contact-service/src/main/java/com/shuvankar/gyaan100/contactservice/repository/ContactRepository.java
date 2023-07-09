package com.shuvankar.gyaan100.contactservice.repository;

import com.shuvankar.gyaan100.contactservice.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
