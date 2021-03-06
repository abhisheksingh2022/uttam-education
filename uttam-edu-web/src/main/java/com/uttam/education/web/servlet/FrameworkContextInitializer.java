package com.uttam.education.web.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uttam.framework.common.APIConstants;
import com.uttam.framework.common.context.ContextProvider;
import com.uttam.framework.service.ServiceLocator;
import com.uttam.web.framework.service.LookupService;

public class FrameworkContextInitializer implements ServletContextListener {

	private static Logger LOG = null;
	static {
		try {
			initializeLog4J();
			LOG = LogManager.getLogger(FrameworkContextInitializer.class);
			LOG.entry();
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
			try {
				ServiceLocator.initialize(path);

				//				final IEnvironmentProvider environmentProvider = ServiceLocator.getInstance().getService(
				//						IEnvironmentProvider.class);
				//environmentProvider.reload();
				//				Filter strutsFilter = sce.getServletContext().createFilter(
				//						org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter.class);
				//
				//				FilterRegistration struts = sce.getServletContext().addFilter("struts2", strutsFilter);
				//				struts.setInitParameter("config", sce.getServletContext().getInitParameter("struts-config-location"));
				//				struts.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
				//						DispatcherType.ASYNC, DispatcherType.ERROR), true, "/*");
				//LookupService.initialize();
				
				System.out.println("******* Education Web App initialized successfully *******");
				LogManager.getLogger(FrameworkContextInitializer.class).error(
						"******* Education Web App initialized successfully *******");
			}

			catch (Exception ex) {
				System.out.println("!!!!!!!!!!!!!!   System Initialization Failed    !!!!!!!!!!!!!!!!!!");
				LogManager.getLogger(FrameworkContextInitializer.class).error(
						"!!!!!!!!!!!!!!   System Initialization Failed    !!!!!!!!!!!!!!!!!!", ex);
				ex.printStackTrace(System.out);
				throw new RuntimeException(ex);

			}
		} finally {
			ContextProvider.unset();
		}

	}

	private static void initializeLog4J() throws Exception {
		//String env = System.getProperty(APIConstants.PROPKEY_ENVIRONMENT);
		String env = "DEV";
		if (StringUtils.isBlank(env)) {
			throw new Exception("Environment Property 'global.environment' not found");
		}
		final String file = "/env-configs/" + env + "/log4j2.xml";
		System.setProperty("log4j.configurationFile", file);
		System.out.println("********'education' - Loaded Log4j configuration file from : " + file);
		//			final String file = "/common/env-configs/" + env + "/log4j2.xml";
		//			InputStream is = null;
		//			try {
		//				is = com.hixapi.framework.servlet.class.getClassLoader().getResourceAsStream(file);
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
