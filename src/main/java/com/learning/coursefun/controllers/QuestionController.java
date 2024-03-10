package com.learning.coursefun.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.coursefun.entities.Assesment;
import com.learning.coursefun.entities.Question;
import com.learning.coursefun.services.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/course/module/assesment/question/{questionId}")
	public ResponseEntity<?> getOneQuestion(@PathVariable int questionId) {
		Optional<Question> res=questionService.getQuestionById(questionId);
		if(res.isPresent()) return new ResponseEntity<>(res.get(),HttpStatus.OK);
		else return new ResponseEntity<>("question not found",HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping("/course/module/assesment/question/add/{userId}/{courseId}/{assesmentId}")
	public ResponseEntity<?> addQuestion(@RequestBody Question question,@PathVariable int userId,@PathVariable int courseId,@PathVariable int assesmentId) {
		Assesment assesment=questionService.addQuestion(userId,courseId,assesmentId,question);
		if(assesment.getId()==0) 
			return new ResponseEntity<>("unauthorised user or something went wrong",HttpStatus.BAD_REQUEST);
		else 
			return new ResponseEntity<>(assesment,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/course/module/assesment/question/delete/{userId}/{courseId}/{assesmentId}/{questionId}")
	public ResponseEntity<?> deleteOneQuestion(@PathVariable int userId,@PathVariable int courseId,@PathVariable int assesmentId,@PathVariable int questionId) {
		
		boolean res=questionService.deleteQuestionById(userId, courseId, assesmentId, questionId);
		
		if(res==true) 
			return new ResponseEntity<>("Question Deleted Succesfully",HttpStatus.OK);
		else
			return new ResponseEntity<>("Unauthorised User or invalid request",HttpStatus.BAD_REQUEST);
	}
	

}
