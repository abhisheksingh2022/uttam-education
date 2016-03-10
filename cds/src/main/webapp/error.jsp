<%@page import="com.hixapi.web.framework.env.IEnvironmentProvider"%>
<%@page import="com.hixapi.web.framework.service.ServiceLocator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/includes/container.jsp"%>
<s:text name="txt.global.title_error" />

<s:if test="exception != null">
	<div class="ui-state-error">
		<s:text name="%{exception.errorCode}"></s:text>
	</div>
</s:if>
<s:else>
	<div class="ui-state-error">
		An unexpected error occured. Please click "Back" to return to the previous page
	</div>
</s:else>


<div class="form-btn errorstyle" >
<button class="checkprot errorback" 
		onclick="javascript:window.history.back()">
		Back
</button>
</div>
<%
	//response.setStatus(500);
	if (!ServiceLocator.getInstance().getService(IEnvironmentProvider.class).isProduction()) {
%>
<div class="errorDetailDiv">
	<pre>
 	<s:property value="%{exception.showDetailView()}" />
 	</pre>
</div>
<%
	}
%>
<%@include file="/includes/footer.jsp"%>

