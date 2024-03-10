package com.learning.coursefun.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CourseModule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String moduleTitle;
	String moduleDesc;

	@OneToMany(cascade = CascadeType.ALL)
	List<ModuleTopic> moduleTopics = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
    Assesment assesment= new Assesment();
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CourseModule))
			return false;
		CourseModule cm = (CourseModule) obj;
		if (this.moduleTitle.equals(cm.moduleTitle) && this.moduleDesc.equals(cm.moduleDesc))
			return true;
		return super.equals(obj);
	}

	public List<ModuleTopic> getModuleTopics() {
		return moduleTopics;
	}

	public void setModuleTopics(List<ModuleTopic> moduleTopics) {
		this.moduleTopics = moduleTopics;
	}
	
	

	public Assesment getAssesment() {
		return assesment;
	}

	public void setAssesment(Assesment assesment) {
		this.assesment = assesment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModuleTitle() {
		return moduleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	

	public CourseModule(int id, String moduleTitle, String moduleDesc, List<ModuleTopic> moduleTopics,
			Assesment assesment) {
		super();
		this.id = id;
		this.moduleTitle = moduleTitle;
		this.moduleDesc = moduleDesc;
		this.moduleTopics = moduleTopics;
		this.assesment = assesment;
	}

	public CourseModule() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CourseModule [id=" + id + ", moduleTitle=" + moduleTitle + ", moduleDesc=" + moduleDesc
				+ ", moduleTopics=" +moduleTopics + ", assesment=" + assesment + "]";
	}

	

}
