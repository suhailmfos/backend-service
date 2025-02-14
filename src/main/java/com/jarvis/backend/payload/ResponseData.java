package com.jarvis.backend.payload;

import lombok.Data;

import java.util.Set;

@Data
public class ResponseData {
	
	private Long primaryContactId;
	
	private Set<String> emails;
	
	private Set<String> phoneNumbers;
	
	private Set<Long> secondaryContactIds;
}