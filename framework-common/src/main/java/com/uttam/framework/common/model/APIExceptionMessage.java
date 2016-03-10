package com.uttam.framework.common.model;

public class APIExceptionMessage {
	/**
	 * 
	 */
	private BaseException exception;
	
	public APIExceptionMessage(){
		
	}
	public APIExceptionMessage(BaseException e) {
		exception = e;
	}

	public BaseException getException() {
		return exception;
	}

	public void setException(BaseException exception) {
		this.exception = exception;
	}
	
	public String getErrorCode(){
		if(exception != null){
			return exception.getErrorCode();
		}
		return "";
	}
	public String getDetail(){
		if(exception != null){
			return exception.showDetailView();
		}
		return "";
	}
	@Override
	public String toString() {
		return "APIExceptionMessage [getErrorCode()=" + getErrorCode() + ", getDetail()=" + getDetail() + "]";
	}
}
