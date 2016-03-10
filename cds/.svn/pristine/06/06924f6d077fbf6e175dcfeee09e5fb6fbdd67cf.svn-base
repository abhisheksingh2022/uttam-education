package com.hixapi.ahct.cds.struts;

import java.util.Locale;

import com.hixapi.web.framework.common.UserMessage;
import com.hixapi.web.framework.struts.BaseAction;
import com.hixapi.web.framework.struts.SessionController;

public class PingAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -448791743540959890L;
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(PingAction.class);


	

	/**
	 * This method should get all the contents to be displayed on the page.
	 * This is called by BaseAction from input() method
	 */
	protected void loadDisplayContents() {
		Locale language = SessionController.getLanguage();
		log.debug("Current Session Language : {}", language.toString());
		
	}

	
	/**
	 * This method should handle the post of execute() method call
	 * This is called by BaseAction from execute() method
	 */
	protected String handleExecute() throws Exception {

		//Store information from input to session via SessionController, do any action that is required
		if(System.currentTimeMillis() % 3 == 0){
			//Error message / Info messages / Warnings.. Note to use this only when the same action page is to be displayed. 
			//This message will not be displayed if you return NEXT or any redirect-results.
			super.addBusinessMessage(new UserMessage(2, "This is random warning message that will come once in a  while, please retry", null, null));
			//if return to same page is required with display of error messages, return FLASH
			return FLASH;
		}
		
		// To go to next page return NEXT
		return NEXT;
	}
	
	

}
