<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/jsp/includes/header.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/hixapi.tld" prefix="common"%>


<body>
	<%@include file="/jsp/sessionTimeoutModal.jsp"%>
	<div id="blockDiv" style="display: none;">
		<img src="<s:property value='contextRoot'/>static/custom/images/loading.gif" />
	</div>
	<div id="noteContent" title="Notice" style="display: none;">
		<p id="alertNotice"></p>
	</div>
	<div class="modal fade modal1" id="Dcert_modal" role="dialog" style="z-index:50000">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">

				<div class="modal-body">
					<span style="display: none;" id="externalUrl1"></span>
					<div style="margin: 5px">
						<div>
							<span> <b><s:text name="txt.planpreview.warning"></s:text> : </b> <s:text name="txt.planpreview.warning1"></s:text>
							</span>
						</div>
						<br>
						<div class="row">
							<a href="" id="Yes" data-toggle="modal" onclick="return closeAwayDialog();" class="save-btn"
								style="margin-right: 20px;"> <s:text name="txt.planpreview.returntoaccesshealth"></s:text>
							</a> <a href="" data-toggle="modal" onclick="openWebsite();" id="No" class="save-btn"><s:text
									name="txt.planpreview.continuetoexternalwebsite"></s:text></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="wrapper">
		<%--closed in footer --%>
		<jsp:include page="/jsp/includes/title.jsp" />



		<div class="body-con">
			<%--closed in footer --%>

			<div class="container maincont">
				<div class="ui-state-error errorMessageDiv">
					<s:if test="hasMessages()">
						<script>
							<s:if test="ajaxRequest">
							highlight();
							</s:if>
							<s:else>
							$(document).ready(function() {
								highlight()
							});
							</s:else>

							function highlight() {
								try {
									<s:iterator value="errorFieldNames">
									$(
											"#<s:property value='formId'/> [name=<s:property/>]")
											.addClass("errorField");
									if($(
											"#<s:property value='formId'/> [name=<s:property/>]").hasClass("form-control")){
												$(
											"#<s:property value='formId'/> [name=<s:property/>]")
											.removeClass("form-control").addClass("form-control");
									}
									</s:iterator>
								} catch (e) {
									alert(e);
								}
							}
						</script>
						<br />
					</s:if>
					<common:ErrorMessages showHelpButtonMessage="false" />
				</div>