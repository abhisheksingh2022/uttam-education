package com.uttam.framework.setup.model;

import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.uttam.framework.core.model.BaseModel;


@Document(collection = "role")
public class Role extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	private String name;

	private String description;
	
	private String icon;

	private Set roleModules;

	private Set privileges;

	private Set userRoles;
	
	private Set reportRoles;

	public String getId() {
		return id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Set getRoleModules() {
		return this.roleModules;
	}

	public void setRoleModules(Set roleModules) {
		this.roleModules = roleModules;
	}

	public String toString() {
		return getName();
	}

	public Set getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set privilages) {
		this.privileges = privilages;
	}

	public Set getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}

	public Set getReportRoles() {
		return reportRoles;
	}

	public void setReportRoles(Set reportRoles) {
		this.reportRoles = reportRoles;
	}
}
