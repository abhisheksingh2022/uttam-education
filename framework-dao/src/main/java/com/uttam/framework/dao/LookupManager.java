/*
 * Author - HIXAPI.
 * v1.0
 * 2009
 */
package com.uttam.framework.dao;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.uttam.framework.common.model.LookupBean;
import com.uttam.framework.service.ServiceLocator;

/**
 * The Class LookupManager is used to access lookup (key value pair properties in the applications It uses { @link
 * LookupManager.hbm.xml } in the classpath to load the lookups present in lookups.properties. Each of the entry in
 * lookups.properties should have a corresponding named query in LookupManager.hbm.xml
 * 
 */
public class LookupManager {/*

	*//** The lookups. *//*
	private static Map<String, List<LookupBean>> lookups;

	*//** The lookup ids. *//*
	private static List<String> lookupIds;

	*//** The locale maps. *//*
	private static Map<String, Map<String, List<LookupBean>>> localeMaps;

	*//** The Constant LOG. *//*
	private static final Logger LOG = LogManager.getLogger(LookupManager.class);
	

	*//**
	 * This is load method for the lookup manager.. This is a costly invocation. Should be used only when there is a change
	 * in data in DB and the change is to be reflected
	 * 
	 * @author admin May 9, 2009
	 *//*
	public static void loadLookups() {
		Session session = null;
		try {

			loadLookupNames();

			// --------------------------
			lookups = new HashMap<String, List<LookupBean>>();

			SessionFactory sessionFactory = ServiceLocator.getInstance().getDefaultHibernateSessionFactory();
			session = sessionFactory.openSession();

			for (String id : lookupIds) {
				Query q = session.getNamedQuery(id);
				@SuppressWarnings("unchecked")
				List<LookupBean> lookupList = q.list();

				if (CollectionUtils.isEmpty(lookupList)) {
					LOG.error("Lookup list of id '" + id + "' is empty. Possible data inconsitency. Query is '"
							+ q.getQueryString() + "'.");
				}

				String lookupKey = id.substring(id.lastIndexOf(".") + 1);
				lookups.put(lookupKey, lookupList);
			}
			localeMaps = getLocaleSortedLookups();

		} catch (Exception e) {
			LOG.error("Error loading lookup ", e);
			throw new RuntimeException("Error in Lookup Manager", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	*//**
	 * Load lookup names.
	 *//*
	private static void loadLookupNames() {
		lookupIds = new ArrayList<String>();
		BufferedInputStream str = null;
		try {
			str = new BufferedInputStream(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/hibernate/lookups.properties"));

			Properties p = new Properties();
			p.load(str);
			for (Entry<Object, Object> e : p.entrySet()) {
				lookupIds.add(((String) e.getValue()).trim());
			}

		} catch (Exception e) {
			LOG.error("Error Loading lookup names", e);
		} finally {
			if (str != null) {
				try {
					str.close();
				} catch (Exception e) {
					LOG.error("Error closing input stream", e);
				}
			}
		}
	}

	*//**
	 * Gets the lookups.
	 * 
	 * @return the lookups
	 *//*
	public static Map<String, List<LookupBean>> getLookups() {
		return new HashMap<String, List<LookupBean>> (lookups);
	}

	*//**
	 * Gets the lookup.
	 * 
	 * @param lookupId
	 *            the lookup id
	 * 
	 * @return the lookup
	 *//*
	public static List<LookupBean> getLookup(String lookupId) {
		final List<LookupBean> list = lookups.get(lookupId);
		if(list != null){
			return new ArrayList<LookupBean>(list);
		}
		return Collections.emptyList();
	}

	*//**
	 * Gets the locale sorted lookups.
	 * 
	 * @return the locale sorted lookups
	 *//*
	private static Map<String, Map<String, List<LookupBean>>> getLocaleSortedLookups() {
		if (localeMaps != null) {
			return new HashMap<String, Map<String, List<LookupBean>>>(localeMaps);
		}
		localeMaps = new HashMap<String, Map<String, List<LookupBean>>>();
		if (lookups != null && lookups.size() > 0) {
			List<String> langList = new ArrayList<String>();
			for (String key : lookups.keySet()) {
				List<LookupBean> values = lookups.get(key);
				for (LookupBean obj : values) {
					// obj.getId().setValue(obj.getId().getValue().trim());
					String langCode = obj.getId().getLangCode();
					if (langCode != null && langCode.trim().length() > 0) {
						if (!langList.contains(langCode)) {
							langList.add(langCode);
							localeMaps.put(langCode, new HashMap<String, List<LookupBean>>());
						}
						Map<String, List<LookupBean>> localeLookups = localeMaps.get(langCode);
						List<LookupBean> lookups = localeLookups.get(key);
						if (lookups == null) {
							lookups = new ArrayList<LookupBean>();
							localeLookups.put(key, lookups);
						}
						lookups.add(obj);
					}
				}
			}
			langList.clear();

		}
		return localeMaps;
	}

	*//**
	 * Gets the locale specific lookups.
	 * 
	 * @param locale
	 *            the locale
	 * 
	 * @return the locale specific lookups
	 *//*
	public static Map<String, List<LookupBean>> getLocaleSpecificLookups(Locale locale) {
		return localeMaps.get(locale.toString());
	}

	*//**
	 * Gets the locale specific lookups.
	 * 
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * 
	 * @return the locale specific lookups
	 *//*
	public static List<LookupBean> getLocaleSpecificLookups(Locale locale, String key) {
		List<LookupBean> retList = null;
		Map<String, List<LookupBean>> localeMap = getLocaleSpecificLookups(locale);
		if (localeMap != null) {
			final List<LookupBean> list = localeMap.get(key);
			if(list != null){
				retList = new ArrayList<LookupBean>(list);
			}
			else{
				retList = Collections.emptyList();
			}
		}
		return retList;

	}

	*//**
	 * Gets the lookup display value.
	 * 
	 * @param locale
	 *            the locale
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * 
	 * @return the lookup display value
	 *//*
	public static String getLookupDisplayValue(Locale locale, String key, String value) {
		List<LookupBean> beans = getLocaleSpecificLookups(locale, key);
		if (beans != null) {
			for (LookupBean bean : beans) {
				if (bean.getId().getLangCode().equals(locale.toString())
						&& bean.getId().getValue().equals(value)) {
					return bean.getLabel();
				}
			}
		}
		return "";
	}
	
	
	*//**
	 * Gets the lookup code
	 * 
	 * @param locale
	 *            the locale
	 * @param lookupName
	 *            the name of lookup
	 * @param label
	 *            the label in the provided locale
	 * 
	 * @return the lookup display value - checking for case insensitive comparison of the label
	 *//*
	public static String getLookupKey(Locale locale, String lookupName, String label) {
		List<LookupBean> beans = getLocaleSpecificLookups(locale, lookupName);
		if (beans != null) {
			for (LookupBean bean : beans) {
				if (bean.getId().getLangCode().equals(locale.toString())
						&& StringUtils.trimToEmpty(bean.getLabel()).equalsIgnoreCase(StringUtils.trimToEmpty(label))) { //Ignoring case in this case
					return bean.getId().getValue();
				}
			}
		}
		return null;
	}

*/}
