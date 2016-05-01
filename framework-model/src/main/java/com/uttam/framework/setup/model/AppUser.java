package com.uttam.framework.setup.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.uttam.framework.core.model.BaseModel;

@Document(collection = "app_user")
public class AppUser extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String passwd;

	private String userId;

	private String firstName;

	private String lastName;

	private String email;

	private Set userPreferences;

	private Set userRoles;

	private int status;

	private String userType;


	public String getId() {
		return id;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(Set userPreferences) {
		this.userPreferences = userPreferences;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int staus) {
		this.status = staus;
	}

	public Set getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
