/*
 * Author - HIXAPI.
 * v1.0
 * 2009
 */
package com.hixapi.web.framework.struts;

import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hixapi.web.framework.common.APIUtil;
import com.hixapi.web.framework.context.ContextKeyEnum;
import com.hixapi.web.framework.context.ContextProvider;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.I18nInterceptor;

/**
 * The Class HIXI18nInterceptor - For setting the locale on the action invocation.
 */
public class HIXI18nInterceptor extends AbstractInterceptor {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1451730953556691919L;

	/** The Constant LOG. */
	protected static final Logger LOG = LogManager.getLogger(HIXI18nInterceptor.class);

	/** The Constant DEFAULT_SESSION_ATTRIBUTE. */
	public static final String DEFAULT_SESSION_ATTRIBUTE = I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE;

	// public static final String DEFAULT_PARAMETER = "request_locale";

	// protected String parameterName = DEFAULT_PARAMETER;
	/** The attribute name. */
	protected String attributeName = DEFAULT_SESSION_ATTRIBUTE;
	
	private static final Locale DEFAULT_LOCALE = APIUtil.getLocale("en_US");

	/**
	 * Instantiates a new hix i18n interceptor.
	 */
	public HIXI18nInterceptor() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("new HIXI18nInterceptor()");
		}
	}

	/**
	 * Sets the attribute name.
	 * 
	 * @param attributeName
	 *            the new attribute name
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * Sets the locale in the action context, if available in the user http session. Else sets the default locale in the
	 * context - English and sets English as the locale in session
	 * 
	 * @param invocation
	 * @return
	 * @throws Exception
	 * @author admin Nov 1, 2009
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		// get requested locale
		// Map<String, Object> params =
		// invocation.getInvocationContext().getParameters();
		// Object requested_locale = params.remove(parameterName);
		// if (requested_locale != null && requested_locale.getClass().isArray()
		// && ((Object[]) requested_locale).length == 1) {
		// requested_locale = ((Object[]) requested_locale)[0];
		//
		// if (LOG.isDebugEnabled()) {
		// LOG.debug("requested_locale=" + requested_locale);
		// }
		// }

		// save it in session
		//	Map<String, Object> session = invocation.getInvocationContext()
		//	    .getSession();

		Locale locale = SessionController.getLanguage();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Current Request Locale " + (locale != null ? locale.getDisplayName() : null));
		}
		if (locale == null) {
			locale = DEFAULT_LOCALE;
		}
		
		Object action = invocation.getAction();

		if (action instanceof BaseAction) {
			BaseAction hixAction = (BaseAction) action;
			if (!hixAction.isInternationalized()) {
				invocation.getInvocationContext().setLocale(DEFAULT_LOCALE);
			} else {
				invocation.getInvocationContext().setLocale(locale);
			}
		} else {
			invocation.getInvocationContext().setLocale(locale);
		}
		LOG.info("Locale set in Context Provider: {}", locale);
		ContextProvider.setContextField(ContextKeyEnum.CURRENT_LOCALE, locale);
		
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		if (session != null) {

			synchronized (session) {
				locale = (Locale) session.get(attributeName);
				if (locale == null) {
					locale = DEFAULT_LOCALE;
					session.put(attributeName, locale);
				}

			}
		} else {
			//No session, use default locale
			locale = DEFAULT_LOCALE;
		}
		final String result = invocation.invoke();

		return result;
	}

	
}
