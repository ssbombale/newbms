package com.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.user.entity.CustomeLoanApply;
import com.user.entity.User;
import com.user.exception.ResourceNotFoundException;
import com.user.repository.CustLoanRepository;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository customerRepo;

	@Autowired
    private PasswordEncoder encoder;
	
	@Autowired
	CustLoanRepository custLoanRepository;

	@Override
	public User saveUser(User user) {
		User savedUser = customerRepo.save(user);
		return savedUser;
	}

	public Integer updateCustomer(User user, Integer id) {
		User existing = customerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
		existing.setCountry(user.getCountry());
		existing.setPassword(encoder.encode(user.getPassword()));
		existing.setAddress(user.getAddress());
		existing.setAccountType(user.getAccountType());
		existing.setContactNumber(user.getContactNumber());
		existing.setEmail(user.getEmail());
		existing.setPan(user.getPan());
		existing.setState(user.getState());
		existing.setUserName(user.getUserName());

		customerRepo.save(existing);
		return existing.getId();
	}

	@Override
	public User loadUserByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer saveLoanApply(CustomeLoanApply user) {
		CustomeLoanApply savedUser = custLoanRepository.save(user);
		return savedUser.getLoanId();
	}

	@Override
	public List<CustomeLoanApply> getAllLoanDetails() {
		// TODO Auto-generated method stub
		return custLoanRepository.findAll();
	}

	@Override
	public List<CustomeLoanApply> getLoanByCustId(Integer custId) {
		// TODO Auto-generated method stub
		return custLoanRepository.findBycustId(custId);
	}

}
