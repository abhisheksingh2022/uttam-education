<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@include file="/includes/container.jsp"%>
<style>
.modal-scroll {
	max-height: 600px;
	overflow: auto;
}

.text-align-center {
	text-align: center;
}

.text-align-justify {
	text-align: justify;
}

body {
	padding-right: 0px !important;
	overflow: auto !important;
}
.enroll-now-planlist{
    background: #f59118;
    color: #fff;
    height: 40px;
    width: 200px;
    text-align: center;
    font-size: 20px;
    padding-top: 6px;
    border-radius: 5px;
    margin-left: auto;
    margin-right: auto;
    display: block;
}
}
</style>
<script
	src="<s:property value='rootPath'/>static/custom/js/jquery.accordion.source.js?v=<s:property value="buildId"/>"></script>
<script>
	
<%-- Initializing the barHeight and barTotalWidth variables for proper alignment of the BARS that are drawn for each PLAN --%>
	barHeight = 25;
	barTotalWidth = 175;
<%-- Setting the value of slider to start from sliderMinValue --%>
	sliderMinValue = 0.00;
	function closeWindow() {
		window.close();
	}
	
<%-- Method used for fetching the Plan Details by using the PLAN DETAILS DISPLAY ID and setting PREMIUM AMOUNT in the pop-up for plan details --%>
	function populateModal(object) {
		detailURL = document.getElementById(object).getAttribute("ModalURL");
		planID = document.getElementById(object).getAttribute("planID");
		var actualPremium = document.getElementById(object).getAttribute("totalPremiumMonthly");
<%-- Method from HIXAPI.js to get the plan details from service and displaying the result in the TARGET ID. 
	--	 RESOURCE is having the URL of the ACTION method that needs to be called,
	--	 URLPARAMS is having the parameters that needs to be passed in a GET Request.
	--	 TARGETID is having the id of the element which needs to be populated by the result returned from the ACTION class.
	--%>
	return ajaxGet({
			resource : detailURL,
			targetId : 'id_div_plan_details',
			blockId : 'id_div_plangrid',
			urlparams : 'planDetailsDisplayId=' + planID + '&premiumAmt='
					+ actualPremium
		});
	}
<%-- Method used for fetching integer part from a value having $ at the starting. Eg. getInteger("$500") --> will return value 500.--%>
	function getInteger(number) {
		if (number.indexOf('$') > -1) {
			return number.substring(1);
		} else {
			return number;
		}
	}

	function createMetalLevelsAccordion() {
		$("#id_div_bronzePlans").hide();
		$("#id_div_silverPlans").hide();
		$("#id_div_goldPlans").hide();
		$("#id_div_catastrophicPlans").hide();
		$("#id_div_platinumPlans").hide();
		flag_1 = 0;
		flag_2 = 0;
		flag_3 = 0;
		flag_4 = 0;
		flag_5 = 0;
<%--On Click of Bronze Metal Level, the 'div' containing the BRONZE plans details will open/close. --%>
	$("#id_img_bronzePlans").click(function() {
			if (flag_1 == 0) {
				$("#id_div_bronzePlans").show();
				flag_1 = 1;
			} else {
				$("#id_div_bronzePlans").hide();
				flag_1 = 0;
			}
		});
<%--On Click of Silver Metal Level, the 'div' containing the SILVER plans details will open/close. --%>
	$("#id_img_silverPlans").click(function() {
			if (flag_2 == 0) {
				$("#id_div_silverPlans").show();
				flag_2 = 1;
			} else {
				$("#id_div_silverPlans").hide();
				flag_2 = 0;
			}
		});
<%--On Click of Gold Metal Level, the 'div' containing the GOLD plans details will open/close. --%>
	$("#id_img_goldPlans").click(function() {
			if (flag_3 == 0) {
				$("#id_div_goldPlans").show();
				flag_3 = 1;
			} else {
				$("#id_div_goldPlans").hide();
				flag_3 = 0;
			}
		});
<%--On Click of Catastrophic Metal Level, the 'div' containing the CATASTROPHIC plans details will open/close. --%>
	$("#id_img_catastrophicPlans").click(function() {
			if (flag_4 == 0) {
				$("#id_div_catastrophicPlans").show();
				flag_4 = 1;
			} else {
				$("#id_div_catastrophicPlans").hide();
				flag_4 = 0;
			}
		});
<%--On Click of Platinum Metal Level, the 'div' containing the PLATINUM plans details will open/close. --%>
	$("#id_img_platinumPlans").click(function() {
			if (flag_5 == 0) {
				$("#id_div_platinumPlans").show();
				flag_5 = 1;
			} else {
				$("#id_div_platinumPlans").hide();
				flag_5 = 0;
			}
		});
	}
	
	$.tablesorter.addParser({ 
	    // set a unique id 
	    id: 'thousands',
	    is: function(s) { 
	        // return false so this parser is not auto detected 
	        return false; 
	    }, 
	    format: function(s) {
	        // format your data for normalization 
	        return s.replace('$','').replace(/,/g,'');
	    }, 
	    // set type, either numeric or text 
	    type: 'numeric' 
	}); 
	
