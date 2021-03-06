package com.uttam.framework.common.model;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.uttam.framework.common.ConversionUtil;

public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2811466351862714577L;

	private String message;
	private String errorCode = "txt.global.error_unknown";
	
	private Long errorIdNbr;

	protected Throwable cause;

	public BaseException() {
		this.message = "Unknown Error";
	}

	public BaseException(String msg, Throwable ex) {
		this.message = Validate.notBlank(msg);
		this.cause = ex;
	}

	/**
	 * Subclasses override this
	 * 
	 * @return
	 */
	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return message;
	}

	@Override
	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String showDetailView() {
		StringBuilder b = new StringBuilder();
		b.append(this.errorCode).append(" - ").append(this.message).append("\n");
		b.append("Reference id: " ).append(ConversionUtil.toString(errorIdNbr)).append("\n");
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		this.printStackTrace(pw);
		b.append(sw.toString());
		return b.toString();
	}

	public String getMessage() {
		return StringUtils.join(new String[] { errorCode, message }, ":");
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getErrorIdNbr() {
		return errorIdNbr;
	}

	public void setErrorIdNbr(Long errorIdNbr) {
		this.errorIdNbr = errorIdNbr;
	}

}
