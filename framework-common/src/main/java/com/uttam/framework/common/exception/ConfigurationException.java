/**
 * 
 */
package com.uttam.framework.common.exception;

import org.apache.commons.lang3.Validate;

import com.uttam.framework.common.model.BaseException;

/**
 * @author admin
 *
 */
public class ConfigurationException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1901539459756043223L;
	
	
	private String configType;
	
	public ConfigurationException(Throwable ex, String msg, String configType){
		super(Validate.notBlank(msg), null);
		this.configType = Validate.notBlank(configType);
		super.setErrorCode ( "exception.configuration");
		super.cause = ex;
	}

	

	/**
	 * @return the configType
	 */
	public String getConfigType() {
		return configType;
	}

	/**
	 * @param configType the configType to set
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	

}
