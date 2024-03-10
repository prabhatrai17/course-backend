package com.learning.coursefun.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CourseCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String categoryName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public CourseCategory(int id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}
	public CourseCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CourseCategory [id=" + id + ", categoryName=" + categoryName + "]";
	}
	
	

}
