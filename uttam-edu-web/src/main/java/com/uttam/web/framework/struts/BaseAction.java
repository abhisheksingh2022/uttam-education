package com.uttam.web.framework.struts;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import com.opensymphony.xwork2.util.ValueStack;
import com.uttam.education.api.model.BusinessServiceErrorVO;
import com.uttam.framework.common.APIConstants;
import com.uttam.framework.common.SanitizerUtil;
import com.uttam.framework.common.env.IEnvironmentProvider;
import com.uttam.framework.common.exception.BusinessServiceException;
import com.uttam.framework.common.exception.UnauthorizedAccessException;
import com.uttam.framework.common.model.BaseException;
import com.uttam.framework.common.model.LookupBean;
import com.uttam.framework.common.model.UserIdentity;
import com.uttam.framework.common.model.UserMessage;
import com.uttam.framework.service.ServiceLocator;
import com.uttam.web.framework.service.LookupService;

public abstract class BaseAction extends ActionSupport implements RequestAware, ParameterNameAware, ParameterAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2170179663275009712L;

	public static final String ASYNCH_ERROR = "asynchError";
	public static final String FRAMEWORK_ERROR = "frameworkError";
	public static final String NOT_FOUND = "notfound";
	public static final String AJAX_SUCCESS = "ajax-simple-success";

	public List<String> errorFieldNames;

	private String formId;

	private Map<String, Object> redirectURLParameters = new LinkedHashMap<String, Object>();

	private Collection<UserMessage> businessMessages;

	/** The session. */
	private Map<String, Object> session;

	/** The request. */
	private Map<String, Object> request;

	/** The parameters. */
	private Map<String, String[]> parameters;

	/** The on error result. */
	private String onErrorResult = Action.INPUT;

	/** The Constant BACK. */
	protected static final String BACK = "back";

	/** The Constant NEXT. */
	protected static final String NEXT = "next";

	/** The Constant NONE. */
	protected static final String NONE = "NONE";

	/** The Constant PAGE. */
	protected static final String PAGE = "page";

	protected static final String EXIT = "exit";

	protected static final String FLASH = "flash";

	protected static final String UNAUTHORIZED = "unauthorized";

	//Set as true in the beginning of methods processing ajaxCalls in action classes
	private boolean ajaxRequest;

	private boolean secureAction;

	private boolean isInternationalized;

	protected abstract void loadDisplayContents() throws Exception;

	protected abstract String handleExecute() throws Exception;

	public final String input() throws Exception {
		try {
			loadDisplayContents();
			return PAGE;
		} catch (UnauthorizedAccessException e) {
			return UNAUTHORIZED;
		}

	}

	public final String execute() throws Exception {
		String returnVal = handleExecute();
		if (FLASH.equals(returnVal)) {
		}
		return returnVal;
	}

	/**
	 * method used by flash results. Default implementation invokes input() method to set the action context and returns JSP
	 * result. Can be overridden by sub classes if required.
	 *
	 * @return the string
	 *
	 * @throws Exception
	 *             the exception
	 */
	public final String flash() throws Exception {
		// Default, calls input method - objects should be set correctly in case
		try {
			Boolean prgRequest = (Boolean) ActionContext.getContext().getActionInvocation().getStack()
					.findValue(APIConstants.FLASH_REQUEST_INDICATOR);
			if (prgRequest == null || !prgRequest) {
				LOG.debug("Not a PRG Request, calling input()");
				loadDisplayContents();
			}
			return PAGE;
		} catch (UnauthorizedAccessException e) {
			return UNAUTHORIZED;
		}
	}

	/**
	 * Exit.
	 *
	 * @return the string
	 *
	 * @throws Exception
	 *             the exception
	 */
	public String exit() throws Exception {
		// do session clearing etc

		return EXIT;
	}

	public void validate() {
		String method = ActionContext.getContext().getActionInvocation().getProxy().getMethod();
		String methodName = "validate_" + method;
		try {
			Method validationMethod = getClass().getMethod(methodName);
			validationMethod.invoke(this);

			if (hasMessages()) {
				onValidationError();
			}

		} catch (NoSuchMethodException nmex) {
			//no-op - ignore it
		} catch (Exception ex) {
			throw new BaseException("Failed to execute validations", ex);
		}

	}

	/**
	 * This method can be overridden by sub classes to reload any missing data fields which is usually set up during screen
	 * load (loadDisplayContents()), and is missing after form submit but is required for screen display
	 */
	public void onValidationError() throws Exception {

	}

	public boolean isInternationalized() {
		return isInternationalized;
	}

	public void setInternationalized(boolean isInternationalized) {
		this.isInternationalized = isInternationalized;
	}

	private UserIdentity userProfile;

	private boolean readOnly;

	public Map<String, Object> getRedirectURLParameters() {
		return redirectURLParameters;
	}

	public void setRedirectURLParameters(Map<String, Object> redirectURLParameters) {
		this.redirectURLParameters = redirectURLParameters;
	}

	public List<String> getErrorFieldNames() {
		return errorFieldNames;
	}

	protected void addValidationError(String msgKey, String... fieldName) {
		Validate.notNull(fieldName);
		Validate.notBlank(msgKey);
		if (this.errorFieldNames == null) {
			this.errorFieldNames = new ArrayList<String>();
		}
		for (String field : fieldName) {
			field = StringUtils.replace(field, ".", "\\\\.");
			this.errorFieldNames.add(field);
		}
		addActionError(getText(msgKey));
	}

	protected void addValidationError(String msgKey, String[] replacements, String... fieldName) {
		Validate.notNull(fieldName);
		Validate.notBlank(msgKey);
		if (this.errorFieldNames == null) {
			this.errorFieldNames = new ArrayList<String>();
		}
		for (String field : fieldName) {
			field = StringUtils.replace(field, ".", "\\\\.");
			this.errorFieldNames.add(field);
		}
		addActionError(getText(msgKey, replacements));
	}

	/**
	 * @return the formId
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * @param formId
	 *            the formId to set
	 */
	public void setFormId(String formName) {
		this.formId = formName;
	}

	public boolean isAjaxRequest() {
		return ajaxRequest;
	}

	public void setAjaxRequest(boolean ajaxRequest) {
		this.ajaxRequest = ajaxRequest;
	}

	/**
	 * @param arg0
	 * @author admin Nov 1, 2009
	 */
	public void setSession(Map<String, Object> arg0) {
		session = arg0;

	}

	/**
	 * Gets the lookups. - using LookupManager. Used for displaying lookup content as comboboxes/radio buttons in screens for
	 * a specific locale
	 *
	 * @return the lookups
	 */
	/*public Map<String, List<LookupBean>> getLookups() {
		return LookupService.getLocaleSpecificLookups(ActionContext.getContext().getLocale());
	}

	public Map<String, List<LookupBean>> getLookups(Locale l) {
		return LookupService.getLocaleSpecificLookups(l);
	}*/

	/**
	 * @param arg0
	 * @author admin Nov 1, 2009
	 */
	public void setRequest(Map<String, Object> arg0) {
		request = arg0;

	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	public Map<String, Object> getRequest() {
		return request;
	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * Workaround to filter out image click co-ordinate parameters from request.
	 * 
	 * @param arg0
	 * @return
	 * @author admin Nov 1, 2009
	 */
	public boolean acceptableParameterName(String arg0) {
		if (arg0.endsWith(".x") || arg0.endsWith(".y")) {
			return false;
		}
		return true;
	}

	/**
	 * The method santizes the parameters and sets on the action class
	 * 
	 * @param arg0
	 * @author admin Nov 1, 2009
	 */
	public void setParameters(Map<String, String[]> arg0) {
		parameters = SanitizerUtil.trimArrayMap(arg0);

	}

	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public Map<String, String[]> getParameters() {
		return parameters;
	}

	/**
	 * Sets the user profile.
	 *
	 * @param userProfile
	 *            the user profile
	 *
	 * @author admin Mar 12, 2009
	 */
	public void setUserProfile(UserIdentity userProfile) {
		this.userProfile = userProfile;

	}

	/**
	 * Gets the user profile.
	 *
	 * @return the userProfile
	 *
	 * @author admin Mar 12, 2009
	 */
	public UserIdentity getUserProfile() {
		return userProfile;
	}

	/**
	 * Gets the business messages.
	 *
	 * @return the businessMessages
	 *
	 * @author admin Mar 16, 2009
	 */
	public Collection<UserMessage> getBusinessMessages() {
		return businessMessages;
	}

	/**
	 * Sets the business messages.
	 *
	 * @param businessMessages
	 *            the businessMessages to set
	 *
	 * @author admin Mar 16, 2009
	 */
	public void setBusinessMessages(Collection<UserMessage> businessMessages) {
		this.businessMessages = businessMessages;
	}

	/**
	 * Adds the business message.
	 *
	 * @param message
	 *            the message
	 */
	public void addBusinessMessage(UserMessage message, String... fieldName) {
		if (this.businessMessages == null) {
			this.businessMessages = new ArrayList<UserMessage>();
		}
		this.businessMessages.add(message);
		if (fieldName != null && fieldName.length > 0) {
			for (String field : fieldName) {
				if (StringUtils.isNotBlank(field)) {
					this.errorFieldNames.add(field);
				}
			}
		}
	}

	public boolean hasMessages() {
		if (hasActionErrors() || hasActionMessages() || (this.businessMessages != null && this.businessMessages.size() > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the redirect url parameters.
	 *
	 * @return the redirectURLParameters
	 *
	 * @author admin Apr 3, 2009
	 */

	/**
	 * Adds a redirect url parameter.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void addRedirectURLParameter(String key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException("Redirect parameter key cannot be null");
		}
		if (this.redirectURLParameters.containsKey(key)) {
			LOG.warn("Replacing existing parameter in 'redirectURLParameters'. Key: " + key + " of value '"
					+ redirectURLParameters.get(key) + "' with value '" + value + "'");
			;
		}
		//    	Enumeration<String> params = PortletActionContext.getPortletConfig().getPublicRenderParameterNames();
		//    	boolean found = false;
		//    	if(params != null){
		//    		String aParam = null;
		//    		while(params.hasMoreElements()){
		//    			aParam = params.nextElement();
		//    			if(key.equals(aParam)){
		//    				found = true;
		//    			}
		//    		}
		//    	}
		//    	if(!found){
		//    		throw new UnsupportedOperationException
		//    		("Redirect Param(Public Render Parameter) not defined in portlet configuration : "+key);
		//    	}
		this.redirectURLParameters.put(key, value);
	}

	/**
	 * Gets the next navigation.
	 *
	 * @return the next navigation
	 *
	 * @throws Exception
	 *             the exception
	 */
	public String getNextNavigation() throws Exception {
		throw new UnsupportedOperationException("Sub class should implement getNextNavigation()");
	}

	/**
	 * Gets the back navigation.
	 *
	 * @return the back navigation
	 *
	 * @throws Exception
	 *             the exception
	 */
	public String getBackNavigation() throws Exception {
		throw new UnsupportedOperationException("Sub class should implement getNextNavigation()");
	}

	/**
	 * Gets the combobox header label.
	 *
	 * @return the combobox header label
	 */
	public String getSelectBoxHeader() {
		return getText("txt.global.select_default");
	}

	public String getSelectAllBoxHeader() {
		return getText("txt.global.all_default");
	}

	/**
	 * Checks if is secure action.
	 *
	 * @return the secureAction
	 *
	 * @author admin May 17, 2009
	 */
	public boolean isSecureAction() {
		return secureAction;
	}

	/**
	 * Sets the secure action.
	 *
	 * @param secureAction
	 *            the secureAction to set
	 *
	 * @author admin May 17, 2009
	 */
	public void setSecureAction(boolean secureAction) {
		this.secureAction = secureAction;
	}

	/**
	 * Gets the on error result.
	 *
	 * @return the on error result
	 */
	public String getOnErrorResult() {
		return onErrorResult;
	}

	/**
	 * Gets the lookup display for a specific locale.
	 *
	 * @param lookupName
	 *            the lookup name
	 * @param key
	 *            the key
	 *
	 * @return the lookup display
	 */
	/*public String getLookupDisplay(String lookupName, String key) {
		return LookupService.getLookupDisplayValue(ActionContext.getContext().getLocale(), lookupName, key);
	}*/

	/**
	 * Gets the sanitized text for rendering user messages
	 * 
	 * @param aTextName
	 * @param args
	 * @return
	 * @author admin Nov 1, 2009
	 */
	@Override
	public String getText(String aTextName, List<?> args) {
		List<Object> escapedArgs = new ArrayList<Object>();
		if (CollectionUtils.isNotEmpty(args)) {
			for (Object arg : args) {
				//The method StringEscapeUtils.escapeJavaScript causing problems to special characters of non-english languages, converting splchars to unicode chars
				//escapedArgs.add(StringEscapeUtils.escapeJavaScript(arg==null?"":arg.toString()));
				escapedArgs.add(arg == null ? "" : arg.toString());
			}
		}

		return super.getText(aTextName, escapedArgs);
	}

	/**
	 * Gets the sanitized text for rendering user messages
	 * 
	 * @param key
	 * @param defaultValue
	 * @param args
	 * @param stack
	 * @return
	 * @author admin Nov 1, 2009
	 */
	@Override
	public String getText(String key, String defaultValue, List<?> args, ValueStack stack) {
		List<Object> escapedArgs = new ArrayList<Object>();
		if (CollectionUtils.isNotEmpty(args)) {
			for (Object arg : args) {
				//The method StringEscapeUtils.escapeJavaScript causing problems to special characters of non-english languages, converting splchars to unicode chars
				//escapedArgs.add(StringEscapeUtils.escapeJavaScript(arg==null?"":arg.toString()));
				escapedArgs.add(arg == null ? "" : arg.toString());
			}
		}
		//return super.getText(key, StringEscapeUtils.escapeJavaScript(defaultValue), escapedArgs, stack);
		return super.getText(key, defaultValue, escapedArgs, stack);
	}

	/**
	 * Gets the sanitized text for rendering user messages
	 * 
	 * @param aTextName
	 * @param defaultValue
	 * @param args
	 * @return
	 * @author admin Nov 1, 2009
	 */
	public String getText(String aTextName, String defaultValue, List<?> args) {
		List<Object> escapedArgs = new ArrayList<Object>();
		if (CollectionUtils.isNotEmpty(args)) {
			for (Object arg : args) {
				//The method StringEscapeUtils.escapeJavaScript causing problems to special characters of non-english languages, converting splchars to unicode chars
				//escapedArgs.add(StringEscapeUtils.escapeJavaScript(arg==null?"":arg.toString()));
				escapedArgs.add(arg == null ? "" : arg.toString());
			}
		}
		//return super.getText(aTextName, StringEscapeUtils.escapeJavaScript(defaultValue), escapedArgs);
		return super.getText(aTextName, defaultValue, escapedArgs);
	}

	/**
	 * Gets the sanitized text for rendering user messages
	 * 
	 * @param aTextName
	 * @param defaultValue
	 * @param obj
	 * @return
	 * @author admin Nov 1, 2009
	 */
	@Override
	public String getText(String aTextName, String defaultValue, String obj) {
		// TODO Auto-generated method stub
		//The method StringEscapeUtils.escapeJavaScript causing problems to special characters of non-english languages, converting splchars to unicode chars
		//return super.getText(aTextName, defaultValue, StringEscapeUtils.escapeJavaScript(obj));
		return super.getText(aTextName, defaultValue, obj);
	}

	/**
	 * Gets the sanitized text for rendering user messages
	 * 
	 * @param key
	 * @param defaultValue
	 * @param args
	 * @param stack
	 * @return
	 * @author admin Nov 1, 2009
	 */
	@Override
	public String getText(String key, String defaultValue, String[] args, ValueStack stack) {
		List<Object> escapedArgs = new ArrayList<Object>();
		if (!ArrayUtils.isEmpty(args)) {
			for (String arg : args) {
				//The method StringEscapeUtils.escapeJavaScript causing problems to special characters of non-english languages, converting splchars to unicode chars
				//escapedArgs.add(StringEscapeUtils.escapeJavaScript(arg));
				escapedArgs.add(arg);
			}
		}
		return super.getText(key, defaultValue, escapedArgs, stack);
	}

	/**
	 * Gets the sanitized text for rendering user messages
	 * 
	 * @param key
	 * @param defaultValue
	 * @param args
	 * @return
	 * @author admin Nov 1, 2009
	 */
	@Override
	public String getText(String key, String defaultValue, String[] args) {
		List<Object> escapedArgs = new ArrayList<Object>();
		if (!ArrayUtils.isEmpty(args)) {
			for (String arg : args) {
				//The method StringEscapeUtils.escapeJavaScript causing problems to special characters of non-english languages, converting splchars to unicode chars
				//escapedArgs.add(StringEscapeUtils.escapeJavaScript(arg));
				escapedArgs.add(arg);
			}
		}
		return super.getText(key, defaultValue, escapedArgs);
	}

	/**
	 * Gets the sanitized text for rendering user messages
	 * 
	 * @param key
	 * @param args
	 * @return
	 * @author admin Nov 1, 2009
	 */
	@Override
	public String getText(String key, String[] args) {
		List<Object> escapedArgs = new ArrayList<Object>();
		if (!ArrayUtils.isEmpty(args)) {
			for (String arg : args) {
				//The method StringEscapeUtils.escapeJavaScript causing problems to special characters of non-english languages, converting splchars to unicode chars
				//escapedArgs.add(StringEscapeUtils.escapeJavaScript(arg));
				escapedArgs.add(arg);
			}
		}
		return super.getText(key, escapedArgs);
	}

	/**
	 * Sets the on error result.
	 *
	 * @param onErrorResult
	 *            the new on error result
	 */
	public void setOnErrorResult(String onErrorResult) {
		this.onErrorResult = onErrorResult;
	}

	public String getEnvironment() {
		return ServiceLocator.getInstance().getService(IEnvironmentProvider.class).getEnvironment();
	}

	public Object replaceApostrophe(Object s1) {
		if (s1 == null)
			return null;
		if (s1 instanceof String) {
			String s = (String) s1;
			return s.contains("'") ? s.replace("'", "&#39;") : s;
		}
		return s1;
	}

	public String getContextRoot() {
		String contextPath = ServletActionContext.getRequest().getContextPath();
		return contextPath + "/";
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public void addBusinessServiceErrorMessage(BusinessServiceException bex) {
		if (bex != null) {
			if (bex.getErrorIdNbr() == null) {
				Long id = WebUtil.logExceptionToDatabase(bex, this);
				bex.setErrorIdNbr(id);
			}
			if (CollectionUtils.isNotEmpty(bex.getErrorList())) {
				for (BusinessServiceErrorVO vo : bex.getErrorList()) {
					final UserMessage userMessage = new UserMessage(vo.getSeverity(), vo.getErrorKey(),
							vo.getSubstitutions(), null);
					userMessage.setErrorIdNbr(bex.getErrorIdNbr());
					addBusinessMessage(userMessage);
				}
			}
		}
	}

	public void addDefaultRedirectParameters() {
		//no-op, sub classes can override
	}

	public String getEnvProperty(String group, String code) {
		return ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
				.getPropertyInGroup(group, code, String.class, "");
	}

	public boolean isProduction() {
		return ServiceLocator.getInstance().getService(IEnvironmentProvider.class).isProduction();
	}

	public String getBuildId() {
		return getEnvProperty(APIConstants.PROPGROUP_GLOBAL, APIConstants.PROPKEY_BUILD_ID);
	}

	

}
