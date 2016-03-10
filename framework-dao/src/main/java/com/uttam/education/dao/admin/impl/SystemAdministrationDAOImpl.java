package com.uttam.education.dao.admin.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.uttam.education.dao.admin.SystemAdministrationDAO;
import com.uttam.education.entity.model.ExceptionLog;
import com.uttam.framework.dao.BaseDAO;

public class SystemAdministrationDAOImpl extends BaseDAO implements SystemAdministrationDAO {
	
	private static final Logger log =LogManager.getLogger(SystemAdministrationDAOImpl.class);
	
	@Override
	public void saveExceptionLog(ExceptionLog exception) {
		log.entry();
		Session session = getCurrentSession();
		session.save(exception);
		session.flush();
		log.exit();
		
	}

}
