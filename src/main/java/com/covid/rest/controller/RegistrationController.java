package com.covid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.covid.rest.entity.Registration;
import com.covid.rest.service.RegistrationService;

@RestController
public class RegistrationController {
	@Autowired
	RegistrationService service;

	@PostMapping(path = "/register-user", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Registration> resgister(
			@RequestBody Registration registration) {
		Registration reg = service.addUser(registration);
		ResponseEntity<Registration> responseEntity = new ResponseEntity<>(reg,
				HttpStatus.OK);
		return responseEntity;
	}
}
