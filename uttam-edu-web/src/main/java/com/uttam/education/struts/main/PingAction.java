package com.uttam.education.struts.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uttam.web.framework.struts.BaseAction;
import com.uttam.web.framework.struts.SessionController;

public class PingAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7296331027656555878L;
	
	private static final Logger _log = LogManager.getLogger(PingAction.class);
	
	

	@Override
	protected void loadDisplayContents() throws Exception {
		_log.entry();
		_log.debug("Extending Session for {}", SessionController.getUser().getLoginId());
		_log.exit();

	}



	@Override
	protected String handleExecute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
