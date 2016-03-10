<%@include file="/includes/header.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<body>
<%@include file="/sessionTimeoutModal.jsp"%>

		<div id="blockDiv" class="display-none">
			<s:url value="/static/custom/images/loading.gif" var="loadingGif" />
			<img src="<s:property value='loadingGif'/>" alt="No Image found"/>
		</div>
		<div id="noteContent"  title="Notice" class="display-none">
			<p id="alertNotice"></p>
		</div>

			<jsp:include page="/includes/title.jsp" />

				<jsp:include page="/includes/errorMessages.jsp" />
				
				