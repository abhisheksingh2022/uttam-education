package com.uttam.education.api.model.criteria;

import java.io.Serializable;

public class PlanRateSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String planId;
	private String ratingArea;
//	private String tobaccoPreference;
	private String age;
	
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getRatingArea() {
		return ratingArea;
	}
	public void setRatingArea(String ratingArea) {
		this.ratingArea = ratingArea;
	}
	/*public String getTobaccoPreference() {
		return tobaccoPreference;
	}
	public void setTobaccoPreference(String tobaccoPreference) {
		this.tobaccoPreference = tobaccoPreference;
	}*/
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}
