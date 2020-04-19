package com.covid.rest.service;

import com.covid.rest.entity.Registration;
import com.covid.rest.model.LoginModel;

public interface RegistrationService {
	public Registration addUser(Registration registration) throws Exception;
	public Registration login(LoginModel model) throws Exception;
}
