package com.learning.coursefun.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.coursefun.dto.UserLoginDto;
import com.learning.coursefun.dto.UserSignupDto;

import com.learning.coursefun.entities.User;

import com.learning.coursefun.services.UserService;





@RestController
public class UserController {

	@Autowired
	ModelMapper modelMapper;
	
	

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") int id) {
		Optional<User> resUser = userService.getUserById(id);
		return new ResponseEntity<>(resUser, HttpStatus.OK);
	}

	@PostMapping("/user")//for testing only
	public ResponseEntity<?> addUser(@RequestBody @Valid UserSignupDto userSignupDto) {//signup is there to create user
		//System.out.println(user);
		User user=modelMapper.map(userSignupDto, User.class);
		User resUser = userService.addUser(user);
		return new ResponseEntity<>(resUser, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto userLoginDto) {
		System.out.println(userLoginDto);
		User user = modelMapper.map(userLoginDto, User.class);
		System.out.println(user);
		User resUser = userService.login(user);

		if (resUser != null)
			return new ResponseEntity<>(resUser, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody UserSignupDto userSignupDto) {
    System.out.println(userSignupDto);
		User user = modelMapper.map(userSignupDto, User.class);
		System.out.println(user);
		//return new ResponseEntity<>( userService.Signup(user),HttpStatus.OK);
		User res=userService.Signup(user);
		if (res == null)
			return new ResponseEntity<>("user already exist", HttpStatus.BAD_REQUEST);

		else
			return new ResponseEntity<>(true, HttpStatus.CREATED);
	}
	
	
	
	
}
