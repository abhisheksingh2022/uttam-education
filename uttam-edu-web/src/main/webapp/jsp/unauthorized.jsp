<%@page import="com.uttam.framework.common.env.IEnvironmentProvider"%>
<%@page import="com.uttam.framework.service.ServiceLocator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%@include file="/jsp/includes/container.jsp"%>
<s:text name="txt.global.title_error" />

	<div class="ui-state-error">
		<s:text name="global.msg.unauthorized_access"></s:text>
	</div>
	<div class="form-btn">
		<button class="save-btn"
			onclick="javascript:window.history.back()">
			Go Back
		</button>
	</div>
<%@include file="/jsp/includes/footer.jsp"%>

