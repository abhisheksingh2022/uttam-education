package com.hixapi.ahct.cds.struts;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hixapi.ahct.cds.model.Household;
import com.hixapi.ahct.cds.model.Individual;
import com.hixapi.ahct.cds.model.MedicalCost;
import com.hixapi.ahct.cds.service.CDSService;
import com.hixapi.web.framework.common.UserMessage;
import com.hixapi.web.framework.service.ServiceLocator;
import com.hixapi.web.framework.struts.BaseAction;
import com.hixapi.web.framework.struts.SessionController;

public class HealthStatusAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -448791743540959890L;
	private static final String SAMPLE_AJAX = "sampleAjax";
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(HealthStatusAction.class);
	private Household household;
	private List<Individual> individuals;

	public List<Individual> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}

	/*
	 * Method for returning to HealthStatus Page on click of Back Button
	 */
	public String goBack() throws Exception {
		setHealthStatusData();
		addRedirectURLParameter("doBack", true);
		return BACK;
	}

	/**
	 * This method should get all the contents to be displayed on the page. This is called by BaseAction from input() method
	 */
	protected void loadDisplayContents() {
		//Call Service via ServiceLocator if required
		setHousehold(SessionController.getHousehold());
		//setHousehold(new CDSServiceImpl().fetchHousehold());
		//SessionController.setHousehold(household);

		//code for setting default values in individual lookups
		List<Individual> coveredMembers = eliminateUnCoveredMemebers(getHousehold().getHouseholdMembers());
		household.setCoveredMembers(coveredMembers);
		//household.setHouseholdMembers(coveredMembers);
		if (household != null && household.getHouseholdMembers() != null && !household.getHouseholdMembers().isEmpty()) {
			for (Individual ind : household.getCoveredMembers()) {
				if (StringUtils.isEmpty(ind.getDisease())) {
					ind.setDisease("None");
				}
				if (StringUtils.isEmpty(ind.getSurgery())) {
					ind.setSurgery("None");
				}
			}
		}
		setIndividuals(household.getCoveredMembers());
	}

	private List<Individual> eliminateUnCoveredMemebers(List<Individual> householdMembers) {
		List<Individual> members = new ArrayList<Individual>(0);
		for (Individual individual : householdMembers) {
			if ("Y".equalsIgnoreCase(individual.getCoveredIndicator())) {
				members.add(individual);
			}
		}
		return members;
	}

	@Override
	protected void onValidationError() {
		// TODO Auto-generated method stub
		household = SessionController.getHousehold();

	}

	protected String handleExecute() throws Exception {
		setHealthStatusData();
		String csrVarId = SessionController.getEligibility().getCsrVarIdForPlansFetch();
		Household household = SessionController.getHousehold();
		household.setCsrVarId(csrVarId);
		MedicalCost medicalCost = (ServiceLocator.getInstance().getService(CDSService.class))
				.calculateTotalMedicalCosts(SessionController.getHousehold());
		SessionController.setMedicalCost(medicalCost);
		return NEXT;
	}

	private void setHealthStatusData() {
		Household household = SessionController.getHousehold();
		/*Eligibility eligibility = SessionController.getEligibility();
		household.setHouseholdFPL(eligibility.getHouseholdFPL());*/
		for (Individual individualNew : individuals) {
			if (individualNew == null) {
				continue;
			}
			List<String> drldwnCd = new ArrayList<String>();
			for (Individual individualOld : household.getHouseholdMembers()) {
				if (individualOld.equals(individualNew)) {
					individualOld.setGender(individualNew.getGender());
					individualOld.setDisease(individualNew.getDisease());
					individualOld.setSurgery(individualNew.getSurgery());
					if (individualOld.getDisease().equals("None")) {
						individualOld.setSeverity("");
					} else {
						individualOld.setSeverity(individualNew.getSeverity());
					}

					drldwnCd.add(individualNew.getDisease());
					drldwnCd.add(individualNew.getSurgery());
					individualOld.setDrldwnCd(drldwnCd);
					break;
				}
			}
		}
		SessionController.setHousehold(household);
		household.setNonMedicaidMembers(new ArrayList<Individual>());
		for (Individual individual : household.getHouseholdMembers()) {
			if (individual.getMedicaidIndicator() == false && individual.getChipIndicator() == false) {
				if ("Y".equalsIgnoreCase(individual.getCoveredIndicator())) {
					household.getNonMedicaidMembers().add(individual);
				}
			}
		}
	}

	public String ajaxPost() {
		log.entry();

		return SAMPLE_AJAX;
	}

	public String ajaxGet() {
		log.entry();

		return SAMPLE_AJAX;
	}

	//This is the validation method for the validationError action
	public void validate_validationError() {
		super.addBusinessMessage(new UserMessage(1, "msg.global.sample_validation_error", null, null));

	}

	public void validate_execute() {
		for (Individual member : individuals) {
			if (member != null && !member.getMedicaidIndicator()) {
				if (member.getGender() == null || member.getGender().equals("")) {
					String field = "'individuals[" + (member.getIndividualSequenceNumber() - 1) + "].gender'";
					super.addValidationError("msg.healthStatus.missing_gender", new String[] { member.getName() }, field);
				}
				if (member.getDisease() == null || member.getDisease().equals("")) {
					String field = "'individuals[" + (member.getIndividualSequenceNumber() - 1) + "].disease'";
					super.addValidationError("msg.healthStatus.missing_disease", new String[] { member.getName() }, field);
				}
				if (member.getSurgery() == null || member.getSurgery().equals("")) {
					String field = "'individuals[" + (member.getIndividualSequenceNumber() - 1) + "].surgery'";
					super.addValidationError("msg.healthStatus.missing_surgery", new String[] { member.getName() }, field);
				}
				if (member.getDisease() != null && !member.getDisease().equals("None")) {
					if (member.getSeverity() == null || member.getSeverity().equals("")) {
						String field = "'individuals[" + (member.getIndividualSequenceNumber() - 1) + "].severity'";
						super.addValidationError("msg.healthStatus.missing_severity", new String[] { member.getName() },
								field);
					}
				} else {
					member.setSeverity("1");
				}

			}
		}
	}

	public Household getHousehold() {
		return household;
	}

	public void setHousehold(Household household) {
		this.household = household;
	}

}
