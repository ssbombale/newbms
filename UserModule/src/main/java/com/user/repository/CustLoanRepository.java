package com.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.user.entity.CustomeLoanApply;

public interface CustLoanRepository extends JpaRepository <CustomeLoanApply, Integer>{

	CustomeLoanApply save(CustomeLoanApply user);

	List<CustomeLoanApply> findBycustId(Integer custId);
	

}
