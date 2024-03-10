package com.learning.coursefun.services;

import java.util.Optional;

import com.learning.coursefun.entities.Assesment;
import com.learning.coursefun.entities.Question;

public interface QuestionService {

	Optional<Question> getQuestionById(int questionId);

	Assesment addQuestion(int userId, int courseId, int assesmentId, Question question);

	boolean deleteQuestionById(int userId, int courseId, int assesmentId,int questionId);

}
