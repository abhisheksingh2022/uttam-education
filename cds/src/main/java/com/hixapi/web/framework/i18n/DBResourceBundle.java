package com.hixapi.web.framework.i18n;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hixapi.web.framework.common.APIConstants;
import com.hixapi.web.framework.context.ContextKeyEnum;
import com.hixapi.web.framework.context.ContextProvider;
import com.hixapi.web.framework.service.ServiceLocator;



public abstract class DBResourceBundle extends ResourceBundle {

	private static final Logger log = LogManager.getLogger(DBResourceBundle.class);

	private Map<String, Map<String, String>> spMap = new HashMap<String, Map<String, String>>();
	private Set<String> entries = new TreeSet<String>();
	private String locale;

	public DBResourceBundle() {
		initialize();
	}

	private void initialize() {
		log.debug("Initializing database resource bundle");

		String[] split = getClass().getName().split("_");
		String language = split.length > 1 ? split[1] : null;
		if(StringUtils.isEmpty(language)){
			language = "en";
		}
		String country = split.length > 2 ? split[2] : null;
		if(StringUtils.isEmpty(country)){
			country = "US";
		}
		String variant = split.length > 3 ? split[3] : null;
		StringBuilder localeBuilder = new StringBuilder(language).append("_").append(country);
		if(StringUtils.isNotBlank(variant)){
			localeBuilder.append(variant);
		}
		locale = localeBuilder.toString();
		String spId = null;
		Map<String, String> spEntries = null;
		List<ResourceBundleEntry> list = ServiceLocator.getInstance().getService(IResourceBundleDao.class)
				.retrieveDBResourceBundle(locale);
		for (ResourceBundleEntry entry : list) {
			spId = entry.getServiceProviderId();
			spEntries = spMap.get(spId);
			if (spEntries == null) {
				spEntries = new HashMap<String, String>();
				spMap.put(spId, spEntries);
			}
			spEntries.put(entry.getKey(), entry.getValue());
			entries.add(spId + ":" + entry.getKey());
		}

	}

	@Override
	protected Object handleGetObject(String key) {
		String serviceProvider = ContextProvider.getContextField(ContextKeyEnum.SERVICE_PROVIDER_ID, String.class);
		if (StringUtils.isEmpty(serviceProvider)) {
			serviceProvider = APIConstants.PROP_GLOBAL;
		}
		Map<String, String> spEntries = spMap.get(serviceProvider);
		String val = null;
		if (spEntries != null) {
			 val  = spEntries.get(key);
		}
		if(val == null){
			return "Not found: " + key + "(" + locale + ") for " + serviceProvider;
		}
		return val;
	}

	@Override
	public Enumeration<String> getKeys() {
		return Collections.enumeration(entries);
	}

}
