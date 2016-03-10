/**
 * 
 */
package com.hixapi.ahct.cds.service.impl;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hixapi.ahct.cds.model.Eligibility;
import com.hixapi.ahct.cds.model.Household;
import com.hixapi.ahct.cds.model.MedicalCost;
import com.hixapi.ahct.cds.model.plan.HealthPlanDeterminant;
import com.hixapi.ahct.cds.model.plan.Plan;
import com.hixapi.ahct.cds.model.plan.PlanContainer;
import com.hixapi.ahct.cds.model.plan.PlanDetailsFinder;
import com.hixapi.ahct.cds.service.CDSService;
import com.hixapi.web.framework.common.APIUtil;
import com.hixapi.web.framework.context.ContextKeyEnum;
import com.hixapi.web.framework.context.ContextProvider;
import com.hixapi.web.framework.service.BaseRestServiceClient;

/**
 * @author admin
 *
 */
public class CDSServiceImpl extends BaseRestServiceClient implements CDSService {

	private static final String SERVICE_CODE_ELIGIBILITY = "resturl.cds.eligibilityCheck";
	private static final String SERVICE_CODE_MEDICALCOST = "resturl.cds.medicalCost";

	private static final Logger log = LogManager
			.getLogger(CDSServiceImpl.class);
	private static final String SERVICE_CODE_PLANSLIST = "resturl.cds.planListing";
	private static final String SERVICE_CODE_PLANDETAILS = "resturl.cds.planDetails";

	@Override
	public Eligibility calculateEligibility(Household hs) {
		log.entry();
		// example
		String url = getEndpoint(SERVICE_CODE_ELIGIBILITY);
		RestTemplate client = getRestClient();
		HttpEntity<Household> body = getRestPostEntity(hs);

		ResponseEntity<Eligibility> eligEntity = client.exchange(url,
				HttpMethod.POST, body, Eligibility.class);
		Eligibility eligibility = eligEntity.getBody();
		return log.exit(eligibility);
	}

	@Override
	public MedicalCost calculateTotalMedicalCosts(Household hs) {
		log.entry();
		// example
		String url = getEndpoint(SERVICE_CODE_MEDICALCOST);
		RestTemplate client = getRestClient();
		HttpEntity<Household> body = getRestPostEntity(hs);

		ResponseEntity<MedicalCost> eligEntity = client.exchange(url,
				HttpMethod.POST, body, MedicalCost.class);
		MedicalCost medicalCost = eligEntity.getBody();
		return log.exit(medicalCost);
	}

	
	public Plan fetchPlanDetails(PlanContainer planContainer, String planId) {
		for (Plan plan : planContainer.getPlans()) {
			if (plan.getPlanId().equals(planId)) {
				return plan;
			}
		}
		return null;
	}

	@Override
	public PlanContainer retrievePlans(HealthPlanDeterminant planDeterminant) {
		log.entry();
		Locale locale = ContextProvider.getContextField(ContextKeyEnum.CURRENT_LOCALE, Locale.class);
		if(locale == null){
			locale = APIUtil.getLocale("en_US");
		}
		planDeterminant.setLanguage(APIUtil.getTwoLetterLanguageCode(locale.toString()));
		String url = getEndpoint(SERVICE_CODE_PLANSLIST);
		RestTemplate client = getRestClient();
		HttpEntity<HealthPlanDeterminant> body = getRestPostEntity(planDeterminant);
		ResponseEntity<PlanContainer> planEntity = client.exchange(url,
				HttpMethod.POST, body, PlanContainer.class);
		PlanContainer planContainer = planEntity.getBody();
		
		// PlanContainer planContainer = new PlanContainer();
		// planContainer.setMaxAPTCAmount();
		// //based on csrvarid, year, language.
		// List<Plan> plans = new ArrayList<Plan>();
		// for (int i = 0; i < 16; i++) {
		// plans.add(createPlan(i));
		// }
		// planContainer.setPlans(plans);

		return log.exit(planContainer);
	}

	@Override
	public PlanContainer retrievePlan(PlanDetailsFinder planDetailsFinder) {
		log.entry();
		Locale locale = ContextProvider.getContextField(ContextKeyEnum.CURRENT_LOCALE, Locale.class);
		if(locale == null){
			locale = APIUtil.getLocale("en_US");
		}
		planDetailsFinder.setLanguage(APIUtil.getTwoLetterLanguageCode(locale.toString()));
		
		String url = getEndpoint(SERVICE_CODE_PLANDETAILS);
		RestTemplate client = getRestClient();
		HttpEntity<PlanDetailsFinder> body = getRestPostEntity(planDetailsFinder);
		ResponseEntity<PlanContainer> planEntity = client.exchange(url,
				HttpMethod.POST, body, PlanContainer.class);
		PlanContainer planContainer = planEntity.getBody();
		return log.exit(planContainer);
	}

	/**
	 * @Override public Double getEstimatedAmount(List<Individual> persons) {
	 *           Double estimatedCost = 0D; Double familyEstimatedCost = 0D;
	 *           Double serviceCost = 0D; if(persons !=null && persons.size() >
	 *           0) { for (Individual individual : persons) { Map<String,
	 *           Integer> utlzVal = individual.getUtlzVal(); Map<String, Double>
	 *           avgCost = individual.getAvgCost(); for (String trtmnt :
	 *           utlzVal.keySet()) { serviceCost = avgCost.get(trtmnt) *
	 *           utlzVal.get(trtmnt); estimatedCost = estimatedCost +
	 *           serviceCost; serviceCost = 0D; } familyEstimatedCost =
	 *           familyEstimatedCost + estimatedCost; estimatedCost = 0D; } }
	 *           return familyEstimatedCost; }
	 **/

}
