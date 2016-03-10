package com.uttam.education.struts.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.uttam.education.api.model.criteria.NotificationSearchCriteria;
import com.uttam.education.api.model.criteria.WorkItemSearchCriteria;
import com.uttam.web.framework.struts.BaseAction;

public class HomeAction extends BaseAction {

	private static final Logger LOG = LogManager.getLogger(HomeAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -7296331027656555878L;
	private static final String NOTIFICATIONS = "notifications";
	private static final String WORK_ITEMS = "work-items";
	private NotificationSearchCriteria notifSearch;
	private WorkItemSearchCriteria workItemCriteria;
	String loginId;
	private String userId;

	@Override
	protected void loadDisplayContents() throws Exception {
		LOG.entry();
		LOG.exit();
		//PlanVO preview = ServiceLocator.getInstance().getService(PlanManagerService.class).retrievePlanPreview("86545CT1330009-01", Language.ENGLISH, (short)2016);
		

	}

	public NotificationSearchCriteria getNotifSearch() {
		return notifSearch;
	}

	public void setNotifSearch(NotificationSearchCriteria notifSearch) {
		this.notifSearch = notifSearch;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Override
	protected String handleExecute() throws Exception {
		return PAGE;
	}

	/**
	 * Load top 10 unread notifications and workItems and unread notifications count from NotificationService
	 */
//	public String ajaxLoadNotififAndWorkItemsWidget() {
//		loginId = SessionController.getUser().getLoginId();
//
//		//notifSearch.setLoginId(loginId);
//		//workItemCriteria.setLoginId(loginId);
//		
//
//		NotificationService notifService = ServiceLocator.getInstance().getService(NotificationService.class);
//		recentNotifications = notifService.retrieveNotificationsForUser(notifSearch);
//		 WorkItemsService wrkItemService = ServiceLocator.getInstance().getService(WorkItemsService.class);
//		 recentWorkItems =	wrkItemService.retrieveWorkItemsForUser(workItemCriteria);
//		 notifAndWorkItemVO = new NotifAndWorkItemVO();
//		notifAndWorkItemVO.setRecentNotifications(recentNotifications);
//		notifAndWorkItemVO.setRecentWorkItems(recentWorkItems);
//		
//		return NOTIFICATIONS;
//	}

	/**
	 * Load top 10 work items ordered by due date and priority ascending
	 */
	public String ajaxLoadWorkItemsWidget() {
		//loginId = SessionController.getUser().getLoginId();
		//workItemCriteria.setLoginId(loginId);
		//recentWorkItems = ServiceLocator.getInstance().getService(WorkItemsService.class)
		//		.retrieveWorkItemsForUser(workItemCriteria);
//		for(WorkItemVO w:recentWorkItems)
//		{
//			LOG.debug("id: "+ w.getWorkItemId()+" message: "+ w.getMessage());
//		}
		return WORK_ITEMS;
	}
	
	/**
	 * Load top 10 work items ordered by due date and priority ascending
	 */
	public String ajaxLoadNotificationsWidget() {
		//loginId = SessionController.getUser().getLoginId();
		//workItemCriteria.setLoginId(loginId);
		//NotificationService notifService = ServiceLocator.getInstance().getService(NotificationService.class);
		//recentNotifications = notifService.retrieveNotificationsForUser(notifSearch);
		return NOTIFICATIONS;
	}

	public WorkItemSearchCriteria getWorkItemCriteria() {
		return workItemCriteria;
	}

	public void setWorkItemCriteria(WorkItemSearchCriteria workItemCriteria) {
		this.workItemCriteria = workItemCriteria;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
