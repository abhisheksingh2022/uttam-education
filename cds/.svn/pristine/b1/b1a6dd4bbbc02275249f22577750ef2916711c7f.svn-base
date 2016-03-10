package com.hixapi.web.framework.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hixapi.web.framework.context.ContextKeyEnum;
import com.hixapi.web.framework.context.ContextProvider;
import com.hixapi.web.framework.env.IEnvironmentProvider;
import com.hixapi.web.framework.service.ServiceLocator;
import com.hixapi.web.framework.struts.BaseAction;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class APIUtil {

	private static final Logger log = LogManager.getLogger(APIUtil.class);

	

	public static Date getCurrentDate() {
		Date returnDate = null;
		if (ServiceLocator.getInstance().getService(IEnvironmentProvider.class).isProduction()) {
			returnDate = new Date();
		} else {
			final Date contextDate = ContextProvider.getContextField(ContextKeyEnum.CURRENT_DATETIME, Date.class);
			if (contextDate != null) {
				log.debug("Date returned from context provider, {}", contextDate);
				returnDate = contextDate;
			} else {
				returnDate = new Date();
			}

		}
		return returnDate;
	}

	public static int getCurrentYear(){
		Date d = getCurrentDate();
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(d);
		return cal.get(Calendar.YEAR);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T convert(String value, Class<T> clazz) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		if (clazz.equals(String.class)) {
			return (T) value;
		}

		if (clazz.equals(Boolean.class)) {
			return (T) Boolean.valueOf(value);
		}

		if (clazz.equals(Integer.class)) {
			return (T) (Integer) Integer.parseInt(value);
		}
		if (clazz.equals(Long.class)) {
			return (T) (Long) Long.parseLong(value);
		}
		if (clazz.equals(Double.class)) {
			return (T) (Double) Double.parseDouble(value);
		}
		try {
			if (clazz.equals(Date.class)) {
				return (T) ContextProvider
						.getContextField(ContextKeyEnum.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, SimpleDateFormat.class).parse(value);
			}
			if (clazz.equals(Timestamp.class)) {
				return (T) new Timestamp(ContextProvider
						.getContextField(ContextKeyEnum.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, SimpleDateFormat.class).parse(value)
						.getTime());
			}
		} catch (ParseException pex) {
			log.throwing(new RuntimeException("Error parsing " + value, pex));
		}

		return null;
	}


	public static String getLocalizedText(String key) {
		return getLocalizedText(key, null);
	}

	public static String getLocalizedText(String key, Object[] args) {
		return getLocalizedText(key, ContextProvider.getContextField(ContextKeyEnum.CURRENT_LOCALE, Locale.class), args);
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

	public static Locale getLocale(String code) {
		if (StringUtils.isBlank(code)) {
			return new Locale("en", "US");
		} else {
			String[] split = code.split("_");
			String language = split[0];
			String country = "";
			String variant = "";
			if (split.length == 2) {
				country = split[1];
			}
			if (split.length == 3) {
				country = split[1];
				variant = split[2];
			}
			return new Locale(language, country, variant);
		}

	}

	public static String getCurrentCoverageYear() {
		Date d = getCurrentDate();
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(d);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		if (month > 9 && day > 14) {
			year = year + 1;
		}
		return String.valueOf(year);

	}

	public static Date getFirstDayOfYear(int coverageYear) {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, 0 );
		cal.set(Calendar.YEAR, coverageYear);
		return cal.getTime();
	}

	public static boolean yearSameAsCurrentYear(Integer year) {
		boolean retVal = false;
		Date d = getCurrentDate();
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(d);
		if(cal.get(Calendar.YEAR) == year.intValue()){
			retVal = true;
		}
		return retVal;
	}

	public static Date getStartOfDay(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static String getTwoLetterLanguageCode(String localeCode) {
		if(StringUtils.startsWithIgnoreCase(localeCode, "en")){
			return "EN";
		}
		if(StringUtils.startsWithIgnoreCase(localeCode, "es")){
			return "ES";
		}
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public static String traceRequest(Object strutsAction) {
		StringBuilder strBuilder = new StringBuilder("Request Trace:\r\n");
		if (strutsAction != null && strutsAction instanceof BaseAction) {

			BaseAction action = (BaseAction) strutsAction;
			Map<String, String[]> params = action.getParameters();
			if (params != null) {

				params = new TreeMap<String, String[]>(params);
				strBuilder.append("****Request Parameters**** \r\n");
				for (String key : params.keySet()) {
					strBuilder.append("Param Name: ").append(key).append("\r\n");
					strBuilder.append("Param Values :{");
					for (String value : params.get(key)) {
						strBuilder.append("[").append(value).append("]");
					}
					strBuilder.append("}\r\n\r\n");
				}
				strBuilder.append("---------------------------\r\n");
			}
		}

		if (ServletActionContext.getRequest() != null && ServletActionContext.getRequest().getCookies() != null) {
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			if (cookies != null) {
				strBuilder.append("****Request Cookies**** \r\n");
				for (Cookie cookie : cookies) {
					strBuilder.append("Cookie Name: ").append(cookie.getName()).append("\r\n{\r\n");
					strBuilder.append("Cookie Value :").append(cookie.getValue()).append("\r\n}");
					strBuilder.append("\r\n\r\n");
				}
				strBuilder.append("---------------------------\r\n");
			}
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		if (request != null) {
			strBuilder.append("****Request Attributes**** \r\n");
			final Enumeration<String> attributeNames = request.getAttributeNames();
			while (attributeNames.hasMoreElements()) {
				String key = attributeNames.nextElement();
				strBuilder.append("Request Attribute Name: ").append(key).append("\r\n");
				strBuilder.append("\tAttribute Values :{");
				strBuilder.append("[").append(request.getAttribute(key)).append("]");
				strBuilder.append("}\r\n\r\n");
			}
			strBuilder.append("---------------------------\r\n");
			
			
			strBuilder.append("****Request Headers**** \r\n");
			final Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = headerNames.nextElement();
				strBuilder.append("Request Header Name: ").append(key).append("\r\n");
				strBuilder.append("\tHeader Values :{");
				strBuilder.append("[").append(request.getHeader(key)).append("]");
				strBuilder.append("}\r\n\r\n");
			}
			strBuilder.append("---------------------------\r\n");
		}

		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		if (httpSession != null) {
			Enumeration<String> attrMap = httpSession.getAttributeNames();
			strBuilder.append("****Session Attributes**** \r\n");
			for (String key : Collections.list(attrMap)) {
				strBuilder.append("Session Attribute Name: ").append(key).append("\r\n");
				strBuilder.append("\tAttribute Values :");
				strBuilder.append("[").append(getString(httpSession.getAttribute(key))).append("]");
				strBuilder.append("\r\n\r\n");
			}
			strBuilder.append("---------------------------\r\n");
		}

		return strBuilder.toString();
	}
	
	@SuppressWarnings("rawtypes")
	private static String getString(Object object) {
		StringBuilder str = new StringBuilder();
		if (object != null) {
			if (object instanceof String) {
				str.append(object);
			} else if (object instanceof Collection) {
				for (Object aVal : (Collection) object) {
					str.append(aVal).append("\r\n\t");
				}
			} else if (object instanceof Object[]) {
				for (Object aVal : (Object[]) object) {
					str.append(aVal).append("\r\n\t");
				}
			} else if (object instanceof Map) {
				for (Object aKey : ((Map) object).keySet()) {
					str.append(aKey).append(":").append(((Map) object).get(aKey)).append("\r\n\t");
				}
			} else {
				str.append(object.toString());
			}
		}
		return str.toString();
	}
}
