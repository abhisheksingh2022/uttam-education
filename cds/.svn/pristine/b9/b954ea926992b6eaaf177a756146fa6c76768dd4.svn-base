<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@include file="/includes/container.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
	.iTypeWidth {
		width: 120px !important;
	}
	.align-right{
		float: right;
	}
	.buttondis-enroll-now
	{
		background: #f59118; 
		color: #fff;
		height:26px;
		width:150px;
		float: right;
		border-radius: 5px;
		text-align: center;
	}
	.text-align-justify{
		text-align: justify;
	}
	.tax p i img{
		margin-right: -10px;
	}
</style>
<div id="id_div_householdForm">
	<s:form action="household" namespace="/eligibility"
		id="id_form_household" autocomplete="off">
		<s:token />
		<s:hidden name="ajaxRequest" value="false" />
		<s:hidden name="formId" />
		<s:hidden name="household.coverageYearStr" />
		<div id="id_household_infodiv">
		<div class="col-md-12 marginmaintop">
				<div class="container maincont">
					<div class="row">
						<div class="col-md-12 heading">
							<h2>
								<s:text name="txt.household.header1" />
							</h2>
						</div>
						<br /> <br />
						<div class="mandatory-warning"><s:text name='txt.household.astrcs.mandatory' /></div>
						<div class="textcontent-household"><s:text name='txt.household.tell.us.about.household' /></div>
						<table>
							<tr>
								<td class="mainlevelbody label"><label for="id_tf_country">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text
											name="txt.household.label1" /><span class="mandatory">*</span></label></td>
								<td><s:select cssClass="drop_boxpart1 select" tabindex="1"
										list="lookups.Counties" id="id_sel_lookupId"
										name="household.county" headerKey=""
										headerValue="%{selectBoxHeader}" listKey="value"
										listValue="label" onchange="clearEligibilityDiv()"></s:select></td>
							</tr>
						</table>
					</div>
					<div id="household_info">
						<table id="id_table_members"
							class="app-table table-content">
							<tbody>
								<tr class="heading2part">

									<th><s:text name="txt.household.label2" /><span
										class="mandatory">*</span></th>
									<th><s:text name="txt.household.label3" /><span
										class="mandatory">*</span>
										<sup><i class="fa fa-question-circle masterTooltip" data-toggle="tooltip"
									title="<s:text name="txt.household.hovertext.age"></s:text>"></i></sup>
										</th>
									<th><s:text name="txt.household.label16" /><span
										class="mandatory">*</span>
										<sup><i class="fa fa-question-circle masterTooltip" data-toggle="tooltip"
									title="<s:text name="txt.household.hovertext.applyingforhealthcoverage"></s:text>"></i></sup>
										</th>
									<th class="wider-tooltip"><s:text name="txt.household.label4" /><span
										class="mandatory">*</span>
										<sup><i class="fa fa-question-circle masterTooltip" data-toggle="tooltip"
									title="<s:text name="txt.household.hovertext.pregnant"></s:text>"></i></sup>
									</th>
									<th class="wider-tooltip"><s:text name="txt.household.label5" /><span
										class="mandatory">*</span>
										<sup><i class="fa fa-question-circle masterTooltip" data-toggle="tooltip"
									title="<s:text name="txt.household.hovertext.relationtoapplicant"></s:text>"></i></sup></th>
								</tr>
								<tr id="id_tr_row_0" class="mainpannelpart2A">

									<td class="heading2part22 applicanthead"><s:text
											name="txt.household.label6" /></td>
									<s:hidden name="household.householdMembers[0].name"
										value='%{getText("txt.household.label6")}' />
									<s:hidden
										name="household.householdMembers[0].individualSequenceNumber"
										value="1" />
									<td class="heading2part22"><s:textfield
											name="household.householdMembers[0].age" maxlength="3"
											tabindex="1" id="id_tf_applicant_age"
											onchange="clearNotLawFulDiv();" cssClass="agetype"/></td>
											
											<s:if test="household.householdMembers[0].pregnantIndicator!=null && household.householdMembers[0].pregnantIndicator!='' ">
												<s:set var="selectedValuePregnantIndicator"  value="%{household.householdMembers[0].pregnantIndicator}"> </s:set>
											</s:if>
											<s:else>
												<s:set var="selectedValuePregnantIndicator"  value="'N'"> </s:set>
											</s:else>
									<td class="heading2part22"><s:select
											list="lookups.YesorNo" tabindex="1"
											id="id_sel_applicant_covered"
											name="household.householdMembers[0].coveredIndicator" headerKey=""
											headerValue="%{selectBoxHeader}" listKey="value"
											listValue="label" onchange="clearNotLawFulDiv();"></s:select></td>
									<td class="heading2part22"><s:select tabindex="1"
											list="lookups.YesorNo" id="id_sel_applicant_pregnant"
											name="household.householdMembers[0].pregnantIndicator"
											 value="%{selectedValuePregnantIndicator}" listKey="value"
											listValue="label" onchange="clearNotLawFulDiv();"></s:select></td>
									<s:hidden
										name="household.householdMembers[0].legalStatusTypeIndicator"
										cssClass="hideFieldAfterAjaxRequired" />
									<s:hidden
										name="household.householdMembers[0].legalStatusTypeCode"
										cssClass="hideFieldAfterAjaxRequired" />
										<s:hidden
										name="household.householdMembers[0].medicaidIndicator"/>
										<s:hidden
										name="household.householdMembers[0].chipIndicator"/>
										<s:hidden
										name="household.householdMembers[0].gender"/>
										
											<s:hidden name="household.householdMembers[0].disease"/>
											<s:hidden name="household.householdMembers[0].surgery"/>
											<s:hidden name="household.householdMembers[0].severity"/>
									<td></td>
								</tr>
								<s:if test="household.householdMembers.size > 1">
									<s:iterator value="household.householdMembers" var="member"
										status="itStatus">
										<s:if test='#member.individualSequenceNumber!=1'>
											<tr class="trIdChangeRequired trborder" 
												id="id_tr_row_<s:property value="%{#itStatus.index}"  />">


												<td class="heading2part22 fontsize" ><span
													class="minuspart"> <img alt="image" 
														id="id_img_removemember" class="imgclass"
														src="<s:property value='rootPath'/>static/custom/images/remove.png"
														
														onclick='removeMember(<s:property value="#itStatus.index" />);' /></span>
													<span class="memberName claswidth"><s:property value="name" /></span></td>

												<s:hidden style="font-size: 18px; text-align: 20px"
													name="household.householdMembers[%{#itStatus.index}].name"
													cssClass="nameChangeRequired memberNameValueChangeRequired" />

												<s:hidden
													name="household.householdMembers[%{#itStatus.index}].individualSequenceNumber"
													cssClass="sequenceNumberChangeRequired nameChangeRequired" />

												<td class="heading2part22"><s:textfield
														name="household.householdMembers[%{#itStatus.index}].age"
														maxlength="2" tabindex="1" cssClass="agetype nameChangeRequired"
														onchange="clearNotLawFulDiv();" /></td>

												<td class="heading2part22"><s:select
														list="lookups.YesorNo" cssClass="nameChangeRequired"
														name='household.householdMembers[%{#itStatus.index}].coveredIndicator'
														headerKey="" headerValue="%{selectBoxHeader}"
														listKey="value" listValue="label" onchange="clearNotLawFulDiv();"></s:select></td>
												

												<td class="heading2part22"><s:select
														list="lookups.YesorNo" cssClass="nameChangeRequired"
														name='household.householdMembers[%{#itStatus.index}].pregnantIndicator'
														listKey="value" listValue="label" onchange="clearNotLawFulDiv();"></s:select></td>

												<td class="heading2part22"><s:select
														list="lookups.Relation" cssClass="nameChangeRequired"
														name="household.householdMembers[%{#itStatus.index}].relationToApplicantCode"
														headerKey="" headerValue="%{selectBoxHeader}"
														listKey="value" listValue="label" onchange="clearEligibilityDiv()"></s:select></td>

												<s:hidden name="household.householdMembers[%{#itStatus.index}].gender" cssClass="nameChangeRequired"/>
													<s:hidden name="household.householdMembers[%{#itStatus.index}].disease" cssClass="nameChangeRequired"/>
													<s:hidden name="household.householdMembers[%{#itStatus.index}].surgery" cssClass="nameChangeRequired"/>
												
												<s:hidden name="household.householdMembers[%{#itStatus.index}].severity" cssClass="nameChangeRequired"/>
												
												<s:hidden name="household.householdMembers[%{#itStatus.index}].medicaidIndicator"
													cssClass="nameChangeRequired"/>
												<s:hidden name="household.householdMembers[%{#itStatus.index}].chipIndicator" 
													cssClass="nameChangeRequired"/>	
												<s:hidden
													name="household.householdMembers[%{#itStatus.index}].legalStatusTypeIndicator"
													cssClass="hideFieldAfterAjaxRequired" />
												<s:hidden
													name="household.householdMembers[%{#itStatus.index}].legalStatusTypeCode"
													cssClass="hideFieldAfterAjaxRequired" />
											</tr>
										</s:if>
									</s:iterator>
								</s:if>
							</tbody>
						</table>
					</div>
					<s:hidden id="memberIndicator"
						value='%{getText("txt.household.label15")}' />
					<div style="display: none">
						<table class="add-row app-table">
							<tbody id="id_div_addmember">
								<tr class="add_member_tr trIdChangeRequired borderstyle">
									<td
										class="heading2part22 "><span class="minuspart positiontr"
										><img alt="image"
											id="id_img_removemember"
											src="<s:property value='rootPath'/>static/custom/images/remove.png"
											width="18" height="18" onclick="" /></span><span class="memberName claswidth"></span></td>

									<s:hidden name="name" value="" cssClass="nameChangeRequired memberNameValueChangeRequired" />

									<s:hidden name="individualSequenceNumber" value=""
										cssClass="nameChangeRequired sequenceNumberChangeRequired" />

									<td class="heading2part22"><s:textfield name="age" tabindex="1"
											maxlength="2"  cssClass="nameChangeRequired"
											onchange="clearNotLawFulDiv();" /></td>
									<td class="heading2part22"><s:select tabindex="1"
											list="lookups.YesorNo" cssClass="nameChangeRequired"
											name="coveredIndicator" headerKey=""
											headerValue="%{selectBoxHeader}" listKey="value"
											listValue="label" onchange="clearNotLawFulDiv();"></s:select></td>
									
									<td class="heading2part22"><s:select tabindex="1"
											list="lookups.YesorNo" cssClass="nameChangeRequired"
											name="pregnantIndicator" value="'N'" listKey="value"
											listValue="label" onchange="clearNotLawFulDiv();"></s:select></td>
									<td class="heading2part22"><s:select tabindex="1"
											list="lookups.Relation" cssClass="nameChangeRequired"
											name="relationToApplicantCode" headerKey=""
											headerValue="%{selectBoxHeader}" listKey="value"
											listValue="label" onchange="clearEligibilityDiv()"></s:select></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="mainpanne2part22A">

						<div class="addhouseholdbutton" id="id_img_addmember">
							<img alt="image"  tabindex="1" class="imgmargin"
								src="<s:property value='rootPath'/>static/custom/images/add.png"
								width="18" height="18" /> <a><s:text
									name="txt.household.label7" /></a>
						</div>
					</div>
					<div id="tf_additional_info">
						<div class="row nextdownpart">
							<div class="col-md-12 heading">
								<h2>
									<s:text name="txt.household.header2" />
								</h2>
							</div>
							<br> <br>
							<div class="clear20"></div>
							<table width="100%" cellpadding="0" border="0" cellspacing="0"
								class="app-table">
								<tr class="app-table1 mainleftinputpart2">
									<td width="48%" class="wider-tooltip app-table1 mainleftinputpart2 fontsize"
										><s:text
											name="txt.household.label8" /><label class="mandatory">
											*</label>
											<sup>
											<i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.income" />" data-toggle="tooltip"></i>
											</sup>
											</td>
									<td width="23%" class="mainleftinputpart2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield
											name="household.householdIncome" maxlength="13" tabindex="1"
											id="id_tf_household_income"  cssClass="integertype" onchange="clearEligibilityDiv()" /> </td>
									<td width="23%" class="selectspan1">&nbsp;<s:select 
											list="lookups.HouseholdIncomeType" tabindex="1"
											id="id_sel_household_incometype"
											name="household.householdIncomeTypeCode" headerKey="" cssClass="iTypeWidth"
											headerValue="%{selectBoxHeader}" listKey="value"
											listValue="label" onchange="clearEligibilityDiv()"></s:select></td>
								</tr>
								
								<tr>
									<td class="app-table1 mainleftinputpart2 fontsize"
										><s:text
											name="txt.household.label9" /><label class="mandatory">
											*</label></td>
									<td>&nbsp;&nbsp; &nbsp;&nbsp;<s:radio theme="spaceradio"
											tabindex="1" list="lookups.YesorNo"
											id="id_radio_AmericanIndian"
											name="household.householdMembers[0].qualifiedAlienIndicator"
											listKey="value" listValue="label" onchange="clearEligibilityDiv()"></s:radio></td>
								</tr>
								<tr>
									<td class="app-table1 mainleftinputpart2 fontsize"
										><s:text
											name="txt.household.label10" /><label class="mandatory">
											*</label>
											<sup><i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.lawful" />" data-toggle="tooltip"></i>
											</sup>
											</td>
									<td >&nbsp;&nbsp; &nbsp;&nbsp;<s:radio theme="spaceradio"
											tabindex="1" list="lookups.YesorNo" id="id_radio_USLaw"
											name="household.householdLegalStatusTypeIndicator"
											listKey="value" listValue="label"></s:radio>
									</td>
								</tr>
								<tr class="app-table1 mainleftinputpart2">
								
									<td>
										<div id="id_div_notlawful_member"></div>
									</td>
								</tr>
							</table>
						</div>
						<div align="right">
							<button class="checkprot buttondis" tabindex="1"
								onclick="return setActionAndSubmit('id_form_household', 'preliminaryEligibiltyCheck');">
								<s:text name="txt.household.button1" />
							</button>
						</div>
						<s:if test="household.householdMembers.size > 0">
							<s:hidden id="memberCount"
								value="%{household.householdMembers.size}" />
						</s:if>
						<s:else>
							<s:hidden id="memberCount" value="0" />
						</s:else>
					</div>
				</div>
			</div>
		</div>

		<s:hidden id="potentialEligibiltyStatus"
			name="potentialEligibiltyStatus" />
		<s:hidden name="household.householdFPL" />
	<s:if test="eligibility.resultList.size > 0 && potentialEligibiltyStatus==true">
		<div class="col-md-12 ">
			<div class="container maincont" id="result">
				<div class="row nextdownpart" id="id_household_eligibilty_portion"
					style="display: none">
					<div class="col-md-12 heading">
						<h2>
							<s:text name="txt.household.header3" />
						</h2>
					</div>
					<div class="col-lg-12h subscribe">
						<p>
							<s:text name="txt.household.label14" /><a href="http://www.accesshealthct.com" target="_blank"><s:text
									name="txt.plandetails.ahct"></s:text></a>
									<%-- <button class="buttondis-enroll-now" tabindex="1"
								onclick="javascript:openInNewTab('https://www.accesshealthct.com/AHCT/ApplyPlanEYO.action');">
								 <s:text name="txt.household.enroll-now" /> 
								 </button> --%>
								 <s:if test="eligibility.allMemberMedicaid != false">
								 <a class="buttondis-enroll-now" target="_blank" href="https://www.accesshealthct.com/AHCT/ApplyPlanEYO.action">  <s:text name="txt.household.enroll-now" /> </a>
							</s:if>
						</p>
					</div>
					<div class="col-md-12">

						<s:iterator value="eligibility.resultList" var="memberResult"
							status="resultItStatus">
							<div class="row tax">
								<h2>
									<s:property value="name" />
									,&nbsp;
									<s:text name="txt.household.label3" />
									&nbsp;
									<s:property value="age" />
								</h2>
								<div class="pre-box">
									<s:if
										test="#memberResult.program1!=null && #memberResult.program1!='' ">
										<s:if
											test="#memberResult.program1=='MA' && #memberResult.subProgram1=='AD'  ">
											<p>
												<s:text name="txt.medicaid.adult" />&nbsp;
												<sup> <i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.medicaid.adult" />" data-toggle="tooltip"></i></sup>
												<i> <img
													src="<s:property value='rootPath'/>static/custom/images/right-green.png"
													alt="" /></i>
											</p>
											<span><s:text name="txt.medicaid.adultDescrip" /> </span>
										</s:if>
										<s:if
											test="#memberResult.program1=='MA' && #memberResult.subProgram1=='PC'  ">
											<p>
												<s:text name="txt.medicaid.prntCrtaker" />&nbsp;
												<sup> <i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.medicaid.prntCrtaker" />" data-toggle="tooltip"></i></sup>
												<i> <img
													src="<s:property value='rootPath'/>static/custom/images/right-green.png"
													alt="" /></i>
											</p>
											<span><s:text name="txt.medicaid.prntCrtakerDescrip" />
											</span>
										</s:if>
										<s:if
											test="#memberResult.program1=='MA' && #memberResult.subProgram1=='PW'  ">
											<p>
												<s:text name="txt.medicaid.pregWm" />&nbsp;
												<sup> <i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.medicaid.prntCrtaker" />" data-toggle="tooltip"></i></sup>
												<i> <img
													src="<s:property value='rootPath'/>static/custom/images/right-green.png"
													alt="" /></i>
											</p>
											
											<span><s:text name="txt.medicaid.pregWmDescrip" /> </span>
										</s:if>
										<s:if
											test="#memberResult.program1=='MA' && #memberResult.subProgram1=='CH'  ">

											<p>
												<s:text name="txt.medicaid.child" />&nbsp;
												<sup> <i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.medicaid.child" />" data-toggle="tooltip"></i></sup>
												<i> <img
													src="<s:property value='rootPath'/>static/custom/images/right-green.png"
													alt="" /></i>
											</p>
											<span><s:text name="txt.medicaid.childDescrip" /> </span>
										</s:if>
										<s:if
											test="#memberResult.program1=='CH' && #memberResult.subProgram1=='B1'  ">
											<p>
												<s:text name="txt.chip.band1" />&nbsp;
												<sup> <i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.chip" />" data-toggle="tooltip"></i></sup>
												<i> <img
													src="<s:property value='rootPath'/>static/custom/images/right-green.png"
													alt="" /></i>
											</p>
											<span><s:text name="txt.chip.band1Descrip" /> </span>
										</s:if>
										<s:if
											test="#memberResult.program1=='CH' && #memberResult.subProgram1=='B2'  ">
											<p>
												<s:text name="txt.chip.band2" />&nbsp;
												<sup> <i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.chip" />" data-toggle="tooltip"></i></sup>
											
												<i> <img
													src="<s:property value='rootPath'/>static/custom/images/right-green.png"
													alt="" /></i>
											</p>
											<span><s:text name="txt.chip.band2Descrip" /> </span>
										</s:if>

										<s:if
											test="#memberResult.program1=='CS' ">

											<p>
												<s:text name="txt.csr.tier1" />&nbsp;
												<sup> <i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.csr" />" data-toggle="tooltip"></i></sup>
												<i> <img
													src="<s:property value='rootPath'/>static/custom/images/right-green.png"
													alt="" /></i>
											</p>
											<span><s:text name="txt.csr.tier1Descrip" /> </span>
											<s:if test="#memberResult.program2=='AP' && getAptcAmount()==0.00">
											
											<p>
												<s:text name="txt.aptcwithoutPTC" />&nbsp;&nbsp;
												<sup><i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.aptc" />" data-toggle="tooltip"></i></sup>
												<i>
												<img
													src="<s:property value='rootPath'/>static/custom/images/right-green.png"
													alt=""/></i>
											</p>
											<span><s:text name="txt.aptcDescrip1" />
												<s:property value="getAptcAmount()" />
												<s:text name="txt.aptcDescrip2" /> </span> 
										</s:if>
											<s:if test="#memberResult.program2=='AP' && getAptcAmount()!=0.00">
											
											<p>
												<s:text name="txt.aptc" />&nbsp;
												<sup><i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.aptc" />" data-toggle="tooltip"></i></sup>
												<i>
												<img
													src="<s:property value='rootPath'/>static/custom/images/right-green.png"
													alt=""/></i>
											</p>
											<span><s:text name="txt.aptcDescrip1" />
												<s:property value="getAptcAmount()" />
												<s:text name="txt.aptcDescrip2" /> </span>
										</s:if>
										
										</s:if>
										
										<s:if test="#memberResult.program1=='AP' && getAptcAmount()==0.00">
													<p>
														<s:text name="txt.aptcwithoutPTC" />&nbsp;&nbsp;
														<sup> <i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.aptc" />" data-toggle="tooltip"></i></sup>
														<i><img
															src="<s:property value='rootPath'/>static/custom/images/right-green.png"
															alt="" /></i>
													</p>
													<s:if test="#memberResult.qhpEligibility!=true">
														<span><s:text name="txt.aptcDescrip1" />
												<s:property value="getAptcAmount()" />
												<s:text name="txt.aptcDescrip2" /> </span>
												</s:if>
												<s:else>
												<span><s:text name="txt.aptc.without.ptc.description"/>
								<a href="http://www.accesshealthct.com" target="_blank"><s:text
									name="txt.plandetails.ahct"></s:text></a>
								<s:text name="txt.aptc.without.ptc.description1"/></span> 
</s:else>
												
											</s:if>
									<s:if test="#memberResult.program1=='AP' && getAptcAmount()!=0.00">
													<p>
														<s:text name="txt.aptc" />&nbsp;
														<sup> <i class="fa fa-question-circle" title="<s:text name="txt.household.hovertext.aptc" />" data-toggle="tooltip"></i></sup>
														<i><img
															src="<s:property value='rootPath'/>static/custom/images/right-green.png"
															alt="" /></i>
													</p>
													<span><s:text name="txt.aptcDescrip1" /> <s:property
															value="getAptcAmount()" /> <s:text
															name="txt.aptcDescrip2" /> </span>
											</s:if>
											
									</s:if>
								</div>
							</div>
						</s:iterator>
						
					</div>
					<s:if test="eligibility.allMemberMedicaid != false">
					<div class="col-md-12 text-align-justify">
					<br />
					<br />
						<s:text name="txt.allmemberMedicaid1" />&nbsp;
								<a href="http://www.accesshealthct.com" target="_blank"><s:text
									name="txt.plandetails.ahct"></s:text></a>
								<s:text name="txt.allmemberMedicaid2" />&nbsp;
								<a href="http://www.huskyhealth.com" target="_blank"><s:text
									name="txt.allmemberMedicaid3"></s:text></a>
								<s:text name="txt.allmemberMedicaid4" />
								
								
					</div>
					<div align="center" class="buttonmar">
						<button class="chknextBt buttonback" 
							onclick="return CloseWindow(); ">
							<s:text name="txt.button.close" />
						</button>
					</div>
				 	
					</s:if>
					<s:else>
					<div class="clcknext">
						<s:text name="txt.household.label11" />
					</div>
					<div>
						<button class="chknextBt buttonback" 
							onclick="return setActionAndSubmit('id_form_household', ''); ">
							<s:text name="txt.global.button1" />
						</button>
					</div>
				 	</s:else>
				</div>
			</div>
		</div>
		 </s:if>	
	</s:form>
</div>
<script>
	var iCnt = 0;
	
	function openInNewTab(url) {
		  var win = window.open(url, '_blank');
		  win.focus();
		}
	
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
	$(document).ready(function() {
	
		
		$("i").each(function(){
			$(this).parent().parent().addClass("position-relative");
		});
		$("#id_title_logo").on("click",function(){
			$("#id_title_logo").attr("href",$("#id_title_logo").attr("href")+"?doBack=true");
		});
	<%-- anchor #result tag was not working when validation in action failed,  instead of page top, control was coming to #result anchor tag
		 Now below code is perfect --%> 
		 <s:if test="!hasMessages() && household != null ">
		 	if($("#result").length){
				$('html,body').animate({scrollTop: $("#result").offset().top}, 'slow');
			 }
		</s:if>
	
		iCnt = parseInt($("#memberCount").val());
	<%--  if the size of members is 4, then iCnt should be 3 as 1 is already covered for applicant	--%>
		if(iCnt > 0 ){
			iCnt--;
		}
	
	<%--  called when add button is clicked --%>
		$('#id_img_addmember').click(
				function() {
				<%-- this logic is for creating dynamic id for each member except Applicant (as for applicant name is hardcoded see above) --%>	
					var container = $("#id_table_members");
					$(container).find("tbody").append($("#id_div_addmember").html());
					iCnt = iCnt + 1;
					var newTR = $(container).find(".add_member_tr");
					$(newTR).attr("id","id_tr_row_" + iCnt);
					
					$(newTR).find("img").attr("onClick","removeMember("+iCnt+")");
					
						 $(newTR).find(".memberName")
							.text( $("#memberIndicator").val()+ " "+ iCnt); 
					
					$(newTR).find("input[name=name]").val(" "+$(newTR).find(".memberName").text());
					$(newTR).find("input[name=individualSequenceNumber]").val(iCnt + 1);
					$(newTR).find(".nameChangeRequired")
							.each(function() {
										$(this).attr("name",
														"household.householdMembers["+ iCnt+ "]."+ $(this).attr("name"));
									});
					$(newTR).removeClass("add_member_tr");
					clearNotLawFulDiv();
				});
	<%--  to show potential Eligiblity div for all members, its sets to true in(householdAction method preliminaryEligibiltyCheck) when potetial eligibility is calculated --%>
	$('#id_household_eligibilty_portion').show();		
	/* if($("#potentialEligibiltyStatus").val()=='true'){
				 $('#id_household_eligibilty_portion').show();
			} */
		/* if($("#potentialEligibiltyStatus").val()=='false'){	
			//if we coming back from next screens then skip
			<s:if test="!hasMessages() && household != null && doBack!=true">
				 return setActionAndSubmit('id_form_household', '');
			</s:if> 
		 } */
	<%--  executed while document load, this calls ajaxRequest and bring response back only when customer select "No" --%>		
		if ($('input[name="household.householdLegalStatusTypeIndicator"]:checked','#id_form_household').val() == 'N') {
			
			return ajaxSubmit({
				formId : 'id_form_household',
				actionPath : 'legalStatusCheck',
				targetId : 'id_div_notlawful_member',
				blockId : 'id_div_householdForm',
				callback : 'response_callback'
			});
			
		} else {
			$("#id_div_notlawful_member").empty();
			$("#id_div_notlawful_member").hide();
		}

	});
	
<%-- used to delete member row, called when delete button is called --%> 	
	function removeMember(removeIndex) {
	 $('#id_tr_row_' + removeIndex).remove();
		    var container = $("#id_table_members");
					
		   iCnt--;  
			var prefixMember = $("#memberIndicator").val();
			   
		   $(container).find('.trIdChangeRequired').each(function(index) {
			    $(this).attr("id","id_tr_row_" + (index+1));
			    $(this).find("img").attr("onClick","removeMember("+(index+1) +")");
			    
			    $(this).find(".memberName").text(prefixMember+" "+(index+1));
    			$(this).find(".memberNameValueChangeRequired").attr("value",prefixMember+" "+(index+1));
    			
			    $(this).find(".nameChangeRequired")
							.each(function() {
										$(this).attr("name",
														"household.householdMembers["+ (index+1)+ "]."+ $(this).attr("name").substring($(this).attr("name").lastIndexOf(".")+1));
												
									});
			});
		   
		    $(container).find('.sequenceNumberChangeRequired').each(function(index) {
		   <%--  actually applicant's sequence number is 1, that cant be removed, first seq. number which can be removed is 2 of Member 1 --%>
			    $(this).attr("value",(index+2));   
			});
			
			
		   clearNotLawFulDiv();
	}
<%-- 
    this method clear and hide the div for those members who are not lawful for at least five years
 	this method is called while click on plus button (to add new member) or click on delete button or we change member age
--%>
	function clearNotLawFulDiv() {
		$("#id_div_notlawful_member").empty();
		$("#id_div_notlawful_member").hide();
		$('input[name="household.householdLegalStatusTypeIndicator"]:checked',
				'#id_form_household').attr('checked', false);
		
		clearEligibilityDiv();
	}
	
	function clearEligibilityDiv(){
		$("#id_household_eligibilty_portion").empty();
		$("#id_household_eligibilty_portion").hide();
	}
<%-- called on lawful status is changed --%>  	
	$("input[name='household.householdLegalStatusTypeIndicator']")
			.on("change",
					function() {
						if ($('input[name="household.householdLegalStatusTypeIndicator"]:checked','#id_form_household').val() == 'N') {
							clearEligibilityDiv();
							return ajaxSubmit({
								formId : 'id_form_household',
								actionPath : 'legalStatusCheck',
								targetId : 'id_div_notlawful_member',
								blockId : 'id_div_householdForm',
								callback : 'response_callback'
							});
						} else {
							 $('.hideFieldAfterAjaxRequired').each(function() {
				  	 		 $(this).remove();
							}); 
							$("#id_div_notlawful_member").empty();
							$("#id_div_notlawful_member").hide();
						}
						clearEligibilityDiv();
					});
			function CloseWindow()
			{
				window.close();
				return false;
			}
</script>
<%@include file="/includes/footer.jsp"%>