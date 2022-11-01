package com.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.entity.CustomeLoanApply;
import com.user.entity.User;

@Service
public interface UserService {
	
	
	
	public User saveUser (User user);

	User loadUserByUsername(String userName);

	Integer saveLoanApply(CustomeLoanApply user);
	
	Integer updateCustomer (User user, Integer id);

	List<CustomeLoanApply> getAllLoanDetails();

	List<CustomeLoanApply> getLoanByCustId(Integer custId);
	
	
}
