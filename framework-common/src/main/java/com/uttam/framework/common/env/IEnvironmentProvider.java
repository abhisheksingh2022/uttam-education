package com.uttam.framework.common.env;

import com.uttam.framework.service.IService;

/**
 * Environment provider interface
 * 
 * @author Abhishek
 *
 */
public interface IEnvironmentProvider extends IService{
	
	/**
	 * Reload the properties. useful for automatic reload of properties stored in
	 * the singleton.
	 */
	public void reload();

	/**
	 * Gets the property.
	 * 
	 * @param code
	 *            the code
	 * 
	 * @return the property
	 */

	/**
	 * Gets the property value.
	 * 
	 * @param code
	 *            the code
	 * 
	 * @return the property value
	 */

	/**
	 * Checks if is production.
	 * 
	 * @return true, if is production
	 */
	public boolean isProduction();
	public String getEnvironment();

	public <T> T getProperty(String code, Class<T> clazz, T defaultVal);
	public <T> T getPropertyInGroup(String group, String code, Class<T> clazz, T defaultVal);

}