<%-- Calculations to be done on page load. --%>
	$(document).ready(function() {
		$("i").each(function() {
			$(this).parent().parent().addClass("position-relative");
		});
		myRadioButton();
		$("#id_title_logo").on("click",function() {
			$("#id_title_logo").attr("href",$("#id_title_logo").attr("href")+ "?doBack=true");
		});
		<%-- 
				 Get the values of slider from the action class. 
			 	 Monthly Slider Values Array(fetches values from sliderValues array in action class).
			--%>
		$(".load-block").fadeOut("slow");
		$('.load-image').fadeOut("slow");
		<%-- Table Sorter API used for sorting the list of plans under a specific metal level. --%>
		$(".myTableSorted").tablesorter({ headers: { 
            3: { 
                sorter:'thousands' 
            } ,
			2: { 
            	sorter:'thousands' 
        	} ,
        	1: { 
            	sorter:'thousands' 
       		} 
        } });
		<%-- List of the plans to be displayed based on the metal levels. The 'div' is hidden initially, and displayed 
				when we click on the particular metal level  --%>
		createMetalLevelsAccordion();
	});
	
	<%-- Method for setting the width and height the LEFT BAR(ORANGE BAR) --%>
	<%-- 
	 1. Get the total width that the specific plan can take. For the plan with 
		maximum amount this would be equal to the barTotalWidth.
	 2. Get the Right Cordinate of the specific plan. For the plan with maximum
			amount this would be equal to the barTotalWidth.
		 3. Get premium calculated from TOTAL COST and OUT OF POCKET COST.	
		 4. Get Mid Cordinate by taking the percent of PREMIUM AMOUNT from the TOTAL 
		 	COST and taking that percent from the Right Cordinate. 
		 5. Setting the width of the left bar from 0 to Mid Cordinate.
	--%>
	function drawImageLeft(total, maxPremium, outOfPocketMax) {
		width = barTotalWidth; // FIXED TO 175 for alignment of BAR and TOTAL COST in one element.
		width = width * (total / maxPremium);
		var rightCordinate = width * (total / maxPremium);
		var premium = total - outOfPocketMax;
		var midCordinate = rightCordinate * (premium / total);
		return midCordinate;

	}
<%-- Method for setting the width and height the RIGHT BAR(GREEN BAR) --%>
<%-- 
 1. Get the total width that the specific plan can take. For the plan with 
	maximum amount this would be equal to the barTotalWidth.
 2. Get the Right Cordinate of the specific plan. For the plan with maximum
	amount this would be equal to the barTotalWidth.
 3. Get premium calculated from TOTAL COST and OUT OF POCKET COST.	
 4. Get Mid Cordinate by taking the percent of PREMIUM AMOUNT from the TOTAL 
 	COST and taking that percent from the Right Cordinate.
--%>
	function drawImageRight(total, maxPremium, outOfPocketMax) {
		width = barTotalWidth; // FIXED TO 175 for alignment of BAR and TOTAL COST in one element.
		width = width * (total / maxPremium);
		var rightCordinate = width * (total / maxPremium);
		var premium = total - outOfPocketMax;
		var midCordinate = rightCordinate * (premium / total);
		return (rightCordinate - midCordinate);
	}

	function drawBarsMonthly() {
		rootpath = <s:property value='rootPath'/>;
		$(".bar")
				.each(
						function() {
							var total = $(this).attr('monthlyTotalPremium');
							var maxPremium = $(this).attr('monthlyMaxPremium');
							var outOfPocketMax = $(this).attr('monthlyOOP');
							leftWidth = drawImageLeft(total, maxPremium,
									outOfPocketMax);
							rightWidth = drawImageRight(total, maxPremium,
									outOfPocketMax);
							height = barHeight;
							var htmlString = "<img src='" + rootpath + "static/custom/images/med.png' alt='Left Bar' height='" + height + "' width = '" + leftWidth + "'>";
							htmlString = htmlString
									+ "<img src='" + rootpath + "static/custom/images/bar.png' alt='Left Bar' height='" + height + "' width = '" + rightWidth + "'>";
							$(this).html(htmlString);
						});
	}

	function drawBarsYearly() {
		rootpath = <s:property value='rootPath'/>;
		$(".bar")
				.each(
						function() {
							var total = $(this).attr('yearlyTotalPremium');
							var maxPremium = $(this).attr('yearlyMaxPremium');
							var outOfPocketMax = $(this).attr('yearlyOOP');
							leftWidth = drawImageLeft(total, maxPremium,
									outOfPocketMax);
							rightWidth = drawImageRight(total, maxPremium,
									outOfPocketMax);
							height = barHeight;
							var htmlString = "<img src='" + rootpath + "static/custom/images/med.png' alt='Left Bar' height='" + height + "' width = '" + leftWidth + "'>";
							htmlString = htmlString
									+ "<img src='" + rootpath + "static/custom/images/bar.png' alt='Left Bar' height='" + height + "' width = '" + rightWidth + "'>";
							$(this).html(htmlString);
						});

	}
