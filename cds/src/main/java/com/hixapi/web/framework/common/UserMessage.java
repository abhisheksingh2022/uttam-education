/*
 * Author - HIXAPI.
 * v1.0
 * 2009
 */
package com.hixapi.web.framework.common;

import java.io.Serializable;
import java.util.List;

/**
 * The Class UserMessage.
 */
public class UserMessage  implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -792041026293052514L;
    
    
    public UserMessage(int severity, String key, List<Object> substitutions, List<String> substitutionKeys){
    	this.severity = severity;
    	this.messageKey = key;
    	this.substitutions = substitutions;
    	this.substitutionKeys = substitutionKeys;
    }

    /** The severity. */
    private int severity;

    /** The message key. */
    private String messageKey;

    /** The substitutions. */
    private List<Object> substitutions;

    /** The substitution keys. */
    private List<String> substitutionKeys;

    /**
         * Gets the message key.
         * 
         * @return the messageKey
         * 
         * @author admin Feb 23, 2009
         */
    public String getMessageKey() {
	return messageKey;
    }

    /**
         * Sets the message key.
         * 
         * @param messageKey
         *                the messageKey to set
         * 
         * @author admin Feb 23, 2009
         */
    public void setMessageKey(String messageKey) {
	this.messageKey = messageKey;
    }

    /**
         * Gets the severity.
         * 
         * @return the severity
         * 
         * @author admin Feb 23, 2009
         */
    public int getSeverity() {
	return severity;
    }

    /**
         * Sets the severity.
         * 
         * @param severity
         *                the severity to set
         * 
         * @author admin Feb 23, 2009
         */
    public void setSeverity(int severity) {
	this.severity = severity;
    }

    /**
         * Sets the substitutions.
         * 
         * @param subs
         *                the new substitutions
         */
    public void setSubstitutions(List<Object> subs) {
	this.substitutions = subs;
    }

    /**
         * Gets the substitutions.
         * 
         * @return the substitutions
         */
    public List<Object> getSubstitutions() {
	return this.substitutions;
    }

    /**
         * Sets the substitution keys.
         * 
         * @param subKeys
         *                the new substitution keys
         */
    public void setSubstitutionKeys(List<String> subKeys) {
	this.substitutionKeys = subKeys;
    }

    /**
         * Gets the substitution keys.
         * 
         * @return the substitution keys
         */
    public List<String> getSubstitutionKeys() {
	return this.substitutionKeys;
    }

}
