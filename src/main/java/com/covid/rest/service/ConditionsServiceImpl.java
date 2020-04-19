package com.covid.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covid.rest.Repositories.ConditionRepository;
import com.covid.rest.entity.Conditions;
import com.covid.rest.model.LoginModel;
@Component
public class ConditionsServiceImpl implements ConditionsService{
	@Autowired
	ConditionRepository repo;
	@Override
	public Conditions addCondition(Conditions condition) {
		return repo.save(condition);
	}

	@Override
	public List<Conditions> getConditions(LoginModel model) {
		return repo.findByMobile(model.getMobile());
	}
	
}
