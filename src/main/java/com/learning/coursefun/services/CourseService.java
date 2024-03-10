package com.learning.coursefun.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.coursefun.entities.Course;
import com.learning.coursefun.entities.CourseCategory;
import com.learning.coursefun.entities.CourseModule;
import com.learning.coursefun.entities.FeedBack;
import com.learning.coursefun.entities.ModuleTopic;

public interface CourseService {

	public List<Course> getAllCourses();

	public Optional<Course> getCourse(int id);
	
	public Course addCourse(Course course);

	public Course addCourseWithTime(Course course,int userId);
	
	public boolean enrollCourse( int userId, int courseId);
	
	public List<Course> enrolledCoursesByUserId(int userId);
	
	public List<Course> boughtCoursesByUserId(int userId);
	
	public boolean buyCourse( int userId, int courseId);

	public Course addCourseModuleByCourseId(CourseModule courseModule, int courseId,int userId);
	
	public Course addCourseFeedbackByCourseId(FeedBack courseFeedback, int courseId,int userId);

	public Course addTopicToCourseModule(ModuleTopic moduleTopic, int userId, int courseId, int moduleId);

	public boolean deleteCourse(int courseId, int userId);

	public boolean deleteCourseModule(int userId, int courseId,int courseModuleId);

	public Course deleteTopicFromCourseModule(int userId, int courseId, int moduleId, int moduleTopicId);

	public CourseCategory addCourseCategory(CourseCategory courseCategory);

	public List<CourseCategory> getAllCourseCategory();

	public CourseCategory getCourseCategoryById(int courseCategoryId);

	public boolean removeCourseCategoryById(int courseCategoryId);

	public List<Course> getAllPendingVerificationCourses();

	public List<Course> getAllAcceptedCourses();

	public List<Course> getAllRejectedCourses();

	public Course aprroveCourse(int userId, int courseId);

	public Course rejectCourse(int userId, int courseId);

}
