package com.learning.coursefun.repo;

import org.springframework.data.repository.CrudRepository;

import com.learning.coursefun.entities.CourseCategory;

public interface CourseCategoriesRepo extends CrudRepository<CourseCategory, Integer> {

}
