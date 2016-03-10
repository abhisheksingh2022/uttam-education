package com.uttam.education.api.model.criteria;

import java.io.Serializable;

public class PlanHistoryCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private String year;
	 private String carrier;
	 private String planId;
	 private String planName;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}

}
