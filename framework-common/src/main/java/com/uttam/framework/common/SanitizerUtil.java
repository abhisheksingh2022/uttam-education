/*
 * Author - HIXAPI.
 * v1.0
 * 2009
 */
package com.uttam.framework.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class SanitizerUtil is used to sanitize string values for malicious code.
 * 

 * @author admin Oct 7, 2009
 * 
 */
public class SanitizerUtil {

	/**
	 * Trim all.
	 * 
	 * @param params
	 *            the params
	 * 
	 * @return the map< string, object>
	 */
	public static Map<String, Object> trimAll(Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (params != null) {
			for (Entry<String, Object> entry : params.entrySet()) {
				Object value = entry.getValue();
				Object newValue = value;
				if (value instanceof String) {
					newValue = sanitizeTags(StringUtils.trimToEmpty((String) value));
				} else if (value instanceof String[]) {
					newValue = trimArray((String[]) value);
				}
				resultMap.put(entry.getKey(), newValue);
			}
		}

		return resultMap;
	}

	private static String sanitizeTags(String string) {
		return string.replaceAll("(?i)<script.*?>.*?</script.*?>", "") // case 1
				.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "") // case 2
				.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", ""); // case 3
	}

	/**
	 * Trim array map.
	 * 
	 * @param params
	 *            the params
	 * 
	 * @return the map< string, string[]>
	 */
	public static Map<String, String[]> trimArrayMap(Map<String, String[]> params) {
		Map<String, String[]> resultMap = new HashMap<String, String[]>();

		if (params != null) {
			for (Entry<String, String[]> entry : params.entrySet()) {
				String[] value = entry.getValue();
				String[] newValue = trimArray(value);
				resultMap.put(entry.getKey(), newValue);
			}
		}

		return resultMap;
	}

	// public static String trimEscapeScript(String value) {
	// return StringEscapeUtils.escapeJavaScript(StringUtils
	// .trimToEmpty(((String) value)));
	// }

	/**
	 * Trim array.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return the string[]
	 */
	public static String[] trimArray(String[] value) {
		String[] result = null;
		if (value != null) {

			int length = value.length;
			result = new String[length];
			for (int count = 0; count < length; count++) {
				result[count] = sanitizeTags(StringUtils.trimToEmpty(value[count]));
			}
		}
		return result;
	}
}
