package com.jarvis.backend.repo;

import com.jarvis.backend.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID>{
	Contact findByEmail(String email);
}
