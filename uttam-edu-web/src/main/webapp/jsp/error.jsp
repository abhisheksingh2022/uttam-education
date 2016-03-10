<%@page import="com.uttam.framework.common.APIConstants"%>
<%@page import="com.uttam.framework.common.env.IEnvironmentProvider"%>
<%@page import="com.uttam.framework.service.ServiceLocator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%@include file="/jsp/includes/container.jsp"%>
<s:text name="txt.global.title_error" />
<%
	if (!response.isCommitted()) {
		//response.setStatus(500);
	}
%>
<s:if test="exception != null">
	<div class="body-bg">
		<div class="ui-state-error" style="padding: 15px;">
			<s:text name="%{exception.errorCode}"></s:text>
			<br /> <b><s:text name="txt.error.reference" /> ERR000<s:property value="%{exception.errorIdNbr}" /></b>

		</div>
	</div>
</s:if>
<s:else>
	<div class="body-bg">
		<div class="ui-state-error" style="padding: 15px;">
			<s:text name="txt.global.error_unknown"></s:text>
			<br /> <b><s:text name="txt.error.reference" /> ERR000</b>

		</div>
	</div>
</s:else>
<div class="form-btn">
	<button class="save-btn" onclick="javascript:window.history.back()">Go Back</button>
</div>
<s:if test="!production">
	<%
		String env = ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
					.getProperty("global.environment", String.class, APIConstants.ENV_PROD);
			if ("DEV".equals(env) || env.startsWith("SIT")) {
	%>
	<div class="errorDetailDiv ui-state-error">
		<pre>
			<s:property value="%{exception.showDetailView()}" />
		</pre>
	</div>
	<%
		}
	%>
</s:if>
<%@include file="/jsp/includes/footer.jsp"%>

