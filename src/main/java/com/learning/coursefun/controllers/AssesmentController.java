package com.learning.coursefun.controllers;

import java.util.Objects;
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
import com.learning.coursefun.entities.ModuleAssesmentStatus;
import com.learning.coursefun.services.AssesmentService;
import com.learning.coursefun.services.ModuleAssesmentStatusService;

@RestController
public class AssesmentController {

	@Autowired
	AssesmentService assesmentService;
	
	@Autowired
	ModuleAssesmentStatusService moduleAssesmentStatusService;
	
	@GetMapping("/course/module/assesment/{assesmentId}")
	public ResponseEntity<?> getOneAssesment(@PathVariable int assesmentId) {
		Optional<Assesment> res=assesmentService.getAssesmentById(assesmentId);
		if(!(res.isEmpty())) return new ResponseEntity<>(res,HttpStatus.OK);
		else return new ResponseEntity<>("assesment not found",HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping("/course/module/assesment/{userId}/{courseId}/{moduleId}")
	public ResponseEntity<?> createAssesment(@PathVariable int userId,@PathVariable int courseId,@PathVariable int moduleId,@RequestBody Assesment assesment) {
		boolean res=assesmentService.createAssesment(userId,courseId,moduleId,assesment);
		if (res!=false) return new ResponseEntity<>("assesment added to moudle successfully",HttpStatus.OK);
		else 
			return new ResponseEntity<>("unathorised user or module not exist",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/course/module/assesment/delete/{userId}/{courseId}/{assesmentId}")
	public ResponseEntity<?> deleteOneAssesment(@PathVariable int userId,@PathVariable int courseId,@PathVariable int assesmentId) {
		boolean res=assesmentService.deleteAssesmentById(userId,courseId,assesmentId);
		
		if(res!=false) return new ResponseEntity<>("Assesment deleted successfully",HttpStatus.OK);
		return new ResponseEntity<>("Coundn't delete assesment, user not authorised or assessment not found",HttpStatus.BAD_GATEWAY);
	}
	
	@GetMapping("user/{userId}/course/{courseId}/module/{moduleId}/assesment/{assesmentId}/start")
	public ResponseEntity<?> startAssesment(@PathVariable int userId,@PathVariable int courseId,@PathVariable int moduleId, @PathVariable int assesmentId) {
		ModuleAssesmentStatus res=moduleAssesmentStatusService.startModuleAssesment(userId,courseId,moduleId,assesmentId);
		if(Objects.nonNull(res)) return new ResponseEntity<>(res,HttpStatus.OK);
		else return new ResponseEntity<>("couldn't start assesment",HttpStatus.BAD_REQUEST);
		
	}
	
	

}
