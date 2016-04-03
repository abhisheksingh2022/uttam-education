package com.uttam.education.struts.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uttam.framework.core.model.DisplayText;
import com.uttam.framework.dao.impl.BaseDAOImpl;
import com.uttam.framework.service.ServiceLocator;

public class BackendSupportAction {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/spring-context.xml");
		BaseDAOImpl dao = (BaseDAOImpl)context.getBean("baseDao");
		
		DisplayText  text = new DisplayText();
		text.setServiceProviderId("EDUCATION");
		text.setTextKey("txt_msg_success");
		text.setTextValue("Record saved successfully");
		text.setTextLangCode("en_US");
		
		dao.create(text);
	}

}
