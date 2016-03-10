package com.hixapi.web.framework.struts;

import java.util.List;

import com.hixapi.web.framework.common.UserIdentity;

public abstract class BaseSecureAction extends BaseAction implements ISecureAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5130981864022738804L;
	protected List<String> allowedRoles;
	private UserIdentity user;

	@Override
	public List<String> getAllowedRoles() {
		return allowedRoles;
	}

	@Override
	public UserIdentity getUser() {
		return user;
	}

	@Override
	public void setUser(UserIdentity u) {
		this.user = u;

	}

}
