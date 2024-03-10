package com.learning.coursefun.services;

import java.util.List;
import java.util.Optional;

import com.learning.coursefun.entities.Assesment;
import com.learning.coursefun.entities.CourseModule;
import com.learning.coursefun.entities.ModuleAssesmentStatus;

public interface ModuleAssesmentStatusService {
	
	List<ModuleAssesmentStatus> createModuleAssesmentStatus(List<CourseModule> courseModuleList);
	List<ModuleAssesmentStatus> updateModuleAssesmentStatus(Assesment assesment,int moduleId);
	ModuleAssesmentStatus startModuleAssesment(int userId, int courseId, int moduleId, int assesmentId);
	

}
