package com.hixapi.web.framework.env;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.hixapi.web.framework.common.APIConstants;
import com.hixapi.web.framework.common.APIUtil;


public abstract class BaseEnvironmentProvider implements IEnvironmentProvider {
	/** The Constant LOG. */
	private static final Logger _log = org.apache.logging.log4j.LogManager.getLogger(BaseEnvironmentProvider.class);

	/** The properties. */
	protected Map<String, Map<String, EnvironmentProperty>> properties;

	/**
	 * Load properties, using File system
	 */
	protected abstract void loadProperties();

	public synchronized void reload() {
		_log.entry();
		_log.info("Loading Environment properties");
		if (properties == null) {
			properties = new LinkedHashMap<String, Map<String, EnvironmentProperty>>();
		}
		loadProperties();
		if (properties == null) {
			throw _log.throwing(new NullPointerException("Properties not initialized by current environment provider"));
		}
		String env = System.getProperty(APIConstants.PROPKEY_ENVIRONMENT);
		if (env == null) {
			throw _log.throwing(new NullPointerException("Java System Property 'global.environment' not found"));
		}
		Map<String, EnvironmentProperty> globalProps = properties.get(APIConstants.PROP_GLOBAL);
		if (globalProps == null) {
			globalProps = new LinkedHashMap<String, EnvironmentProperty>();
			properties.put(APIConstants.PROP_GLOBAL, globalProps);
		}
		globalProps.put(APIConstants.PROPKEY_ENVIRONMENT,
				new EnvironmentProperty(APIConstants.PROPKEY_ENVIRONMENT, env, APIConstants.PROP_GLOBAL));

		_log.info("Environment properties loaded");

		if (properties != null && _log.isDebugEnabled()) {
			StringBuilder str = new StringBuilder("\n** Environment Properties **\n");
			for (Entry<String, Map<String, EnvironmentProperty>> entry : properties.entrySet()) {
				str.append("Provider : ").append(entry.getKey()).append('\n');
				for (Entry<String, EnvironmentProperty> scopedEntry : entry.getValue().entrySet()) {
					str.append('\t').append(scopedEntry.getKey()).append(" = ").append(scopedEntry.getValue().getValue())
							.append('\n');
				}

			}
			_log.debug(str.toString());
		}
		_log.exit();
	}

	@Override
	public boolean isProduction() {
		_log.entry();
		if (properties == null) {
			_log.error("Application environment properties not initialized");
		}
		return StringUtils.trimToEmpty(getProperty(APIConstants.PROPKEY_ENVIRONMENT, String.class, "")).equals(
				APIConstants.ENV_PROD);
	}

	@Override
	public String getEnvironment() {
		_log.entry();
		if (properties == null) {
			_log.error("Application environment properties not initialized");
		}
		return StringUtils.trimToEmpty(getProperty(APIConstants.PROPKEY_ENVIRONMENT, String.class, ""));
	}

	@Override
	public <T> T getPropertyForServiceProvider(String provider, String code, Class<T> clazz, T defaultVal) {
		_log.entry(provider, code);
		if (properties == null) {
			_log.error("Application environment properties not initialized");
		}
		Map<String, EnvironmentProperty> scopeMap = properties.get(provider);
		if (scopeMap == null) {
			return defaultVal;
		}
		EnvironmentProperty property = scopeMap.get(code);
		if (property == null) {
			return defaultVal;
		}
		final String value = property.getValue();
		T result = APIUtil.convert(value, clazz);
		return _log.exit(result);
	}

	@Override
	public <T> T getProperty(String code, Class<T> clazz, T defaultVal) {
		_log.entry(code);
		if (properties == null) {
			_log.error("Application environment properties not initialized");
			return defaultVal;
		}
		return _log.exit(getPropertyForServiceProvider(APIConstants.PROP_GLOBAL, code, clazz, defaultVal));
	}

}
