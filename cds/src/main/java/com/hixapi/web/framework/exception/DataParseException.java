/**
 * 
 */
package com.hixapi.web.framework.exception;

import org.apache.commons.lang3.Validate;

import com.hixapi.model.common.BaseException;

/**
 * @author admin
 *
 */
public class DataParseException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1901539459756043223L;

	private String fieldValue;

	public DataParseException(String msg, String field, Exception ex) {
		super(Validate.notBlank(msg), null);
		this.fieldValue = Validate.notBlank(field);
		super.setErrorCode("INVALID_DATA");
		super.cause = ex;
	}

	/**
	 * @return the fieldValue
	 */
	public String getFieldValue() {
		return fieldValue;
	}

	/**
	 * @param fieldValue
	 *            the fieldValue to set
	 */
	public void setFieldValue(String configType) {
		this.fieldValue = configType;
	}

}
