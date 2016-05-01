package com.uttam.framework.setup.model;

import java.util.Set;

import org.springframework.data.annotation.Id;

import com.uttam.framework.core.model.BaseModel;

public class Module extends BaseModel {

	@Id
	private String id;

	private String name;

	private String displayName;
	
	private String description;
	
	private String icon;

	private Set roleModules;

	private Set resourceModules;
	
	private Set reportModules;


	@Override
	public String getId() {
		return id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Set getRoleModules() {
		return this.roleModules;
	}

	public void setRoleModules(Set roleModules) {
		this.roleModules = roleModules;
	}

	
	public Set getResourceModules() {
		return this.resourceModules;
	}

	public void setResourceModules(Set resourceModules) {
		this.resourceModules = resourceModules;
	}

	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Set getReportModules() {
		return reportModules;
	}

	public void setReportModules(Set reportModules) {
		this.reportModules = reportModules;
	}
}
