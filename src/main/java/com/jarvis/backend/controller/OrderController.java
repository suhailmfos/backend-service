package com.jarvis.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jarvis.backend.payload.RequestPayload;
import com.jarvis.backend.payload.ResponseData;
import com.jarvis.backend.service.ContactService;

@RestController
@RequestMapping("")
public class OrderController {
	
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping(path = "/identify")
	public ResponseEntity<ResponseData> placeOrder(@RequestBody RequestPayload payload){
		
		ResponseData responseData = contactService.executeRequest(payload);
		
		return ResponseEntity.ok(responseData);
		
	}
	
}
