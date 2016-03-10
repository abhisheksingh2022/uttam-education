package com.hixapi.web.framework.env;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.hixapi.web.framework.common.APIConstants;
import com.hixapi.web.framework.exception.ConfigurationException;

public class FileSystemEnvProvider extends BaseEnvironmentProvider {
	/** The Constant LOG. */
	private static final Logger _log = org.apache.logging.log4j.LogManager.getLogger(FileSystemEnvProvider.class);

	/**
	 * Load properties, using File system
	 */
	protected void loadProperties() {
		_log.entry();
		if (properties != null && !properties.isEmpty()) {
			properties.clear();
		}
		_log.info("Loading list of properties in environment manager through filesystem");
		Properties props = new Properties();
		InputStream propFileStream = null;
		
		
		try {
			String path = getRootFolder();
			propFileStream = getClass().getClassLoader().getResourceAsStream(path + "system.properties");
			props.load(propFileStream);
			Set<Entry<Object, Object>> entries = props.entrySet();
			HashMap<String, EnvironmentProperty> globalMap = new HashMap<String, EnvironmentProperty>();
			properties.put(APIConstants.PROP_GLOBAL, globalMap);
			for (Entry<Object, Object> entry : entries) {
				globalMap.put(entry.getKey().toString().trim(), new EnvironmentProperty(entry.getKey().toString().trim(), entry
						.getValue().toString().trim(), APIConstants.PROP_GLOBAL));
			}

		} catch (Exception e) {
			_log.error("Failed to load system properties from file system", e);
		} finally {
			if (propFileStream != null) {
				try {
					propFileStream.close();
				} catch (IOException e) {
					_log.error("Error closing stream", e);
				}
			}
		}
	
		_log.exit();

	}

	private String getRootFolder() throws ConfigurationException {
		String env = System.getProperty(APIConstants.PROPKEY_ENVIRONMENT);
		if(StringUtils.isBlank(env)){
			throw new ConfigurationException("Environment Property not found", "global.environment");
		}
		final String path = "/env-configs/" + env + "/";
		return path;
	}

	

}
