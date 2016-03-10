package com.hixapi.web.framework.struts;

import java.util.Locale;

import org.apache.struts2.ServletActionContext;

import com.hixapi.ahct.cds.model.Eligibility;
import com.hixapi.ahct.cds.model.Household;
import com.hixapi.ahct.cds.model.MedicalCost;
import com.hixapi.ahct.cds.model.plan.PlanContainer;
import com.hixapi.web.framework.common.UserIdentity;

public class SessionController {

	private static final String DISCLAIMER = "hisapi.session.disclaimer";
	private static final String USER = "hixapi.session.user";
	private static final String HOUSEHOLD = "hixapi.cds.session.household";
	private static final String PLANCONTAINER = "hixapi.cds.session.planContainer";
	private static final String ELIGIBILITY = "hixapi.cds.session.eligibility";
	private static final String MEDICAL_COST = "hixapi.cds.session.medicalcost";
	private static final String IGNORE_REFERRER_LANG = "hixapi.cds.session.ignore_referrer_lang";

	public static String getDisclaimerAcceptance() {
		if (ServletActionContext.getRequest().getSession() != null) {
			return (String) ServletActionContext.getRequest().getSession().getAttribute(DISCLAIMER);
		}
		return null;
	}

	public static void setDisclaimerAcceptance(String accepted) {
		ServletActionContext.getRequest().getSession(true).setAttribute(DISCLAIMER, accepted);
	}

	public static void setUser(UserIdentity u) {
		ServletActionContext.getRequest().getSession(true).setAttribute(USER, u);
	}

	public static UserIdentity getUser() {
		if (ServletActionContext.getRequest().getSession() != null) {
			return (UserIdentity) ServletActionContext.getRequest().getSession().getAttribute(USER);
		}
		return null;
	}

	public static void setHousehold(Household h) {
		ServletActionContext.getRequest().getSession(true).setAttribute(HOUSEHOLD, h);
	}

	public static Household getHousehold() {
		if (ServletActionContext.getRequest().getSession() != null) {
			return (Household) ServletActionContext.getRequest().getSession().getAttribute(HOUSEHOLD);
		}
		return null;
	}

	public static void setPlanContainer(PlanContainer p) {
		ServletActionContext.getRequest().getSession(true).setAttribute(PLANCONTAINER, p);
	}

	public static PlanContainer getPlanContainer() {
		if (ServletActionContext.getRequest().getSession() != null) {
			return (PlanContainer) ServletActionContext.getRequest().getSession().getAttribute(PLANCONTAINER);
		}
		return null;
	}

	public static void setEligibility(Eligibility e) {
		ServletActionContext.getRequest().getSession(true).setAttribute(ELIGIBILITY, e);
	}

	public static Eligibility getEligibility() {
		if (ServletActionContext.getRequest().getSession() != null) {
			return (Eligibility) ServletActionContext.getRequest().getSession(true).getAttribute(ELIGIBILITY);
		}
		return null;
	}

	public static void setMedicalCost(MedicalCost e) {
		ServletActionContext.getRequest().getSession(true).setAttribute(MEDICAL_COST, e);
	}

	public static MedicalCost getMedicalCost() {
		if (ServletActionContext.getRequest().getSession() != null) {
			return (MedicalCost) ServletActionContext.getRequest().getSession().getAttribute(MEDICAL_COST);
		}
		return null;
	}

	public static void setLanguage(Locale language) {
		ServletActionContext.getRequest().getSession(true)
				.setAttribute(HIXI18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, language);

	}

	public static Locale getLanguage() {
		if (ServletActionContext.getRequest().getSession() != null) {
			return (Locale) ServletActionContext.getRequest().getSession()
					.getAttribute(HIXI18nInterceptor.DEFAULT_SESSION_ATTRIBUTE);
		}
		return null;
	}

	public static void clearSession() {
		if(ServletActionContext.getRequest().getSession() != null){
			ServletActionContext.getRequest().getSession().removeAttribute(MEDICAL_COST);
			ServletActionContext.getRequest().getSession().removeAttribute(ELIGIBILITY);
			ServletActionContext.getRequest().getSession().removeAttribute(PLANCONTAINER);
			ServletActionContext.getRequest().getSession().removeAttribute(HOUSEHOLD);
			ServletActionContext.getRequest().getSession().removeAttribute(DISCLAIMER);
		}
		
	}

	public static boolean honorReferrerLanguage() {
		boolean retVal = true;
		if (ServletActionContext.getRequest().getSession() != null) {
			String setVal = (String) ServletActionContext.getRequest().getSession()
			.getAttribute(IGNORE_REFERRER_LANG);
			if("true".equals(setVal)){
				retVal = false;
			}
		}
		return retVal;
	}
	public static void setIgnoreReferrerLanguage(){
		ServletActionContext.getRequest().getSession(true).setAttribute(IGNORE_REFERRER_LANG, "true");
	}
}
