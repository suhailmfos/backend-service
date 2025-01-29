package com.jarvis.backend.service;

import com.jarvis.backend.model.Contact;
import com.jarvis.backend.payload.RequestPayload;
import com.jarvis.backend.payload.ResponseData;
import com.jarvis.backend.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public Contact findByEmail(String email){
		return contactRepository.findByEmail(email);
	}
	
	public ResponseData executeRequest(RequestPayload payload) {
		return null;
	}

}
