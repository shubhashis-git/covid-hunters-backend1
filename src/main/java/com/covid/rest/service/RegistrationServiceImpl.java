package com.covid.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covid.rest.Repositories.RegistrationRepository;
import com.covid.rest.entity.Registration;
@Component
public class RegistrationServiceImpl implements RegistrationService{
	@Autowired
	private RegistrationRepository repo;
	@Override
	public Registration addUser(Registration registration) {
		/*Registration reg = new Registration();
		reg.setFirstName("shub");
		reg.setLastName("das");
		reg.setMobile(1234567890);
		reg.setType("admin");
		return reg;*/
		return repo.save(registration);
	}

}
