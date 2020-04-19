package com.covid.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covid.rest.Repositories.RegistrationRepository;
import com.covid.rest.entity.Registration;
import com.covid.rest.model.LoginModel;
@Component
public class RegistrationServiceImpl implements RegistrationService{
	@Autowired
	private RegistrationRepository repo;
	@Override
	public Registration addUser(Registration registration) throws Exception{
		try{
		return repo.save(registration);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	@Override
	public Registration login(LoginModel model) throws Exception{
		Optional<Registration> reg = repo.findById(model.getMobile());
		if(reg.isPresent()){
			return reg.get();
		}
		else {
			throw new Exception("mobile number is not present");
		}
	}

}
