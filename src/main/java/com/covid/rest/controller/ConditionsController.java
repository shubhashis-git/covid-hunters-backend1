package com.covid.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.covid.rest.entity.Conditions;
import com.covid.rest.model.LoginModel;
import com.covid.rest.service.ConditionsService;

@RestController
public class ConditionsController {
	@Autowired
	ConditionsService service;
	
	@PostMapping(path = "/add-condition", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Conditions> addCondition(
			@RequestBody Conditions condition) throws Exception {
		condition.setId(new Date().toString());
		Conditions con = service.addCondition(condition);
		if(con == null){
			throw new Exception("cannot process request");
		}
		ResponseEntity<Conditions> responseEntity = new ResponseEntity<>(con,
				HttpStatus.OK);
		return responseEntity;
	}
	
	@PostMapping(path = "/get-conditions", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<Conditions>> getConditions(
			@RequestBody LoginModel model) throws Exception {
		List<Conditions> con = service.getConditions(model);
		if(con == null){
			throw new Exception("cannot process request");
		}
		ResponseEntity<List<Conditions>> responseEntity = new ResponseEntity<>(con,
				HttpStatus.OK);
		return responseEntity;
	}
}
