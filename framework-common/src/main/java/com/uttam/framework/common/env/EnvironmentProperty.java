package com.uttam.framework.common.env;

// default package
// Generated Mar 3, 2009 3:14:37 PM by Hibernate Tools 3.2.0.b9

import java.util.Date;

/**
 * The Class EnvironmentProperty.
 */

public class EnvironmentProperty implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5256366163039665779L;

	private String providerId;

	private String propGroup;

	/** The code. */
	private String code;

	/** The value. */
	private String value;

	/** The description. */
	private String description;

	/** The create date. */
	private Date createDate;

	/** The modify date. */
	private Date modifyDate;
	
	
	

	/**
	 * Instantiates a new environment property.
	 */
	public EnvironmentProperty() {
	}

	/**
	 * Instantiates a new environment property.
	 * 
	 * @param propertyCd
	 *            the property cd
	 * @param propertyValue
	 *            the property value
	 * @param propertyDesc
	 *            the property desc
	 * @param createDate
	 *            the create date
	 */
	public EnvironmentProperty(String propertyCd, String propertyValue, String providerId, String propGroup) {
		this.code = propertyCd;
		this.value = propertyValue;
		this.providerId = providerId;
		this.propGroup = propGroup;
	}

	
	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param propertyCd
	 *            the new code
	 */
	public void setCode(String propertyCd) {
		this.code = propertyCd;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param propertyValue
	 *            the new value
	 */
	public void setValue(String propertyValue) {
		this.value = propertyValue;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param propertyDesc
	 *            the new description
	 */
	public void setDescription(String propertyDesc) {
		this.description = propertyDesc;
	}

	/**
	 * Gets the creates the date.
	 * 
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * Sets the creates the date.
	 * 
	 * @param createDate
	 *            the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the modify date.
	 * 
	 * @return the modify date
	 */
	public Date getModifyDate() {
		return this.modifyDate;
	}

	/**
	 * Sets the modify date.
	 * 
	 * @param modifyDate
	 *            the new modify date
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * Hash code.
	 * 
	 * @return the int
	 * 
	 * @author admin Mar 3, 2009
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/**
	 * Equals.
	 * 
	 * @param obj
	 *            the obj
	 * 
	 * @return true, if equals
	 * 
	 * @author admin Mar 3, 2009
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EnvironmentProperty))
			return false;
		final EnvironmentProperty other = (EnvironmentProperty) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getPropGroup() {
		return propGroup;
	}

	public void setPropGroup(String propGroup) {
		this.propGroup = propGroup;
	}

}
