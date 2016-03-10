<script>
	alert('This is a sample callback, from ajax result');
</script>

<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<s:token id="updatedtoken"/>
<fieldset>
	<legend> Here is the ajax call output </legend>
	<div>
		<jsp:include page="/includes/errorMessages.jsp" />
		Request URI:
		<%=ServletActionContext.getRequest().getRequestURI()%><br />
		Parameters:
		<s:iterator value="request" var="param">
			<s:property value="key" /> : <s:property value="value" />
			<br />
		</s:iterator>
		<br />
		<s:iterator value="parameters" var="param">
			<s:property value="key" /> : <s:property value="value" />
			<br />
		</s:iterator>
	</div>
</fieldset>