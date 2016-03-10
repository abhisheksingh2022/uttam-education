<%@page import="com.hixapi.web.framework.env.IEnvironmentProvider"%>
<%@page import="com.hixapi.web.framework.service.ServiceLocator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"
	session="false"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%@include file="/includes/container.jsp"%>
<s:text name="txt.global.title_error" />

<s:if test="exception != null">
	<div class="ui-state-error">
		<s:text name="%{exception.errorCode}"></s:text>
	</div>
</s:if>
<%
	response.setStatus(500);
	if (!ServiceLocator.getInstance().getService(IEnvironmentProvider.class).isProduction()) {
%>
<div class="errorDetailDiv ui-state-error">
	<pre>
 	<s:property value="%{exception.showDetailView()}" />
 	</pre>
</div>
<%
	}
%>
<%@include file="/includes/footer.jsp"%>

