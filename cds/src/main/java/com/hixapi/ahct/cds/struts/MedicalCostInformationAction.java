package com.hixapi.ahct.cds.struts;

import com.hixapi.ahct.cds.model.MedicalCost;
import com.hixapi.web.framework.struts.BaseAction;
import com.hixapi.web.framework.struts.SessionController;

public class MedicalCostInformationAction  extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2809068308609158931L;
	private String medicalCostInsured;
	private String medicalCostUninsured;
	
	@Override
	protected void loadDisplayContents() throws Exception {
		// TODO Auto-generated method stub
		MedicalCost medicalCost = SessionController.getMedicalCost();
		
		medicalCostInsured = medicalCost.getMedicalCostInsured();
		medicalCostUninsured = medicalCost.getMedicalCostUninsured();
		
	}

	@Override
	protected String handleExecute() throws Exception {
		// TODO Auto-generated method stub
		
		return NEXT;
	}
	public String goBack() throws Exception {
		addRedirectURLParameter("doBack", true);
		return BACK;
	}
	public String getMedicalCostInsured() {
		return medicalCostInsured;
	}

	public void setMedicalCostInsured(String medicalCostInsured) {
		this.medicalCostInsured = medicalCostInsured;
	}

	public String getMedicalCostUninsured() {
		return medicalCostUninsured;
	}

	public void setMedicalCostUninsured(String medicalCostUninsured) {
		this.medicalCostUninsured = medicalCostUninsured;
	}

}
