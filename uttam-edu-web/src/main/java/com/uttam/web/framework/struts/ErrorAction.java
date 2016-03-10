package com.uttam.web.framework.struts;

import com.uttam.framework.common.model.UserIdentity;


public class ErrorAction extends BaseSecureAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4094940160620879771L;

	@Override
	protected void loadDisplayContents() throws Exception {
		//no-op
		
	}

	@Override
	protected String handleExecute() throws Exception {
		throw new UnsupportedOperationException("execute is not supported");
	}

	@Override
	public boolean shouldAllowCurrentUserAccess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserIdentity getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUser(UserIdentity u) {
		// TODO Auto-generated method stub
		
	}
	
}
