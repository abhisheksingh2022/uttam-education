package com.uttam.framework.dao.i18n;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.uttam.framework.common.i18n.IResourceBundleDao;
import com.uttam.framework.common.i18n.ResourceBundleEntry;

public class ResourceBundleDAO implements IResourceBundleDao {
	private static final Logger log = LogManager.getLogger(ResourceBundleDAO.class);
	private JdbcTemplate template;
	private String applicationId;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public List<ResourceBundleEntry> retrieveDBResourceBundle(String localeCode) {
		final String sql = "select text_key, text_lang_cd, service_provider_id, text_val from display_text where text_lang_cd = ? and service_provider_id = ?";

		log.info("Loading Resource Bundle of locale {} for service provider {}", localeCode, applicationId);
		List<ResourceBundleEntry> list = template.query(sql, new Object[] { localeCode, applicationId },
				new RowMapper<ResourceBundleEntry>() {

					@Override
					public ResourceBundleEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
						log.entry(rowNum);
						ResourceBundleEntry entry = new ResourceBundleEntry();
						entry.setKey(rs.getString("text_key"));
						entry.setLangCode(rs.getString("text_lang_cd"));
						entry.setServiceProviderId(rs.getString("service_provider_id"));
						try {
							entry.setValue(rs.getString("text_val"));
						} catch (SQLException ex) {
							log.error(
									"Invalid string found for key: {}, language: {}. This text will not be available in resource bundle",
									entry.getKey(), entry.getLangCode(), ex);
							entry.setValue("Invalid text content");
						}
						log.exit();
						return entry;
					}

				});
		return list;
	}

	/**
	 * @return the template
	 */
	public JdbcTemplate getTemplate() {
		return template;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

}
