package com.covid.rest.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.covid.rest.entity.Registration;

public interface RegistrationRepository extends CrudRepository<Registration, String>{

}
