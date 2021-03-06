/**
 * 
 */
package com.uttam.web.framework.common;

import java.util.Arrays;
import java.util.Locale;

import org.apache.struts2.util.TextProviderHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.uttam.framework.common.APIUtil;
import com.uttam.framework.common.context.ContextKeyEnum;
import com.uttam.framework.common.context.ContextProvider;

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
	
	public static String getLocalizedText(String key, Locale l, Object[] args) {
		Locale currentLocale = l;
		if (currentLocale == null) {
			currentLocale = Locale.getDefault();
		}
		String text = null;
		if (args != null) {
			text = LocalizedTextUtil.findText(APIUtil.class, key, currentLocale, key, args);
		} else {
			text = LocalizedTextUtil.findText(APIUtil.class, key, currentLocale);
		}
		return text;
	}
	
	public static String getLocalizedText(String key) {
		return getLocalizedText(key, null);
	}

	public static String getLocalizedText(String key, Object[] args) {
		return getLocalizedText(key, ContextProvider.getContextField(ContextKeyEnum.CURRENT_LOCALE, Locale.class), args);
	}

}
