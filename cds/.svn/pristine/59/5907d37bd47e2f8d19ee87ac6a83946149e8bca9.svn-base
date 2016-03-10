<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@include file="/includes/container.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	
</script>
<s:form action="home" namespace="/eligibility" id="id_form_home">
	<s:token />
	<s:hidden name="ajaxRequest" value="false" />
	<s:hidden name="formId"/>
	<fieldset>

		<legend> Sample Form </legend>
		<table>
			<tr>
				<td><label for="id_tf_name">Sample Text Field:</label> <s:textfield
						name="name" maxlength="20" tabindex="1" id="id_tf_name" /></td>
				<td><label for="id_sel_lookupId">Sample Select box: </label> <s:select cssStyle="width:400px;"
						list="lookups.ResidentType" id="id_sel_lookupId" name="yesNo" headerKey="" headerValue="%{selectBoxHeader}"
						listKey="value" listValue="label"
						></s:select></td>
			</tr>
			<tr>
				<td><label for="id_tf_age">Number Text Field: </label> <s:textfield
						id="id_tf_age" name="age" maxlength="3" size="5"
						cssClass="agetype" /></td>
			</tr>
		</table>
		<br />
		<button
			onclick="return setActionAndSubmit('id_form_home', 'validationError');">Trigger
			Validation Error</button>
		<button
			onclick="return setActionAndSubmit('id_form_home', '');">Submit</button>
	</fieldset>
</s:form>
<br/>
<div id="id_div_ajaxForm">
	<s:form action="home" namespace="/eligibility" id="id_form_home2">
		<s:token />
		<s:hidden name="ajaxRequest" value="false" />
		<s:hidden name="formId"/>
		<fieldset>

			<legend> Example Ajax Form Submit using jquery.form.js </legend>
			<table>
				<tr>
					<td><label for="id_tf_name">Sample Text Field:</label> <s:textfield
							name="name" maxlength="20" tabindex="1" id="id_tf_name" /></td>
					<td><label for="id_sel_lookupId">Sample Select box: </label> <s:select
						list="lookups.Gender" id="id_sel_lookupId" name="lookupId" headerKey="" headerValue="%{selectBoxHeader}"
						listKey="value" listValue="label"
						></s:select></td>
				</tr>
				<tr>
					<td><label for="id_tf_age">Number Text Field: </label> <s:textfield
							id="id_tf_age" name="age" maxlength="3" size="5"
							cssClass="agetype" /></td>
				</tr>
			</table>
			<br />
			<button
				onclick="return ajaxSubmit({formId: 'id_form_home2', actionPath: 'ajaxPost',  targetId:'id_div_ajaxResult', blockId:'id_div_ajaxForm', callback:'response_callback'});">Post via Ajax</button>
			<s:url var="path" action="home_ajaxGet" namespace="/eligibility"/>
			<a href="#" onclick="return ajaxGet({resource:'<s:property value="path"/>',targetId: 'id_div_ajaxResult', urlparams:{key:'value'}})">Get via Ajax</a>
		</fieldset>
	</s:form>
	
	<div id="id_div_ajaxResult" style="display: none;"></div>
</div>
<%@include file="/includes/footer.jsp"%>