/*
 * Copyright (c) 2002-2006 by OpenSymphony
 * All rights reserved.
 */
package com.uttam.web.framework.struts;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import com.opensymphony.xwork2.ognl.OgnlValueStack;
import com.uttam.framework.common.APIConstants;

/**
 * <!-- START SNIPPET: description --> Flash interceptor ({@link FlashInterceptor}) possibly with {@link FlashResult} allows
 * current action to be available even after a redirect. It does this by saving the current action into http session and
 * pushing it back into the stack next request, resulting in the nett effect of the action and its related information being
 * available across redirect. <!-- END SNIPPET: description -->
 * 
 * 
 * <!-- START SNIPPET: parameters -->
 * <ul>
 * <li>key - The Http Session key under which the action will be stored, default to {@link FlashInterceptor#DEFAULT_KEY}
 * which is the string '__flashAction'.</li>
 * <li>operation - The operation mode of this interceptor, either {@link FlashInterceptor#STORE} having a string value of
 * 'Store' or {@link FlashInterceptor#RETRIEVE} having a string value of 'Retrieve' The default operation mode is
 * {@link FlashInterceptor#RETRIEVE}</li>
 * </ul>
 * <!-- END SNIPPET: parameters -->
 * 
 * 
 * <!-- START SNIPPET: extending --> There's no intended extension points <!-- END SNIPPET: extending -->
 * 
 * 
 * <pre>
 * &lt;!-- START SNIPPET: example --&gt;
 * &lt;!-- Usage 1: (Using only Flash interceptor)  --&gt;
 * &lt;action name=&quot;store&quot; ...&gt;
 * &lt;interceptor-ref name=&quot;flash&quot;&gt;
 * &lt;param name=&quot;operation&quot;&gt;Store&lt;/param&gt;
 * &lt;/interceptor-ref&gt;
 * &lt;interceptor-ref name=&quot;defaultStack&quot; /&gt;
 * &lt;result type=&quot;redirect&quot;&gt;redirectToSomeWhere.jsp&lt;/result&gt;
 * &lt;/action&gt;
 * &lt;action name=&quot;retrieve&quot;&gt;
 * &lt;interceptor-ref name=&quot;flash&quot;&gt;
 * &lt;param name=&quot;operation&quot;&gt;Retrieve&lt;/param&gt;
 * &lt;/interceptor-ref&gt;
 * &lt;interceptor-ref name=&quot;defaultStack&quot; /&gt;
 * &lt;result&gt;pageWhereWeNeedFlashActionStored.jsp&lt;/result&gt;
 * &lt;/action&gt;
 * 
 * 
 * &lt;!-- Usage 2: (Using Flash Interceptor and Flash Result) --&gt;
 * &lt;action name=&quot;store&quot;&gt;
 * &lt;result type=&quot;flash&quot;&gt;redirectToSomeWhere.jsp&lt;/result&gt;
 * &lt;/action&gt;
 * &lt;action name=&quot;retrieve&quot;&gt;
 * &lt;interceptor-ref name=&quot;flash&quot;&gt;
 * &lt;param name=&quot;operation&quot;&gt;Retrieve&lt;/param&gt;
 * &lt;/interceptor-ref&gt;
 * &lt;interceptor-ref name=&quot;defaultStack&quot; /&gt;
 * &lt;result&gt;pageWhereWeNeedFlashActionStored.jsp&lt;/result&gt;
 * &lt;/action&gt;
 * 
 * &lt;!-- END SNIPPET: example --&gt;
 * </pre>
 * 
 * @author admin
 * @version $Date: 2009-02-15
 */
public class FlashInterceptor extends AroundInterceptor {

	/** The Constant DEFAULT_KEY. */
	public static final String DEFAULT_KEY = "__flashAction";

	/** The Constant EXPR_OVERRIDES. */
	public static final String EXPR_OVERRIDES = "__exprOverrides";

	/** The Constant STORE. */
	public static final String STORE = "Store";

	/** The Constant RETRIEVE. */
	public static final String RETRIEVE = "Retrieve";

	/** The Constant LOG. */
	private static final Logger LOG = LogManager.getLogger(FlashInterceptor.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9200319895107209641L;

	/** The Constant EXCEPTION. */
	public static final String EXCEPTION = "__exceptionHolder";

	/** The key. */
	private String key = DEFAULT_KEY;

	/** The operation. */
	private String operation = RETRIEVE;

	/**
	 * Sets the key.
	 * 
	 * @param key
	 *            the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operation
	 *            the new operation
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * Gets the operation.
	 * 
	 * @return the operation
	 */
	public String getOperation() {
		return this.operation;
	}

	/**
	 * After.
	 * 
	 * @param invocation
	 *            the invocation
	 * @param result
	 *            the result
	 * 
	 * @throws Exception
	 *             the exception
	 * 
	 * @see com.opensymphony.xwork.interceptor.AroundInterceptor#after(com.opensymphony.xwork.ActionInvocation,
	 *      java.lang.String)
	 */
	protected void after(ActionInvocation invocation, String result) throws Exception {
	}

	/**
	 * Before.
	 * 
	 * @param invocation
	 *            the invocation
	 * 
	 * @throws Exception
	 *             the exception
	 * 
	 * @see com.opensymphony.xwork.interceptor.AroundInterceptor#before(com.opensymphony.xwork.ActionInvocation)
	 */
	protected void before(ActionInvocation invocation) throws Exception {
		LOG.entry();
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		if (sessionMap.get(key) != null) {
			final Object action = sessionMap.get(key);

			@SuppressWarnings("unchecked")
			final Map<Object, Object> overrides = (Map<Object, Object>) sessionMap.get(EXPR_OVERRIDES);
			final Object exception = sessionMap.get(EXCEPTION);
			sessionMap.remove(key);
			sessionMap.remove(EXPR_OVERRIDES);
			sessionMap.remove(EXCEPTION);
			invocation.getStack().set(APIConstants.FLASH_REQUEST_INDICATOR, true);
			invocation.addPreResultListener(new PreResultListener() {
				public void beforeResult(ActionInvocation invocation, String resultCode) {
					OgnlValueStack stack = (OgnlValueStack) ActionContext.getContext().get(ActionContext.VALUE_STACK);
					stack.push(action);
					if (exception != null) {
						stack.push(exception);
					}
					stack.setExprOverrides((Map<Object, Object>) overrides);
				}
			});

		}

		// if (STORE.equalsIgnoreCase(operation)) {
		// invocation.addPreResultListener(new PreResultListener() {
		// public void beforeResult(ActionInvocation invocation, String
		// resultCode) {
		// Map sessionMap = ActionContext.getContext()
		// .getSession();
		// Object action = invocation.getAction();
		// if (LOG.isDebugEnabled())
		// LOG.debug("inserting action [" + action + "] into session with key ["
		// + key + "]");
		// sessionMap.put(key, action);
		// }
		// });
		// }

		// if (RETRIEVE.equalsIgnoreCase(operation)) {

		// }
	}

}
