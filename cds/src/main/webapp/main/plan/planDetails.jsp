<%@include file="/includes/header.jsp"%>


<%-- <script
	src="<s:property value='rootPath'/>static/custom/js/jquery-ui.js"></script>
 --%>
<script
	src="<s:property value='rootPath'/>static/custom/js/jquery.accordion.source.js?v=<s:property value="buildId"/>"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('ul').accordion();
						$("#id_div_plan_details a")
								.each(
										function() {
											var ahref = $(this).attr("href");
											if (typeof ahref != "undefined") {
												if ($(this).attr("id") != 'id_a_pdf-link') {
													if (ahref
															.indexOf('http://') != 0
															&& ahref
																	.indexOf('https://') != 0  && ahref.indexOf("/") != 0) {
														$(this)
																.attr(
																		"href",
																		"http://"
																				+ ahref);
													}
												}
											}

										});

					});
	$(function() {
		$("#accordion1").accordion({
			heightStyle : "content"
		});
	});
</script>
<style>
.notice-paragraph .tooltip .tooltip-inner{
	max-width: 300px;
}
.extremepart label .tooltip-inner, .catastrophic-tooltip .tooltip-inner{
	width : 500px !important;  
	max-width: 500px !important;
}
.catastrophic-tooltip .tooltip-inner{
	width : 400px !important;  
	max-width: 400px !important;
}
</style>
<h2>

	<%-- 	<s:text name="txt.plandetails.healthyCTInc"></s:text> --%>
	<s:property value="planContainer.plan.carrierName" />
	<br />
	<%-- 	<s:text name="txt.plandetails.silverEnhanced"></s:text> --%>
	<s:property value="planContainer.plan.planName" />
</h2>
<ul class="accordion">
	<li><a><s:text name="txt.plandetails.notice_planDetails"></s:text></a>
		<div class="text-align-justify">
			<p class="notice-paragraph">
				<s:text name="txt.plandetails.noticeText1"></s:text>
				<br />
				<s:text name="txt.plandetails.noticeText2"></s:text>
				<i class="fa fa-question-circle"
					title="<s:text name="txt.plan.hovertext.expectedmonthlypremium" />"
					data-toggle="tooltip"></i>&nbsp;
				<s:text name="txt.plandetails.noticeText3"></s:text>
				<i class="fa fa-question-circle"
					title="<s:text name="txt.plan.hovertext.qhp" />"
					data-toggle="tooltip"></i>&nbsp;
				<s:text name="txt.plandetails.noticeText4"></s:text>

			</p>
		</div></li>
</ul>
<ul class="accordion">
	<li><a> <s:text name="txt.plandetails.planOverview"></s:text></a>

		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.expectedmonthlypremium" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.estimatedMonthlyPremium"></s:text></label>
			<div class="rightppart">
				$
				<s:property
					value="planContainer.plan.planDetails.planOverview.estimatedMonthlyPremium" />
			</div>
		</div>

		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.healthcareprovider" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.healthCareProvider"></s:text></label>
			<div class="rightppart">
				<a id="id_a_provider-link"
					href="<s:property
						value="planContainer.plan.planDetails.planOverview.healthCareProviderLink" />"
					target="_blank"> <s:text name="txt.plandetails.searchProviders"></s:text>
				</a>
			</div>
		</div>

		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.plantype" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.planType"></s:text></label>
			<div class="rightppart">
				<s:property
					value="planContainer.plan.planDetails.planOverview.planType" />
			</div>
		</div>

		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.planLevel" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.planLevel"></s:text></label>
			<div class="rightppart catastrophic-tooltip">
				<img class="middle"
							src="<s:property value='rootPath'/>static/custom/images/metal_tier_<s:property
					value="planContainer.plan.planDetails.planOverview.planLevel.toUpperCase()" />.png" />
					&#160;<s:property
					value="planContainer.plan.planDetails.planOverview.planLevel" />&nbsp;
					<s:if test="planContainer.plan.planDetails.planOverview.planLevel == 'Catastrophic'">
					<i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hover.catastrophic" />"
				data-toggle="tooltip"></i></s:if>
				
			</div>
		</div>

		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.qualityrating" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.qualityRating"></s:text></label>
			<div class="rightppart">
