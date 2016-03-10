package com.uttam.education.api.model.criteria;

import java.io.Serializable;
import java.util.Date;

public class LoginHistorySearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dateFrom;
	private Date dateTo;
	private String carrierId;
	private String organisation;
	private String userId;
	
	
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public String getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
