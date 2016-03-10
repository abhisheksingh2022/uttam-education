package com.uttam.framework.common.context;

public enum MetricKeyEnum {

	LOGIN ("LOGIN", "User Login"), 
	PLAN_UPLOAD ("PLAN_UPLOAD", "Plan Upload"), 
	PRESCREEN_FAMILY("PRESCREEN_FAMILY","Perform Family Prescreening"),
	PLAN_DETAIL_VIEW ("PLAN_DETAIL_VIEW", "View Plan Details"), 
	CREATE_CARRIER_ACCOUNT ("CREATE_CARRIER_ACCOUNT", "Attempt CarAccount Creation"),
	DOWNLOAD_PDF_FILE_DRAFT("DOWNLOAD_PDF_FILE_DRAFT","Download Draft PDF File"),
	INBOX_MESSAGE_VIEW("INBOX_MESSAGE_VIEW", "View Message List"),
	PDF_DOC_UPLOAD("PDF_DOC_UPLOAD","Upload PDF Document"),
	UNEXPECTED_ERROR("UNEXPECTED_ERROR_VIEW","Unexpected Error Page Viewed"), LOGOUT("LOGOUT","User Logout");
	
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
