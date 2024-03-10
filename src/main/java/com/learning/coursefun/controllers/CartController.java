package com.learning.coursefun.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.coursefun.entities.Cart;
import com.learning.coursefun.entities.Course;
import com.learning.coursefun.services.CartService;

@RestController
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> currentCartOfUser(@PathVariable int userId) {
		
		Cart res=cartService.getCurrentCart(userId);
		if(res!=null) return new ResponseEntity<>(res,HttpStatus.OK);
		else return new ResponseEntity<>("No value Found in cart",HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/cart/add/{userId}/{courseId}")
	public ResponseEntity<?> addCourseToCart(@PathVariable int userId,@PathVariable int courseId) {
		
		List<Course> resList=cartService.addCourseToCart(userId,courseId);
		if(resList!=null) return new ResponseEntity<>(resList,HttpStatus.OK);
		else return new ResponseEntity<>("Item Couldn't added",HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/cart/remove/{userId}/{courseId}")
	public ResponseEntity<?> removeCourseFromCart(@PathVariable int userId,@PathVariable int courseId) {
		
		List<Course> resList=cartService.removeCourseFromCart(userId,courseId);
		if(resList!=null) return new ResponseEntity<>(resList,HttpStatus.OK);
		else return new ResponseEntity<>("Item Couldn't deleted",HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/cart/remove/{userId}")
	public ResponseEntity<?> removeAllCourseFromCart(@PathVariable int userId) {
		
		List<Course> resList=cartService.removeAllCourseFromCart(userId);
		if(resList!=null) return new ResponseEntity<>(resList,HttpStatus.OK);
		else return new ResponseEntity<>("All Item Couldn't deleted",HttpStatus.NOT_FOUND);
		
	}

}
