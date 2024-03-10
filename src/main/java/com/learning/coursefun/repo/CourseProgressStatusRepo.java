package com.learning.coursefun.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.coursefun.entities.CourseProgressStatus;

public interface CourseProgressStatusRepo extends JpaRepository<CourseProgressStatus, Integer> {

}
