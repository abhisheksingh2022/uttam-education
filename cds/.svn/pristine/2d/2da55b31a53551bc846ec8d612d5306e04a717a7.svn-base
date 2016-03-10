package com.hixapi.ahct.cds.struts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.hixapi.ahct.cds.model.plan.PlanContainer;
import com.hixapi.ahct.cds.model.plan.PlanDetailsFinder;
import com.hixapi.ahct.cds.service.CDSService;
import com.hixapi.web.framework.common.APIConstants;
import com.hixapi.web.framework.common.UserMessage;
import com.hixapi.web.framework.env.IEnvironmentProvider;
import com.hixapi.web.framework.service.ServiceLocator;
import com.hixapi.web.framework.struts.BaseAction;
import com.opensymphony.xwork2.Action;

public class PlanDetailsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3189615713902880462L;
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(AllPlanGridAction.class);

	private String planDetailsDisplayId;
	private String premiumAmt;
	private PlanContainer planContainer;
	private String pvpInNetwork;
	private String pvpOutNetwork;

	private String basePdfUrl;

	public PlanContainer getPlanContainer() {
		return planContainer;
	}

	public void setPlanContainer(PlanContainer planContainer) {
		this.planContainer = planContainer;
	}

	public String getPlanDetailsDisplayId() {
		return planDetailsDisplayId;
	}

	public void setPlanDetailsDisplayId(String planDetailsDisplayId) {
		this.planDetailsDisplayId = planDetailsDisplayId;
	}

	public String getPremiumAmt() {
		return premiumAmt;
	}

	public void setPremiumAmt(String premiumAmt) {
		this.premiumAmt = premiumAmt;
	}

	public String goBack() {
		log.debug("IN GOBACK PLAN DETAILS");
		return BACK;
	}

	@Override
	protected void loadDisplayContents() throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("****************************");
		PlanDetailsFinder detailsFinder = new PlanDetailsFinder();
		detailsFinder.setPlanDetailsDisplayId(planDetailsDisplayId);
		detailsFinder.setPremiumAmt(premiumAmt);
		setPlanContainer(ServiceLocator.getInstance().getService(CDSService.class).retrievePlan(detailsFinder));
		pvpInNetwork = planContainer.getPlan().getPlanDetails().getPhysicianServices().getpVPInjury().getInNetwork();
		pvpOutNetwork = planContainer.getPlan().getPlanDetails().getPhysicianServices().getpVPInjury().getOutOfNetwork();
	}

	@Override
	protected String handleExecute() throws Exception {
		// TODO Auto-generated method stub
		if (System.currentTimeMillis() % 2 == 0) {
			super.addBusinessMessage(new UserMessage(1, "This is random error message that will come once in a  while",
					null, null));
			return FLASH;
		}
		return PAGE;
	}

	/*
	 * Empty method to allow the user to ping the server to refresh the session
	 * timeout clock.
	 */
	public String pingSession() {
		// do nothing

		return SUCCESS;
	}
	
	public String pdf() throws IOException {
		String doc = ServletActionContext.getRequest().getParameter("doc");
		final String diskFile = System.getProperty("user.home") +"/2015-cds-pdf/" + 
				StringUtils.replaceEachRepeatedly(doc, new String[]{"..", "/", "\\"}, new String[]{"", "", ""});
		log.info("Loading Plan Pdf File : {} from {}", doc, diskFile );
		byte[] file = FileUtils.readFileToByteArray(new File(diskFile));
		ServletActionContext.getResponse().setContentLength(file.length);
		ServletActionContext.getResponse().setContentType("application/pdf");
		ServletActionContext.getResponse().addHeader("Content-Disposition", "inline;filename=\"document.pdf\"");
		ServletActionContext.getResponse().getOutputStream().write(file);
		return Action.NONE;
	}


	public String getPlanPdfUrl(String planId, String variant, String year, String lang, String carrier) {
		if ("2015".equals(year)) {
			return "/cds/plan/plandetails_pdf?doc="+carrier+"-"+planId+"-"+variant+"_"+lang+".pdf";
		} else {
			if (StringUtils.isEmpty(basePdfUrl)) {
				basePdfUrl = ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
						.getProperty(APIConstants.PROPKEY_PLAN_PDF_URL, String.class, "");
			}
			StringBuilder fullUrl = new StringBuilder(basePdfUrl);
			fullUrl.append("?planId=").append(planId).append("-").append(variant).append("&");
			fullUrl.append("language=").append(lang).append("&");
			fullUrl.append("year=").append(year).append("&");
			fullUrl.append("carrier=").append(carrier);
			return fullUrl.toString();
		}
		
	}

	public String getPvpInNetwork() {
		return pvpInNetwork;
	}

	public void setPvpInNetwork(String pvpInNetwork) {
		this.pvpInNetwork = pvpInNetwork;
	}

	public String getPvpOutNetwork() {
		return pvpOutNetwork;
	}

	public void setPvpOutNetwork(String pvpOutNetwork) {
		this.pvpOutNetwork = pvpOutNetwork;
	}

	

		public static void main(String[] a) throws Exception {
			FileInputStream f = new FileInputStream("D:/temp/2015-pdf/2015-pdf.txt");
			List<String> files = IOUtils.readLines(f);
			String base = "\\\\sit3appportal1\\d$\\Data\\Shared_Data\\PlanManagementUploads\\Individual\\";
			for (String file : files) {
				String[] split = StringUtils.split(file, '\t');
				String fullPath = base + split[0] + "\\Medical\\archive\\" + split[1];
				String fullPathEs = base + split[0] + "\\Medical\\archive_ES\\" + split[1];
				FileInputStream in = null;
				File pdfFile = new File(fullPath);
				if (pdfFile.exists()) {
					in = new FileInputStream(pdfFile);
				} else {
					File pdfFileEs = new File(fullPathEs);
					if (pdfFileEs.exists()) {
						in = new FileInputStream(fullPathEs);
					}
				}
				if(in == null){
					System.out.println("Can't find file: " + split[1]);
					continue;
				}
	
				final FileOutputStream out = new FileOutputStream(new File("D:/temp/2015-pdf/"+split[2]+"-"+split[3]+"-"+split[4]+"_"+split[5]+".pdf"));
				IOUtils.copy(in, out);
				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(out);
			}
			IOUtils.closeQuietly(f);
		}

}