<!--  			<script>alert("1");alert("<s:property value='planContainer.plan.planDetails.planOverview.qualityRating' />");</script>
 -->				<s:if
					test="planContainer.plan.planDetails.planOverview.qualityRating>0">
					<s:iterator begin="1" step="1" end="planContainer.plan.planDetails.planOverview.qualityRating">
						<img class="middle"
							src="<s:property value='rootPath'/>static/custom/images/star.png"
							alt="Full Star">
					</s:iterator>
					<s:iterator begin="planContainer.plan.planDetails.planOverview.qualityRating+1" step="1" end="4">
						<img class="middle"
							src="<s:property value='rootPath'/>static/custom/images/star_empty.png"
							alt="Full Star">
					</s:iterator>
				</s:if>
				<s:else>
					<s:text name="txt.plandetails.unrated"></s:text>
				</s:else>
			</div>
		</div></li>
	<li><a><s:text name="txt.plandetails.costSharing"></s:text></a>
	
	<!-- COST SHARING OVERVIEW - DEDUCTIBLE -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.deductible" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.deductible"></s:text></label>
			<div class="rightppart">
				<div class="bold_underline">
					<s:text name="txt.plandetails.individual"></s:text>
				</div>
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.costSharingOverview.deductible.individualInAndOutNetwork.inNetwork" />
				<br>
				<s:if
					test="planContainer.plan.planDetails.costSharingOverview.deductible.individualInAndOutNetwork.outOfNetwork != null">
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.costSharingOverview.deductible.individualInAndOutNetwork.outOfNetwork" />

				</s:if>
				<div class="bold_underline">
					<s:text name="txt.plandetails.family"></s:text>
				</div>
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.costSharingOverview.deductible.familyInAndOutNetwork.inNetwork" />
				<br>
				<s:if
					test="planContainer.plan.planDetails.costSharingOverview.deductible.familyInAndOutNetwork.outOfNetwork != null">
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.costSharingOverview.deductible.familyInAndOutNetwork.outOfNetwork" />

				</s:if>
			</div>
		</div> 
		<!-- COST SHARING OVERVIEW - MAX-OUT-OF-POCKET -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.maxooutofpkt" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.outOfPocket"></s:text></label>
			<div class="rightppart">
				<div class="bold_underline">
					<s:text name="txt.plandetails.individual"></s:text>
				</div>
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.costSharingOverview.outOfPocketMax.individualInAndOutNetwork.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.costSharingOverview.outOfPocketMax.individualInAndOutNetwork.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.costSharingOverview.outOfPocketMax.individualInAndOutNetwork.outOfNetwork" />
				</s:if>
				<div class="bold_underline">
					<s:text name="txt.plandetails.family"></s:text>
				</div>
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.costSharingOverview.outOfPocketMax.familyInAndOutNetwork.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.costSharingOverview.outOfPocketMax.familyInAndOutNetwork.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.costSharingOverview.outOfPocketMax.familyInAndOutNetwork.outOfNetwork" />
				</s:if>
			</div>
		</div> 
	</li>

	<li><a> <s:text name="txt.plandetails.physicianServices"></s:text>
	</a>
	<!-- PHYSICIAN SERVICES - PREVENTIVE CARE -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.preventivecare" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.preventiveCare"></s:text></label>
			<div class="rightppart">
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.physicianServices.preventiveCare.inNetwork" />
					<s:if
					test="planContainer.plan.planDetails.physicianServices.preventiveCare.outOfNetwork != null">
					<br><s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.physicianServices.preventiveCare.outOfNetwork" />
				</s:if>
			</div>
		</div> 
		<!-- PHYSICIAN SERVICES - PRIMARY CARE -->
		<s:if test="pvpInNetwork != null">
			<div class="extremepart">
				<label><i class="fa fa-question-circle"
					title="<s:text name="txt.plan.hovertext.primarycare" />"
					data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
						name="txt.plandetails.primaryCare"></s:text> </label>
				<div class="rightppart">
					<s:text name="txt.plandetails.inNetwork"></s:text>
					<s:property value="pvpInNetwork" />
					<s:if test="pvpOutNetwork != null">
						<br>
						<s:text name="txt.plandetails.outNetwork"></s:text>
						<s:property value="pvpOutNetwork" />

					</s:if>
				</div>
			</div>
		</s:if>
		<!-- PHYSICIAN SERVICES - Specialist VISIT -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.specialistvisit" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.specialistVisit"></s:text></label>
			<div class="rightppart">
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.physicianServices.specialistVisit.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.physicianServices.specialistVisit.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.physicianServices.specialistVisit.outOfNetwork" />
				</s:if>
			</div>
		</div></li>
	<li><a> <s:text name="txt.plandetails.prescriptionDrugs"></s:text>
	</a>
	<!-- PRESCRIPTION DRUGS - DEDUCTIBLE -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.presDrugDeductible" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.PDDeductible"></s:text></label>
			<div class="rightppart">
				<div class="bold_underline">
					<s:text name="txt.plandetails.individual"></s:text>
				</div>
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.prescriptionDrugs.drugDeductible.individualInAndOutNetwork.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.prescriptionDrugs.drugDeductible.individualInAndOutNetwork.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.prescriptionDrugs.drugDeductible.individualInAndOutNetwork.outOfNetwork" />

				</s:if>
				<div class="bold_underline">
					<s:text name="txt.plandetails.family"></s:text>
				</div>
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.prescriptionDrugs.drugDeductible.familyInAndOutNetwork.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.prescriptionDrugs.drugDeductible.familyInAndOutNetwork.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.prescriptionDrugs.drugDeductible.familyInAndOutNetwork.outOfNetwork" />

				</s:if>
			</div>
		</div>
