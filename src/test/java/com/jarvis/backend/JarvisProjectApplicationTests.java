package com.jarvis.backend;

import com.jarvis.backend.model.Contact;
import com.jarvis.backend.repo.ContactRepository;
import com.jarvis.backend.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JarvisProjectApplicationTests {

	@Mock
	ContactRepository contactRepository;

	@InjectMocks
	ContactService contactService;

	private Contact contact;


	@Test
	void contextLoads() {
	}

}