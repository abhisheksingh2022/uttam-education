package com.hixapi.ahct.cds.struts;

import java.util.ArrayList;
import java.util.List;

import com.hixapi.ahct.cds.model.Household;
import com.hixapi.web.framework.common.UserMessage;
import com.hixapi.web.framework.struts.BaseAction;
//not using this class can remove
public class EligibilityResultsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -448791743540959890L;
	private static final String SAMPLE_AJAX = "sampleAjax";
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(EligibilityResultsAction.class);

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

	
	protected String handleExecute() throws Exception {

		//Store information from input to session via SessionController, do any action that is required
		if(System.currentTimeMillis() % 2 == 0){
			super.addBusinessMessage(new UserMessage(1, "This is random error message that will come once in a  while", null, null));
			return FLASH;
		}
		

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
		super.addBusinessMessage(new UserMessage(1, "msg.global.sample_validation_error", null, null));
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
