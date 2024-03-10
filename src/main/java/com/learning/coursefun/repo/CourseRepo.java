package com.learning.coursefun.repo;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.coursefun.entities.Course;
import com.learning.coursefun.entities.User;

public interface CourseRepo extends JpaRepository<Course, Integer> {
	
	@Query(value="select * from courses where course_verified='pending'",nativeQuery=true)
	public List<Course> getAllPendingCourse();
	
	@Query(value="select * from courses where course_verified='accepted'",nativeQuery=true)
	public List<Course> getAllVerifiedCourses();
	
	@Query(value="select * from courses where course_verified='rejected'",nativeQuery=true)
	public List<Course> getAllRejectedCourses();

//	@Query(value="select * from users", nativeQuery=true)
//	public List<User> getEnrolledCoursesOfUser();
}
