/**
 * 
 */
package com.uttam.education.service.admin;

import com.uttam.education.entity.model.ExceptionLog;
import com.uttam.framework.service.IService;

/**
 * @author admin
 *
 */
public interface SystemAdministrationService extends IService {
	void saveExceptionLog(ExceptionLog log);
}
