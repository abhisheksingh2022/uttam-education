package com.uttam.education.api.model.criteria;

import java.io.Serializable;

public class WorkItemSearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loginId;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	

}
