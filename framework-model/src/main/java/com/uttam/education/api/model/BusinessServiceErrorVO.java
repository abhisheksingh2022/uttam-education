package com.uttam.education.api.model;

import java.io.Serializable;
import java.util.List;

public class BusinessServiceErrorVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorKey;
	private List<Object> substitutions;
	private int severity = 1;

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public List<Object> getSubstitutions() {
		return substitutions;
	}

	public void setSubstitutions(List<Object> substitutions) {
		this.substitutions = substitutions;
	}

	public int getSeverity() {
		// TODO Auto-generated method stub
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}
}
