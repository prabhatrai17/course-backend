package com.learning.coursefun.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	int courseId;
	String courseName;
	String courseDesc;
	int coursePrice;
	String category;
	String imgUrl;
	int courseBy;
	int courseDuration;
	String courseDifficulty;
	String courseDate;
	String courseVerified = "pending";// accepted , rejected, pending
	int courseVerifiedBy;

	@OneToMany(cascade = CascadeType.ALL)
	List<CourseModule> courseModule = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	List<FeedBack> feedBack = new ArrayList<>();

	// @JsonIgnoreProperties("enrolledCourse")
	@ManyToMany(cascade = CascadeType.ALL)
	List<User> enrolledUser = new ArrayList<>();

	// @JsonIgnoreProperties("purchasedCourse")
	// @JsonManagedReference
	// @JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	List<User> userBought = new ArrayList<>();

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public List<CourseModule> getCourseModule() {
		return courseModule;
	}

	public void setCourseModule(List<CourseModule> courseModule) {
		this.courseModule = courseModule;
	}

	public List<FeedBack> getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(List<FeedBack> feedBack) {
		this.feedBack = feedBack;
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

	public String getCourseVerified() {
		return courseVerified;
	}

	public void setCourseVerified(String courseVerified) {
		this.courseVerified = courseVerified;
	}

	public int getCourseVerifiedBy() {
		return courseVerifiedBy;
	}

	public void setCourseVerifiedBy(int courseVerifiedBy) {
		this.courseVerifiedBy = courseVerifiedBy;
	}

	public List<User> getEnrolledUser() {
		return enrolledUser;
	}

	public void setEnrolledUser(List<User> enrolledUser) {
		this.enrolledUser = enrolledUser;
	}

	public List<User> getUserBought() {
		return userBought;
	}

	public void setUserBought(List<User> userBought) {
		this.userBought = userBought;
	}

	public Course(int courseId, String courseName, String courseDesc, int coursePrice, String category, String imgUrl,
			int courseBy, int courseDuration, String courseDifficulty, String courseDate, String courseVerified,
			int courseVerifiedBy, List<CourseModule> courseModule, List<FeedBack> feedBack, List<User> enrolledUser,
			List<User> userBought) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.coursePrice = coursePrice;
		this.category = category;
		this.imgUrl = imgUrl;
		this.courseBy = courseBy;
		this.courseDuration = courseDuration;
		this.courseDifficulty = courseDifficulty;
		this.courseDate = courseDate;
		this.courseVerified = courseVerified;
		this.courseVerifiedBy = courseVerifiedBy;
		this.courseModule = courseModule;
		this.feedBack = feedBack;
		this.enrolledUser = enrolledUser;
		this.userBought = userBought;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public String toString() {
//		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDesc=" + courseDesc
//				+ ", coursePrice=" + coursePrice + ", category=" + category + ", imgUrl=" + imgUrl + ", courseBy="
//				+ courseBy + ", courseDuration=" + courseDuration + ", courseDifficulty=" + courseDifficulty
//				+ ", courseDate=" + courseDate + ", courseVerified=" + courseVerified + ", courseVerifiedBy="
//				+ courseVerifiedBy + ", courseModule=" + courseModule + ", feedBack=" + feedBack + ", enrolledUser="
//				+ enrolledUser + ", userBought=" + userBought + "]";
//	}

}
