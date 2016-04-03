package com.uttam.framework.dao.i18n;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.uttam.framework.common.i18n.IResourceBundleDao;
import com.uttam.framework.common.i18n.ResourceBundleEntry;
import com.uttam.framework.core.model.DisplayText;
import com.uttam.framework.dao.impl.BaseDAOImpl;

public class ResourceBundleDAO<T> extends BaseDAOImpl<T> implements IResourceBundleDao {
	private static final Logger log = LogManager.getLogger(ResourceBundleDAO.class);
	
	public ResourceBundleDAO() {
		log.debug("ResourceBundleDAO Instantiated");
	}

	private String applicationId;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public List<ResourceBundleEntry> retrieveDBResourceBundle(String localeCode) {
		Query query = new Query();
		query.addCriteria(Criteria.where("textLangCode").is(localeCode).and("serviceProviderId").is(applicationId));

		query.fields().include("textKey");
		query.fields().include("textLangCode");
		query.fields().include("serviceProviderId");
		query.fields().include("textValue");
		try{
		List<DisplayText> displayTextCol = getMongoOps().find(query,DisplayText.class);
		List<ResourceBundleEntry> list = loadResourceBundle(displayTextCol);
		
		log.info("Loading Resource Bundle of locale {} for service provider {}", localeCode, applicationId);
		log.info("Number of Entry found {}",list.size());
		return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	private List<ResourceBundleEntry> loadResourceBundle(List<DisplayText> list){
		log.entry();
		List<ResourceBundleEntry> bundleList = new ArrayList<ResourceBundleEntry>();
		for(DisplayText displayText:list){
			ResourceBundleEntry entry = new ResourceBundleEntry();
			entry.setKey(displayText.getTextKey());
			entry.setLangCode(displayText.getTextLangCode());
			entry.setServiceProviderId(displayText.getServiceProviderId());
			
			if(StringUtils.isEmpty(displayText.getTextValue())){
				log.error(
						"Invalid string found for key: {}, language: {}. This text will not be available in resource bundle",
						entry.getKey(), entry.getLangCode());
				entry.setValue("Invalid text content");
			}else{
				entry.setValue(displayText.getTextValue());
			}
			bundleList.add(entry);
		}
		return log.exit(bundleList);
	}
}
