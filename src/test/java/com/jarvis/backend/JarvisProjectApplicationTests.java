package com.jarvis.backend;

import com.jarvis.backend.model.Contact;
import com.jarvis.backend.repo.ContactRepository;
import com.jarvis.backend.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JarvisProjectApplicationTests {

	@Mock
	ContactRepository contactRepository;

	@InjectMocks
	ContactService contactService;

	private Contact contact;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		contact = new Contact();
		contact.setEmail("suhail@gmail.com");
	}

	@Test
	void testByEmail(){
		when(contactRepository.findByEmail("suhail@gmail.com")).thenReturn((contact));

		Contact foundContact = contactService.findByEmail("suhail@gmail.com");

		assertNotNull(foundContact);

		assertEquals("suhail@gmail.com", foundContact.getEmail());

	}


	@Test
	void contextLoads() {
	}

}
