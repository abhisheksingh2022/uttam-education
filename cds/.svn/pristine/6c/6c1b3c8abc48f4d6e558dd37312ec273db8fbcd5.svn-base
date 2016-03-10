package com.hixapi.ahct.cds.struts;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.hixapi.ahct.cds.model.Eligibility;
import com.hixapi.ahct.cds.model.Household;
import com.hixapi.ahct.cds.model.Individual;
import com.hixapi.ahct.cds.model.IndividualEligibilityResults;
import com.hixapi.web.framework.common.APIConstants;
import com.hixapi.web.framework.common.model.LookupBean;
import com.hixapi.web.framework.context.MetricEvent;
import com.hixapi.web.framework.context.MetricKeyEnum;
import com.hixapi.web.framework.service.LookupManager;
import com.hixapi.web.framework.struts.BaseAction;
import com.hixapi.web.framework.struts.SessionController;

public class DisclaimerAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ACCEPTED = "accepted";
	private static final String AJAX_SUCCESS = "ajax-simple-succes";
	
	private static final String MEMBER_EN = "Member";
	private static final String MEMBER_ES = "miembro";
	private static final String APPLICANT_EN = "Applicant";
	private static final String APPLICANT_ES = "Solicitante";
	
	private static final String LANGUAGE_SPANISH = "Spanish";
	private static final String LANGUAGE_ENGLISH = "English";
	
	private String cds_income;
	private String cds_incomeType;
	private String cds_county;
	private String cds_year;
	private String cds_userLanguage;
	private Household cds_household;

	
	@Override
	public String changeLanguage() {
		super.changeLanguage();
		Household  household = SessionController.getHousehold();
		Eligibility eligibility = SessionController.getEligibility();
		String language = SessionController.getLanguage().getDisplayLanguage();
		if(household != null){
			List<Individual> individuals = household.getHouseholdMembers();
			Individual individual = individuals.get(0);
			if(LANGUAGE_SPANISH.equalsIgnoreCase(language)){
				individual.setName(APPLICANT_ES);
			}else{
				individual.setName(APPLICANT_EN);
			}
			for(int individualCounter = 1; individualCounter < individuals.size(); individualCounter++){
				individual = individuals.get(individualCounter);
				if(LANGUAGE_SPANISH.equalsIgnoreCase(language)){
					individual.setName(MEMBER_ES + " " + individualCounter);
				}else{
					individual.setName(MEMBER_EN + " " + individualCounter);
				}
			}
		}
		if(eligibility != null){
			List<IndividualEligibilityResults> individualEligibilityResults = eligibility.getResultList();
			IndividualEligibilityResults indResult = individualEligibilityResults.get(0);
			if(LANGUAGE_SPANISH.equalsIgnoreCase(language)){
				indResult.setName(APPLICANT_ES);
			}else{
				indResult.setName(APPLICANT_EN);
			}
			for(int individualCounter = 1; individualCounter < individualEligibilityResults.size(); individualCounter++){
				indResult = individualEligibilityResults.get(individualCounter);
				if(LANGUAGE_SPANISH.equalsIgnoreCase(language)){
					indResult.setName(MEMBER_ES + " " + individualCounter);
				}else{
					indResult.setName(MEMBER_EN + " " + individualCounter);
				}
			}
		}
		
		return AJAX_SUCCESS;
	}

	@Override
	protected void loadDisplayContents() throws Exception {
		Locale locale = SessionController.getLanguage();
		boolean honorReferrerLang = SessionController.honorReferrerLanguage();
		SessionController.clearSession();
		if (locale != null) {
			SessionController.setLanguage(locale);
		}

		if (honorReferrerLang && StringUtils.isNotBlank(cds_userLanguage)) {
			super.setLanguage(getMappedLanguageCode(cds_userLanguage));
			changeLanguage();
		}

		if (cds_household != null) {
			Household h = cds_household;
			h.setCoverageYearStr(cds_year);
			h.setCounty(mapCounty(cds_county));
			if (StringUtils.isNotBlank(cds_income)) {
				if (cds_income.contains(",")) {
					cds_income = cds_income.replace(",", "");
				}
				h.setHouseholdIncome(Long.parseLong(cds_income));
			}
			h.setHouseholdIncomeTypeCode(mapIncomeTypeCode(cds_incomeType));
			if (h.getHouseholdMembers() != null) {
				String memberIndex = null;
				for (Individual indv : h.getHouseholdMembers()) {
					if (indv.getIndividualSequenceNumber() != null) {
						memberIndex = String.valueOf(indv.getIndividualSequenceNumber());
						indv.setIndividualSequenceNumber(indv.getIndividualSequenceNumber() + 1);
						if (indv.getIndividualSequenceNumber() != 1) {
							indv.setName(getText("txt.household.label15") + " " + memberIndex);
							//indv.setIndividualSequenceNumber(indv.getIndividualSequenceNumber() + 1);
						} else if (indv.getIndividualSequenceNumber() == 1) {
							indv.setName(getText("txt.household.label6"));
						}
					}

					if ("SP".equals(indv.getRelationToApplicantCode())) {
						indv.setRelationToApplicantCode("S");
					} else if ("DP".equals(indv.getRelationToApplicantCode())) {
						indv.setRelationToApplicantCode("D");
					}

					if ("YES".equals(indv.getPregnantIndicator())) {
						indv.setPregnantIndicator(APIConstants.IND_YES);
					} else {
						indv.setPregnantIndicator(APIConstants.IND_NO);
					}
				}
			}
			SessionController.setHousehold(h);
		}
		if(cds_userLanguage != null)
		{
			new MetricEvent(MetricKeyEnum.NAVIGATED_FOM_CP,true).log();
		}
	}

	private String mapIncomeTypeCode(String type) {
		if ("WK".equals(type)) {
			return APIConstants.INC_TYPE_WEEKLY;
		} else if ("AN".equals(type)) {
			return APIConstants.INC_TYPE_ANNUALLY;
		} else if ("MN".equals(type)) {
			return APIConstants.INC_TYPE_MONTHLY;
		}
		return "";
	}

	private String mapCounty(String cpCounty) {
		List<LookupBean> counties = LookupManager.getLocaleSpecificLookups(getLocale(), APIConstants.LOOKUP_COUNTIES);
		for (LookupBean county : counties) {
			if (StringUtils.trimToEmpty(county.getLabel()).equalsIgnoreCase(cpCounty)) {
				return county.getValue();
			}
		}
		return "";

	}

	private String getMappedLanguageCode(String lang) {
		if ("ES".equalsIgnoreCase(lang)) {
			return "es_US";
		} else
			return "en_US";
	}

	@Override
	protected String handleExecute() throws Exception {
		SessionController.setDisclaimerAcceptance(ACCEPTED);
		// TODO Auto-generated method stub
		return NEXT;
	}

	public String doNotAgree() throws Exception {
		// TODO Auto-generated method stub
		return "fail";
	}

	public String logout() {
		SessionController.clearSession();
		return PAGE;
	}

	public String getCds_income() {
		return cds_income;
	}

	public void setCds_income(String cds_income) {
		this.cds_income = cds_income;
	}

	public String getCds_incomeType() {
		return cds_incomeType;
	}

	public void setCds_incomeType(String cds_incomeType) {
		this.cds_incomeType = cds_incomeType;
	}

	public String getCds_county() {
		return cds_county;
	}

	public void setCds_county(String cds_county) {
		this.cds_county = cds_county;
	}

	public String getCds_year() {
		return cds_year;
	}

	public void setCds_year(String cds_year) {
		this.cds_year = cds_year;
	}

	public String getCds_userLanguage() {
		return cds_userLanguage;
	}

	public void setCds_userLanguage(String cds_userLanguage) {
		this.cds_userLanguage = cds_userLanguage;
	}

	public Household getCds_household() {
		return cds_household;
	}

	public void setCds_household(Household cds_household) {
		this.cds_household = cds_household;
	}

}
