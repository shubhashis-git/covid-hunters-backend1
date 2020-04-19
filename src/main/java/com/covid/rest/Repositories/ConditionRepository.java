package com.covid.rest.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.covid.rest.entity.Conditions;

public interface ConditionRepository extends CrudRepository<Conditions, String>{
	List<Conditions> findByMobile (Integer mobile);
}
