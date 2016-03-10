/*
 * Author - HIXAPI.
 * v1.0
 * 2009
 */
package com.hixapi.web.framework.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.util.TextProviderHelper;

import com.opensymphony.xwork2.ActionContext;

/**
 * The Class ValidationMessageFormatter.
 */
public class ValidationMessageFormatter {

    /** The IMAG e_ error. */
    private static String IMAGE_ERROR = "icon_error.gif";

    /** The IMAG e_ warning. */
    private static String IMAGE_WARNING = "icon_warning.gif";

    /** The IMAG e_ message. */
    private static String IMAGE_MESSAGE = "icon_message.gif";

    /** The IMAGEAL t_ error. */
    private static String IMAGEALT_ERROR = "144";

    /** The IMAGEAL t_ warning. */
    private static String IMAGEALT_WARNING = "145";

    /** The IMAGEAL t_ message. */
    private static String IMAGEALT_MESSAGE = "146";

    /**
         * Gets the image.
         * 
         * @param severity
         *                the severity
         * 
         * @return the image
         */
    public static String getImage(int severity) {
	if (severity == 1) {
	    return IMAGE_ERROR;
	}
	if (severity == 2) {
	    return IMAGE_WARNING;
	}
	if (severity == 3) {
	    return IMAGE_MESSAGE;
	}
	return "unknown";
    }

    /**
         * Gets the image alt.
         * 
         * @param severity
         *                the severity
         * 
         * @return the image alt
         */
    public static String getImageAlt(int severity) {
	if (severity == 1) {
	    return IMAGEALT_ERROR;
	}
	if (severity == 2) {
	    return IMAGEALT_WARNING;
	}
	if (severity == 3) {
	    return IMAGEALT_MESSAGE;
	}
	return "unknown";
    }

    /**
         * Gets the text for the message based on severity and substitutions.
         * 
         * @param msg
         *                the msg
         * 
         * @return the text
         */
    public static String getText(UserMessage msg) {
    	if(msg!=null&&msg.getSubstitutions()!=null&&msg.getSubstitutions().size()>0){
    		for(int i=0;i<msg.getSubstitutions().size();i++){
    			if(msg.getSubstitutions().get(i)!=null && msg.getSubstitutions().get(i) instanceof String){
    				String s =(String)msg.getSubstitutions().get(i);
    				if(s.contains("'")){
    					msg.getSubstitutions().set(i,s.replace("'", "&#39;"));
    				}
    			}
    		}
    	}
    	
	if (msg.getSubstitutions() != null && msg.getSubstitutions().size() > 0) {
	    return TextProviderHelper
		.getText(msg.getMessageKey(), msg.getMessageKey(), msg.getSubstitutions(),
		    ActionContext.getContext().getValueStack());
	} else if (msg.getSubstitutionKeys() != null
	    && msg.getSubstitutionKeys().size() > 0) {
	    List<Object> subs = new ArrayList<Object>(msg.getSubstitutionKeys()
		.size());
	    for (String aSub : msg.getSubstitutionKeys()) {
		subs.add(TextProviderHelper.getText(aSub, msg.getMessageKey(), ActionContext
		    .getContext().getValueStack()));
	    }
	    return TextProviderHelper.getText(msg.getMessageKey(), msg.getMessageKey(), subs,
		ActionContext.getContext().getValueStack());
	} else {
	    return TextProviderHelper.getText(msg.getMessageKey(), msg.getMessageKey(),
		ActionContext.getContext().getValueStack());
	}

    }

}
