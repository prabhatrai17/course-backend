package com.learning.coursefun.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FeedBack {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	int rating;
	String reviewTitle;
	String reviewDesc;
	int ratingBy;
	
	

	public int getRatingBy() {
		return ratingBy;
	}

	public void setRatingBy(int ratingBy) {
		this.ratingBy = ratingBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewDesc() {
		return reviewDesc;
	}

	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}

	

	public FeedBack(int id, int rating, String reviewTitle, String reviewDesc, int ratingBy) {
		super();
		this.id = id;
		this.rating = rating;
		this.reviewTitle = reviewTitle;
		this.reviewDesc = reviewDesc;
		this.ratingBy = ratingBy;
	}

	public FeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FeedBack [id=" + id + ", rating=" + rating + ", reviewTitle=" + reviewTitle + ", reviewDesc="
				+ reviewDesc + ", ratingBy=" + ratingBy + "]";
	}

	

}
