package com.uttam.framework.setup.model;

import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.uttam.framework.core.model.BaseModel;

@Document(collection = "user_group")
public class UserGroup extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	private String groupName;

	private String description;

	private String fullyQualifiedName;

	private Set userRoles;

	private UserGroup parentGroup;

	private Set childGroups;

	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getFullyQualifiedName() {
		return fullyQualifiedName;
	}

	public void setFullyQualifiedName(String fullyQualifiedName) {
		this.fullyQualifiedName = fullyQualifiedName;
	}
	public Set getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}

	public Set getChildGroups() {
		return childGroups;
	}

	public void setChildGroups(Set childGroups) {
		this.childGroups = childGroups;
	}

	public UserGroup getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(UserGroup parentGroup) {
		this.parentGroup = parentGroup;
	}
}
