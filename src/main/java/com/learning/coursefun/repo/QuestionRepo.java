package com.learning.coursefun.repo;

import org.springframework.data.repository.CrudRepository;

import com.learning.coursefun.entities.Question;

public interface QuestionRepo extends CrudRepository<Question, Integer> {

}
