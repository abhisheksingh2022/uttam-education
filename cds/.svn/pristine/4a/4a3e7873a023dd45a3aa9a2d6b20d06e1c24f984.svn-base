<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/hixapi.tld" prefix="common"%>
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
				$("#<s:property value='formId'/> [name=<s:property/>]").addClass("errorField");
				</s:iterator>
			} catch (e) {
				alert(e);
			}
		}
	</script>
	<%-- <div class="ui-state-error errorMessageDiv">
			<s:actionerror />
	</div> --%>
	<div class="ui-state-error errorMessageDiv">
		<common:ErrorMessages showHelpButtonMessage="false" />
	</div>
	<br />
</s:if>