package com.hixapi.ahct.cds.struts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hixapi.ahct.cds.model.Eligibility;
import com.hixapi.ahct.cds.model.Household;
import com.hixapi.ahct.cds.model.Individual;
import com.hixapi.ahct.cds.model.IndividualEligibilityResults;
import com.hixapi.ahct.cds.service.CDSService;
import com.hixapi.web.framework.common.ConversionUtil;
import com.hixapi.web.framework.service.ServiceLocator;
import com.hixapi.web.framework.struts.BaseAction;
import com.hixapi.web.framework.struts.SessionController;

public class HouseholdAction extends BaseAction {

	private static final long serialVersionUID = -2311803452322257862L;
	private static final String SAMPLE_AJAX = "sampleAjax";
	private static final String LEGAL_STATUS = "legalStatus";
	private static final Integer CHILD_AGE = 20;
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(HouseholdAction.class);

	private Household household;

	private List<String> lookup;
	private List<Individual> notLawfulMembers;
	private List<Individual> allMembers;
	private Boolean potentialEligibiltyStatus;
	private Eligibility eligibility;
	private Boolean doBack;
	private String aptcAmount;
	
	public Eligibility getEligibility() {
		return eligibility;
	}

	public void setEligibility(Eligibility eligibility) {
		this.eligibility = eligibility;
	}

	/**	
	 * This method should get all the contents to be displayed on the page.
	 * This is called by BaseAction from input() method
	 */
	protected void loadDisplayContents() {
		//Call Service via ServiceLocator if required
		setHousehold(SessionController.getHousehold());
		setEligibility(SessionController.getEligibility());
		if(SessionController.getEligibility()!=null){
			setPotentialEligibiltyStatus(false);
			checkIfAllQhp(SessionController.getEligibility());
			setAptcAmountValue();
		}
		/*List<Individual> householdMembers =  getHousehold().getHouseholdMembers();*/
		log.debug("in load contents of household");
	}

	/**
	 * This is a sample method, this does not get invoked, its validation is always configured to fail - see validate_validationError
	 * @return
	 * @throws Exception
	 */
	public String validationError() throws Exception {
		//Call Service via ServiceLocator if required

		return FLASH; // Use flash to perform a redirect to support the POST-REDIRECT-GET pattern
	}

	/**
	 * This method should handle the post of execute() method call
	 * This is called by BaseAction from execute() method
	 */
	protected String handleExecute() throws Exception {
		SessionController.setHousehold(household);
		//Store information from input to session via SessionController, do any action that is required
		//if(System.currentTimeMillis() % 3 == 0){
			//Error message / Info messages / Warnings.. Note to use this only when the same action page is to be displayed. 
			//This message will not be displayed if you return NEXT or any redirect-results.
			//super.addBusinessMessage(new UserMessage(2, "This is random warning message that will come once in a  while, please retry", null, null));
			//if return to same page is required with display of error messages, return FLASH
		//	return FLASH;
		//}
		
		// To go to next page return NEXT
		return NEXT;
	}
	
	

	public String ajaxPost() {
		log.entry();

		return SAMPLE_AJAX;
	}

