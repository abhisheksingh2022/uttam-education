package com.hixapi.ahct.cds.struts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.hixapi.ahct.cds.model.AptcDeterminant;
import com.hixapi.ahct.cds.model.Eligibility;
import com.hixapi.ahct.cds.model.Household;
import com.hixapi.ahct.cds.model.MedicalCost;
import com.hixapi.ahct.cds.model.plan.HealthPlanDeterminant;
import com.hixapi.ahct.cds.model.plan.Plan;
import com.hixapi.ahct.cds.model.plan.PlanContainer;
import com.hixapi.ahct.cds.service.CDSService;
import com.hixapi.web.framework.common.ConversionUtil;
import com.hixapi.web.framework.service.ServiceLocator;
import com.hixapi.web.framework.struts.BaseAction;
import com.hixapi.web.framework.struts.SessionController;

public class AllPlanGridAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5036585345699319849L;
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(AllPlanGridAction.class);

	HealthPlanDeterminant planDeterminant;
	private PlanContainer planContainer;
	private List<Plan> bronzePlans;
	private List<Plan> silverPlans;
	private List<Plan> goldPlans;
	private List<Plan> platinumPlans;
	private List<Plan> catastrophicPlans;
	private Double bronzeMin;
	private Double bronzeMax;
	private Double silverMin;
	private Double silverMax;
	private Double goldMin;
	private Double goldMax;
	private Double platinumMin;
	private Double platinumMax;
	private Double catastrophicMin;
	private Double catastrophicMax;
	private Double bronzeMinYearly;
	private Double bronzeMaxYearly;
	private Double silverMinYearly;
	private Double silverMaxYearly;
	private Double goldMinYearly;
	private Double goldMaxYearly;
	private Double platinumMinYearly;
	private Double platinumMaxYearly;
	private Double catastrophicMinYearly;
	private Double catastrophicMaxYearly;
	private Set<Double> sliderValues;
	private Set<Double> sliderValuesYearly;
	private Boolean catastrophicFlag;
	private String yearlyOrMonthly = "M";
	private String language;

	private void initPlanList() {
		bronzePlans = new ArrayList<Plan>();
		silverPlans = new ArrayList<Plan>();
		goldPlans = new ArrayList<Plan>();
		platinumPlans = new ArrayList<Plan>();
		catastrophicPlans = new ArrayList<Plan>();
	}

	void setPlanList() {
		List<Plan> plans = getPlanContainer().getPlans();
		Iterator<Plan> iterator = plans.iterator();
		while (iterator.hasNext()) {
			Plan plan = iterator.next();
			if ("Bronze".equalsIgnoreCase(plan.getMetalLevelCode())
					|| "Bronce".equalsIgnoreCase(plan.getMetalLevelCode())) {
				bronzePlans.add(plan);
			} else if ("Silver".equalsIgnoreCase(plan.getMetalLevelCode())
					|| "Plata".equalsIgnoreCase(plan.getMetalLevelCode())) {
				silverPlans.add(plan);
			} else if ("Gold".equalsIgnoreCase(plan.getMetalLevelCode())
					|| "Oro".equalsIgnoreCase(plan.getMetalLevelCode())) {
				goldPlans.add(plan);
			} else if ("Platinum".equalsIgnoreCase(plan.getMetalLevelCode())
					|| "Platino".equalsIgnoreCase(plan.getMetalLevelCode())) {
				platinumPlans.add(plan);
			} else if ("Catastrophic"
					.equalsIgnoreCase(plan.getMetalLevelCode())
					|| "Catastrophic"
							.equalsIgnoreCase(plan.getMetalLevelCode())) {
				catastrophicPlans.add(plan);
			}
		}
	}

	private Double findMinMax(List<Plan> plans, String s, String monthlyOrYearly) {
		Double minMax = 0D;
		if (monthlyOrYearly.equals("monthly")) {
			minMax = ConversionUtil
					.toDouble(plans.get(0).getTotalCostMonthly());
			if (s.equals("min")) {
				for (Plan plan : plans) {
					Double val = ConversionUtil.toDouble(plan
							.getTotalCostMonthly());
					if (minMax > val) {
						minMax = val;
					}
				}
			} else {
				for (Plan plan : plans) {
					Double val = ConversionUtil.toDouble(plan
							.getTotalCostMonthly());
					if (minMax < val) {
						minMax = val;
					}
				}
			}
		} else {
			minMax = ConversionUtil.toDouble(plans.get(0).getTotalCostYearly());
			if (s.equals("min")) {
				for (Plan plan : plans) {
					Double val = ConversionUtil.toDouble(plan
							.getTotalCostYearly());
					if (minMax > val) {
						minMax = val;
					}
				}
			} else {
				for (Plan plan : plans) {
					Double val = ConversionUtil.toDouble(plan
							.getTotalCostYearly());
					if (minMax < val) {
						minMax = val;
					}
				}
			}
		}
		return minMax;
	}

	private void calculateMinMaxForAll() {
		if (bronzePlans.size() > 0) {
			setBronzeMin(findMinMax(bronzePlans, "min", "monthly"));
			setBronzeMax(findMinMax(bronzePlans, "max", "monthly"));
			setBronzeMinYearly(findMinMax(bronzePlans, "min", "yearly"));
			setBronzeMaxYearly(findMinMax(bronzePlans, "max", "yearly"));
		}
		if (silverPlans.size() > 0) {
			setSilverMin(findMinMax(silverPlans, "min", "monthly"));
			setSilverMax(findMinMax(silverPlans, "max", "monthly"));
			setSilverMinYearly(findMinMax(silverPlans, "min", "yearly"));
			setSilverMaxYearly(findMinMax(silverPlans, "max", "yearly"));
		}
		if (goldPlans.size() > 0) {
			setGoldMin(findMinMax(goldPlans, "min", "monthly"));
			setGoldMax(findMinMax(goldPlans, "max", "monthly"));
			setGoldMinYearly(findMinMax(goldPlans, "min", "yearly"));
			setGoldMaxYearly(findMinMax(goldPlans, "max", "yearly"));
		}
		if (platinumPlans.size() > 0) {
			setPlatinumMin(findMinMax(platinumPlans, "min", "monthly"));
			setPlatinumMax(findMinMax(platinumPlans, "max", "monthly"));
			setPlatinumMinYearly(findMinMax(platinumPlans, "min", "yearly"));
			setPlatinumMaxYearly(findMinMax(platinumPlans, "max", "yearly"));
		}
		if (catastrophicPlans.size() > 0) {
			// if (catastrophicFlag == true) {
			setCatastrophicMin(findMinMax(catastrophicPlans, "min", "monthly"));
			setCatastrophicMax(findMinMax(catastrophicPlans, "max", "monthly"));
			setCatastrophicMinYearly(findMinMax(catastrophicPlans, "min",
					"yearly"));
			setCatastrophicMaxYearly(findMinMax(catastrophicPlans, "max",
					"yearly"));
			// }
		}

	}

	private Set<Double> calculateSlider(Double maxAPTC) {
		Integer max = (int) Math.floor(maxAPTC);
		Set<Double> sliderValues = new TreeSet<Double>();
		for (int i = 0; i <= max; i++) {
			sliderValues.add((double) i);
		}
		sliderValues.add(maxAPTC);
		return sliderValues;
	}

	/*
	 * Method for returning to HealthStatus Page on click of Back Button
	 */
	public String goBack() {
		log.debug("IN GOBACK");

		return BACK;
	}

	@Override
	protected void loadDisplayContents() throws Exception {
		language = SessionController.getLanguage().getDisplayLanguage();
		Eligibility eligibility = SessionController.getEligibility();
		MedicalCost medicalCost = SessionController.getMedicalCost();
//		if(medicalCost.getExpectedOOP().size() != 0)
//		{
		Household household = SessionController.getHousehold();
		AptcDeterminant aptcFactors = eligibility.getAptcFactors();
		Double aptcAmountMonthly = eligibility.getAptcAmount();
		Double aptcAmountYearly = aptcAmountMonthly * 12;

		setYearlyOrMonthly("M");
		planDeterminant = new HealthPlanDeterminant();
		planDeterminant.setCoverageYear(eligibility
				.getCoverageYear());
		planDeterminant.setCounty(household.getCounty());
		planDeterminant.setPregnantPersonPresent(eligibility.isPregHouse());

		if (eligibility.isCreditEligibility()) {
			planDeterminant.setRatingAreaId(aptcFactors.getRatingAreaId());
			planDeterminant.setMemberList(aptcFactors.getAptcMembers());
			planDeterminant.setAgeCategories(aptcFactors
					.getHshlSubsidyAgeCategories());
			planDeterminant.setAptcAmountMonthly(aptcAmountMonthly);
			planDeterminant.setAptcAmountYearly(aptcAmountYearly);
			planDeterminant.setFamilyCreditEligibility(true);
		} else {
			planDeterminant.setMemberList(SessionController.getHousehold()
					.getHouseholdMembers());
			planDeterminant.setFamilyCreditEligibility(false);
		}
		planDeterminant.setCsrVarIdForPlansFetch(eligibility
				.getCsrVarIdForPlansFetch());
		setPlanContainer(ServiceLocator.getInstance()
				.getService(CDSService.class).retrievePlans(planDeterminant));
		// for(Plan pl: planContainer.getPlans())
		// {
		// System.out.println("plan Id from All plangrid and csr Id "+
		// pl.getPlanId() +" , "+pl.getCsrVarId());
		// }
		planContainer.setMaxAPTCAmount(aptcAmountMonthly);
		setCatastrophicFlag(planContainer.getCatastrophicFlag());
		// This below estimatedAmount is for household need to get the cost for
		// household by multiplying totalUtliz*AvgCost For service.

		populateExpectedOOPs(medicalCost, planContainer.getPlans());
		populateTotalCosts(planContainer.getPlans(),
				medicalCost.getNoMedicalConditionsHouse());
		setSliderValues(calculateSlider(aptcAmountMonthly));
		setSliderValuesYearly(calculateSlider(aptcAmountYearly));
		// --------------Below values are kept for sake of UI bar width
		// calculation.
		BigDecimal estimatedCost = medicalCost.getEstimatedCost();
		// BigDecimal monthlyEst = setEstimatedCost(estimatedCost);
		planContainer.setEstimatedAmount(estimatedCost);
		
		// below code is used for display in that expected Max oop on
		// PlanListing page.
		if (medicalCost.getNoMedicalConditionsHouse() == false) {
			Map<String, BigDecimal> expectedOOPMap = medicalCost
					.getExpectedOOP();

			setExpectedOOP(expectedOOPMap);
		} else {
			setExpectedOOPOld(planContainer.getPlans());
		}
		initPlanList();
		setPlanList();
		//to make sure there were TplanBenefit that were fetched. if size is not zero then there were some plan benefit calculation run.
		
		calculateMinMaxForAll();
		
		planContainer.setEstimatedCostMonthly(ConversionUtil.toString(medicalCost.getEstimatedCost().doubleValue()));
		planContainer.setEstimatedCostYearly(medicalCost.getMedicalCostInsured());
		// --------up till here

	/**	log.debug(
				"printing medical cost insured: {} , medical cost uninsured : {}",
				medicalCost.getMedicalCostInsured(),
				medicalCost.getMedicalCostUninsured());
		log.debug("Printing plan listing page value, Monthly: ");
		for (Plan pl : planContainer.getPlans()) {
			log.debug(
					" Plan Name : {} plan Id : {} netPremium : {} expectedOOP : {} total Cost : {}",
					pl.getPlanName(), pl.getPlanId(),
					pl.getNetPremiumMonthly(), pl.getExpectedOOPMonthlyStr(),
					pl.getTotalCostMonthly());
		}
		log.debug("Printing plan listing page value,Yearly: ");
		for (Plan pl : planContainer.getPlans()) {
			log.debug(
					" Plan Name : {} plan Id : {} netPremium : {} expectedOOP : {} total Cost : {}",
					pl.getPlanName(), pl.getPlanId(), pl.getNetPremiumYearly(),
					pl.getExpectedOOPYearlyStr(), pl.getTotalCostYearly()); 
		}**/
		}
	//}

	// --kept for sake of UI bar width caculation values
	// when no medical conditions in household
	private void setExpectedOOPOld(List<Plan> plans) {
		Double expectedOOP = 0D;
		for (Plan pl : plans) {
			pl.setExpectedOOP(new BigDecimal(expectedOOP));
		}

	}

	/**
	 * Setting the calculated expectedMaxOOP for displaying in UI
	 * 
	 * @param expectedOOPMap
	 */
	private void setExpectedOOP(Map<String, BigDecimal> expectedOOPMap) {
		for (String planId : expectedOOPMap.keySet()) {
			Plan plan = findPlan(planId);
			plan.setExpectedOOP(expectedOOPMap.get(planId));
		}

	}

	// ------------

	private void populateExpectedOOPs(MedicalCost medicalCost, List<Plan> plans) {
		if (medicalCost.getNoMedicalConditionsHouse() == false) {
			Map<String, String> monthlyOOPMapStr = medicalCost
					.getExpectedOOPMonthlyMap();
			Map<String, String> yearlyOOPMapStr = medicalCost
					.getExpectedOOPYearlyMap();
			if(monthlyOOPMapStr.size() != 0)
			{
			setMonthlyOOP(monthlyOOPMapStr);
			setYearlyOOP(yearlyOOPMapStr);
			}
		}

		else {
			// sets both yearly and monthly as 0.00
			setExpectedOOP(planContainer.getPlans());
		}

	}

	private void populateTotalCosts(List<Plan> plans,
			Boolean noMedicalConditionsHouse) {

		for (Plan pl : plans) {
			if (noMedicalConditionsHouse == false) {
				if(pl.getNetPremiumMonthly() !=null && pl.getExpectedOOPMonthlyStr() != null)
				{
				BigDecimal monthlyTotalCost = new BigDecimal(
						pl.getNetPremiumMonthly()).add(
						new BigDecimal(pl.getExpectedOOPMonthlyStr()))
						.setScale(2, RoundingMode.UP);
				pl.setTotalCostMonthly(monthlyTotalCost.toString());
				}
				if(pl.getNetPremiumYearly() != null && pl.getExpectedOOPYearlyStr() != null)
				{
				BigDecimal yearlyTotalCost = new BigDecimal(
						pl.getNetPremiumYearly()).add(
						new BigDecimal(pl.getExpectedOOPYearlyStr())).setScale(
						2, RoundingMode.UP);
				pl.setTotalCostYearly(yearlyTotalCost.toString());
				}
			} else {
				pl.setTotalCostMonthly(pl.getNetPremiumMonthly());
				pl.setTotalCostYearly(pl.getNetPremiumYearly());
			}

		}

	}

	// when medical conditions in household
	private void setYearlyOOP(Map<String, String> yearlyOOPMapStr) {
		for (String planId : yearlyOOPMapStr.keySet()) {
			Plan plan = findPlan(planId);
			plan.setExpectedOOPYearlyStr(yearlyOOPMapStr.get(planId));
		}

	}

	// when medical conditions in household
	private void setMonthlyOOP(Map<String, String> monthlyOOPMapStr) {
		for (String planId : monthlyOOPMapStr.keySet()) {
			Plan plan = findPlan(planId);
			plan.setExpectedOOPMonthlyStr(monthlyOOPMapStr.get(planId));
		}

	}

	// when no medical conditions in household
	private void setExpectedOOP(List<Plan> plans) {
		String value = "0.00";
		for (Plan pl : plans) {
			pl.setExpectedOOPMonthlyStr(value);
			pl.setExpectedOOPYearlyStr(value);
		}

	}

	private Plan findPlan(String planId) {
		for (Plan plan : planContainer.getPlans()) {
			if (planId.equalsIgnoreCase(plan.getPlanId() + "-"
					+ plan.getCsrVarId())) {
				return plan;
			}
		}
		if (bronzePlans != null && bronzePlans.size() > 0) {
			for (Plan plan : bronzePlans) {
				if (planId.equalsIgnoreCase(plan.getPlanId() + "-"
						+ plan.getCsrVarId())) {
					return plan;
				}
			}
		}
		if (silverPlans != null && silverPlans.size() > 0) {
			for (Plan plan : silverPlans) {
				if (planId.equalsIgnoreCase(plan.getPlanId() + "-"
						+ plan.getCsrVarId())) {
					return plan;
				}
			}
		}
		if (goldPlans != null && goldPlans.size() > 0) {
			for (Plan plan : goldPlans) {
				if (planId.equalsIgnoreCase(plan.getPlanId() + "-"
						+ plan.getCsrVarId())) {
					return plan;
				}
			}
		}
		if (platinumPlans != null && platinumPlans.size() > 0) {
			for (Plan plan : platinumPlans) {
				if (planId.equalsIgnoreCase(plan.getPlanId() + "-"
						+ plan.getCsrVarId())) {
					return plan;
				}
			}
		}
		if (catastrophicPlans != null && catastrophicPlans.size() > 0) {
			for (Plan plan : catastrophicPlans) {
				if (planId.equalsIgnoreCase(plan.getPlanId() + "-"
						+ plan.getCsrVarId())) {
					return plan;
				}
			}
		}
		return null;
	}

	@Override
	protected String handleExecute() throws Exception {
		// Store information from input to session via SessionController, do any
		// action that is required
		return null;
	}

	public HealthPlanDeterminant getPlanDeterminant() {
		return planDeterminant;
	}

	public void setPlanDeterminant(HealthPlanDeterminant planDeterminant) {
		this.planDeterminant = planDeterminant;
	}

	public Boolean getCatastrophicFlag() {
		return catastrophicFlag;
	}

	public void setCatastrophicFlag(Boolean catastrophicFlag) {
		this.catastrophicFlag = catastrophicFlag;
	}

	public PlanContainer getPlanContainer() {
		return planContainer;
	}

	public void setPlanContainer(PlanContainer planContainer) {
		this.planContainer = planContainer;
	}

	public String getYearlyOrMonthly() {
		return yearlyOrMonthly;
	}

	public void setYearlyOrMonthly(String yearlyOrMonthly) {
		this.yearlyOrMonthly = yearlyOrMonthly;
	}

	public List<Plan> getBronzePlans() {
		return bronzePlans;
	}

	public void setBronzePlans(List<Plan> bronzePlans) {
		this.bronzePlans = bronzePlans;
	}

	public List<Plan> getSilverPlans() {
		return silverPlans;
	}

	public void setSilverPlans(List<Plan> silverPlans) {
		this.silverPlans = silverPlans;
	}

	public List<Plan> getGoldPlans() {
		return goldPlans;
	}

	public void setGoldPlans(List<Plan> goldPlans) {
		this.goldPlans = goldPlans;
	}

	public List<Plan> getPlatinumPlans() {
		return platinumPlans;
	}

	public void setPlatinumPlans(List<Plan> platinumPlans) {
		this.platinumPlans = platinumPlans;
	}

	public List<Plan> getCatastrophicPlans() {
		return catastrophicPlans;
	}

	public void setCatastrophicPlans(List<Plan> catastrophicPlans) {
		this.catastrophicPlans = catastrophicPlans;
	}

	public Set<Double> getSliderValues() {
		return sliderValues;
	}

	public void setSliderValues(Set<Double> sliderValues) {
		this.sliderValues = sliderValues;
	}

	public Set<Double> getSliderValuesYearly() {
		return sliderValuesYearly;
	}

	public void setSliderValuesYearly(Set<Double> sliderValuesYearly) {
		this.sliderValuesYearly = sliderValuesYearly;
	}

	/**
	 * @return the bronzeMin
	 */
	public Double getBronzeMin() {
		return bronzeMin;
	}

	/**
	 * @param bronzeMin
	 *            the bronzeMin to set
	 */
	public void setBronzeMin(Double bronzeMin) {
		this.bronzeMin = bronzeMin;
	}

	public Double getBronzeMax() {
		return bronzeMax;
	}

	public void setBronzeMax(Double bronzeMax) {
		this.bronzeMax = bronzeMax;
	}

	public Double getSilverMin() {
		return silverMin;
	}

	public void setSilverMin(Double silverMin) {
		this.silverMin = silverMin;
	}

	public Double getSilverMax() {
		return silverMax;
	}

	public void setSilverMax(Double silverMax) {
		this.silverMax = silverMax;
	}

	public Double getGoldMin() {
		return goldMin;
	}

	public void setGoldMin(Double goldMin) {
		this.goldMin = goldMin;
	}

	public Double getGoldMax() {
		return goldMax;
	}

	public void setGoldMax(Double goldMax) {
		this.goldMax = goldMax;
	}

	public Double getPlatinumMin() {
		return platinumMin;
	}

	public void setPlatinumMin(Double platinumMin) {
		this.platinumMin = platinumMin;
	}

	public Double getPlatinumMax() {
		return platinumMax;
	}

	public void setPlatinumMax(Double platinumMax) {
		this.platinumMax = platinumMax;
	}

	public Double getCatastrophicMin() {
		return catastrophicMin;
	}

	public void setCatastrophicMin(Double catastrophicMin) {
		this.catastrophicMin = catastrophicMin;
	}

	public Double getCatastrophicMax() {
		return catastrophicMax;
	}

	public void setCatastrophicMax(Double catastrophicMax) {
		this.catastrophicMax = catastrophicMax;
	}

	public Double getBronzeMinYearly() {
		return bronzeMinYearly;
	}

	public void setBronzeMinYearly(Double bronzeMinYearly) {
		this.bronzeMinYearly = bronzeMinYearly;
	}

	public Double getBronzeMaxYearly() {
		return bronzeMaxYearly;
	}

	public void setBronzeMaxYearly(Double bronzeMaxYearly) {
		this.bronzeMaxYearly = bronzeMaxYearly;
	}

	public Double getSilverMinYearly() {
		return silverMinYearly;
	}

	public void setSilverMinYearly(Double silverMinYearly) {
		this.silverMinYearly = silverMinYearly;
	}

	public Double getSilverMaxYearly() {
		return silverMaxYearly;
	}

	public void setSilverMaxYearly(Double silverMaxYearly) {
		this.silverMaxYearly = silverMaxYearly;
	}

	public Double getGoldMinYearly() {
		return goldMinYearly;
	}

	public void setGoldMinYearly(Double goldMinYearly) {
		this.goldMinYearly = goldMinYearly;
	}

	public Double getGoldMaxYearly() {
		return goldMaxYearly;
	}

	public void setGoldMaxYearly(Double goldMaxYearly) {
		this.goldMaxYearly = goldMaxYearly;
	}

	public Double getPlatinumMinYearly() {
		return platinumMinYearly;
	}

	public void setPlatinumMinYearly(Double platinumMinYearly) {
		this.platinumMinYearly = platinumMinYearly;
	}

	public Double getPlatinumMaxYearly() {
		return platinumMaxYearly;
	}

	public void setPlatinumMaxYearly(Double platinumMaxYearly) {
		this.platinumMaxYearly = platinumMaxYearly;
	}

	public Double getCatastrophicMinYearly() {
		return catastrophicMinYearly;
	}

	public void setCatastrophicMinYearly(Double catastrophicMinYearly) {
		this.catastrophicMinYearly = catastrophicMinYearly;
	}

	public Double getCatastrophicMaxYearly() {
		return catastrophicMaxYearly;
	}

	public void setCatastrophicMaxYearly(Double catastrophicMaxYearly) {
		this.catastrophicMaxYearly = catastrophicMaxYearly;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
