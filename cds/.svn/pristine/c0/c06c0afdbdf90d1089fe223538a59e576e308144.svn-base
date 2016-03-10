/**
 * 
 */
package com.hixapi.web.framework.struts;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.TokenInterceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.util.TextParseUtil;

/**
 * @author admin
 *
 */
public class HIXTokenInterceptor extends TokenInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2169663530164397337L;
	
	private Set<String> ignoreActionsSet = new HashSet<String>();
	
	 public void setIgnoreActions(String ignoreActions) {
	        this.ignoreActionsSet = TextParseUtil.commaDelimitedStringToSet(ignoreActions);
	    }
	
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String method = ServletActionContext.getRequest().getMethod();
		String action = ServletActionContext.getRequest().getServletPath();
		log.debug("Current Action is : "+ action);
		if(("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method))
				&&
		!this.ignoreActionsSet.contains(action)
		){
			
			return super.doIntercept(invocation);
		}
		
		
		return invocation.invoke();
	}

	private static final String INVALID_TOKEN_MESSAGE_KEY = "msg.global.invalid_token";

	@Override
	protected String handleInvalidToken(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		String errorMessage = getErrorMessage(invocation);

		if (action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(errorMessage);
		} else {
			log.warn(errorMessage);
		}

		return BaseAction.FLASH;
	}

	protected String getErrorMessage(ActionInvocation invocation) {
		Object action = invocation.getAction();
		if (action instanceof BaseAction) {
			return ((BaseAction) action)
					.getText(INVALID_TOKEN_MESSAGE_KEY, "Please do not click the buttons more than once");
		}
		return "Please do not click the buttons more than once";
	}

}
