/*
 * Author - admin.
 * v1.0
 * 2009
 */

package com.uttam.web.framework.struts;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ServletRedirectResult;
import org.apache.struts2.dispatcher.mapper.ActionMapper;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.views.util.UrlHelper;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.opensymphony.xwork2.util.reflection.ReflectionException;
import com.opensymphony.xwork2.util.reflection.ReflectionExceptionHandler;

/**
 * <!-- START SNIPPET: description -->
 * 
 * This extends Struts ServletActionRedirectResult for adding dynamic parameters to URL.
 * 
 * @see ActionMapper
 */
public class ActionRedirectResult extends ServletRedirectResult implements ReflectionExceptionHandler {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6774830858654750323L;

	/** The default parameter. */
	public static final String DEFAULT_PARAM = "actionName";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ActionRedirectResult.class);

	/** The action name. */
	protected String actionName;

	/** The namespace. */
	protected String namespace;

	/** The method. */
	protected String method;

	/** The supress empty parameters. */
	protected boolean supressEmptyParameters = false;

	/** The request parameters. */
	private Map<String, Object> requestParameters = new LinkedHashMap<String, Object>();

	/**
	 * Instantiates a new hix action redirect result.
	 */
	public ActionRedirectResult() {
		super();
	}

	/**
	 * Instantiates a new hix action redirect result.
	 * 
	 * @param actionName
	 *            the action name
	 */
	public ActionRedirectResult(String actionName) {
		this(null, actionName, null);
	}

	/**
	 * Instantiates a new hix action redirect result.
	 * 
	 * @param actionName
	 *            the action name
	 * @param method
	 *            the method
	 */
	public ActionRedirectResult(String actionName, String method) {
		this(null, actionName, method);
	}

	/**
	 * Instantiates a new hix action redirect result.
	 * 
	 * @param namespace
	 *            the namespace
	 * @param actionName
	 *            the action name
	 * @param method
	 *            the method
	 */
	public ActionRedirectResult(String namespace, String actionName, String method) {
		super(null);
		this.namespace = namespace;
		this.actionName = actionName;
		this.method = method;
	}

	/** The prohibited result param. */
	protected List<String> prohibitedResultParam = Arrays.asList(new String[] { DEFAULT_PARAM, "namespace", "method",
			"encode", "parse", "location", "prependServletContext", "supressEmptyParameters" });

	/**
	 * Execute the result as a redirect. Before the redirect is done, the parameters on the action class in
	 * actionRedirectParameters are added to the URL
	 * 
	 * @param invocation
	 *            the invocation
	 * 
	 * @throws Exception
	 *             the exception
	 * 
	 * @see com.opensymphony.xwork2.Result#execute(com.opensymphony.xwork2.ActionInvocation)
	 */
	public void execute(ActionInvocation invocation) throws Exception {
		actionName = conditionalParse(actionName, invocation);
		if (namespace == null) {
			namespace = invocation.getProxy().getNamespace();
		} else {
			namespace = conditionalParse(namespace, invocation);
		}
		if (method == null) {
			method = "";
		} else {
			method = conditionalParse(method, invocation);
		}

		String resultCode = invocation.getResultCode();
		if (resultCode != null) {
			ResultConfig resultConfig = invocation.getProxy().getConfig().getResults().get(resultCode);
			Map<String, String> resultConfigParams = resultConfig.getParams();
			addURLParametersFromConfig(invocation, resultConfigParams);
			// Custom code for adding parameters from hixBaseAction
			if (invocation.getAction() instanceof BaseAction) {
				Map<String, Object> urlParams = ((BaseAction) invocation.getAction()).getRedirectURLParameters();
				if (urlParams != null && !urlParams.isEmpty()) {
					addURLParameters(invocation, urlParams);
				}
			}
		}

		StringBuilder tmpLocation = new StringBuilder(actionMapper.getUriFromActionMapping(new ActionMapping(actionName,
				namespace, method, null)));
		ServletActionContext.getContext().getInstance(UrlHelper.class)
				.buildParametersString(requestParameters, tmpLocation, "&");

		setLocation(tmpLocation.toString());

		super.execute(invocation);
	}

	/**
	 * Adds the url parameters.
	 * 
	 * @param invocation
	 *            the invocation
	 * @param params
	 *            the params
	 * 
	 * @author admin Apr 3, 2009
	 */
	private void addURLParametersFromConfig(ActionInvocation invocation, Map<String, String> params) {
		for (Iterator<Map.Entry<String, String>> i = params.entrySet().iterator(); i.hasNext();) {
			Map.Entry<String, String> e = i.next();
			if (!prohibitedResultParam.contains(e.getKey())) {
				String potentialValue = e.getValue() == null ? "" : conditionalParse(e.getValue().toString(), invocation);
				if (!supressEmptyParameters || ((potentialValue != null) && (potentialValue.length() > 0))) {
					requestParameters.put(e.getKey().toString(), potentialValue);
				}
			}
		}
	}

	private void addURLParameters(ActionInvocation invocation, Map<String, Object> params) {
		for (Iterator<Map.Entry<String, Object>> i = params.entrySet().iterator(); i.hasNext();) {
			Map.Entry<String, Object> e = i.next();
			if (!prohibitedResultParam.contains(e.getKey())) {
				String potentialValue = e.getValue() == null ? "" : conditionalParse(e.getValue().toString(), invocation);
				if (!supressEmptyParameters || ((potentialValue != null) && (potentialValue.length() > 0))) {
					requestParameters.put(e.getKey().toString(), potentialValue);
				}
			}
		}
	}

	/**
	 * Sets the action name.
	 * 
	 * @param actionName
	 *            The name
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * Sets the namespace.
	 * 
	 * @param namespace
	 *            The namespace
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * Sets the method.
	 * 
	 * @param method
	 *            The method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Sets the supressEmptyParameters option.
	 * 
	 * @param supressEmptyParameters
	 *            the supress empty parameters
	 */
	public void setSupressEmptyParameters(boolean supressEmptyParameters) {
		this.supressEmptyParameters = supressEmptyParameters;
	}

	/**
	 * Adds a request parameter to be added to the redirect url.
	 * 
	 * @param key
	 *            The parameter name
	 * @param value
	 *            The parameter value
	 * 
	 * @return the hix action redirect result
	 */
	public ActionRedirectResult addParameter(String key, Object value) {
		requestParameters.put(key, String.valueOf(value));
		return this;
	}

	/**
	 * @param ex
	 * @author admin Nov 1, 2009
	 */
	public void handle(ReflectionException ex) {
		// Only log as debug as they are probably parameters to be appended to
		// the url
		LOG.debug(ex.getMessage(), ex);
	}

	@Override
	protected List<String> getProhibitedResultParams() {
		// TODO Auto-generated method stub
		return prohibitedResultParam;
	}

}
