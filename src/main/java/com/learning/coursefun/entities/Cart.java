package com.learning.coursefun.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int cartId;
	int cartValue;
	int cartQty;
	@OneToOne
	User user;
	@ManyToMany
	List<Course> courseList = new ArrayList<>();

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCartValue() {
		return cartValue;
	}

	public void setCartValue(int cartValue) {
		this.cartValue = cartValue;
	}

	public int getCartQty() {
		return cartQty;
	}

	public void setCartQty(int cartQty) {
		this.cartQty = cartQty;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public Cart(int cartId, int cartValue, int cartQty, User user, List<Course> courseList) {
		super();
		this.cartId = cartId;
		this.cartValue = cartValue;
		this.cartQty = cartQty;
		this.user = user;
		this.courseList = courseList;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", cartValue=" + cartValue + ", cartQty=" + cartQty + ", user=" + user
				+ ", courseList=" + courseList + "]";
	}

}
