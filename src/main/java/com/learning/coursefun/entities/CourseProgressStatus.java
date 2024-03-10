package com.learning.coursefun.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CourseProgressStatus {
	@Id
	@GeneratedValue
	int id;
	int courseId;
	boolean courseCompleted;
	int completionPercentage;
	@OneToMany
	List<ModuleAssesmentStatus> moduleAssesmentStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public boolean isCourseCompleted() {
		return courseCompleted;
	}
	public void setCourseCompleted(boolean courseCompleted) {
		this.courseCompleted = courseCompleted;
	}
	public int getCompletionPercentage() {
		return completionPercentage;
	}
	public void setCompletionPercentage(int completionPercentage) {
		this.completionPercentage = completionPercentage;
	}
	public List<ModuleAssesmentStatus> getModuleAssesmentStatus() {
		return moduleAssesmentStatus;
	}
	public void setModuleAssesmentStatus(List<ModuleAssesmentStatus> moduleAssesmentStatus) {
		this.moduleAssesmentStatus = moduleAssesmentStatus;
	}
	public CourseProgressStatus(int id, int courseId, boolean courseCompleted, int completionPercentage,
			List<ModuleAssesmentStatus> moduleAssesmentStatus) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.courseCompleted = courseCompleted;
		this.completionPercentage = completionPercentage;
		this.moduleAssesmentStatus = moduleAssesmentStatus;
	}
	public CourseProgressStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CourseProgressStatus [id=" + id + ", courseId=" + courseId + ", courseCompleted=" + courseCompleted
				+ ", completionPercentage=" + completionPercentage + ", moduleAssesmentStatus=" + moduleAssesmentStatus
				+ "]";
	}
	
	
	
	
	

}