	public String ajaxGet() {
		log.entry();

		return SAMPLE_AJAX;
	}

// This method checks for those member who all are more than 18 years old
	public String legalStatusCheck(){
		
		List<Individual> householdMembers =  getHousehold().getHouseholdMembers();
		notLawfulMembers = new ArrayList<Individual>(0);
		for(Individual member:householdMembers){
			if(member.getAge()!=null && member.getAge()>CHILD_AGE){
				if ("Y".equalsIgnoreCase(member.getCoveredIndicator()) && "N".equalsIgnoreCase(member.getPregnantIndicator())) {
					notLawfulMembers.add(member);
				}
			}
		}
		return LEGAL_STATUS;
	}

/* This method will invoke Service to calculate Eligibility for all members and will give back to JSP */
	public String preliminaryEligibiltyCheck()
	{	
		int count=0;
		setPotentialEligibiltyStatus(false);
		setEligibility(ServiceLocator.getInstance().getService(CDSService.class).calculateEligibility(household));
		household.setHouseholdFPL(eligibility.getHouseholdFPL());
		checkIfAllQhp(getEligibility());
		setPotentialEligibiltyStatus(true);
		List<Individual> members = new ArrayList<Individual>(0); 
		for(Individual individual:getHousehold().getHouseholdMembers())
		{
			individual.setCheckCoveredIndicator(false);
			if("Y".equalsIgnoreCase(individual.getCoveredIndicator()))
			{
				individual.setCheckCoveredIndicator(true);
				members.add(individual);
			}
		}
		for(Individual individual: getHousehold().getHouseholdMembers())
		{	
			   individual.setMedicaidIndicator(false);
			   individual.setChipIndicator(false);
			   
			   for(IndividualEligibilityResults individualEligibility: getEligibility().getResultList()){
			     if(individualEligibility.getIndividualSequenceNumber()== individual.getIndividualSequenceNumber()){
			      if(individualEligibility.getMedicaidEligibility()){
			       individual.setMedicaidIndicator(true);
			       count=count+1;
			       break;
			      }
			      if(individualEligibility.getChipEligibility()) {
				       individual.setChipIndicator(true);
				       count=count+1;
				       break;
				      }
			     }
			   }
			  }
		if(count==members.size())
		{
			log.debug("All Memeber are medicaid eligible");
			eligibility.setAllMemberMedicaid(true);
		}
		
		setAptcAmountValue();
		SessionController.setEligibility(eligibility); 	
		SessionController.setHousehold(household); 	
	 return FLASH;
	}
	
	private void setAptcAmountValue(){
		Double aptcAmountDouble = eligibility.getAptcAmount();
		BigDecimal bd = new BigDecimal(aptcAmountDouble);
		aptcAmount = ConversionUtil.toString(bd.setScale(2,RoundingMode.UP));
	}
	
