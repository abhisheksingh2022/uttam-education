<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<s:token id="updatedtoken"/>
   <div class="selHosehold">
   <br />
   <br /> 
   	<s:if test="notLawfulMembers.size > 0">
   		<p class="mainleftinputpart2 fontsize" ><s:text name="txt.household.label12"/><p>
   	
		<table width="100%" cellpadding="0" border="0" cellspacing="0" class="" >
		 
			<s:iterator value="notLawfulMembers" var="member" status="itStatus">
					<tr class="app-age">
					<s:hidden name="checkBoxStatus" value="%{#member.legalStatusTypeIndicator}" />
						<td><s:checkbox cssClass="legalStatusDivShowHideRequired"  name="household.householdMembers[%{#member.individualSequenceNumber-1}].legalStatusTypeIndicator" onchange='showLegalStatusType(this,"%{#itStatus.index}")'></s:checkbox></td>
						<td class="fontsize"><s:property value="name" />,&nbsp;<s:text name="txt.household.label3" />&nbsp;<s:property value="age" /></td>
					</tr>
					<tr>
						<td colspan="3">
							<div id="id_div_legalStatusType_<s:property value="#itStatus.index" />" style="display:none">
							<span ><s:text name="txt.household.label13"></s:text></span><br /><br />
							<!-- here id is used only for this page purpose, while name maps with Action Class -->
									<s:select id='household.householdMembers[%{#itStatus.index}].legalStatusTypeCode'  name='household.householdMembers[%{#member.individualSequenceNumber-1}].legalStatusTypeCode' headerKey=""
										headerValue="%{selectBoxHeader}" list="lookups.ResidentType"
										listKey="value" listValue="label" cssStyle="width:400px"  cssClass="input-large" onchange='clearEligibilityDiv();' />
										
							</div>
						</td>
					</tr>
			</s:iterator>
	</table>
  </s:if>
			
	</div>
	<script>
		$(document).ready(function(){
			
			<%-- logic to select elements after re-load which all already were selected --%>
			 var chkBoxes = $("input[name='checkBoxStatus']");
			$(".legalStatusDivShowHideRequired").each(function(index){
				 var currentElement = $(this);
				 var statusdiv = $("#id_div_legalStatusType_"+(index));  <%--  index starts from 0 --%>
				 var selObj = document.getElementById("household.householdMembers["+index+"].legalStatusTypeCode");
				 $(selObj).selectbox();
				 if(chkBoxes[index].value == 'true'){
					 currentElement.attr('checked', true);
					 statusdiv.show();
					 
				<%-- this code will add error css if legal status is already selected --%>
				
					<s:if test="potentialEligibiltyStatus==false">
						 var selObj = document.getElementById("household.householdMembers["+index+"].legalStatusTypeCode");
						 if(selObj.value == ""){
							 $(selObj).addClass("errorField");
						 } 
					</s:if>
				 }else{
					 currentElement.attr('checked', false);
					 var selObj = document.getElementById("household.householdMembers["+index+"].legalStatusTypeCode");
					 selObj.value="";
					 statusdiv.hide();
				 }
			});
			<%-- this is very important step "hideFieldAfterAjaxRequired" is declareed as hidden fields in household.jsp,
				and these fields are for just bringing those field value to 'this' jsp, after this these fields need to be removed.
			--%>
			 $('.hideFieldAfterAjaxRequired').each(function() {
				    $(this).remove();
			}); 
			
		});
		 function showLegalStatusType(checkboxObj,index) {
			 var statusdiv = $("#id_div_legalStatusType_"+index);
			 if($(checkboxObj).is(":checked")){
				statusdiv.show();
			}else{
				statusdiv.hide();
			}
			 clearEligibilityDiv();
		} 
		 function removeCss(selObj){
			 if($(selObj).val()==""){
				 $(selObj).addClass("errorField");
			 }else{
			 	$(selObj).removeClass("errorField");
			 }
		 }
	</script>
