package com.uttam.web.framework.struts;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.struts2.ServletActionContext;

import com.uttam.framework.common.model.UserIdentity;

public class SessionController {

	private static final String USER = "hixapi.session.user";
	private static final String PLAN_MANAGE_PLAN = "hixapi.session.plan_manage_plan";
	private static final String CARRIER_ICON = "hixapi.session.carrier_icon";
	private static final String CARRIER_ICON_PREVIEW = "hixapi.session.carrier_icon_preview";
	private static final String HOUSEHOLD = "hixapi.cds.session.household";
	private static final String ELIGIBILITY = "hixapi.cds.session.eligibility";

	private static final String SYSTEM_DATE = "hixapi.session.system_date";
	
	private static final String COMPARE_PLANS = "hixapi.session.prescreen.compare_plans";

	/*public static void setHousehold(Household h) {
		ServletActionContext.getRequest().getSession(true).setAttribute(HOUSEHOLD, h);
	}

	public static Household getHousehold() {
		if (ServletActionContext.getRequest().getSession() != null) {
			return (Household) ServletActionContext.getRequest().getSession().getAttribute(HOUSEHOLD);
		}
		return null;
	}*/
	
	
	public static void setComparePlans(HashMap<String, String> map) {
		ServletActionContext.getRequest().getSession(true).setAttribute(COMPARE_PLANS, map);
	}

	
	public static Date getSystemDate() {
		return (Date) ServletActionContext.getRequest().getSession(true).getAttribute(SYSTEM_DATE);
	}

	public static void setSystemDate(Date p) {
		ServletActionContext.getRequest().getSession(true).setAttribute(SYSTEM_DATE, p);
	}

	public static void setUser(UserIdentity u) {
		ServletActionContext.getRequest().getSession(true).setAttribute(USER, u);
	}

	public static UserIdentity getUser() {
		return (UserIdentity) ServletActionContext.getRequest().getSession(true).getAttribute(USER);
	}

	public static void setLanguage(Locale language) {
		ServletActionContext.getRequest().getSession(true)
				.setAttribute(UttamI18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, language);

	}

	public static Locale getLanguage() {
		if (ServletActionContext.getRequest().getSession() != null) {
			return (Locale) ServletActionContext.getRequest().getSession()
					.getAttribute(UttamI18nInterceptor.DEFAULT_SESSION_ATTRIBUTE);
		}
		return null;
	}

	public static void clearSession() {
		if (ServletActionContext.getRequest().getSession() != null) {
			ServletActionContext.getRequest().getSession().removeAttribute(UttamI18nInterceptor.DEFAULT_SESSION_ATTRIBUTE);
			ServletActionContext.getRequest().getSession().removeAttribute(CARRIER_ICON);
			ServletActionContext.getRequest().getSession().removeAttribute(CARRIER_ICON_PREVIEW);
			ServletActionContext.getRequest().getSession().removeAttribute(USER);
			ServletActionContext.getRequest().getSession().removeAttribute(SYSTEM_DATE);
			ServletActionContext.getRequest().getSession().removeAttribute(PLAN_MANAGE_PLAN);
			ServletActionContext.getRequest().getSession().removeAttribute(ELIGIBILITY);
			ServletActionContext.getRequest().getSession().removeAttribute(HOUSEHOLD);
		}

	}

	public static byte[] getCarrierIcon() {
		return (byte[]) ServletActionContext.getRequest().getSession(true).getAttribute(CARRIER_ICON);
	}

	public static void setCarrierIcon(byte[] p) {
		ServletActionContext.getRequest().getSession(true).setAttribute(CARRIER_ICON, p);
	}

	public static byte[] getCarrierIconPreview() {
		return (byte[]) ServletActionContext.getRequest().getSession(true).getAttribute(CARRIER_ICON_PREVIEW);
	}

	public static void setCarrierIconPreview(byte[] p) {
		ServletActionContext.getRequest().getSession(true).setAttribute(CARRIER_ICON_PREVIEW, p);
	}

}
