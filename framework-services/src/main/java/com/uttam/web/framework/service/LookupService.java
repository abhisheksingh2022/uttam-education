package com.uttam.web.framework.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.uttam.framework.common.model.LookupBean;
import com.uttam.framework.dao.LookupManager;

public class LookupService {

	public static void initialize() {
		LookupManager.loadLookups();
	}

	public static Map<String, List<LookupBean>> getLocaleSpecificLookups(Locale locale) {
		// TODO Auto-generated method stub
		return LookupManager.getLocaleSpecificLookups(locale);
	}

	public static String getLookupDisplayValue(Locale locale, String lookupName, String key) {
		return LookupManager.getLookupDisplayValue(locale, lookupName, key);
	}
}
