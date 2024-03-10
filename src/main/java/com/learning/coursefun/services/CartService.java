package com.learning.coursefun.services;

import java.util.List;

import com.learning.coursefun.entities.Cart;
import com.learning.coursefun.entities.Course;

public interface CartService {

	public Cart getCurrentCart(int userId);
	Cart addNewCartToUser(int userId);
	List<Course> addCourseToCart(int userId,int courseId);
	List<Course> removeCourseFromCart(int userId,int courseId);
	List<Course> removeAllCourseFromCart(int userId);
	
}
