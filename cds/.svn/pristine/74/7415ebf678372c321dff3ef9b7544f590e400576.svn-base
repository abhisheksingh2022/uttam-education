<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@include file="/includes/container.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
$(document).ready(function(){
	$(".formatValues").each(function(){
		$(this).text(accounting.formatMoney($(this).text()));
	});
	
});

</script>
<style>
.text-bold{
font-weight: bold;
}
</style>

<section class="col-md-12 marginmaintop">
	<div class="container maincont">
		<div class="row">
			<s:form action="medical-cost-information" namespace="/eligibility"
				id="id_form_medical-cost-information">
				<s:token />
				<s:hidden name="ajaxRequest" value="false" />
				<s:hidden name="formId" />
				<div class="heading">
					<h2>
						<s:text name='txt.medicalcostinfo.header1' />
						</h2>
				</div>
				<div class="textcontent-medical-information">
					<div class="question">
						<s:text name='txt.medicalcostinfo.question1' />
					</div>
					<br>
					<s:text name='txt.medicalcostinfo.label1' />
					<div>
						<br>
						<table class="cost-table" border="1">
							<tr>
								<th><s:text name='txt.medicalcostinfo.table.header1' /></th>
								<th><s:text name='txt.medicalcostinfo.table.header2' /></th>
							</tr>
							<tr>
								<td><s:text name='txt.medicalcostinfo.table.label1' /> &nbsp;<span  class="formatValues text-bold"><s:property value="medicalCostInsured"/></span> </td>
								<td><s:text name='txt.medicalcostinfo.table.label2' /> &nbsp;<span  class="formatValues text-bold"><s:property value="medicalCostUninsured"/></span> </td>
							</tr>
							<tr>
								<td colspan="2" class="table-info-text"><s:text
										name='txt.medicalcostinfo.table.note1' /></td>
							</tr>
						</table>

					</div>
					<br>
					<s:text name='txt.medicalcostinfo.label2' />
					<br> <br>
					<br>
					<div class="question">
						<s:text name='txt.medicalcostinfo.question2' />
					</div>
					<br>
					<s:text name='txt.medicalcostinfo.label3' />
					<br><br> <s:text name='txt.medicalcostinfo.label4'/> <br>
					<br><s:text name='txt.medicalcostinfo.label5'/>


				</div>
				<div class="chkbackBt">
					<input class="back" type="button"
						value="< <s:text name="txt.global.button2"></s:text>"
						onclick="return setActionAndSubmit('id_form_medical-cost-information', 'goBack');" />
				</div>
				<div class="chknextBt">
					<input type="button"
						value="<s:text name="txt.global.button1"></s:text> >"
						onclick="return setActionAndSubmit('id_form_medical-cost-information', '');" />
				</div>
			</s:form>
		</div>
	</div>
</section>
<script>
	$(document).ready(function() {
	});
</script>
<%@include file="/includes/footer.jsp"%>