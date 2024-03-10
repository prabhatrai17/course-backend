package com.learning.coursefun.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	int id;
	String name;
	String email;
	String pass;
	String role;

	// @JsonManagedReference

	// @JsonIgnoreProperties("enrolledUser")
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "enrolledUser")
	List<Course> enrolledCourse = new ArrayList<>();

	// @JsonIgnoreProperties("userBought")
	// @JsonBackReference
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "userBought")
	List<Course> purchasedCourse = new ArrayList<>();

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	Cart currentCart;
	
	@OneToMany
	List<CourseProgressStatus> courseProgressStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Course> getEnrolledCourse() {
		return enrolledCourse;
	}

	public void setEnrolledCourse(List<Course> enrolledCourse) {
		this.enrolledCourse = enrolledCourse;
	}

	public List<Course> getPurchasedCourse() {
		return purchasedCourse;
	}

	public void setPurchasedCourse(List<Course> purchasedCourse) {
		this.purchasedCourse = purchasedCourse;
	}

	public Cart getCurrentCart() {
		return currentCart;
	}

	public void setCurrentCart(Cart currentCart) {
		this.currentCart = currentCart;
	}
	
	

	public List<CourseProgressStatus> getCourseProgressStatus() {
		return courseProgressStatus;
	}

	public void setCourseProgressStatus(List<CourseProgressStatus> courseProgressStatus) {
		this.courseProgressStatus = courseProgressStatus;
	}

	

	public User(int id, String name, String email, String pass, String role, List<Course> enrolledCourse,
			List<Course> purchasedCourse, Cart currentCart, List<CourseProgressStatus> courseProgressStatus) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.enrolledCourse = enrolledCourse;
		this.purchasedCourse = purchasedCourse;
		this.currentCart = currentCart;
		this.courseProgressStatus = courseProgressStatus;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", pass=" + pass + ", role=" + role
				+ ", enrolledCourse=" + enrolledCourse + ", purchasedCourse=" + purchasedCourse + ", currentCart="
				+ currentCart + ", courseProgressStatus=" + courseProgressStatus + "]";
	}



}
