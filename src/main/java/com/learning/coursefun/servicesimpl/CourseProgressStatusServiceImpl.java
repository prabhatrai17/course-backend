package com.learning.coursefun.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.coursefun.entities.CourseProgressStatus;
import com.learning.coursefun.repo.CourseProgressStatusRepo;
import com.learning.coursefun.services.CourseProgressStatusService;

@Service
public class CourseProgressStatusServiceImpl implements CourseProgressStatusService {
	
	@Autowired
	CourseProgressStatusRepo courseProgressStatusRepo;

	@Override
	public CourseProgressStatus saveCourseProgressStatus(CourseProgressStatus courseProgressStatus) {
		// TODO Auto-generated method stub
		return courseProgressStatusRepo.save(courseProgressStatus);
	
	}

}
