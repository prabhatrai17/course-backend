package com.learning.coursefun.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.coursefun.entities.Cart;
import com.learning.coursefun.entities.Course;
import com.learning.coursefun.entities.User;
import com.learning.coursefun.repo.CartRepo;
import com.learning.coursefun.services.CartService;
import com.learning.coursefun.services.CourseService;
import com.learning.coursefun.services.UserService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepo cartRepo;

	@Autowired
	UserService userService;

	@Autowired
	CourseService courseService;

	@Override
	public Cart getCurrentCart(int userId) {

		Cart resCart = cartRepo.findByUserId(userId);
		return resCart;

	}

	@Override
	public Cart addNewCartToUser(int userId) {

		Optional<User> user = userService.getUserById(userId);

		user.get().setCurrentCart(null);
		return null;
	}

	@Override
	public List<Course> addCourseToCart(int userId, int courseId) {
		// get user object
		Optional<User> user = userService.getUserById(userId);

		// get cart object by using user id of cart
		Cart cart = cartRepo.findByUserId(userId);

		// if user has cart append course to course list
		Optional<Course> course = courseService.getCourse(courseId);

		if (cart == null) {
			cart = new Cart();
		}
		if(!cart.getCourseList().contains(course.get()))//check if course already exist
		cart.getCourseList().add(course.get());// course appended
		cart.setCartQty(cart.getCartQty() + 1);
		cart.setCartValue(cart.getCartValue() + course.get().getCoursePrice());
		cart.setUser(user.get());
		Cart cartObj = cartRepo.save(cart);// save current cart
		user.get().setCurrentCart(cartObj);// sets cart to current user
		userService.addUser(user.get());// save user
		return cart.getCourseList();

	}

	@Override
	public List<Course> removeCourseFromCart(int userId, int courseId) {
		Cart cart=this.getCurrentCart(userId);
		Optional<Course> course=courseService.getCourse(courseId);
		if(cart.getCourseList().contains(course.get())) {
			 cart.getCourseList().remove(course.get());
			 cartRepo.save(cart);
		}
		return cart.getCourseList();
	}

	@Override
	public List<Course> removeAllCourseFromCart(int userId) {
		
		Cart cart=this.getCurrentCart(userId);
		 cart.getCourseList().clear();
		 cartRepo.save(cart);
		 return cart.getCourseList();
	}

}
