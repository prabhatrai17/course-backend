package com.learning.coursefun.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ModuleAssesmentStatus {
	@Id
	@GeneratedValue
	int id;
	int moduleId;
	boolean moduleCompleted=false;
	int assesmentId;
	int assesmentScore=0;
	boolean assesmentPassed=false;
	boolean assesmentCompleted=false;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public boolean isModuleCompleted() {
		return moduleCompleted;
	}
	public void setModuleCompleted(boolean moduleCompleted) {
		this.moduleCompleted = moduleCompleted;
	}
	public int getAssesmentId() {
		return assesmentId;
	}
	public void setAssesmentId(int assesmentId) {
		this.assesmentId = assesmentId;
	}
	public int getAssesmentScore() {
		return assesmentScore;
	}
	public void setAssesmentScore(int assesmentScore) {
		this.assesmentScore = assesmentScore;
	}
	public boolean isAssesmentPassed() {
		return assesmentPassed;
	}
	public void setAssesmentPassed(boolean assesmentPassed) {
		this.assesmentPassed = assesmentPassed;
	}
	public boolean isAssesmentCompleted() {
		return assesmentCompleted;
	}
	public void setAssesmentCompleted(boolean assesmentCompleted) {
		this.assesmentCompleted = assesmentCompleted;
	}
	public ModuleAssesmentStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ModuleAssesmentStatus(int id, int moduleId, boolean moduleCompleted, int assesmentId, int assesmentScore,
			boolean assesmentPassed, boolean assesmentCompleted) {
		super();
		this.id = id;
		this.moduleId = moduleId;
		this.moduleCompleted = moduleCompleted;
		this.assesmentId = assesmentId;
		this.assesmentScore = assesmentScore;
		this.assesmentPassed = assesmentPassed;
		this.assesmentCompleted = assesmentCompleted;
	}
	@Override
	public String toString() {
		return "ModuleAssesmentStatus [id=" + id + ", moduleId=" + moduleId + ", moduleCompleted=" + moduleCompleted
				+ ", assesmentId=" + assesmentId + ", assesmentScore=" + assesmentScore + ", assesmentPassed="
				+ assesmentPassed + ", assesmentCompleted=" + assesmentCompleted + "]";
	}
	
	
	
	
	

}
