package com.hixapi.web.framework.common;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hixapi.model.common.BaseException;
import com.hixapi.web.framework.context.ContextKeyEnum;
import com.hixapi.web.framework.context.ContextProvider;
import com.hixapi.web.framework.exception.DataParseException;

public class ConversionUtil {

	private static final Logger log = LogManager.getLogger(ConversionUtil.class);

	
	public static final List<Long> parseCommaSeparatedLongs(String value) {
		return parseDelimitedLongs(value, ",");

	}

	public static final List<String> parseCommaSeparatedStrings(String value) {
		return parseDelimitedStrings(value, ",");

	}

	public static final List<Long> parseDelimitedLongs(String value, String delimiter) {
		if (StringUtils.isBlank(value)) {
			return Collections.emptyList();
		}

		List<Long> numbers = new ArrayList<Long>();
		String[] parsed = value.split(",");

		for (String aNumber : parsed) {
			numbers.add(Long.parseLong(StringUtils.trimToEmpty(aNumber)));
		}

		return numbers;
	}

	public static final List<String> parseDelimitedStrings(String value, String delimiter) {
		if (StringUtils.isBlank(value)) {
			return Collections.emptyList();
		}

		List<String> values = new ArrayList<String>();
		String[] parsed = value.split(",");

		for (String aNumber : parsed) {
			values.add(StringUtils.trimToEmpty(aNumber));
		}

		return values;
	}

	public static final String buildDelimitedLongs(List<Long> numbers) {
		if (numbers == null || numbers.size() == 0) {
			return null;
		}

		StringBuilder str = new StringBuilder();
		int count = 1;
		for (Long aNumber : numbers) {
			str.append(aNumber.longValue());
			if ((count++) < numbers.size()) {
				str.append(",");
			}
		}
		return str.toString();
	}

	public static final String buildCommaDelimitedStrings(Set<String> numbers) {
		return buildDelimitedStrings(numbers, ",");
	}

	public static final String buildDelimitedStrings(Set<String> numbers, String delimiter) {
		if (numbers == null || numbers.size() == 0) {
			return null;
		}

		StringBuilder str = new StringBuilder();
		int count = 1;
		for (String aNumber : numbers) {
			str.append(aNumber);
			if ((count++) < numbers.size()) {
				str.append(delimiter);
			}
		}
		return str.toString();
	}

	public static final Date toDate(String str) throws BaseException {
		if (StringUtils.isNotBlank(str)) {
			try {
				return ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_MM_DD_YYYY, DateFormat.class).parse(str);
			} catch (ParseException e) {
				throw new DataParseException("Cannot parse as date : ", str, e);
			}
		}
		return null;
	}

	public static final Long toLong(String str) throws BaseException {
		if (StringUtils.isNotBlank(str)) {
			try {
				return Long.parseLong(str);
			} catch (NumberFormatException e) {
				throw new DataParseException("Cannot parse as long : ", str, e);
			}
		}
		return null;
	}
	
	public static final Double toDouble(String str) throws BaseException {
		if (StringUtils.isNotBlank(str)) {
			try {
				return Double.parseDouble(str);
			} catch (NumberFormatException e) {
				throw new DataParseException("Cannot parse as Double : ", str, e);
			}
		}
		return null;
	}

	public static String toString(Long nbr) {
		if (nbr != null) {
			return nbr.toString();
		}
		return "";
	}
	
	public static String toString(Integer nbr) {
		if (nbr != null) {
			return nbr.toString();
		}
		return "";
	}
	
	public static String toString(Double nbr) {
		if (nbr != null) {
			return nbr.toString();
		}
		return "";
	}

	public static String toString(Date date) {
		if (date != null) {
			return ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_MM_DD_YYYY, SimpleDateFormat.class).format(date);
		}
		return "";
	}

	public static void shallowCopy(Object sourceObj, Object destObj, String[] fields) {
		int srcIndex = 0;
		int destIndex = 1;

		for (String fieldEntry : fields) {
			String[] split = fieldEntry.split(" ");
			String srcProperty;
			String destProperty;
			if (split.length == 2) {
				srcProperty = split[srcIndex];
				destProperty = split[destIndex];
			} else {
				srcProperty = fieldEntry;
				destProperty = fieldEntry;
			}
			try {
				BeanUtils.setProperty(destObj, destProperty, BeanUtils.getProperty(sourceObj, srcProperty));
			} catch (IllegalAccessException e) {
				log.debug("Exception while processing property ::: " + srcProperty);
				log.debug("Exception ::: " + e);
			} catch (InvocationTargetException e) {
				log.debug("Exception while processing property ::: " + srcProperty);
				log.debug("Exception ::: " + e);
			} catch (NoSuchMethodException e) {
				log.debug("Exception while processing property ::: " + srcProperty);
				log.debug("Exception ::: " + e);
			}
		}
	}

	public static void shallowCopyMatchingFields(Object src, Object target) {
		try {
			BeanUtils.copyProperties(target, src);
		} catch (IllegalAccessException e) {
			log.debug("Exception while processing shallow copy ::: ", e);
		} catch (InvocationTargetException e) {
			log.debug("Exception while processing shallow copy ::: ", e);
			log.debug("Exception ::: " + e);
		}
	}

	

	public static String dollarStringToAmount(String str) {
		if (str.startsWith("$")) {
			return str.substring(1).trim();
		}
		return str;
	}

	public static String toString(BigDecimal amount) {
		if (amount == null) {
			return "";
		}
		return amount.toString();
	}

	public static String toYYYY_MM_DD(Date taxFilerDOB) {
		SimpleDateFormat formatter = ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_YYYY_MM_DD, SimpleDateFormat.class);
		return formatter.format(taxFilerDOB);
	}
}
