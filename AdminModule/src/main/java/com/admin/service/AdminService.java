package com.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.admin.entity.AdminDetail;
import com.admin.entity.CustomeLoanApply;

@Service
public interface AdminService {
	
	
	
	Integer saveUser (AdminDetail user);

	AdminDetail loadUserByUsername(String userName);

	List<CustomeLoanApply> getAllLoanDetails();

	CustomeLoanApply getLoanbyId(Integer loanid);

	void updateLoanDetails(CustomeLoanApply customeLoanApply);
	
	List<Object[]> getCustById(Integer id);
	
	
}
