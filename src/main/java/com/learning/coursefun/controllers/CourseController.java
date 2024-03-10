package com.learning.coursefun.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.coursefun.dto.CourseDto;
import com.learning.coursefun.entities.Course;
import com.learning.coursefun.entities.CourseCategory;
import com.learning.coursefun.entities.CourseModule;
import com.learning.coursefun.entities.FeedBack;
import com.learning.coursefun.entities.ModuleTopic;
import com.learning.coursefun.entities.User;
import com.learning.coursefun.services.CourseService;
import com.learning.coursefun.services.UserService;
import com.learning.coursefun.util.LoggerUtil;

@RestController
public class CourseController {
	
	Logger logger=LoggerUtil.getLogger();

	@Autowired
	CourseService courseService;

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;

	// get all course
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}
   //get course by id
	@GetMapping("/course/{id}")
	public ResponseEntity<?> getCourse(@PathVariable("id") int id) {
		Optional<Course> resCourse = courseService.getCourse(id);
		return new ResponseEntity<>(resCourse, HttpStatus.OK);
	}

	// create course
	@PostMapping("/course/{userId}")
	public ResponseEntity<?> addCourse(@RequestBody @Valid CourseDto courseDto, @PathVariable("userId") int userId) {
		System.out.println(courseDto);
		Course course = modelMapper.map(courseDto, Course.class);

		Course resCourse = courseService.addCourseWithTime(course, userId);
		if (resCourse != null)
			return new ResponseEntity<>(resCourse, HttpStatus.OK);
		else
			return new ResponseEntity<>("course not created, unauthorised user or something went wrong", HttpStatus.BAD_REQUEST);

	}

	// delete course
	@DeleteMapping("/course/delete/{courseId}/{userId}")
	public ResponseEntity<?> deleteCourse(@PathVariable int courseId, @PathVariable int userId) {
		// System.out.println(courseDto);
		// Course course = modelMapper.map(courseDto, Course.class);

		boolean res = courseService.deleteCourse(courseId, userId);
		if (res == true)
			return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
		else
			return new ResponseEntity<>("cannot delete, unauthorised user", HttpStatus.BAD_REQUEST);

	}

	// enroll course
	@GetMapping("/courseEnrolled/{userId}/{courseId}")
	public ResponseEntity<?> enrollCourse(@PathVariable("userId") int userId, @PathVariable("courseId") int courseId) {
		boolean res = courseService.enrollCourse(userId, courseId);
		if (res == true)
			return new ResponseEntity<>("Enrolled successfully ", HttpStatus.OK);
		else
			return new ResponseEntity<>("cannot enroll, buy before enrolling, user unathorised or already enrolled or something wrong", HttpStatus.BAD_REQUEST);
	}
	//get enrolled courses of user
	@GetMapping("/courses/enrolled/{userId}")
	public ResponseEntity<?> getEnrolledCoursesByUserId( @PathVariable int userId){
		
		logger.info("in get all course by user");
		logger.info("user id recieved: "+userId);
		List<Course> enrolledList= courseService.enrolledCoursesByUserId(userId);
		if(enrolledList.isEmpty()) 
			return new ResponseEntity<>("No course Enrolled or invalid user or something went wrong",HttpStatus.NOT_FOUND);
		logger.info("fetched course list");
		return new ResponseEntity<>(enrolledList,HttpStatus.OK);
	}
	//get bought courses by user
	@GetMapping("/courses/bought/{userId}")
	public ResponseEntity<?> getBoughtCoursesByUserId( @PathVariable int userId){
		System.out.println("in course bought");
		List<Course> boughtList=courseService.boughtCoursesByUserId(userId);
		if(boughtList.isEmpty()) 
			return new ResponseEntity<>("No course bought or invalid user or something went wrong",HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(boughtList,HttpStatus.OK);
	}

	// buy course
	@GetMapping("/courseBuy/{userId}/{courseId}")
	public ResponseEntity<?> buyCourse(@PathVariable("userId") int userId, @PathVariable("courseId") int courseId) {
		boolean res = courseService.buyCourse(userId, courseId);
		if (res == true)
			return new ResponseEntity<>("Bought successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("cannot buy, user unathorised or already bought or something wrong", HttpStatus.BAD_REQUEST);
	}

	// add course module to course
	@PostMapping("/course/module/{courseId}/{userId}")
	public ResponseEntity<?> addCourseModule(@RequestBody CourseModule courseModule, @PathVariable int courseId,
			@PathVariable int userId) {

		System.out.println(courseModule);
		Course resCourse = courseService.addCourseModuleByCourseId(courseModule, courseId, userId);
		if (resCourse != null)
			return new ResponseEntity<>(resCourse, HttpStatus.OK);
		else
			return new ResponseEntity<>("module cannot be added, user not authorised or module already exist",
					HttpStatus.BAD_REQUEST);

	}

	// delete course module from course
	@DeleteMapping("/course/module/delete/{userId}/{courseId}/{courseModuleId}")
	public ResponseEntity<?> deleteCourseModule(@PathVariable int userId, @PathVariable int courseId,
			@PathVariable int courseModuleId) {

		// System.out.println(courseModule);
		boolean res = courseService.deleteCourseModule(userId, courseId, courseModuleId);
		if (res == true)
			return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("module cannot be deleted, user not authorised or module not exist",
					HttpStatus.BAD_REQUEST);

	}

	// add topic to course module
	@PostMapping("/course/module/topic/{userId}/{courseId}/{moduleId}")
	public ResponseEntity<?> addTopicToCourseModule(@RequestBody ModuleTopic moduleTopic, @PathVariable int userId,
			@PathVariable int courseId, @PathVariable int moduleId) {

		// System.out.println(moduleTopic);
		Course resCourse = courseService.addTopicToCourseModule(moduleTopic, userId, courseId, moduleId);
		if (resCourse != null)
			return new ResponseEntity<>(resCourse, HttpStatus.OK);
		else
			return new ResponseEntity<>("topic cannot be added, user not authorised or topic already exist",
					HttpStatus.BAD_REQUEST);

	}

	// delete topic from course module topic
	@DeleteMapping("/course/module/topic/delete/{userId}/{courseId}/{moduleId}/{moduleTopicId}")
	public ResponseEntity<?> deleteTopicToCourseModule(@PathVariable int userId, @PathVariable int courseId,
			@PathVariable int moduleId, @PathVariable int moduleTopicId) {

		// System.out.println(moduleTopic);
		Course resCourse = courseService.deleteTopicFromCourseModule(userId, courseId, moduleId, moduleTopicId);
		if (resCourse != null)
			return new ResponseEntity<>(resCourse, HttpStatus.OK);
		else
			return new ResponseEntity<>("topic cannot be deleted, user not authorised or topic not exist",
					HttpStatus.BAD_REQUEST);

	}

	// add feedback/rating&review to course
	@PostMapping("/course/feedback/{courseId}/{userId}")
	public ResponseEntity<?> addCourseFeedback(@RequestBody FeedBack courseFeedBack, @PathVariable int courseId,
			@PathVariable int userId) {

		System.out.println(courseFeedBack);
		Course resCourse = courseService.addCourseFeedbackByCourseId(courseFeedBack, courseId, userId);
		if (resCourse != null)
			return new ResponseEntity<>(resCourse, HttpStatus.OK);
		else
			return new ResponseEntity<>("user already given review & rating for course or user unauthorised to give review", HttpStatus.BAD_REQUEST);

	}

	// get all pending(not verified) course
	@GetMapping("/courses/pending")
	public ResponseEntity<?> getAllPendingVerificationCourses() {
		List<Course> pendingList= courseService.getAllPendingVerificationCourses();
		if(pendingList!=null)return new ResponseEntity<>(pendingList,HttpStatus.OK);
		return  new ResponseEntity<>("error occured, try again",HttpStatus.BAD_REQUEST);
	}

	// get all accepted(verified) course
	@GetMapping("/courses/accepted")
	public ResponseEntity<?> getAllAcceptedCourses() {
		List<Course> acceptedList=courseService.getAllAcceptedCourses();
		if(acceptedList!=null) return new ResponseEntity<>(acceptedList,HttpStatus.OK);
		return  new ResponseEntity<>("error occured, try again",HttpStatus.BAD_REQUEST);
	}

	// get all rejected(verification rejected) course
	@GetMapping("/courses/rejected")
	public ResponseEntity<?> getAllRejectedCourses() {
		List<Course> rejectedList=courseService.getAllRejectedCourses();
		if(rejectedList!=null) return new ResponseEntity<>(rejectedList,HttpStatus.OK);
		return  new ResponseEntity<>("error occured, try again",HttpStatus.BAD_REQUEST);
	}
	//approve course by admin
	@GetMapping("/approve/course/{userId}/{courseId}")
	public ResponseEntity<?> approveCourse(@PathVariable int userId, @PathVariable int courseId){
		Course courseRes=courseService.aprroveCourse(userId,courseId);
		if(courseRes!=null) return new ResponseEntity<>(courseRes,HttpStatus.OK);
		return new ResponseEntity<>("cannot approve course, user not authorised or course already reviewed or something went wrong",HttpStatus.BAD_REQUEST);
	}
	
	//reject course by admin
	@GetMapping("/reject/course/{userId}/{courseId}")
	public ResponseEntity<?> rejectCourse(@PathVariable int userId, @PathVariable int courseId){
		Course courseRes=courseService.rejectCourse(userId,courseId);
		if(courseRes!=null) return new ResponseEntity<>(courseRes,HttpStatus.OK);
		return new ResponseEntity<>("user not authorised or course already reviewed or something went wrong",HttpStatus.BAD_REQUEST);
	}

	// add categories of course for admin
	@PostMapping("/addCourseCategory")
	public ResponseEntity<?> addCourseCategory(@RequestBody CourseCategory courseCategory) {
		System.out.println(courseCategory);
		CourseCategory courseCategoryRes = courseService.addCourseCategory(courseCategory);
		System.out.println(courseCategoryRes);
		if (courseCategoryRes != null)
			return new ResponseEntity<>(courseCategoryRes, HttpStatus.OK);
		return new ResponseEntity<>("category cannot created", HttpStatus.BAD_REQUEST);
	}

	// get course categories 
	@GetMapping("/courseCategories")
	public ResponseEntity<?> getAllCourseCategories() {
		List<CourseCategory> courseCategoryList = courseService.getAllCourseCategory();
		if (courseCategoryList != null)
			return new ResponseEntity<>(courseCategoryList, HttpStatus.OK);
		return new ResponseEntity<>("categories cannot be fetched", HttpStatus.BAD_REQUEST);
	}

	// get course category by id
	@GetMapping("/courseCategory/{courseCategoryId}")
	public ResponseEntity<?> getCourseCategoryById(@PathVariable int courseCategoryId) {
		CourseCategory courseCategory = courseService.getCourseCategoryById(courseCategoryId);
		if (courseCategory != null)
			return new ResponseEntity<>(courseCategory, HttpStatus.OK);
		return new ResponseEntity<>("category cannot be fetched", HttpStatus.BAD_REQUEST);
	}

	// remove course category by id
	@DeleteMapping("/courseCategory/remove/{courseCategoryId}")
	public ResponseEntity<?> removeCourseCategoryById(@PathVariable int courseCategoryId) {
		boolean courseCategory = courseService.removeCourseCategoryById(courseCategoryId);
		if (courseCategory != false)
			return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("category cannot be deleted", HttpStatus.BAD_REQUEST);
	}
}
