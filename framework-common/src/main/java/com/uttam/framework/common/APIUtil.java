package com.uttam.framework.common;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uttam.framework.common.context.ContextKeyEnum;
import com.uttam.framework.common.context.ContextProvider;
import com.uttam.framework.common.env.IEnvironmentProvider;
import com.uttam.framework.service.ServiceLocator;


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

	public static String getCurrentTimestampString() {
		Date d = getCurrentDate();
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(d);
		StringBuilder stamp = new StringBuilder();
		stamp.append(cal.get(Calendar.YEAR)).append("-")
				.append(StringUtils.leftPad(String.valueOf(cal.get(Calendar.MONTH) + 1), 2, '0')).append("-")
				.append(StringUtils.leftPad(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), 2, '0')).append("_")
				.append(StringUtils.leftPad(String.valueOf(cal.get(Calendar.HOUR)), 2, '0')).append("-")
				.append(StringUtils.leftPad(String.valueOf(cal.get(Calendar.MINUTE)), 2, '0')).append("-")
				.append(StringUtils.leftPad(String.valueOf(cal.get(Calendar.SECOND)), 2, '0'))
				.append(cal.get(Calendar.MILLISECOND));
		return stamp.toString();
	}

	public static String getCurrentDateSource() {
		String source = "";
		if (ServiceLocator.getInstance().getService(IEnvironmentProvider.class).isProduction()) {
			source = "Server";
		} else {
			final Date contextDate = ContextProvider.getContextField(ContextKeyEnum.CURRENT_DATETIME, Date.class);
			if (contextDate != null) {
				source = "Session";
			} else {

				source = "Server";
			}

		}
		return source;
	}

	public static int getCurrentYear() {
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
		if (clazz.equals(Short.class)) {
			return (T) (Short) Short.parseShort(value);
		}
		try {
			if (clazz.equals(Date.class)) {
				return (T) ContextProvider.getContextField(ContextKeyEnum.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS,
						SimpleDateFormat.class).parse(value);
			}
			if (clazz.equals(Timestamp.class)) {
				return (T) new Timestamp(ContextProvider
						.getContextField(ContextKeyEnum.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, SimpleDateFormat.class)
						.parse(value).getTime());
			}
		} catch (ParseException pex) {
			throw log.throwing(new RuntimeException("Error parsing " + value, pex));
		}

		return null;
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

	public static Date getFirstDayOfYear(int coverageYear) {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, coverageYear);
		return cal.getTime();
	}

	public static boolean yearSameAsCurrentYear(Integer year) {
		boolean retVal = false;
		Date d = getCurrentDate();
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(d);
		if (cal.get(Calendar.YEAR) == year.intValue()) {
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

	/*public static String printValidationEvents(ValidationEventHandlerImpl handler) {

		StringBuilder eventStr = new StringBuilder();
		if (CollectionUtils.isNotEmpty(handler.getEvents())) {

			for (ValidationEvent event : handler.getEvents()) {
				eventStr.append("\nValidation Errors:").append(" Severity ").append(event.getSeverity()).append(": ")
						.append(event.getMessage());
				eventStr.append("\n\tDetails: \n");
				eventStr.append("Line Number: ").append(event.getLocator().getLineNumber()).append(", Object: ")
						.append(event.getLocator().getObject());
				eventStr.append("\n");
				Throwable ex = event.getLinkedException();
				if (ex != null) {
					StackTraceElement[] trace = ex.getStackTrace();

					if (trace != null) {

						for (int i = 0; i < trace.length; i++) {
							eventStr.append(trace[i]).append("\n\t");
						}
					}
				}
			}
			log.debug(eventStr.toString());
		}
		return eventStr.toString();

	}*/

	public static String truncateString(String val, int limit) {
		if (val == null) {
			return "";
		}
		if (val.length() <= limit) {
			return val;
		}
		int end = limit > val.length() ? val.length() : limit;
		return val.substring(0, end);
	}

	public static void mkdirs(String folderPath) {
		log.debug("Creating location if not existing: {}", folderPath);
		File f = new File(folderPath);
		if (!f.exists()) {
			boolean result = f.mkdirs();
			log.debug("Folder creation status: {}", result);
		} else {
			log.debug("Folder exists");
		}

	}

	public static boolean backupFile(File saveFile) throws IOException {
		boolean copied = false;
		if (saveFile.exists() && saveFile.isFile()) {
			String dirPath = saveFile.getParent();
			log.debug("Saving backup in {} for {}", dirPath, saveFile.getName());
			String backupPrefix = getBackupPrefix();
			String newFileName = dirPath + "/" + saveFile.getName() + backupPrefix;
			File destFile = new File(newFileName);
			log.debug("Saving backup file as {}", newFileName);
			FileUtils.copyFile(saveFile, destFile, true);
			copied = true;
		}
		return copied;

	}

	private static String getBackupPrefix() {
		Date d = getCurrentDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		StringBuilder str = new StringBuilder();
		str.append(cal.get(Calendar.YEAR)).append("-")
				.append(StringUtils.leftPad(String.valueOf(cal.get(Calendar.MONTH) + 1), 2, '0')).append("-")
				.append(StringUtils.leftPad(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), 2, '0')).append("_")
				.append(StringUtils.leftPad(String.valueOf(cal.get(Calendar.HOUR)), 2, '0')).append("-")
				.append(StringUtils.leftPad(String.valueOf(cal.get(Calendar.MINUTE)), 2, '0')).append("-")

				.append(cal.get(Calendar.MILLISECOND));
		return str.toString();
	}

	public static String getTwoLetterLanguageCode(String localeCode) {
		if (StringUtils.startsWithIgnoreCase(localeCode, "en")) {
			return "EN";
		}
		if (StringUtils.startsWithIgnoreCase(localeCode, "es")) {
			return "ES";
		}
		return "";
	}

	public static String getCurrentCoverageYear() {
		Date currentDate = getCurrentDate();
		String oeStartStr = ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
				.getProperty(APIConstants.PROP_KEY_OESTART_DATE, String.class, "");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//yyyy-MM-dd'T'HH:mm:ss.SSSXXX
		Date oeStartDate = null;
		try {
			oeStartDate = sdf.parse(oeStartStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(currentDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		if (currentDate.after(oeStartDate) || currentDate.equals(oeStartDate)) {
			year = year + 1;
		}
		return String.valueOf(year);

	}
}
