package com.learning.coursefun.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class CourseDto {
	@NotEmpty(message = "please provide course name")
	String courseName;
	@NotEmpty(message = "please provide course description")
	String courseDesc;
	@NotNull(message = "cannot be null")
	int coursePrice;
	@NotEmpty(message = "please provide course category")
	String category;
	@NotEmpty(message = "please provide course img")
	String imgUrl;
	@NotNull(message = "courseBy required")
	int courseBy;
	@NotNull(message = "course duration required")
	int courseDuration;
	@NotEmpty(message = "course difficulty required")
	String courseDifficulty;
//	@NotEmpty(message = "course date required")
	String courseDate;
//	User userEnrolled;
//
//	public User getUserEnrolled() {
//		return userEnrolled;
//	}
//
//	public void setUserEnrolled(User userEnrolled) {
//		this.userEnrolled = userEnrolled;
//	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public int getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(int coursePrice) {
		this.coursePrice = coursePrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getCourseBy() {
		return courseBy;
	}

	public void setCourseBy(int courseBy) {
		this.courseBy = courseBy;
	}

	public int getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseDifficulty() {
		return courseDifficulty;
	}

	public void setCourseDifficulty(String courseDifficulty) {
		this.courseDifficulty = courseDifficulty;
	}

	public String getCourseDate() {
		return courseDate;
	}

	public void setCourseDate(String courseDate) {
		this.courseDate = courseDate;
	}

	public CourseDto(@NotEmpty(message = "please provide course name") String courseName,
			@NotEmpty(message = "please provide course description") String courseDesc,
			@NotNull(message = "cannot be null") int coursePrice,
			@NotEmpty(message = "please provide course category") String category,
			@NotEmpty(message = "please provide course img") String imgUrl,
			@NotNull(message = "courseBy required") int courseBy,
			@NotNull(message = "course duration required") int courseDuration,
			@NotEmpty(message = "course difficulty required") String courseDifficulty,
			@NotEmpty(message = "course date required") String courseDate) {
		super();
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.coursePrice = coursePrice;
		this.category = category;
		this.imgUrl = imgUrl;
		this.courseBy = courseBy;
		this.courseDuration = courseDuration;
		this.courseDifficulty = courseDifficulty;
		this.courseDate = courseDate;
//		this.userEnrolled = userEnrolled;
	}

	public CourseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CourseDto [courseName=" + courseName + ", courseDesc=" + courseDesc + ", coursePrice=" + coursePrice
				+ ", category=" + category + ", imgUrl=" + imgUrl + ", userEnrolled="  + "]";
	}

}
