package com.jarvis.backend.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jarvis.backend.model.Contact;
import com.jarvis.backend.payload.RequestPayload;
import com.jarvis.backend.payload.ResponseData;
import com.jarvis.backend.repo.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	public ResponseData executeRequest(RequestPayload payload) {
		ResponseData response = new ResponseData();
		Contact contact = null;
		List<Contact> listOfContacts = null;
		Set<String> emails = new HashSet<>();
		Set<String> phoneNumbers = new HashSet<>();
		Set<Long> secondaryId = new HashSet<>();
		
		if(payload.getEmail() == null && payload.getPhoneNumber() != null){
			listOfContacts = contactRepository.findAllByPhoneNumber(payload.getPhoneNumber());
		}else if(payload.getPhoneNumber() == null && payload.getEmail() != null) {
			listOfContacts = contactRepository.findAllByEmail(payload.getEmail());
		}else {
			listOfContacts = contactRepository.findAllByEmail(payload.getEmail());
			if(listOfContacts.isEmpty()) {
				listOfContacts = contactRepository.findAllByPhoneNumber(payload.getPhoneNumber());
			}
		}
		if(listOfContacts.isEmpty()) {
			contact = new Contact();
			contact.setEmail(payload.getEmail());
			contact.setPhoneNumber(payload.getPhoneNumber());
			contact.setLinkPrecedence("primary");
			contact.setLinkedId(null);
			contact.setCreatedAt(LocalDateTime.now());
			contact.setUpdatedAt(LocalDateTime.now());
			contact.setDeletedAt(null);
			contact = contactRepository.save(contact);
			response.setPrimaryContactId(contact.getId());
			emails.add(contact.getEmail());
			response.setEmails(emails);
			phoneNumbers.add(contact.getPhoneNumber());
			response.setPhoneNumbers(phoneNumbers);
			response.setSecondaryContactIds(secondaryId);
			
			return response;
		}
		else {
			contact = listOfContacts.stream().findFirst().get();
			Contact contact2 = null;
			if((contact.getEmail().equals(payload.getEmail()) && !contact.getPhoneNumber().equals(payload.getPhoneNumber()))
				||(!contact.getEmail().equals(payload.getEmail()) && contact.getPhoneNumber().equals(payload.getPhoneNumber()))){
				contact2 = new Contact();
				contact2.setLinkedId(contact.getId());
				contact2.setLinkPrecedence("secondary");
				contact2.setEmail(payload.getEmail());
				contact2.setPhoneNumber(payload.getPhoneNumber());
				contact2.setCreatedAt(LocalDateTime.now());
				contact2.setUpdatedAt(LocalDateTime.now());
				contact2.setDeletedAt(null);
				contact2 = contactRepository.save(contact2); // if fields are different then the data is saved into the db
			}
			
			response.setPrimaryContactId(contact.getId());
			emails.add(contact.getEmail());
			phoneNumbers.add(contact.getPhoneNumber());
			
			
			listOfContacts.stream().skip(1).forEach(e->{
				emails.add(e.getEmail());
				phoneNumbers.add(e.getPhoneNumber());
				secondaryId.add(e.getId());
			});
			
			if(contact2 != null) {
				emails.add(contact2.getEmail());
				phoneNumbers.add(contact2.getPhoneNumber());
				secondaryId.add(contact2.getId());
			}
			
			response.setEmails(emails);
			response.setPhoneNumbers(phoneNumbers);
			response.setSecondaryContactIds(secondaryId);
			
			return response;
		}
		
//		if(payload.getEmail() != null) {
//			listOfContacts = contactRepository.findAllByEmail(payload.getEmail());
//		}
//		
//		
//  		for(Contact c: listOfContacts) {
//			response.setPrimaryContactId(c.getId());
//			emails.add(c.getEmail());
//			phoneNumbers.add(c.getPhoneNumber());
//			secondaryId.add(c.getId());
//			
//		}
//  		
//  		response.setEmails(emails);
//  		response.setPhoneNumbers(phoneNumbers);
//  		response.setSecondaryContactIds(secondaryId);
//  		
//		if(payload.getPhoneNumber() != null) {
//			listOfContacts = contactRepository.findAllByPhoneNumber(payload.getPhoneNumber());
//		}
	}
}
