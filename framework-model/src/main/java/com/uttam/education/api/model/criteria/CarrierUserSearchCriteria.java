package com.uttam.education.api.model.criteria;

import java.io.Serializable;

public class CarrierUserSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String statusCode;
	private String userId;
	private String carrierIdNbr;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCarrierIdNbr() {
		return carrierIdNbr;
	}
	public void setCarrierIdNbr(String carrierIdNbr) {
		this.carrierIdNbr = carrierIdNbr;
	}
	
}
