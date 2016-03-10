package com.hixapi.web.framework.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class PerformanceInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 505984847239667466L;

	private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(PerformanceInterceptor.class);

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		String result = null;
		long start = System.currentTimeMillis();
		StringBuilder builder = new StringBuilder("Request received:");
		builder.append(request.getServletPath()).append(':').append(request.getMethod());
		log.info(builder.toString());
		if (log.isDebugEnabled()) {
			log.debug("Query String:{}", request.getQueryString());
			// HIXLog4JUtil.log(log, Level.DEBUG,
			// String.format("Request parameters:%s:%s:%s",
			// request.getServletPath(), request.getMethod(),
			// request.getQueryString()))
		}
		try {
			result = invocation.invoke();
		} finally {
			StringBuilder actionBuilder = new StringBuilder("ActionDuration:").append(System.currentTimeMillis() - start)
					.append(':').append(request.getServletPath()).append(':').append(request.getMethod());
			log.info(actionBuilder.toString());
		}
		return result;
	}
}
