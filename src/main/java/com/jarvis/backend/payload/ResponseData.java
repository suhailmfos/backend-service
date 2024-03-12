package com.jarvis.backend.payload;

import java.util.Set;

public class ResponseData {
	
	private Long primaryContactId;
	
	private Set<String> emails;
	
	private Set<String> phoneNumbers;
	
	private Set<Long> secondaryContactIds;

	public Long getPrimaryContactId() {
		return primaryContactId;
	}

	public void setPrimaryContactId(Long primaryContactId) {
		this.primaryContactId = primaryContactId;
	}

	public Set<String> getEmails() {
		return emails;
	}

	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}

	public Set<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Set<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Set<Long> getSecondaryContactIds() {
		return secondaryContactIds;
	}

	public void setSecondaryContactIds(Set<Long> secondaryContactIds) {
		this.secondaryContactIds = secondaryContactIds;
	}

	
}
