package com.hixapi.web.framework.context;

public enum MetricKeyEnum {

	
	NAVIGATED_FOM_CP ("NAVIGATED_FOM_CP", "Consumer Navigated from Customer Portal");
	
	
	private String code;
	private String description;
	
	MetricKeyEnum(String newCode, String newDescription){
		this.code = newCode;
		this.description = newDescription;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
