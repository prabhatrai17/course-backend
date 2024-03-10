package com.learning.coursefun.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.coursefun.entities.Assesment;
import com.learning.coursefun.entities.Course;
import com.learning.coursefun.entities.CourseModule;
import com.learning.coursefun.entities.User;
import com.learning.coursefun.repo.AssesmentRepo;
import com.learning.coursefun.repo.CourseModuleRepo;
import com.learning.coursefun.services.AssesmentService;
import com.learning.coursefun.services.CourseService;
import com.learning.coursefun.services.UserService;

@Service
public class AssesmentServiceImpl implements AssesmentService {
	
	@Autowired
	AssesmentRepo assesmentRepo;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CourseModuleRepo courseModuleRepo;

	@Override
	public boolean createAssesment(int userId, int courseId, int moduleId, Assesment assesment) {
		// TODO Auto-generated method stub
		
		Course course=courseService.getCourse(courseId).get();
		// User user=userService.getUserById(userId).get();
		 
		 if(course.getCourseBy()!=userId) {
			 return false;
		 }
		 List<CourseModule> cmList=course.getCourseModule();
		 for(CourseModule cm:cmList ) {
			
			 if(cm.getId()==moduleId) {
				 System.out.println("course module found");
				 cm.setAssesment(assesment);
				 courseService.addCourse(course);
				// courseModuleRepo.save(cm);
				 return true;
				 //break;
			 }
		 }
		 
		 return false;
		
		
	}

	@Override
	public Optional<Assesment> getAssesmentById(int assesmentId) {
		// TODO Auto-generated method stub
		Optional<Assesment> as=assesmentRepo.findById(assesmentId);
		return as;
		
	}

	@Override
	public boolean deleteAssesmentById(int userId, int courseId, int assesmentId) {
		// TODO Auto-generated method stub
		Optional<Course> course=courseService.getCourse(courseId);
		if(course.isPresent()) {
			if(course.get().getCourseBy()!=userId) return false;
			for(CourseModule cm:course.get().getCourseModule()) {
				if(cm.getAssesment()!=null&&cm.getAssesment().getId()==assesmentId) {
					
					//assesmentRepo.deleteById(assesmentId);
					//cm.setAssesment(null);
					course.get().getCourseModule()
					.get(course
							.get()
							.getCourseModule()
							.indexOf(cm))
					.setAssesment(null);
					courseService.addCourse(course.get());
					assesmentRepo.deleteById(assesmentId);
					return true;
				}
				}
		}
	
		return false;
		}
		
	}