<%-- Toggle the ESTIMATED AMOUNT, TOTAL COST, SLIDER and OUT OF POCKET COSTS according to the MONTHLY/YEARLY value selected. --%>
	function myRadioButton() {
		if ($(".myRadio:checked").val() == "M") {
			$(".changeMinMaxYearlyMonthly").each(
					function() {
						minValue = accounting.formatMoney($(this).attr(
								'monthlyValueMin'));
						maxValue = accounting.formatMoney($(this).attr(
								'monthlyValueMax'));
						$(this).text(minValue + "   -   " + maxValue);
					});
			$(".changeValuesOnYearlyMonthly").each(
					function() {
						$(this).text(
								accounting.formatMoney($(this).attr(
										'monthlyValue')));
					});
			drawBarsMonthly();
		} else {
			$(".changeMinMaxYearlyMonthly").each(
					function() {
						minValue = accounting.formatMoney($(this).attr(
								'yearlyValueMin'));
						maxValue = accounting.formatMoney($(this).attr(
								'yearlyValueMax'));
						$(this).text(minValue + " - " + maxValue);
					});
			$(".changeValuesOnYearlyMonthly").each(
					function() {
						$(this).text(
								accounting.formatMoney($(this).attr(
										'yearlyValue')));
					});
			drawBarsYearly();
		}
	}
</script>

