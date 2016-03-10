package com.uttam.web.framework.struts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * An abstract interceptor that provides simple access to before/after callouts.
 * 
 * @author admin
 */
public abstract class AroundInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -247636433073756813L;
	/** The log. */
	protected transient Logger log = LogManager.getLogger(AroundInterceptor.class);

	/**
	 * 
	 * @author admin Nov 1, 2009
	 */
	public void destroy() {
	}

	/**
	 * 
	 * @author admin Nov 1, 2009
	 */
	public void init() {
	}

	/**
	 * @param invocation
	 * @return
	 * @throws Exception
	 * @author admin Nov 1, 2009
	 */
	public String intercept(ActionInvocation invocation) throws Exception {

		before(invocation);
		String result = invocation.invoke();
		after(invocation, result);
		return result;
	}

	/**
	 * Called after the invocation has been executed.
	 * 
	 * @param result
	 *            the result value returned by the invocation
	 * @param dispatcher
	 *            the dispatcher
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected abstract void after(ActionInvocation dispatcher, String result) throws Exception;

	/**
	 * Called before the invocation has been executed.
	 * 
	 * @param invocation
	 *            the invocation
	 * 
	 * @throws Exception
	 *             the exception
	 */
	protected abstract void before(ActionInvocation invocation) throws Exception;
}
