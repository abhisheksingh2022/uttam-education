package com.uttam.framework.setup.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uttam.framework.core.model.BaseModel;

@Document(collection = "user_role")
public class UserRole extends BaseModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	private AppUser appUser;

	private Role role;
	
	private UserGroup userGroup;
	
	public String getId() {
		return id;
	}
	
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
}