<section class="col-md-12 margintoppart">
	<div class="container maincont">
		<div class="row">
			<div id="id_div1">
				<s:form id="id_form_allPlans" action="plan">
					<s:token />
					<s:hidden name="ajaxRequest" value="false" />
					<s:hidden name="formId" />
					<!-- Page loading-->
					<div class="load-block" id="load-block">
						<img class="load-image"
							src='<s:property value='rootPath'/>static/custom/images/loading.gif' />
					</div>
					<div class="col-md-12 hd-btm-sec">
						<div class="bt-header-rht col-md-3" style="width: 500px;">
							<h2 style="display: inline;">
								<s:text name="txt.plandetails.yearlyOrMonthly"></s:text>
								<sup> <i class="fa fa-question-circle"
									data-toggle="tooltip"
									title="<s:text name="txt.plan.hovertext.yearlyOrMonthly"></s:text>"></i>
								</sup>
							</h2>
							&#160;&#160;&#160;
							<s:radio list="lookups.YearlyorMonthly" id="id_sel_lookupId1"
								name="yearlyOrMonthly" listKey="value" listValue="label"
								theme="spaceradio" onclick="myRadioButton();" class="myRadio"></s:radio>
						</div>
						<div class="bt-header-lft col-md-9" style="display: none;">
							<h2>
								<s:text name="txt.plandetails.slider_text"></s:text>
							</h2>
							<div>
								<div id="slider-range-min"></div>
								<div class="chnge row">
									<br> <span class="col-md-6">$0.00</span> <span
										class="col-md-6"><input
										class="changeAPTCMonthlyYearly sliderMaxValue" type="text"
										id="amount" readonly></span>
								</div>
							</div>
						</div>


						<div class="clearfix"></div>
					</div>
					<div class="col-md-12 heading">
						<h2>
							<s:text name="txt.plandetails.header1"></s:text>
						</h2>
					</div>
					<div class="col-lg-12h subscribe">
						<%-- <p>
							<s:text name="txt.plandetails.estimatedCosts"></s:text>
							<b><span class="changeValuesOnYearlyMonthly"
								yearlyValue="<s:property value='planContainer.estimatedCostYearly'/>"
								monthlyValue="<s:property value='planContainer.estimatedCostMonthly'/>"></span></b>
							<sup> <i class="fa fa-question-circle"
								data-toggle="tooltip"
								title="<s:text name="txt.plan.hovertext.estimatedCosts"></s:text>"></i>
							</sup>
							<s:text name="txt.plandetails.estimatedCostsFullStop"></s:text>
						</p> --%>
						<p>
							<s:text name="txt.plandetails.disclaimermsg">
								<s:param name="value">
									<s:property value="planDeterminant.coverageYear" />
								</s:param>
							</s:text>
							<br /><br />
							<s:text name="txt.plandetails.disclaimermsg1"></s:text>
							<br /> <br />
							<s:text name="txt.plandetails.clickMetalLevel"></s:text>
						</p>
					</div>
					<div id="content">
						<div class="ac-Hdng">
							<h2>
								<s:text name="txt.plandetails.metalLevelText"></s:text>
								<sup> <i class="fa fa-question-circle"
									data-toggle="tooltip"
									title="<s:text name="txt.plan.hovertext.metalLevelText"></s:text>"></i>
								</sup>
							</h2>
							<h2 class="wider-tooltip">
								<s:text name="txt.plandetails.totalCostRangeText"></s:text>
								<sup> <i class="fa fa-question-circle"
									data-toggle="tooltip"
									title="<s:text name="txt.plan.hovertext.totalcostrange"></s:text>"></i>
								</sup>
							</h2>

						</div>

						<div class="ac-Content">

							<s:if test="bronzePlans != null && bronzePlans.size() > 0">
								<div class="page_collapsible collapse-close" id="body-section1">

									<div class="row">
										<div class="col-lg-7 metalLevel">
											<s:if test="language == 'Spanish'">
											<img alt="Bronze Plans" id="id_img_bronzePlans"
												class="pointer-toggle"
												src="<s:property value='rootPath'/>static/custom/images/bronze_spanish.png">
											</s:if>
											<s:else>
											<img alt="Bronze Plans" id="id_img_bronzePlans"
												class="pointer-toggle"
												src="<s:property value='rootPath'/>static/custom/images/bronze.png"></s:else>
										</div>
										<div class="col-lg-5 min-max text-align-center">
											<div class="changeMinMaxYearlyMonthly"
												monthlyValueMin="<s:property value='bronzeMin'/>"
												monthlyValueMax="<s:property value='bronzeMax'/>"
												yearlyValueMin="<s:property value='bronzeMinYearly'/>"
												yearlyValueMax="<s:property value='bronzeMaxYearly'/>">
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<div class="ac-dis-con display-block">
								<div class="content">
									<div id="id_div_bronzePlans">
										<jsp:include page="/includes/plan-grid_table-header.jsp"></jsp:include>
										<s:iterator value="bronzePlans" status="bronzeIndex"
											var="bronzePlan">
											<tr class="ac-trCon">
												<td class="td-name planname"><s:url
														action="plandetails_input" var="detailUrl">
													</s:url> <img class="logo-img"
													alt="<s:property value="#bronzePlan.carrierName"/>"
													src="<s:property value='rootPath'/>static/custom/images/<s:property value="#bronzePlan.carrierId"/>.jpg" />
													<a href="#" style="text-decoration: underline;"
													id="id_a_bronze_<s:property value='#bronzeIndex.index'/>"
													data-toggle="modal" data-target="#myModal"
													onclick="populateModal(this.id);" class="BronzeModalClass"
													modalURL="<s:property value='detailUrl'/>"
													planID="<s:property value='#bronzePlan.planDetailsDisplayId'/>"
													totalPremiumMonthly = "<s:property value='#bronzePlan.netPremiumMonthly'/>">
														<s:property value="#bronzePlan.planName" />
												</a></td>
												<td class="ac-net changeValuesOnYearlyMonthly"
													monthlyValue="<s:property value='#bronzePlan.netPremiumMonthly'/>"
													yearlyValue="<s:property value='#bronzePlan.netPremiumYearly'/>">
												</td>
												<td class="ac-co changeValuesOnYearlyMonthly"
													monthlyValue="<s:property value='#bronzePlan.expectedOOPMonthlyStr'/>"
													yearlyValue="<s:property value='#bronzePlan.expectedOOPYearlyStr'/>">
												</td>
												<td>
													<div class="row">
														<div class="bar col-lg-9 text-align-left"
															monthlyTotalPremium="<s:property value='#bronzePlan.totalCostMonthly'/>"
															yearlyTotalPremium="<s:property value='#bronzePlan.totalCostYearly'/>"
															monthlyOOP="<s:property value='#bronzePlan.expectedOOPMonthlyStr'/>"
															yearlyOOP="<s:property value='#bronzePlan.expectedOOPYearlyStr'/>"
															monthlyMaxPremium="<s:property value='bronzeMax'/>"
															yearlyMaxPremium="<s:property value='bronzeMaxYearly'/>">
														</div>
														<div
															class="col-lg-3 changeValuesOnYearlyMonthly stymargin"
															monthlyValue="<s:property value='#bronzePlan.totalCostMonthly'/>"
															yearlyValue="<s:property value='#bronzePlan.totalCostYearly'/>"></div>
													</div>
												</td>
											</tr>
										</s:iterator>
										<jsp:include page="/includes/plan-grid_table-footer.jsp"></jsp:include>
									</div>
								</div>
							</div>
							<s:if test="silverPlans != null && silverPlans.size() > 0">
								<div class="page_collapsible collapse-close" id="body-section2">

									<div class="row">
										<div class="col-lg-7 metalLevel">
											<s:if test="language == 'Spanish'">
												<img alt="Silver Plans" id="id_img_silverPlans"
													class="pointer-toggle"
													src="<s:property value='rootPath'/>static/custom/images/silver_spanish.png">
											</s:if>
											<s:else>
												<img alt="Silver Plans" id="id_img_silverPlans"
													class="pointer-toggle"
													src="<s:property value='rootPath'/>static/custom/images/silver.png">
											</s:else>
										</div>
										<div class="col-lg-5 min-max text-align-center">
											<div class="changeMinMaxYearlyMonthly"
												monthlyValueMin="<s:property value='silverMin'/>"
												monthlyValueMax="<s:property value='silverMax'/>"
												yearlyValueMin="<s:property value='silverMinYearly'/>"
												yearlyValueMax="<s:property value='silverMaxYearly'/>">
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<div class="ac-dis-con display-block">
								<div class="content">
									<div id="id_div_silverPlans">
										<jsp:include page="/includes/plan-grid_table-header.jsp"></jsp:include>
										<s:iterator value="silverPlans" status="silverIndex"
											var="silverPlan">
											<tr class="ac-trCon">
												<td class="planname"><s:url action="plandetails_input"
														var="detailUrl">
													</s:url> <img class="logo-img"
													alt="<s:property value="#silverPlan.carrierName"/>"
													src="<s:property value='rootPath'/>static/custom/images/<s:property value="#silverPlan.carrierId"/>.jpg" />

													<a href="#" style="text-decoration: underline;"
													id="id_a_silver_<s:property value="#silverIndex.index"/>"
													data-toggle="modal" data-target="#myModal"
													onclick="populateModal(this.id);"
													modalURL="<s:property value="detailUrl"/>"
													planID="<s:property value="#silverPlan.planDetailsDisplayId"/>"
													totalPremiumMonthly = "<s:property value='#silverPlan.netPremiumMonthly'/>">
														<s:property value="#silverPlan.planName" />
												</a></td>
												<td class="ac-net changeValuesOnYearlyMonthly"
													monthlyValue="<s:property value='#silverPlan.netPremiumMonthly'/>"
													yearlyValue="<s:property value='#silverPlan.netPremiumYearly'/>">
												</td>
												<td class="ac-co changeValuesOnYearlyMonthly"
													monthlyValue="<s:property value='#silverPlan.expectedOOPMonthlyStr'/>"
													yearlyValue="<s:property value='#silverPlan.expectedOOPYearlyStr'/>">
												</td>
												<td>
													<div class="row">
														<div class="bar col-lg-9 text-align-left"
															monthlyTotalPremium="<s:property value='#silverPlan.totalCostMonthly'/>"
															yearlyTotalPremium="<s:property value='#silverPlan.totalCostYearly'/>"
															monthlyOOP="<s:property value='#silverPlan.expectedOOPMonthlyStr'/>"
															yearlyOOP="<s:property value='#silverPlan.expectedOOPYearlyStr'/>"
															monthlyMaxPremium="<s:property value='silverMax'/>"
															yearlyMaxPremium="<s:property value='silverMaxYearly'/>">
														</div>
														<div
															class="col-lg-3 changeValuesOnYearlyMonthly stymargin"
															monthlyValue="<s:property value='#silverPlan.totalCostMonthly'/>"
															yearlyValue="<s:property value='#silverPlan.totalCostYearly'/>"></div>
														</div>
												</td>
												</tr>
										</s:iterator>
										<jsp:include page="/includes/plan-grid_table-footer.jsp"></jsp:include>
									</div>
								</div>
							</div>
							<s:if test="goldPlans != null && goldPlans.size() > 0">
								<div class="page_collapsible collapse-close" id="body-section1">

									<div class="row">
										<div class="col-lg-7 metalLevel">
											<s:if test="language == 'Spanish'">
												<img alt="Gold Plans" id="id_img_goldPlans"
													class="pointer-toggle"
													src="<s:property value='rootPath'/>static/custom/images/gold_spanish.png">
											</s:if>
											<s:else>
												<img alt="Gold Plans" id="id_img_goldPlans"
													class="pointer-toggle"
													src="<s:property value='rootPath'/>static/custom/images/gold.png">
											</s:else>
										</div>
										<div class="col-lg-5 min-max text-align-center">
											<div class="changeMinMaxYearlyMonthly"
												monthlyValueMin="<s:property value='goldMin'/>"
												monthlyValueMax="<s:property value='goldMax'/>"
												yearlyValueMin="<s:property value='goldMinYearly'/>"
												yearlyValueMax="<s:property value='goldMaxYearly'/>">
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<div class="ac-dis-con display-block">
								<div class="content">
									<div id="id_div_goldPlans">
										<jsp:include page="/includes/plan-grid_table-header.jsp"></jsp:include>
										<s:iterator value="goldPlans" status="goldIndex"
											var="goldPlan">
											<tr class="ac-trCon">
												<td class="planname"><s:url action="plandetails_input"
														var="detailUrl">

													</s:url> <img class="logo-img"
													alt="<s:property value="#goldPlan.carrierName"/>"
													src="<s:property value='rootPath'/>static/custom/images/<s:property value="#goldPlan.carrierId"/>.jpg" />

													<a href="#" style="text-decoration: underline;"
													id="id_a_gold_<s:property value="#goldIndex.index"/>"
													data-toggle="modal" data-target="#myModal"
													onclick="populateModal(this.id);"
													modalURL="<s:property value="detailUrl"/>"
													planID="<s:property value="#goldPlan.planDetailsDisplayId"/>"
													totalPremiumMonthly = "<s:property value='#goldPlan.netPremiumMonthly'/>">
														<s:property value="#goldPlan.planName" />
												</a></td>
												<td class="ac-net changeValuesOnYearlyMonthly"
													monthlyValue="<s:property value='#goldPlan.netPremiumMonthly'/>"
													yearlyValue="<s:property value='#goldPlan.netPremiumYearly'/>">
												</td>
												<td class="ac-co changeValuesOnYearlyMonthly"
													monthlyValue="<s:property value='#goldPlan.expectedOOPMonthlyStr'/>"
													yearlyValue="<s:property value='#goldPlan.expectedOOPYearlyStr'/>">
												</td>
												<td>
													<div class="row">
														<div class="bar col-lg-9 text-align-left"
															monthlyTotalPremium="<s:property value='#goldPlan.totalCostMonthly'/>"
															yearlyTotalPremium="<s:property value='#goldPlan.totalCostYearly'/>"
															monthlyOOP="<s:property value='#goldPlan.expectedOOPMonthlyStr'/>"
															yearlyOOP="<s:property value='#goldPlan.expectedOOPYearlyStr'/>"
															monthlyMaxPremium="<s:property value='goldMax'/>"
															yearlyMaxPremium="<s:property value='goldMaxYearly'/>">
														</div>
														<div
															class="col-lg-3 changeValuesOnYearlyMonthly stymargin"
															monthlyValue="<s:property value='#goldPlan.totalCostMonthly'/>"
															yearlyValue="<s:property value='#goldPlan.totalCostYearly'/>"></div>
													</div>
												</td>
												</tr>
										</s:iterator>
										<jsp:include page="/includes/plan-grid_table-footer.jsp"></jsp:include>
									</div>
								</div>
							</div>
							<s:if
								test="catastrophicPlans != null && catastrophicPlans.size() > 0">
								<div class="page_collapsible collapse-close" id="body-section1">

									<div class="row">
										<div class="col-lg-7 metalLevel">
											<s:if test="language == 'Spanish'">
												<img alt="Catastrophic Plans" id="id_img_catastrophicPlans"
													class="pointer-toggle"
													src="<s:property value='rootPath'/>static/custom/images/cata_spanish.png">
											</s:if>
											<s:else>
												<img alt="Catastrophic Plans" id="id_img_catastrophicPlans"
													class="pointer-toggle"
													src="<s:property value='rootPath'/>static/custom/images/cata.png">
											</s:else>
										</div>
										<div class="col-lg-5 min-max text-align-center">
											<div class="changeMinMaxYearlyMonthly"
												monthlyValueMin="<s:property value='catastrophicMin'/>"
												monthlyValueMax="<s:property value='catastrophicMax'/>"
												yearlyValueMin="<s:property value='catastrophicMinYearly'/>"
												yearlyValueMax="<s:property value='catastrophicMaxYearly'/>">
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<div class="ac-dis-con display-block">
								<div class="content">
									<div id="id_div_catastrophicPlans">
										<jsp:include page="/includes/plan-grid_table-header.jsp"></jsp:include>
										<s:iterator value="catastrophicPlans"
											status="catastrophicIndex" var="catastrophicPlan">
											<tr class="ac-trCon">
												<td class="planname"><s:url action="plandetails_input"
														var="detailUrl">
													</s:url> <img class="logo-img"
													alt="<s:property value="#catastrophicPlan.carrierName"/>"
													src="<s:property value='rootPath'/>static/custom/images/<s:property value="#catastrophicPlan.carrierId"/>.jpg" />
													<a href="#" style="text-decoration: underline;"
													id="id_a_catastrophic_<s:property value="#catastrophicIndex.index"/>"
													data-toggle="modal" data-target="#myModal"
													onclick="populateModal(this.id);"
													modalURL="<s:property value="detailUrl"/>"
													planID="<s:property value="#catastrophicPlan.planDetailsDisplayId"/>"
													totalPremiumMonthly = "<s:property value='#catastrophicPlan.netPremiumMonthly'/>">
														<s:property value="#catastrophicPlan.planName" />
												</a></td>
												<td class="ac-net changeValuesOnYearlyMonthly"
													monthlyValue="<s:property value='#catastrophicPlan.netPremiumMonthly'/>"
													yearlyValue="<s:property value='#catastrophicPlan.netPremiumYearly'/>">
												</td>
												<td class="ac-co changeValuesOnYearlyMonthly"
													monthlyValue="<s:property value='#catastrophicPlan.expectedOOPMonthlyStr'/>"
													yearlyValue="<s:property value='#catastrophicPlan.expectedOOPYearlyStr'/>">
												</td>
												<td>
													<div class="row">
														<div class="bar col-lg-9 text-align-left"
															monthlyTotalPremium="<s:property value='#catastrophicPlan.totalCostMonthly'/>"
															yearlyTotalPremium="<s:property value='#catastrophicPlan.totalCostYearly'/>"
															monthlyOOP="<s:property value='#catastrophicPlan.expectedOOPMonthlyStr'/>"
															yearlyOOP="<s:property value='#catastrophicPlan.expectedOOPYearlyStr'/>"
															monthlyMaxPremium="<s:property value='catastrophicMax'/>"
															yearlyMaxPremium="<s:property value='catastrophicMaxYearly'/>">
														</div>
														<div
															class="col-lg-3 changeValuesOnYearlyMonthly stymargin"
															monthlyValue="<s:property value='#catastrophicPlan.totalCostMonthly'/>"
															yearlyValue="<s:property value='#catastrophicPlan.totalCostYearly'/>"></div>
														</div>
												</td>
													</tr>
										</s:iterator>
										<jsp:include page="/includes/plan-grid_table-footer.jsp"></jsp:include>
									</div>
								</div>
							</div>
							<s:if test="platinumPlans != null && platinumPlans.size() > 0">
								<div class="page_collapsible collapse-close" id="body-section1">
									<div class="row">
										<div class="col-lg-7 metalLevel">
											<s:if test="language == 'Spanish'">
												<img alt="Platinum Plans" id="id_img_platinumPlans"
													class="pointer-toggle"
													src="<s:property value='rootPath'/>static/custom/images/platinum_spanish.png">
											</s:if>
											<s:else>
												<img alt="Platinum Plans" id="id_img_platinumPlans"
													class="pointer-toggle"
													src="<s:property value='rootPath'/>static/custom/images/platinum.png">
											</s:else>
										</div>
										<div class="col-lg-5 min-max text-align-center">
											<div class="changeMinMaxYearlyMonthly"
												monthlyValueMin="<s:property value='platinumMin'/>"
												monthlyValueMax="<s:property value='platinumMax'/>"
												yearlyValueMin="<s:property value='platinumMinYearly'/>"
												yearlyValueMax="<s:property value='platinumMaxYearly'/>">
											</div>
										</div>
									</div>
								</div>
							</s:if>
							<div class="ac-dis-con display-block">
								<div class="content">
									<div id="id_div_platinumPlans">
										<jsp:include page="/includes/plan-grid_table-header.jsp"></jsp:include>
										<s:iterator value="platinumPlans" status="platinumIndex"
											var="platinumPlan">
											<tr class="ac-trCon">
												<td class="planname"><s:url action="plandetails_input"
														var="detailUrl" escapeAmp="false">
													</s:url> <img class="logo-img"
													alt="<s:property value="#platinumPlan.carrierName"/>"
													src="<s:property value='rootPath'/>static/custom/images/<s:property value="#platinumPlan.carrierId"/>.jpg" />

													<a href="#" style="text-decoration: underline;"
													id="id_a_platinum_<s:property value="#platinumIndex.index"/>"
													data-toggle="modal" data-target="#myModal"
													onclick="populateModal(this.id);"
													modalURL="<s:property value="detailUrl"/>"
													planID="<s:property value="#platinumPlan.planDetailsDisplayId"/>"
													totalPremiumMonthly = "<s:property value='#platinumPlan.netPremiumMonthly'/>">
														<s:property value="#platinumPlan.planName" />
												</a></td>
												<td class="ac-net changeValuesOnYearlyMonthly"
													monthlyValue="<s:property value='#platinumPlan.netPremiumMonthly'/>"
													yearlyValue="<s:property value='#platinumPlan.netPremiumYearly'/>">
												</td>
												<td class="ac-co changeValuesOnYearlyMonthly"
													monthlyValue="<s:property value='#platinumPlan.expectedOOPMonthlyStr'/>"
													yearlyValue="<s:property value='#platinumPlan.expectedOOPYearlyStr'/>">
												</td>

												<td><div class="row">
														<div class="bar col-lg-9 text-align-left"
															monthlyTotalPremium="<s:property value='#platinumPlan.totalCostMonthly'/>"
															yearlyTotalPremium="<s:property value='#platinumPlan.totalCostYearly'/>"
															monthlyOOP="<s:property value='#platinumPlan.expectedOOPMonthlyStr'/>"
															yearlyOOP="<s:property value='#platinumPlan.expectedOOPYearlyStr'/>"
															monthlyMaxPremium="<s:property value='platinumMax'/>"
															yearlyMaxPremium="<s:property value='platinumMaxYearly'/>">
														</div>
														<div
															class="col-lg-3 changeValuesOnYearlyMonthly stymargin"
															monthlyValue="<s:property value='#platinumPlan.totalCostMonthly'/>"
															yearlyValue="<s:property value='#platinumPlan.totalCostYearly'/>"></div>
															</div></td>
