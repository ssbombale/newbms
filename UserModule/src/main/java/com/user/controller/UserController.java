package com.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.AuthResponse;
import com.user.entity.CustomeLoanApply;
import com.user.entity.Login;
import com.user.entity.OutputModel;
import com.user.entity.User;
import com.user.security.jwt.JwtUtils;
import com.user.service.UserImplementation;
import com.user.service.UserService;

@RestController
@RequestMapping(value = "/api/User")

public class UserController {
	
	@Autowired
	private UserService customerService;
	
	@GetMapping(value = "/welcome")
	public String test() {
		return "Hello !!!!";
	} 
	
	@PostMapping("/registration")
	public User createUser(@Valid @RequestBody User user) {
		User user1 = new User(user.getName(), user.getUserName(),encoder.encode(user.getPassword()),user.getAddress(),user.getState(), user.getCountry(), user.getEmail(), user.getPan(), user.getContactNumber(),user.getDate(), user.getAccountType());
		 customerService.saveUser(user1);
		 return user1;
	}
	
	@GetMapping(value = "/getAllLoanDetailsById/{custId}")
	public List<CustomeLoanApply> getAllLoanDetailsById(@PathVariable Integer custId) {
		List<CustomeLoanApply> list = customerService.getLoanByCustId(custId);
		return list;
	} 
	
	@GetMapping(value = "/getAllLoanDetails/{custId}")
	public List<CustomeLoanApply> getAllLoanDetails() {
		List<CustomeLoanApply> list = customerService.getAllLoanDetails();
		return list;
	} 
	@PostMapping("/applyLoan")
	public Integer applyloan( @RequestBody CustomeLoanApply user) {
		Integer id = customerService.saveLoanApply(user);
		return id;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateCustomer(@PathVariable Integer id, @RequestBody User user){
	String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		


//		System.out.println(id);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			
			customerService.updateCustomer(user, id);
		}
		catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			
		}
		return responseEntity;
	}
	
	
	
	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserImplementation useraccountServiceImpl;
	
	@Autowired
    private PasswordEncoder encoder;

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody Login login) throws Exception {

		try { 

			User useraccount = useraccountServiceImpl.getUseraccountByUsername(login.getUserName());
			if (useraccount != null) {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));

				String jwt = jwtUtil.generateToken(login.getUserName());
				AuthResponse auth = new AuthResponse(jwt, useraccount.getId(), useraccount.getUserName(), "",
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
	
	
	
}
