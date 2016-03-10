package com.hixapi.ahct.env;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hixapi.web.framework.common.APIConstants;
import com.hixapi.web.framework.env.BaseEnvironmentProvider;
import com.hixapi.web.framework.env.EnvironmentProperty;

public class DatabaseEnvProvider extends BaseEnvironmentProvider implements ResultSetExtractor<Boolean> {
	/** The Constant LOG. */
	private static final Logger _log = org.apache.logging.log4j.LogManager.getLogger(DatabaseEnvProvider.class);

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Load properties, using database tables
	 */
	protected void loadProperties() {
		_log.entry();
		if (properties != null && !properties.isEmpty()) {
			properties.clear();
		}
		_log.info("Loading list of properties in environment manager from database");
		try {
			boolean success = this.jdbcTemplate
					.query("select prop_key, prop_val, service_provider_id from env_property where disabled_ind <> 'Y' order by service_provider_id",
							this);
			if (!success) {
				_log.error("Loading environment properties from database returned failure");
			}
		} catch (Exception ex) {
			_log.error("Failed to load environment properties from db", ex);
		}
		_log.exit();

	}

	

	@Override
	public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
		Boolean success = false;
		Map<String, EnvironmentProperty> globalMap = new LinkedHashMap<String, EnvironmentProperty>();
		properties.put(APIConstants.PROP_GLOBAL, globalMap);
		List<EnvironmentProperty> providerProperties = new ArrayList<EnvironmentProperty>();
		String provider = null;
		String key = null;
		String value = null;
		while (rs.next()) {
			success = true;
			provider = rs.getString("service_provider_id");
			key = rs.getString("prop_key");
			value = rs.getString("prop_val");
			if (APIConstants.PROP_GLOBAL.equals(provider)) {
				globalMap.put(key.trim(), new EnvironmentProperty(key.trim(), value.trim(), APIConstants.PROP_GLOBAL));
			} else {
				EnvironmentProperty prop = new EnvironmentProperty(key.trim(), value.trim(), provider);
				providerProperties.add(prop);
			}
		}
		return success;
	}

}
