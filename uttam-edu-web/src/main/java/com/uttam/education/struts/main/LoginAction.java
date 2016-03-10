package com.uttam.education.struts.main;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.uttam.education.api.model.APICodeConstants.UserActivationStatus;
import com.uttam.framework.common.APIConstants;
import com.uttam.framework.common.EncryptionUtil;
import com.uttam.framework.common.env.IEnvironmentProvider;
import com.uttam.framework.common.model.UserMessage;
import com.uttam.framework.service.ServiceLocator;
import com.uttam.web.framework.struts.BaseAction;
import com.uttam.web.framework.struts.SecurityInterceptor;

public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7296331027656555878L;

	private static final Logger _log = LogManager.getLogger(LoginAction.class);

	private String userId;
	private List<String> roles;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Override
	protected void loadDisplayContents() throws Exception {
		_log.entry();
		final IEnvironmentProvider environment = ServiceLocator.getInstance().getService(IEnvironmentProvider.class);
		if (!environment.isProduction() && !environment.getProperty(APIConstants.PROPKEY_AUTH_REQUIRED, Boolean.class, true)) {
		}
		Cookie userCookie = new Cookie(SecurityInterceptor.HTTP_WEBSEC_USERID, "");
		userCookie.setPath("/");
		userCookie.setMaxAge(0);
		Cookie roleCookie = new Cookie(SecurityInterceptor.HTTP_WEBSEC_USER_ROLES, "");
		roleCookie.setPath("/");
		roleCookie.setMaxAge(0);
		ServletActionContext.getResponse().addCookie(userCookie);
		ServletActionContext.getResponse().addCookie(roleCookie);
	}

	@Override
	protected String handleExecute() throws Exception { return SUCCESS;}

	public void validate_execute() {
		if (StringUtils.isEmpty(userId)) {
			super.addValidationError("msg.login.user_id_required", "userId");
		}
		if(CollectionUtils.isEmpty(roles)){
			super.addValidationError("msg.login.roles_required", "roles");
		}

	}

	@Override
	public void onValidationError() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
