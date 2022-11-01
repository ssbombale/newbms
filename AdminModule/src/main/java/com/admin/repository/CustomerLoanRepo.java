package com.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entity.AdminDetail;
import com.admin.entity.CustomeLoanApply;

public interface CustomerLoanRepo extends JpaRepository <CustomeLoanApply, Integer> {

	List<CustomeLoanApply> findAll();


}
