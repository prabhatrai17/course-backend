package com.learning.coursefun.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String qTitle;
	String qOption1;
	String qOption2;
	String qOption3;
	String qOption4;
	boolean isAnswered=false;
	String answer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqOption1() {
		return qOption1;
	}
	public void setqOption1(String qOption1) {
		this.qOption1 = qOption1;
	}
	public String getqOption2() {
		return qOption2;
	}
	public void setqOption2(String qOption2) {
		this.qOption2 = qOption2;
	}
	public String getqOption3() {
		return qOption3;
	}
	public void setqOption3(String qOption3) {
		this.qOption3 = qOption3;
	}
	public String getqOption4() {
		return qOption4;
	}
	public void setqOption4(String qOption4) {
		this.qOption4 = qOption4;
	}
	public boolean isAnswered() {
		return isAnswered;
	}
	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Question(int id, String qTitle, String qOption1, String qOption2, String qOption3, String qOption4,
			boolean isAnswered, String answer) {
		super();
		this.id = id;
		this.qTitle = qTitle;
		this.qOption1 = qOption1;
		this.qOption2 = qOption2;
		this.qOption3 = qOption3;
		this.qOption4 = qOption4;
		this.isAnswered = isAnswered;
		this.answer = answer;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", qTitle=" + qTitle + ", qOption1=" + qOption1 + ", qOption2=" + qOption2
				+ ", qOption3=" + qOption3 + ", qOption4=" + qOption4 + ", isAnswered=" + isAnswered + ", answer="
				+ answer + "]";
	}
	
	
	

}
