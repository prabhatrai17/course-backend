package com.learning.coursefun.servicesimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.coursefun.entities.Assesment;
import com.learning.coursefun.entities.Course;
import com.learning.coursefun.entities.CourseModule;
import com.learning.coursefun.entities.Question;
import com.learning.coursefun.entities.User;
import com.learning.coursefun.repo.AssesmentRepo;
import com.learning.coursefun.repo.CourseModuleRepo;
import com.learning.coursefun.repo.CourseRepo;
import com.learning.coursefun.repo.QuestionRepo;
import com.learning.coursefun.repo.UserRepo;
import com.learning.coursefun.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionRepo questionRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	CourseRepo courseRepo;
	@Autowired
	CourseModuleRepo courseModuleRepo;
	@Autowired
	AssesmentRepo assesmentRepo;
	

	@Override
	public Optional<Question> getQuestionById(int questionId) {
		// TODO Auto-generated method stub
		return questionRepo.findById(questionId);
		
	}

	@Override
	public Assesment addQuestion(int userId, int courseId, int assesmentId, Question question) {
		// TODO Auto-generated method stub
		//Optional<User> user=userRepo.findById(userId);
		System.out.println("add question impl");
		Optional<Course> course=courseRepo.findById(courseId);
		if(course.isPresent()) {
			if(course.get().getCourseBy()!=userId) return new Assesment();
			questionRepo.save(question);
			Assesment temp=assesmentRepo.findById(assesmentId).get();
			temp.setNoQuestions(temp.getNoQuestions()+1);
			assesmentRepo.save(temp); 
			
			for(CourseModule cm:course.get().getCourseModule()) {
				if(cm.getAssesment().getId()==assesmentId) {
					cm.getAssesment().getQuestion().add(question);
					courseModuleRepo.save(cm);
					
					return cm.getAssesment();
				}
			}
		
		}
		 return new Assesment();
		 
		
	}

	@Override
	public boolean deleteQuestionById(int userId, int courseId, int assesmentId, int questionId) {
		// TODO Auto-generated method stub
		Optional<Course> course=courseRepo.findById(courseId);
		if(course.isPresent()) {
			if(course.get().getCourseBy()!=userId) {
				return false;
			}
			for(CourseModule cm:course.get().getCourseModule()) {
				if(cm.getAssesment().getId()==assesmentId){
					Question ques=questionRepo.findById(questionId).get();
					cm.getAssesment().getQuestion().remove(ques);
					courseModuleRepo.save(cm);
					questionRepo.deleteById(questionId);
					
					return true;
				}
				
			}
		}
		return false;
		
	}

}
