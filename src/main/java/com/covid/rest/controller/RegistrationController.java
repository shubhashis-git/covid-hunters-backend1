package com.covid.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.covid.rest.entity.Registration;
import com.covid.rest.model.LoginModel;
import com.covid.rest.model.RegistrationModel;
import com.covid.rest.service.RegistrationService;

@RestController
public class RegistrationController {
	@Autowired
	RegistrationService service;
	@PostMapping(path = "/register-user", consumes = "application/json", produces = "application/json")
	public ResponseEntity<RegistrationModel> resgister(
			@RequestBody RegistrationModel registration) throws Exception{
		Registration reg = service.addUser(getRegistrationEntity(registration));
		if(reg == null){
			throw new Exception("cannot process request");
		}
		ResponseEntity<RegistrationModel> responseEntity = new ResponseEntity<>(toRegistrationModel(reg),
				HttpStatus.OK);
		return responseEntity;
	}
	
	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<RegistrationModel> login(
			@RequestBody LoginModel model) throws Exception {
		Registration reg = service.login(model);
		if(reg == null){
			throw new Exception("mobile number does not exist");
		}
		ResponseEntity<RegistrationModel> responseEntity = new ResponseEntity<>(toRegistrationModel(reg),
				HttpStatus.OK);
		return responseEntity;
	}
	
	
	
	private Registration getRegistrationEntity(RegistrationModel model){
		Registration entity = new Registration();
		entity.setFirstName(model.getFirstName());
		entity.setLastName(model.getLastName());
		entity.setMobile(model.getMobile());
		entity.setType(model.getType());
		if(model.getType().equalsIgnoreCase("admin")){
			entity.setStatus("PENDING");
		}
		else{
			entity.setStatus("NA");
		}
		entity.setImage(model.getImage().getBytes());
		entity.setDeviceId(model.getDeviceId());
		return entity;
	}
	
	private RegistrationModel toRegistrationModel(Registration entity){
		RegistrationModel model = new RegistrationModel();
		model.setFirstName(entity.getFirstName());
		model.setLastName(entity.getLastName());
		model.setMobile(entity.getMobile());
		model.setImage(new String(entity.getImage()));
		model.setStatus(entity.getStatus());
		model.setType(entity.getType());
		model.setDeviceId(entity.getDeviceId());
		return model;
	}
}
