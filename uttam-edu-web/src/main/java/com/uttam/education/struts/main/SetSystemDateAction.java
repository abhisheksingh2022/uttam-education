package com.uttam.education.struts.main;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.uttam.framework.common.ConversionUtil;
import com.uttam.web.framework.struts.BaseAction;
import com.uttam.web.framework.struts.SessionController;

public class SetSystemDateAction extends BaseAction {

	//SessionController sessionController = new SessionController();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String newDate;

	public String getNewDate() {
		return newDate;
	}

	public void setNewDate(String myDate) {
		this.newDate = myDate;
	}

	/**
	 * Method call from: footer.jsp page as ajax call
	 * When is calling: on clicking of setDateToSession button in footer.jsp
	 * Functionality: to set the user selected date to session controller
	 * @return page
	 * @throws ParseException
	 */
	
	public String ajaxSetDate() throws ParseException {
		//System.out.println("Into ajax function");
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		if (StringUtils.isNotBlank(newDate)) {
			Date date = ConversionUtil.toDate(newDate);
			cal.setTime(date);
			Calendar newCal = Calendar.getInstance();
			newCal.setLenient(false);
			newCal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
			newCal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			newCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			SessionController.setSystemDate(newCal.getTime());
		}
		return PAGE;

	}

	@Override
	protected void loadDisplayContents() throws Exception {
		
	}

	@Override
	protected String handleExecute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