</tr>
										</s:iterator>
										<jsp:include page="/includes/plan-grid_table-footer.jsp"></jsp:include>
									</div>
										<div class="modal fade" id="myModal" role="dialog">
										<div class="modal-dialog modal-lg">
											<div class="modal-content">
												<div class="modal-body modal-scroll">
													<div id="id_div_plan_details"></div>
												</div>
												<div class="clearfix"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div>
							<br>
							<s:text name="txt.plandetails.pleaseVisit"></s:text>
							<a href="http://www.accesshealthct.com" target="_blank"><s:text
									name="txt.plandetails.ahct"></s:text></a>
							<s:text name="txt.plandetails.buyPlan"></s:text>
						</div>
							<div class="clearfix"></div><br>
						<a class="enroll-now-planlist" target="_blank" href="https://www.accesshealthct.com/AHCT/ApplyPlanEYO.action">  <s:text name="txt.household.enroll-now" /> </a>
							<div class="clearfix"></div>
						<div id="id_div_disclaimerMessage" class="text-align-justify">
							<br />
							<div class="col-md-12 heading">
								<h2>
									<s:text name="txt.plandetails.disclaimer"></s:text>
								</h2>
							</div>
							<br /> <br /> <br />
							<s:text name="txt.plandetails.disclaimer1"></s:text>
							<hr>
							<s:text name="txt.plandetails.disclaimer7"></s:text>
							<ul>
								<li><s:text name="txt.plandetails.disclaimer2"></s:text></li>
								<li><s:text name="txt.plandetails.disclaimer3"></s:text></li>
								<li><s:text name="txt.plandetails.disclaimer4"></s:text></li>
