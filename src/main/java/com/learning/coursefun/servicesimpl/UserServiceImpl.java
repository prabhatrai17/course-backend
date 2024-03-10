package com.learning.coursefun.servicesimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.coursefun.custom.exception.BadCredentialException;
import com.learning.coursefun.custom.exception.EmptyInputException;
import com.learning.coursefun.entities.User;
import com.learning.coursefun.repo.UserRepo;
import com.learning.coursefun.services.UserService;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public User addUser(User user) {
		if (user.getEmail() == "" && user.getEmail().length() == 0 || user.getPass().length() == 0
				|| user.getRole() == "") {
			throw new EmptyInputException("601", "Input Field is empty");
		}
		User savedUser = userRepo.save(user);
		return savedUser;

	}

	@Override
	public List<User> getAllUser() {

		return userRepo.findAll();
	}

	@Override
	public Optional<User> getUserById(int id) {
		Optional<User> user = userRepo.findById(id);
		if (Objects.isNull(user)||user.isEmpty())
			throw new NoSuchElementException();
		return user;
	}

	@Override
	public User login(User user) {

		User resUser = this.getUserByEmail(user.getEmail());
		//System.out.println(user.getEmail());
		// System.out.println(resUser.getEmail());
		if (resUser == null || !resUser.getEmail().equals(user.getEmail())
				|| !resUser.getPass().equals(user.getPass())|| !resUser.getRole().equals(user.getRole())) {
			throw new BadCredentialException();
		}
		return resUser;

	}

	@Override
	public User getUserByEmail(String email) {
		//User resUser=userRepo.findByEmail(email);
		User resUser = userRepo.getUserByEmail(email);
		return resUser;
	}

	@Override
	public User Signup(User user) {
		User resUser=userRepo.getUserByEmail(user.getEmail());
		if(resUser==null) {
			
			return userRepo.save(user);
		}
		
		return null;
	}

}
