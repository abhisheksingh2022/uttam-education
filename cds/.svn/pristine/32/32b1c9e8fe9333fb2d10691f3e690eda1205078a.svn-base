package com.hixapi.web.framework.struts;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.struts2.ServletActionContext;

import com.hixapi.model.common.BaseException;
import com.hixapi.web.framework.common.APIConstants;
import com.hixapi.web.framework.common.APIUtil;
import com.hixapi.web.framework.common.EncryptionUtil;
import com.hixapi.web.framework.common.UserIdentity;
import com.hixapi.web.framework.context.ContextKeyEnum;
import com.hixapi.web.framework.context.ContextProvider;
import com.hixapi.web.framework.env.IEnvironmentProvider;
import com.hixapi.web.framework.service.ServiceLocator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SecurityInterceptor implements Interceptor {

	private static final Logger _log = LogManager.getLogger(SecurityInterceptor.class);

	private static final long serialVersionUID = 8913873567138694339L;

	public static final String HTTP_ISAM_USERID = "iv-user";
	public static final String HTTP_ISAM_USER_ROLES = "iv-groups";

	public void destroy() {

	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		_log.debug("Entry");
		Object action = ActionContext.getContext().getActionInvocation().getAction();
		boolean isSecureAction = (action instanceof ISecureAction);
		if (!isSecureAction) {
			_log.debug("Action not marked as security required, no-op : " + action.getClass().getName());
			return invocation.invoke();
		}

		_log.debug("Secure Action being invoked : " + action.getClass().getName());
		boolean authRequired = true;
		final IEnvironmentProvider envService = ServiceLocator.getInstance().getService(IEnvironmentProvider.class);
		if (!envService.isProduction()) {
			authRequired = envService.getProperty("global.authrequired", Boolean.class, true);
			_log.info("Byepassing security in test environment, ignoring security headers and using cookies");
		}

		String userId = "";
		List<String> userRoles = Collections.emptyList();
		if (authRequired) {
			// From ISAM header if security is enabled
			userId = readHeader(HTTP_ISAM_USERID);
			String roles = readHeader(HTTP_ISAM_USER_ROLES);
			if (StringUtils.isNotBlank(roles)) {
				userRoles = buildRoles(roles);
			}
		} else {
			// From dev cookie if security is not enabled
			// ("See DevPKMSLogin.java action")
			userId = EncryptionUtil.decrypt(readCookie(HTTP_ISAM_USERID), true);
			String roles = EncryptionUtil.decrypt(readCookie(HTTP_ISAM_USER_ROLES), true);
			if (StringUtils.isNotBlank(roles)) {
				userRoles = buildRoles(roles);
			}
		}
		_log.debug("User Id : " + userId);
		_log.debug("Roles: " + userRoles);
		// Check if user id is found
		if (StringUtils.isBlank(userId) || (userRoles == null || userRoles.isEmpty())) {
			BaseException ex = new BaseException(APIConstants.ERRORCODE_SESSION_INVALID + ": User Id / Roles not provided", null);
			ex.setErrorCode(APIConstants.ERRORCODE_SESSION_INVALID);
			throw ex;
		}

		ISecureAction secureAction = (ISecureAction) action;
		List<String> allowedRoles = secureAction.getAllowedRoles();
		if (!CollectionUtils.containsAny(allowedRoles, userRoles)) {
			BaseException ex = new BaseException(MessageFormat.format("{0} No valid roles : Requires Any {0}\n Actual: {1}",
					APIConstants.ERRORCODE_ACCESS_DENIED, allowedRoles, userRoles), null);
			ex.setErrorCode(APIConstants.ERRORCODE_ACCESS_DENIED);
			throw ex;
		}

		// User id is found - Get user information and set the context
		// variable
		UserIdentity sessionUser = SessionController.getUser();
		if (sessionUser == null || !StringUtils.equals(sessionUser.getLoginId(), userId)) {
			sessionUser = setUserInformation(action, userId, userRoles);
		}
		((ISecureAction) action).setUser(sessionUser);
		ContextProvider.setContextField(ContextKeyEnum.REQUEST_USER, sessionUser);
		ThreadContext.put("USER_ID", userId);
		
		final HttpServletRequest request = ServletActionContext.getRequest();

		final Locale locale = APIUtil.getLocale(request.getParameter("lang"));
		ContextProvider.setContextField(ContextKeyEnum.CURRENT_LOCALE, locale);
		ActionContext.getContext().setLocale(locale);
		
		String result = invocation.invoke();
		_log.debug("Exit");
		return result;
	}

	private UserIdentity setUserInformation(Object action, String userId, List<String> userRoles) {
		UserIdentity u = new UserIdentity();
		u.setLoginId(userId);
		u.setRoles(userRoles);
		SessionController.setUser(u);
		
		_log.debug("Set User Identity on Session");

	
		return u;
	}

	private List<String> buildRoles(String roles) {
		String[] loggedInUserRoles = null;
		roles = roles.replace("\"", "");
		loggedInUserRoles = roles.split(",");
		List<String> result = new ArrayList<String>();
		for (String role : loggedInUserRoles) {
			if (StringUtils.isNotBlank(role)) {
				result.add(StringUtils.trimToEmpty(role));
			}
		}
		return result;
	}

	private String readCookie(String paramName) {
		Validate.notNull(paramName, "Cookie Name");
		_log.debug("Finding cookie " + paramName);
		String paramValue = null;
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				_log.debug("Comparing cookie  name : " + c.getName() + " >  " + paramName);
				if (c.getName().equals(paramName)) {
					paramValue = c.getValue();
					break;
				}
			}
		}
		return paramValue;
	}

	private String readHeader(String paramName) {
		Validate.notNull(paramName, "Header Name");
		_log.debug("Finding Header " + paramName);
		String paramValue = ServletActionContext.getRequest().getHeader(paramName);
		return paramValue;
	}

}
