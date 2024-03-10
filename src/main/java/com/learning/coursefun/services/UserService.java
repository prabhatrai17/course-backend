package com.learning.coursefun.services;

import java.util.List;
import java.util.Optional;

import com.learning.coursefun.entities.User;




public interface UserService {
	public User addUser(User user);

	public Optional<User> getUserById(int id);

	public List<User> getAllUser();

	public User getUserByEmail(String email);

	public User login(User user);
	
	public User Signup(User user);

}
