package com.uttam.framework.service;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLocator {

	private static final Logger _log = LogManager.getLogger(ServiceLocator.class);
	private static ServiceLocator INSTANCE = new ServiceLocator();

	private ApplicationContext springContext;

	private Map<Class<IService>, IService> services;
	
	private String applicationId;

	private ServiceLocator() {

	}

	public static ServiceLocator getInstance() {
		return INSTANCE;
	}

	@SuppressWarnings("unchecked")
	public <E extends IService> E getService(Class<E> clazz) {
		_log.entry(clazz);
		if(services != null){
			IService service = services.get(clazz);
			return _log.exit((E) service);
		}
		_log.error("***Warning!!!*** - services not registered - potential spring configuration issue?");
		return _log.exit(null);
		
	}

	public void setServices(Map<Class<IService>, IService> services) {
		this.services = services;
	}

	
	public void setSpringContext(ApplicationContext springContext) {
		this.springContext = springContext;
	}

	public static void initialize(String path) {
		ClassPathXmlApplicationContext applicationContext = null;
		applicationContext = new ClassPathXmlApplicationContext(path);
		_log.info("Initialized Spring Application Context of id " + applicationContext.getDisplayName());
		getInstance().setSpringContext(applicationContext);
		

	}

	public String getContextProperty(String key) {
		return this.springContext.getEnvironment().getProperty(key);
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public ApplicationContext getSpringContext() {
		return springContext;
	}
}
