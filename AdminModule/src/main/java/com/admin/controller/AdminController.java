package com.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.entity.AdminDetail;
import com.admin.entity.AuthResponse;
import com.admin.entity.CustomeLoanApply;
import com.admin.entity.Login;
import com.admin.entity.OutputModel;
import com.admin.security.jwt.JwtUtils;
import com.admin.service.AdminImplementation;
import com.admin.service.AdminService;

@RestController
@RequestMapping(value = "/api/admin")

public class AdminController {
	
	@Autowired
	private AdminService customerService;
	
	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AdminImplementation useraccountServiceImpl;

	
	@GetMapping(value = "/welcome")
	public String test() {
		return "Hello !!!!";
	} 
	
	@PostMapping("/registration")
	Integer createUser(@Valid @RequestBody AdminDetail user) {
		Integer id = customerService.saveUser(user);
		return id;
	}
	
	
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody Login login) throws Exception {

		try { 

			AdminDetail useraccount = useraccountServiceImpl.getUseraccountByUsername(login.getUsername());
			if (useraccount != null) {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

				String jwt = jwtUtil.generateToken(login.getUsername());
				AuthResponse auth = new AuthResponse(jwt, useraccount.getUserId(), useraccount.getUserName(), "",
						useraccount.getName(), useraccount.getEmail(),true,"Login Successfully");
				return ResponseEntity.ok(auth);

			} else {
				OutputModel outputModel = new OutputModel(false, "username or password invalid");
				return ResponseEntity.ok(outputModel);
			}

		} catch (Exception e) {
			System.err.println(e);
			OutputModel outputModel = new OutputModel(false, "username or password invalid");
			return ResponseEntity.ok(outputModel);
			
		}
	}
	
	
	
	@GetMapping(value = "/getAllLoanDetails")
	public List<CustomeLoanApply> getAllLoanDetails() {
		List<CustomeLoanApply> list = customerService.getAllLoanDetails();
		return list;
		
	} 
	
	@GetMapping(value = "/getAllLoanDetailsCust/{id}")
	public List<Object[]> getAllcustdatabyId(@PathVariable Integer id) {
		List<Object[]> list = customerService.getCustById(id);
		return list;
		
	} 
	
	
	

	@PutMapping(value = "/updateloanDetails/{loanid}/{status}")
	public OutputModel updateloanDetails(@PathVariable Integer loanid, @PathVariable String status) {
		OutputModel model = new OutputModel();
		try {
			CustomeLoanApply customeLoanApply =  customerService.getLoanbyId(loanid);
			System.out.println(customeLoanApply);
			if(customeLoanApply!=null) {
				customeLoanApply.setActivateStatus(status);
				customerService.updateLoanDetails(customeLoanApply);
				model = new OutputModel(true, "Loan Was "+ status);
			}else {
				model = new OutputModel(false, "Loan id Not Found");
			}
			
		}catch(Exception e) {
			System.err.println(e);
			model = new OutputModel(false, "Loan id Not Found");
		}
		return model;
	} 
	
}
