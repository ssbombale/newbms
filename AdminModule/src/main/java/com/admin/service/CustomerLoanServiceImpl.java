package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.admin.entity.CustomeLoanApply;
import com.admin.repository.CustomerLoanRepo;

public class CustomerLoanServiceImpl implements CustomerLoanService {

	@Autowired
	CustomerLoanRepo custRepo;
	
	
	@Override
	public List<CustomeLoanApply> getAllLoanDetails() {
		return custRepo.findAll();
	}

}
