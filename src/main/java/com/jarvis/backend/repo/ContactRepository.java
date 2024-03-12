package com.jarvis.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jarvis.backend.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

	
	public Contact findByEmail(String email);
	
	public Contact findByPhoneNumber(String phoneNumber);
	
	public List<Contact> findAllByEmail(String email);
	
	public List<Contact> findAllByPhoneNumber(String phoneNumber);
	
}
