<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@include file="/includes/container.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.diseaseheading5{
width: 20%;
}

.mainpannelpart1-ie-only{
border-top: none; 
}

.diseaseheading1-ie-only, .diseaseheading2-ie-only, .diseaseheading3-ie-only, .diseaseheading4-ie-only{
margin-top: 20px !important;
}
/*.tooltip {
	display: none;
	position: absolute;
	border: 1px solid #333;
	 background-color: #161616;
	border-radius: 10px; 
	padding: 18px;
	color: #123455;
	font-size: 14px;
	line-height: 150%;
left: auto !important;
top: auto !important;
}*/
</style>
<section class="col-md-12 marginmaintop">
	<div class="container maincont">
		<div class="row">
			<s:form action="health-status" namespace="/eligibility"
				id="id_form_healthStatus">
				<s:token />
				<s:hidden name="ajaxRequest" value="false" />
				<s:hidden name="formId" />
				<div class="col-md-12 heading">
					<h2>
						<s:text name="txt.healthStatus.header1"></s:text>
					</h2>
				</div>
				<div class="mandatory-warning"><s:text name='txt.household.astrcs.mandatory' /></div>
				<div class="col-md-12 column-headings">
					<div class="diseaseheading1 mycol">
						<h2>
							<s:text name="txt.healthStatus.col1"></s:text>
						</h2>
					</div>

					<div class="diseaseheading2  mycol">
						<h2>
							<s:text name="txt.healthStatus.col2"></s:text><span
							class="mandatory">*</span>
						</h2>
					</div>

					<div class="diseaseheading3  mycol">
						<table>
							<tr>
								<td class="wider-tooltip"><h2 >
										<s:text name="txt.healthStatus.col3"></s:text><sup><i class="fa fa-question-circle masterTooltip rightfootpart"
									data-toggle="tooltip"
									title="<s:text name="txt.healthstatus.hovertext.disease"></s:text>"></i></sup>
									</h2></td>
							</tr>

						</table>

					</div>

					<div class="diseaseheading4 mycol">
						<table>
							<tr>
								<td class="wider-tooltip"><h2 >
										<s:text name="txt.healthStatus.col4"></s:text><sup><i class="fa fa-question-circle masterTooltip rightfootpart"
									 data-toggle="tooltip"
									title="<s:text name="txt.healthstatus.hovertext.surgery"></s:text>"></i></sup>
									</h2></td>
							</tr>

						</table>

					</div>

					<div class="diseaseheading5 mycol">
						<table>
							<tr>
							<td class="wider-tooltip"><h2 >
										<s:text name="txt.healthStatus.col5"></s:text><sup><i class="fa fa-question-circle masterTooltip rightfootpart"
									 data-toggle="tooltip" data-html="true"
									title="<s:text name="txt.healthstatus.hovertext.severity"></s:text>"></i></sup>
									</h2></td>
							</tr>

						</table>
					</div>

				</div>
				
				<div class="clearfix"></div>
				<div id="row_wrapper_div">
					<s:iterator value="household.coveredMembers" status="memberIndex"
						var="individual">
						
						<s:if
							test="%{(medicaidIndicator != true ) && (chipIndicator != true) && (checkCoveredIndicator != false)}"  >
							<div class="col-md-12 mainpannelpart1">
								<s:hidden
									name="individuals[%{#memberIndex.index}].individualSequenceNumber"
									value="%{household.coveredMembers[#memberIndex.index].individualSequenceNumber}" />
								<s:hidden
									name="individuals[%{#memberIndex.index}].medicaidIndicator"
									value="%{household.coveredMembers[#memberIndex.index].medicaidIndicator}" />
								<s:hidden name="individuals[%{#memberIndex.index}].name"
									value="%{household.coveredMembers[#memberIndex.index].name}" />
								<div class="diseaseheading1 mycol mycolinput">
									<h3>
										<s:property value="name" />
										(
										<s:text name="txt.household.label3"></s:text>&nbsp;
										<s:property value="age" />
										)
									</h3>
								</div>
								
								<div class="diseaseheading2 mycol mycolinput">
									<s:select list="lookups.Gender" class="class_sel_lookupGender"
										id="id_sel_lookupGender%{#memberIndex.index}"
										name="individuals[%{#memberIndex.index}].gender" headerKey=""
										headerValue="%{selectBoxHeader}" listKey="value"
										listValue="label"></s:select>
								</div>
							
								<div class="diseaseheading3 mycol mycolinput">
									<s:select list="lookups.Disease"
										class="class_sel_lookupDisease"
										memindex="%{#memberIndex.index}"
										id="id_sel_lookupDisease%{#memberIndex.index}"
										name="individuals[%{#memberIndex.index}].disease" headerKey=""
										headerValue="%{selectBoxHeader}" listKey="value"
										listValue="label"></s:select>
								</div>
								
								<div class="diseaseheading4 mycol mycolinput">
									<s:select list="lookups.Surgery"
										class="class_sel_lookupSurgery"
										id="id_sel_lookupSurgery%{#memberIndex.index}"
										name="individuals[%{#memberIndex.index}].surgery" headerKey=""
										headerValue="%{selectBoxHeader}" listKey="value"
										listValue="label"></s:select>
								</div>

								<div class="diseaseheading5 mycol mycolinput">
									<s:select list="lookups.Severity"
										class="class_sel_lookupSeverity"
										id="id_sel_lookupSeverity%{#memberIndex.index}"
										name="individuals[%{#memberIndex.index}].severity"
										headerKey="" headerValue="%{selectBoxHeader}" listKey="value"
										listValue="label" ></s:select>
								</div><br>
							</div>

						</s:if>
					</s:iterator>
					<div class="col-md-12 mainpannelpart1"></div>
					<div class="clearfix"></div>
					<s:iterator value="household.coveredMembers" var="individual">
						<s:if test="medicaidIndicator">
							<p class="middle-text">
								<s:property value="name" />
								(
								<s:text name="txt.household.label3"></s:text>
								<s:property value="age" />
								)
								<s:text name="txt.healthStatus.medicaid_msg"></s:text>
							</p>
						</s:if>
						<s:if test="chipIndicator" >
							<p class="middle-text">
								<s:property value="name" />
								(
								<s:text name="txt.household.label3"></s:text>
								<s:property value="age" />
								<s:text name="txt.healthstatus.label1" />
							</p>
						</s:if>
						
					</s:iterator>
				</div>
				<br />
				<div class="clcknext">
					<s:text name="txt.healthStatus.footer_msg"></s:text>
				</div>
				<br />
				<div class="chkbackBt">
					<input class="back" type="button"
						value="< <s:text name="txt.global.button2"></s:text>"
						onclick="return setActionAndSubmit('id_form_healthStatus', 'goBack');" />
				</div>
				<div class="chknextBt">
					<input type="button"
						value="<s:text name="txt.global.button1"></s:text> >"
						onclick="return setActionAndSubmit('id_form_healthStatus', '');" />
				</div>
			</s:form>
		</div>
	</div>
</section>
<script>
$(document).ready(function() {
	if(isIE())
	{		
		$('.mainpannelpart1').addClass('mainpannelpart1-ie-only'); 
		$('.column-headings .diseaseheading1').addClass('diseaseheading1-ie-only');
		$('.column-headings .diseaseheading2').addClass('diseaseheading2-ie-only');
		$('.column-headings .diseaseheading3').addClass('diseaseheading3-ie-only');
		$('.column-headings .diseaseheading4').addClass('diseaseheading4-ie-only');
	}
	
	$("#id_title_logo").on("click",function(){
		$("#id_title_logo").attr("href",$("#id_title_logo").attr("href")+"?doBack=true");
	});
	
	$(".class_sel_lookupDisease").each(function(){
		var memId = $(this).attr("memindex");
		if($(this).val() == "None") {
			$("#id_sel_lookupSeverity"+memId).prop("disabled", true)
			$("#id_sel_lookupSeverity"+memId+" option:first-child").attr("selected", "selected")
		}
		else {
			$("#id_sel_lookupSeverity"+memId).prop("disabled", false)
		}
	})
	$(".class_sel_lookupDisease").change(function(){
		var memId = $(this).attr("memindex");
		if($(this).val() == "None" || !$(this).val()) {
			$("#id_sel_lookupSeverity"+memId).prop("disabled", true)
			$("#id_sel_lookupSeverity"+memId+" option:first-child").attr("selected", "selected")
		}
		else {
			$("#id_sel_lookupSeverity"+memId).prop("disabled", false)
		}
	})
});

$('sup i').each(function() {
    var options = {
        placement: function (context, source) {
            var position = $(source).offset();
            if (position.left < 500) {
                return "right";
            }
    
            if (position.top < 280){
                return "bottom";
            }
            
            else {
                return "left";
            }
        } , trigger: "hover"
       
    };
    $(this).tooltip(options);
}); 
</script>
<%@include file="/includes/footer.jsp"%>