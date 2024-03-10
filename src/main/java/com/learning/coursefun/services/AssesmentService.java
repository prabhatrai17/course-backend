package com.learning.coursefun.services;

import java.util.Optional;

import com.learning.coursefun.entities.Assesment;

public interface AssesmentService {

	boolean createAssesment(int userId, int courseId,int moduleId,Assesment assesment);

	Optional<Assesment> getAssesmentById(int assesmentId);

	boolean deleteAssesmentById(int userId, int courseId, int assesmentId);

}
