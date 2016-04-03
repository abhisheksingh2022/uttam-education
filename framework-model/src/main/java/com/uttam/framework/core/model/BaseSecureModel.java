package com.uttam.framework.core.model;

import java.util.Date;

public abstract class BaseSecureModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String modifiedBy;
	
	private String modifiedGroup;
	
	private Date modifiedDate;
	
	private Date createdDate;

	
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedGroup() {
		return modifiedGroup;
	}

	public void setModifiedGroup(String modifiedGroup) {
		this.modifiedGroup = modifiedGroup;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
