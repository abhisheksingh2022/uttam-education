package com.uttam.education.dao.admin.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;

import com.uttam.education.dao.admin.SystemAdministrationDAO;
import com.uttam.education.entity.model.ExceptionLog;
import com.uttam.framework.dao.impl.BaseDAOImpl;

public class SystemAdministrationDAOImpl<T> extends  BaseDAOImpl<T> implements SystemAdministrationDAO {
	
	private static final Logger log =LogManager.getLogger(SystemAdministrationDAOImpl.class);
	
	@Override
	public void saveExceptionLog(ExceptionLog exception) {
		log.entry();
		MongoOperations mongoOps = getMongoOps();
		mongoOps.save(exception);
		log.exit();
	}
}
