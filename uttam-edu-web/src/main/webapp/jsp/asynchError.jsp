
<%@page import="com.uttam.framework.common.APIConstants"%>
<%@page import="com.uttam.framework.service.ServiceLocator"%>
<%@page import="com.uttam.framework.common.env.IEnvironmentProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Asynch: --><s:text name="txt.global.error_unknown" />

<s:if test="exception != null">
	<div class="ui-state-error" style="margin: 5px;max-width: 100px; max-height: 200px;">
		 <s:text name="%{exception.errorCode}"></s:text>
		 <br/>
		<b><s:text name="txt.error.reference"/> ERR000<s:property value="%{exception.errorIdNbr}"/></b>
	</div>
</s:if>
<s:if test="!production">
<%

String env = ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
	.getProperty("global.environment", String.class, APIConstants.ENV_PROD);
if("DEV".equals(env) || env.startsWith("SIT")){

 %>
 <div class="errorDetailDiv ui-state-error">
 	<pre><s:property value="%{exception.showDetailView()}" /></pre>
 </div>
 <%
 }
  %>
</s:if>

