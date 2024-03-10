package com.learning.coursefun.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ModuleTopic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String topicTitle;
	String topicDesc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopicTitle() {
		return topicTitle;
	}
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	public String getTopicDesc() {
		return topicDesc;
	}
	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}
	public ModuleTopic(int id, String topicTitle, String topicDesc) {
		super();
		this.id = id;
		this.topicTitle = topicTitle;
		this.topicDesc = topicDesc;
	}
	public ModuleTopic() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ModuleTopic [id=" + id + ", topicTitle=" + topicTitle + ", topicDesc=" + topicDesc + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(!(obj instanceof ModuleTopic)) return false;
		ModuleTopic mt=(ModuleTopic)obj;
		if(this.topicTitle.equals(mt.topicTitle)&&this.topicDesc.equals(mt.topicDesc)) return true;
		return super.equals(obj);
	}
	
	

}
