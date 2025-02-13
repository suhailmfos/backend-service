package com.jarvis.backend.model;

import java.util.UUID;

//@Entity
//@Table(name = "contacts")
public class Contact {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String email;
}