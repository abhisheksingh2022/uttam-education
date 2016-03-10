package com.hixapi.web.framework.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hixapi.web.framework.common.APIConstants;
import com.hixapi.web.framework.context.ContextProvider;
import com.hixapi.web.framework.env.IEnvironmentProvider;
import com.hixapi.web.framework.service.LookupManager;
import com.hixapi.web.framework.service.ServiceLocator;

public class HIXAPIContextInitializer implements ServletContextListener {

	private static Logger LOG = null;
	static {
		try {
			initializeLog4J();
			LOG = LogManager.getLogger(HIXAPIContextInitializer.class);
		} catch (Exception ex) {
			System.out.println("!!!!!!!!!!!!!!   Log4J Initialization Failed    !!!!!!!!!!!!!!!!!!");
			ex.printStackTrace(System.out);
			throw new RuntimeException(ex);
		}
	}

	/**
		 * 
		 */

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ContextProvider.setRequestId(APIConstants.STARTUP_REQ_ID);
		try {
			String path = sce.getServletContext().getInitParameter("spring-config-location");
			ClassPathXmlApplicationContext applicationContext = null;
			try {
				applicationContext = new ClassPathXmlApplicationContext(path);
				LOG.info("Initialized Spring Application Context of id " + applicationContext.getDisplayName());
				ServiceLocator.getInstance().setSpringContext(applicationContext);
				final IEnvironmentProvider environmentProvider = ServiceLocator.getInstance().getService(
						IEnvironmentProvider.class);
				environmentProvider.reload();
//				Filter strutsFilter = sce.getServletContext().createFilter(
//						org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter.class);
//
//				FilterRegistration struts = sce.getServletContext().addFilter("struts2", strutsFilter);
//				struts.setInitParameter("config", sce.getServletContext().getInitParameter("struts-config-location"));
//				struts.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
//						DispatcherType.ASYNC, DispatcherType.ERROR), true, "/*");
				LookupManager.loadLookups();
			}

			catch (Exception ex) {
				System.out.println("!!!!!!!!!!!!!!   System Initialization Failed    !!!!!!!!!!!!!!!!!!");
				ex.printStackTrace(System.out);
				throw new RuntimeException(ex);

			} 
		} finally {
			ContextProvider.unset();
		}

	}

	private static void initializeLog4J() throws Exception {
		String env = System.getProperty(APIConstants.PROPKEY_ENVIRONMENT);
		if (StringUtils.isBlank(env)) {
			throw new Exception("Environment Property 'global.environment' not found");
		}
		final String file = "/env-configs/" + env + "/log4j2.xml";
		System.setProperty("log4j.configurationFile", file);
		System.out.println("********'cds' - Loaded Log4j configuration file from : " + file);
		//			final String file = "/common/env-configs/" + env + "/log4j2.xml";
		//			InputStream is = null;
		//			try {
		//				is = com.hixapi.web.framework.servlet.class.getClassLoader().getResourceAsStream(file);
		//				ConfigurationSource source = new ConfigurationSource(is);
		////				Configuration config = XmlConfigurationFactory.getInstance().getConfiguration(source);
		//				Configurator.initialize(null, source);
		//				System.out.println("Log4J initialized from file: " + file);
		//			} finally {
		//				if (is != null) {
		//					is.close();
		//				}
		//			}

	}
}
