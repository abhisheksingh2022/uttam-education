package com.uttam.web.framework.struts;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.uttam.education.api.model.APICodeConstants.AccessCheckMode;
import com.uttam.framework.common.context.ContextKeyEnum;
import com.uttam.framework.common.context.ContextProvider;
import com.uttam.framework.common.model.UserIdentity;

public abstract class BaseSecureAction extends BaseAction implements ISecureAction {/*
	*//**
	 * 
	 *//*

	private static final long serialVersionUID = -5130981864022738804L;

	private UserIdentity user;

	@Override
	public boolean shouldAllowCurrentUserAccess() {
		String action = WebUtil.getActionPath(ServletActionContext.getRequest());
		//return PMPAccessController.getInstance().isAllowedForCurrentUser(AccessCheckMode.WEB_ACTION, action);
		return true;
	}

	@Override
	public UserIdentity getUser() {
		if (user == null) {
			user = SessionController.getUser();
		}
		return user;
	}

	@Override
	public void setUser(UserIdentity u) {
		this.user = u;

	}

	public boolean isUserAllowedToAccessAction(String actionPath) {
		if (StringUtils.isEmpty(actionPath)) {
			return true;
		}
		boolean setContextUser = false;
		try {
			if (user != null && ContextProvider.getContextField(ContextKeyEnum.REQUEST_USER) == null) {
				ContextProvider.setContextField(ContextKeyEnum.REQUEST_USER, this.user);
				setContextUser = true;
			}

			boolean result = PMPAccessController.getInstance().isAllowedForCurrentUser(AccessCheckMode.WEB_ACTION,
					actionPath);
			return result;
		} finally {
			if (setContextUser) {
				ContextProvider.setContextField(ContextKeyEnum.REQUEST_USER, null);
			}
		}
	}

	public boolean isUserAllowedToAccessFunction(String functionCode) {
		boolean retVal = false;
		boolean setContextUser = false;
		try {
			if (user != null && ContextProvider.getContextField(ContextKeyEnum.REQUEST_USER) == null) {
				ContextProvider.setContextField(ContextKeyEnum.REQUEST_USER, this.user);
				setContextUser = true;
			}

			boolean result = PMPAccessController.getInstance().isAllowedForCurrentUser(AccessCheckMode.USER_ACTION,
					functionCode);
			retVal = result;
		} finally {
			if (setContextUser) {
				ContextProvider.setContextField(ContextKeyEnum.REQUEST_USER, null);
			}
		}
		return retVal;
	}

*/}
