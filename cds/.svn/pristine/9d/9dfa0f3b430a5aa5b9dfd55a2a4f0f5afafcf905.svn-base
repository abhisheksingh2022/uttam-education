package com.hixapi.web.framework.service;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;

public class ServiceLocator {

	private static final Logger _log = LogManager.getLogger(ServiceLocator.class);
	private static ServiceLocator INSTANCE = new ServiceLocator();

	private ApplicationContext springContext;

	private Map<Class<IService>, IService> services;

	private ServiceLocator() {

	}

	public static ServiceLocator getInstance() {
		return INSTANCE;
	}

	@SuppressWarnings("unchecked")
	public <E extends IService> E getService(Class<E> clazz) {
		_log.entry(clazz);
		IService service = services.get(clazz);
		return _log.exit((E) service);
	}

	public void setServices(Map<Class<IService>, IService> services) {
		this.services = services;
	}

	public SessionFactory getDefaultHibernateSessionFactory() {
		return getHibernateSessionFactory("sessionFactory");
	}

	public SessionFactory getHibernateSessionFactory(String name) {
		return this.springContext.getBean(name, SessionFactory.class);
	}

	public void setSpringContext(ApplicationContext springContext) {
		this.springContext = springContext;
	}
}
