package com.hixapi.ahct.cds.struts;

import java.util.ArrayList;
import java.util.List;

import com.hixapi.ahct.cds.model.Household;
import com.hixapi.web.framework.common.UserMessage;
import com.hixapi.web.framework.struts.BaseAction;

public class HomeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -448791743540959890L;
	private static final String SAMPLE_AJAX = "sampleAjax";
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(HomeAction.class);

	private Household household;

	private List<String> lookup;

	

	/**
	 * This method should get all the contents to be displayed on the page.
	 * This is called by BaseAction from input() method
	 */
	protected void loadDisplayContents() {
		//Call Service via ServiceLocator if required
		lookup = new ArrayList<String>();
		lookup.add("Test");
	}

	/**
	 * This is a sample method, this does not get invoked, its validation is always configured to fail - see validate_validationError
	 * @return
	 * @throws Exception
	 */
	public String validationError() throws Exception {
		//Call Service via ServiceLocator if required

		return FLASH; // Use flash to perform a redirect to support the POST-REDIRECT-GET pattern
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
	
	

	public String ajaxPost() {
		log.entry();

		return SAMPLE_AJAX;
	}

	public String ajaxGet() {
		log.entry();

		return SAMPLE_AJAX;
	}


	//This is the validation method for the validationError action
	public void validate_validationError() {
		super.addValidationError("msg.global.sample_validation_error", "name", "age");
	}
	
	public void validate_execute() {
		
	}

	public Household getHousehold() {
		return household;
	}

	public void setHousehold(Household household) {
		this.household = household;
	}
	
	public List<String> getLookup() {
		return lookup;
	}

	public void setLookup(List<String> lookup) {
		this.lookup = lookup;
	}
}
