package com.uttam.framework.dao.env;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.uttam.framework.common.env.BaseEnvironmentProvider;
import com.uttam.framework.common.env.EnvironmentProperty;

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
			boolean success = this.jdbcTemplate.query(
					"select prop_key, prop_val, application_id, prop_group from env_property where disabled_ind <> 'Y' "
							+ "and application_id = '" + getApplicationId() + "' " + "order by prop_group", this);
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

		String key = null;
		String value = null;
		String group = null;
		Map<String, EnvironmentProperty> groupMap = null;
		while (rs.next()) {
			success = true;
			key = StringUtils.trimToEmpty(rs.getString("prop_key"));
			value = StringUtils.trimToEmpty(rs.getString("prop_val"));
			group = StringUtils.trimToEmpty(rs.getString("prop_group"));

			if (properties.get(group) == null) {
				groupMap = new LinkedHashMap<String, EnvironmentProperty>();
				properties.put(group, groupMap);
			}
			groupMap.put(key, new EnvironmentProperty(key, value, getApplicationId(), group));

		}
		return success;
	}

}
