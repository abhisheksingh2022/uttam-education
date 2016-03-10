package com.hixapi.web.framework.struts;

import java.util.List;

import com.hixapi.web.framework.common.UserIdentity;

public interface ISecureAction {

	public List<String> getAllowedRoles();

	public UserIdentity getUser();

	public void setUser(UserIdentity u);
}