<!-- PRESCRIPTION DRUGS - MOOP -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.presDrugOOP" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.PDOutOfPocket"></s:text></label>
			<div class="rightppart">
				<div class="bold_underline">
					<s:text name="txt.plandetails.individual"></s:text>
				</div>
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.prescriptionDrugs.drugOutOfPocketMax.individualInAndOutNetwork.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.prescriptionDrugs.drugOutOfPocketMax.individualInAndOutNetwork.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.prescriptionDrugs.drugOutOfPocketMax.individualInAndOutNetwork.outOfNetwork" />

				</s:if>
				<div class="bold_underline">
					<s:text name="txt.plandetails.family"></s:text>
				</div>
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.prescriptionDrugs.drugOutOfPocketMax.familyInAndOutNetwork.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.prescriptionDrugs.drugOutOfPocketMax.familyInAndOutNetwork.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.prescriptionDrugs.drugOutOfPocketMax.familyInAndOutNetwork.outOfNetwork" />

				</s:if>

			</div>
		</div> 
		<!-- PRESCRIPTION DRUGS - RETAIL PHARMACY -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.presDrugRetail" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.PDFromARetail"></s:text></label>
			<div class="rightppart">
				<div class="bold_underline">
					<s:text name="txt.plandetails.tier1"></s:text>
				</div>
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier1.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier1.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier1.outOfNetwork" />
				</s:if>
				<div class="bold_underline">
					<s:text name="txt.plandetails.tier2"></s:text>
				</div>
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier2.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier2.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier2.outOfNetwork" />
				</s:if>
				<s:if
					test="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier3.inNetwork != null">
					<div class="bold_underline">
						<s:text name="txt.plandetails.tier3"></s:text>
					</div>
					<s:text name="txt.plandetails.inNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier3.inNetwork" />
					<s:if
						test="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier3.outOfNetwork != null">
						<br>
						<s:text name="txt.plandetails.outNetwork"></s:text>
						<s:property
							value="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier3.outOfNetwork" />
					</s:if>
				</s:if>
				<s:if
					test="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier4.inNetwork != null">
					<div class="bold_underline">
						<s:text name="txt.plandetails.tier4"></s:text>
					</div>
					<s:text name="txt.plandetails.inNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier4.inNetwork" />
					<s:if
						test="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier4.outOfNetwork != null">
						<br>
						<s:text name="txt.plandetails.outNetwork"></s:text>
						<s:property
							value="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier4.outOfNetwork" />
					</s:if>
				</s:if>
				<s:if
					test="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier5.inNetwork != null">
					<div class="bold_underline">
						<s:text name="txt.plandetails.tier5"></s:text>
					</div>
					<s:text name="txt.plandetails.inNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier5.inNetwork" />
					<s:if
						test="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier5.outOfNetwork != null">
						<br>
						<s:text name="txt.plandetails.outNetwork"></s:text>
						<s:property
							value="planContainer.plan.planDetails.prescriptionDrugs.retailPharmacyDrugs.tier5.outOfNetwork" />
					</s:if>
				</s:if>
			</div>
		</div>
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.presDrugSearch" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.PDFormulatory"></s:text></label>
			<div class="rightppart">
				<a id="id_a_formulary-link"
					href="<s:property
							value="planContainer.plan.planDetails.prescriptionDrugs.drugFormularySearchLink" />"
					target="_blank"><s:text
						name="txt.plandetails.searchPrescription"></s:text> </a>
			</div>
		</div></li>
	<li><a> <s:text name="txt.plandetails.emergencyCare"></s:text>
	</a>
	<!-- EMERGENCY CARE - EMERGENCY ROOM -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.emergencyRoom" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.emergencyRoom"></s:text></label>
			<div class="rightppart">
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.emergencyCareAndHospitalService.emergencyRoomService.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.emergencyCareAndHospitalService.emergencyRoomService.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.emergencyCareAndHospitalService.emergencyRoomService.outOfNetwork" />

				</s:if>
			</div>
		</div> <!-- EMERGENCY CARE - INPATIENT HOSPITAL -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.inpatientHospital" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.inPatient"></s:text></label>
			<div class="rightppart">
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.emergencyCareAndHospitalService.inpatientHospitalServices.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.emergencyCareAndHospitalService.inpatientHospitalServices.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.emergencyCareAndHospitalService.inpatientHospitalServices.outOfNetwork" />
				</s:if>
			</div>
		</div></li>
	<li><a> <s:text name="txt.plandetails.pediatricDental"></s:text>
	</a> <!-- PEDIATRIC DENTAL - DIAGNOSTIC -->
		<div class="extremepart">
			<label><s:text name="txt.plandetails.diagnostic"></s:text></label>
			<div class="rightppart">
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.pediatricDentalCare.diagnosticPreventive.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.pediatricDentalCare.diagnosticPreventive.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.pediatricDentalCare.diagnosticPreventive.outOfNetwork" />

				</s:if>
			</div>
		</div> <!-- PEDIATRIC DENTAL - BASIC RESTORATIVE -->
		<div class="extremepart">
			<label><s:text name="txt.plandetails.basicRestorative"></s:text></label>
			<div class="rightppart">
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.pediatricDentalCare.basicRestorative.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.pediatricDentalCare.basicRestorative.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.pediatricDentalCare.basicRestorative.outOfNetwork" />

				</s:if>
			</div>
		</div> <!-- PEDIATRIC DENTAL - MAJOR RESTORATIVE -->
		<div class="extremepart">
			<label><s:text name="txt.plandetails.majorRestorative"></s:text></label>
			<div class="rightppart">
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.pediatricDentalCare.majorRestorative.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.pediatricDentalCare.majorRestorative.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.pediatricDentalCare.majorRestorative.outOfNetwork" />
				</s:if>
			</div>
		</div> <!-- PEDIATRIC DENTAL - ORTHODONTIC SERVICES -->
		<div class="extremepart">
			<label><s:text name="txt.plandetails.orthodonticServices"></s:text></label>
			<div class="rightppart">
				<s:text name="txt.plandetails.inNetwork"></s:text>
				<s:property
					value="planContainer.plan.planDetails.pediatricDentalCare.orthodonticServices.inNetwork" />
				<s:if
					test="planContainer.plan.planDetails.pediatricDentalCare.orthodonticServices.outOfNetwork != null">
					<br>
					<s:text name="txt.plandetails.outNetwork"></s:text>
					<s:property
						value="planContainer.plan.planDetails.pediatricDentalCare.orthodonticServices.outOfNetwork" />

				</s:if>
			</div>
		</div></li>
	<li><a> <s:text name="txt.plandetails.additionalInformation"></s:text>
	</a> <!-- ADDITIONAL INFORMATION - PDF DOCUMENT -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.document" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.detailedPlan"></s:text></label>
			<div class="rightppart">
				<a target="_blank" id="id_a_pdf-link"
					href="<s:property
								value="getPlanPdfUrl(planContainer.plan.planId, planContainer.plan.csrVarId, planContainer.plan.year, planContainer.plan.langTx, planContainer.plan.carrierId)" />">
					<s:text name="txt.plandetails.view"></s:text>
				</a>
			</div>
		</div> <!-- ADDITIONAL INFORMATION - LINK TO SUMMARY -->
		<div class="extremepart">
			<label><i class="fa fa-question-circle"
				title="<s:text name="txt.plan.hovertext.viewSummary" />"
				data-toggle="tooltip"></i>&nbsp;&nbsp;&nbsp;<s:text
					name="txt.plandetails.viewSummary"></s:text></label>
			<div class="rightppart">
				<a target="_blank" id="id_a_sob-link"
					href="<s:property
							value="planContainer.plan.planDetails.additionalInformation.benefitsAndCoverageLink" />">
					<s:text name="txt.plandetails.summary"></s:text>
				</a>
			</div>
		</div></li>
</ul>
<br />
<br />
<div class="clearfix"></div>
<div class="blank-space-accordion">
	<button type="button" class="btn btn-default float-right"
		data-dismiss="modal">
		<s:text name="txt.button.close" />
	</button>
</div>
<script>
$('.fa-question-circle').each(function() {
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
    $('.fa-question-circle').tooltip(options);
});



</script>

</body>
</html>