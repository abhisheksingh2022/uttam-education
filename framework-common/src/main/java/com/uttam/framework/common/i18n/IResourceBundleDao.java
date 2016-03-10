package com.uttam.framework.common.i18n;

import java.util.List;

import com.uttam.framework.service.IService;

public interface IResourceBundleDao extends IService {

	public List<ResourceBundleEntry> retrieveDBResourceBundle(String localeCode);
}
