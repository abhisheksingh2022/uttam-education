/**
 * 
 */
package com.hixapi.ahct.cds.service;

import com.hixapi.ahct.cds.model.Eligibility;
import com.hixapi.ahct.cds.model.Household;
import com.hixapi.ahct.cds.model.MedicalCost;
import com.hixapi.ahct.cds.model.plan.HealthPlanDeterminant;
import com.hixapi.ahct.cds.model.plan.PlanContainer;
import com.hixapi.ahct.cds.model.plan.PlanDetailsFinder;
import com.hixapi.web.framework.service.IService;

/**
 * @author admin
 *
 */
public interface CDSService extends IService {
	Eligibility calculateEligibility(Household hs);

	MedicalCost calculateTotalMedicalCosts(Household hs);

	PlanContainer retrievePlans(HealthPlanDeterminant planDeterminant);

	PlanContainer retrievePlan(PlanDetailsFinder planDetailsFinder);

	//Double getEstimatedAmount(List<Individual> persons);
}
