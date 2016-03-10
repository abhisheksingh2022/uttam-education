/**
 * 
 */
package com.hixapi.web.framework.common;

import java.util.Arrays;

import org.apache.struts2.util.TextProviderHelper;

import com.opensymphony.xwork2.ActionContext;

/**
 * The Class hixMessageFormatter. A utility class for formatting user messages.
 * 
 * @author admin
 */
public class MessageFormatter {

	/**
	 * Gets the text.
	 * 
	 * @param key
	 *            the key
	 * 
	 * @return the text
	 */
	public static String getText(String key) {
		String ret = TextProviderHelper.getText(key, "", ActionContext.getContext().getValueStack());
		if (ret == null || ret.length() == 0) {
			ret = key;
		}
		return ret;

	}

	/**
	 * Gets the text.
	 * 
	 * @param key
	 *            the key
	 * @param substitutes
	 *            the substitutes
	 * 
	 * @return the text
	 */
	public static String getText(String key, Object[] substitutes) {
		if (substitutes != null && substitutes.length > 0) {
			for (int i = 0; i < substitutes.length; i++) {
				if (substitutes[i] != null && substitutes[i] instanceof String) {
					String s = (String) substitutes[i];
					if (s.contains("'")) {
						substitutes[i] = s.replace("'", "&#39;");
					}
				}
			}
		}

		String ret = TextProviderHelper.getText(key, "", Arrays.asList(substitutes), ActionContext.getContext()
				.getValueStack());
		if (ret == null || ret.length() == 0) {
			ret = key;
		}
		return ret;
	}

	/**
	 * Gets the text.
	 * 
	 * @param key
	 *            the key
	 * @param substitute
	 *            the substitute
	 * 
	 * @return the text
	 */
	public static String getText(String key, String substitute) {
		return getText(key, new String[] { substitute });
	}

}