	/* check if all members are QHP eligible, then don't show eligibility result, directly show Plan listing page */	
	private void checkIfAllQhp(Eligibility eligibility){
		List<IndividualEligibilityResults> eligibilityResults = eligibility.getResultList();
		for (IndividualEligibilityResults individualEligibilityResult : eligibilityResults) {
			if(individualEligibilityResult.getQhpEligibility()==false){
				setPotentialEligibiltyStatus(true);
				break;
			}
		}
		setPotentialEligibiltyStatus(true);
	}
	
/* This method validates all fields of household.jsp
 * It is  called just before preliminaryEligibiltyCheck() method  */	
public void validate_preliminaryEligibiltyCheck() 
{	
	// don't remove it, otherwise validation highlight will not work for whole page(household.jsp)
		setAjaxRequest(false);
		int count=0;
	  setPotentialEligibiltyStatus(false);
	  List<Individual> householdMembers =  getHousehold().getHouseholdMembers();
	  if(StringUtils.isEmpty(household.getCounty()))
	  {
		  super.addValidationError("msg.household.msg7", "'household.county'");
	  }
	  for(Individual member:householdMembers){
		  String memberName = member.getName();
		 
		   if(member!=null && (member.getAge()==null || member.getAge() <= 0))
		   {
			   String ageField = "'household.householdMembers["+(member.getIndividualSequenceNumber()-1)+"].age'";
			   super.addValidationError("msg.household.msg8",new String[]{memberName}, ageField);
		   }
		   if(StringUtils.isEmpty(member.getCoveredIndicator()))
		   {
			   super.addValidationError("msg.household.msg11",new String[]{memberName}, "'household.householdMembers["+(member.getIndividualSequenceNumber()-1)+"].coveredIndicator'");
		   }
		   if(StringUtils.isEmpty(member.getPregnantIndicator()))
		   {
			   super.addValidationError("msg.household.msg1",new String[]{memberName}, "'household.householdMembers["+(member.getIndividualSequenceNumber()-1)+"].pregnantIndicator'");
		   }
		   if(member!=null && member.getIndividualSequenceNumber()==1){
			 if(StringUtils.isEmpty(member.getQualifiedAlienIndicator())){
				 super.addValidationError("msg.household.msg5", "'household.householdMembers["+(member.getIndividualSequenceNumber()-1)+"].qualifiedAlienIndicator()'");
			 }
		   }else{
			   if(StringUtils.isEmpty(member.getRelationToApplicantCode()))
			   {
				   super.addValidationError("msg.household.msg2",new String[]{memberName}, "'household.householdMembers["+(member.getIndividualSequenceNumber()-1)+"].relationToApplicantCode'");
			   }
		   }
		   if(member.getCoveredIndicator().equals("N")){
			   ++count;
		   }
		   
	  }
	  if(household.getHouseholdIncome()==null || household.getHouseholdIncome() < 0 )
	  {
		  super.addValidationError("msg.household.msg3", "'household.householdIncome'");
	  }
	  if(StringUtils.isEmpty(household.getHouseholdIncomeTypeCode()))
	  {
		  super.addValidationError("msg.household.msg4", "'household.householdIncomeTypeCode'");
	  }
	  
	  if(StringUtils.isEmpty(household.getHouseholdLegalStatusTypeIndicator()))
	  {
		  super.addValidationError("msg.household.msg6", "'household.householdLegalStatusTypeIndicator'");
	  }
	  
	  if(!StringUtils.isEmpty(household.getHouseholdLegalStatusTypeIndicator()) && household.getHouseholdLegalStatusTypeIndicator().equals("N")){
		  int chkCount = 0;
		  for(Individual member:householdMembers){
			  if(member.getLegalStatusTypeIndicator()!=null && member.getLegalStatusTypeIndicator().equalsIgnoreCase("true")){
				  ++chkCount;
				  if(member.getLegalStatusTypeIndicator()!=null && member.getLegalStatusTypeCode().equals("")){
					  super.addValidationError("msg.household.msg9",new String[]{member.getName()}, "'household.householdMembers["+(member.getIndividualSequenceNumber()-1)+"].legalStatusTypeCode'");
				  }
			  }
		  }
		 legalStatusCheck();
		 if(notLawfulMembers.size()>0 && chkCount==0){
			  super.addValidationError("msg.household.msg10","'household.householdLegalStatusTypeIndicator'");
		  }
	  }
	  if(count==household.getHouseholdMembers().size())
	   {
		   log.debug("All Memeber are not apply for health coverage");
		   super.addValidationError("msg.household.msg12");
	   }
}
	//This is the validation method for the validationError action
	public void validate_validationError() {
		super.addValidationError("msg.global.sample_validation_error", "name", "age");
	}
	
	public void validate_execute() {
		
	}

	public Household getHousehold() {
		return household;
	}

	public void setHousehold(Household household) {
		this.household = household;
	}
	
	public List<String> getLookup() {
		return lookup;
	}

	public void setLookup(List<String> lookup) {
		this.lookup = lookup;
	}
	public List<Individual> getNotLawfulMembers() {
		return notLawfulMembers;
	}

	public void setNotLawfulMembers(List<Individual> notLawfulMembers) {
		this.notLawfulMembers = notLawfulMembers;
	}

	public Boolean getPotentialEligibiltyStatus() {
		return potentialEligibiltyStatus;
	}

	public void setPotentialEligibiltyStatus(Boolean potentialEligibiltyStatus) {
		this.potentialEligibiltyStatus = potentialEligibiltyStatus;
	}

	public List<Individual> getAllMembers() {
		return allMembers;
	}

	public void setAllMembers(List<Individual> allMembers) {
		this.allMembers = allMembers;
	}

	public Boolean getDoBack() {
		return doBack;
	}

	public void setDoBack(Boolean doBack) {
		this.doBack = doBack;
	}

	/**
	 * @return the aptcAmount
	 */
	public String getAptcAmount() {
		return aptcAmount;
	}

	/**
	 * @param aptcAmount the aptcAmount to set
	 */
	public void setAptcAmount(String aptcAmount) {
		this.aptcAmount = aptcAmount;
	}
}
