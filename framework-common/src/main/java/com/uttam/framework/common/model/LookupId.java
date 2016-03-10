/*
 * Author - HIXAPI.
 * v1.0
 * 2009
 */
package com.uttam.framework.common.model;

import java.io.Serializable;

/**
 * The Class LookupId.
 */

public class LookupId implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5313588217754743299L;
	
	/** The value. */
	private String value;
	
	/** The lang code. */
	private String langCode;
	
	/** The lookup key. */
	private String lookupKey;
	
	/**
         * Gets the lang code.
         * 
         * @return the langCode
         * 
         * @author admin Feb 20, 2009
         */
	public String getLangCode() {
		return langCode;
	}
	
	/**
         * Sets the lang code.
         * 
         * @param langCode
         *                the langCode to set
         * 
         * @author admin Feb 20, 2009
         */
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
	/**
         * Gets the lookup key.
         * 
         * @return the lookupKey
         * 
         * @author admin Feb 20, 2009
         */
	public String getLookupKey() {
		return lookupKey;
	}
	
	/**
         * Sets the lookup key.
         * 
         * @param lookupKey
         *                the lookupKey to set
         * 
         * @author admin Feb 20, 2009
         */
	public void setLookupKey(String lookupKey) {
		this.lookupKey = lookupKey;
	}
	
	/**
         * Gets the value.
         * 
         * @return the value
         * 
         * @author admin Feb 20, 2009
         */
	public String getValue() {
		return value;
	}
	
	/**
         * Sets the value.
         * 
         * @param value
         *                the value to set
         * 
         * @author admin Feb 20, 2009
         */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
         * Hash code.
         * 
         * @return the int
         * 
         * @author admin Feb 20, 2009
         */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((langCode == null) ? 0 : langCode.hashCode());
		result = PRIME * result + ((lookupKey == null) ? 0 : lookupKey.hashCode());
		result = PRIME * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	/**
         * Equals.
         * 
         * @param obj
         *                the obj
         * 
         * @return true, if equals
         * 
         * @author admin Feb 20, 2009
         */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final LookupId other = (LookupId) obj;
		if (langCode == null) {
			if (other.langCode != null)
				return false;
		} else if (!langCode.equals(other.langCode))
			return false;
		if (lookupKey == null) {
			if (other.lookupKey != null)
				return false;
		} else if (!lookupKey.equals(other.lookupKey))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	

}
