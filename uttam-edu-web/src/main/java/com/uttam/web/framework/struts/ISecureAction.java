package com.uttam.web.framework.struts;

import java.util.List;

import com.uttam.framework.common.model.UserIdentity;

public interface ISecureAction {

	public boolean shouldAllowCurrentUserAccess();

	public UserIdentity getUser();

	public void setUser(UserIdentity u);
}
