/**
 * --Description--
 * --Usage--
 * @author admin
 * Sep 23, 2009
 */
package com.uttam.web.framework.struts;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.ParametersInterceptor;
import com.uttam.framework.common.SanitizerUtil;

/**
 * The Class ParameterSanitizerInterceptor.
 * 
 * @author admin Sep 23, 2009
 */
public class ParameterSanitizerInterceptor extends ParametersInterceptor {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 486119316603461712L;

    /**
     * @param ac
     * @return
     * @author admin
     * Nov 1, 2009
     */
    @Override
    /**
     * This method currently does a trim of all parameters. No sanitizing (XSS) is done.
     */
    protected Map<String, Object> retrieveParameters(ActionContext ac) {
	Map<String,Object> contextParamMap = ac.getParameters();
	return SanitizerUtil.trimAll(contextParamMap);
    }

    

}
