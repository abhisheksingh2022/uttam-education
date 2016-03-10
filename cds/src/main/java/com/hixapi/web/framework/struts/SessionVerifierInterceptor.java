package com.hixapi.web.framework.struts;

import org.apache.commons.lang3.StringUtils;

import com.hixapi.ahct.cds.model.Eligibility;
import com.hixapi.ahct.cds.model.Household;
import com.hixapi.ahct.cds.model.MedicalCost;
import com.hixapi.web.framework.struts.SessionController;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionVerifierInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3469764541479748243L;
	private static final String NOTACCEPTED = "DisclaimerNotAccepted";
	private static final String HOUSEHOLDEMPTY = "HouseholdNotSet";
	private static final String MEDICALCOSTEMPTY = "MedicalCostNotSet";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		String disclaimer = SessionController.getDisclaimerAcceptance();
		Household household = SessionController.getHousehold();
		MedicalCost medicalCost = SessionController.getMedicalCost();
		Eligibility eligibility = SessionController.getEligibility();


		 String action = invocation.getProxy().getActionName();
		
		if (action.startsWith("disclaimer")) {
			return invocation.invoke();
		} 
		 else{
			   if (StringUtils.isBlank(disclaimer)) {
			    // System.out.println("NA");
			    return NOTACCEPTED;
			   }
			   else if (action.startsWith("health-status")) {
			    if (household == null || eligibility == null) {
			     return HOUSEHOLDEMPTY;
			    }
			   }
			   else if (action.startsWith("plan")) {
				    if (household == null || eligibility == null) {
				     return HOUSEHOLDEMPTY;
				    }
				    else if(medicalCost == null){
				    	return MEDICALCOSTEMPTY;
				    }
				    	
				   }
			   else if (action.startsWith("medical-cost-information")){
				   if (household == null || eligibility == null) {
					     return HOUSEHOLDEMPTY;
					    }
					    else if(medicalCost == null){
					    	return MEDICALCOSTEMPTY;
					    }
			   }
			   
		 }
		return invocation.invoke();
	}

}