<%-- 								<li><s:text name="txt.plandetails.disclaimer5"></s:text></li> --%>
								<li><s:text name="txt.plandetails.disclaimer6"></s:text></li>
							</ul>
						</div>
						<div class="row">
							<%-- <div class="chkbackBt">
								<input class="back" type="button"
									value="< <s:text name="txt.global.button2"></s:text>"
									onclick="window.history.back();" />
							</div> --%>

							<div class="chkbackBt">
								<input class="back" type="button"
									value="< <s:text name="txt.global.button2"></s:text>"
									onclick="return setActionAndSubmit('id_form_allPlans', 'goBack');" />
							</div>

							<div class="chknextBt">
								<input type="button"
									value="<s:text name="txt.global.close"></s:text>"
									onclick="closeWindow()" />
							</div>
						</div>

					</div>
				</s:form>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
</section>

<script>
$('.fa-question-circle').each(function() {
    var options = {
        placement: function (context, source) {
	            var position = $(source).offset();
	            if (position.left < 280) {
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
/* 

//$tooltip.options.template = $template[0].outerHTML;
$(document).ready(function(){
	$("[data-toggle='tooltip']").tooltip({
	    container: 'body'
	  
	  }).on('shown.bs.tooltip', function () {
		//alert("1");	
		var $tooltip = $(this).data('bs.tooltip')
		var $template = $($tooltip.options.template)

		alert($template.find(".tooltip-inner").height());
			if($(this).next().height() > 500){
				$(this).child(".tooltip-inner").addClass("tooltip-inner-wider");
			}
		});
	
}); */

</script>



<%@include file="/includes/footer.jsp"%>