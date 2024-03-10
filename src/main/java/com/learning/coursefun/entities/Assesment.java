package com.learning.coursefun.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Assesment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String assesmentName;
	int noQuestions=0; 	
	boolean isCompleted=false;
	@OneToMany
	List<Question> question= new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAssesmentName() {
		return assesmentName;
	}
	public void setAssesmentName(String assesmentName) {
		this.assesmentName = assesmentName;
	}
	public int getNoQuestions() {
		return noQuestions;
	}
	public void setNoQuestions(int noQuestions) {
		this.noQuestions = noQuestions;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	public List<Question> getQuestion() {
		return question;
	}
	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public Assesment(int id, String assesmentName, int noQuestions, boolean isCompleted, List<Question> question) {
		super();
		this.id = id;
		this.assesmentName = assesmentName;
		this.noQuestions = noQuestions;
		this.isCompleted = isCompleted;
		this.question = question;
	}
	public Assesment() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Assesment [id=" + id + ", assesmentName=" + assesmentName + ", noQuestions=" + noQuestions
				+ ", isCompleted=" + isCompleted + ", question=" + question + "]";
	}
	
	
	

}
