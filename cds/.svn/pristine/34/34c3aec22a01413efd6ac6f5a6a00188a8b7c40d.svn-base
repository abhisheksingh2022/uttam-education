package com.hixapi.ahct.i18n;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hixapi.web.framework.i18n.IResourceBundleDao;
import com.hixapi.web.framework.i18n.ResourceBundleEntry;

public class ResourceBundleDAO implements IResourceBundleDao {
	private static final Logger log = LogManager.getLogger(ResourceBundleDAO.class);
	private JdbcTemplate template;

	

	@Override
	public List<ResourceBundleEntry> retrieveDBResourceBundle(String localeCode) {
		final String sql = "select text_key, text_lang_cd, service_provider_id, text_val from display_text where text_lang_cd = ?";

		log.info("Loading Resource Bundle of locale {}", localeCode);
		List<ResourceBundleEntry> list = template.query(sql, new Object[] { localeCode }, new RowMapper<ResourceBundleEntry>() {

			@Override
			public ResourceBundleEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
				log.entry(rowNum);
				ResourceBundleEntry entry = new ResourceBundleEntry();
				entry.setKey(rs.getString("text_key"));
				entry.setLangCode(rs.getString("text_lang_cd"));
				entry.setServiceProviderId(rs.getString("service_provider_id"));
				entry.setValue(rs.getString("text_val"));
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
	 * @param template the template to set
	 */
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

}
