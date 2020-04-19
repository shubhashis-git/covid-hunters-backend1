package com.covid.rest.service;

import java.util.List;

import com.covid.rest.entity.Conditions;
import com.covid.rest.model.LoginModel;

public interface ConditionsService {
	public Conditions addCondition(Conditions condition);
	public List<Conditions> getConditions(LoginModel model);
}
