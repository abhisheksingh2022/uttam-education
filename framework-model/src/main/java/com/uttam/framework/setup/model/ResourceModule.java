package com.uttam.framework.setup.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uttam.framework.core.model.BaseModel;


@Document(collection = "resource_module")
public class ResourceModule extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Resource resource;

	private Module module;

	private Set privileges;

	private Set userFavorites;
	
	 public String getId() {
	    	return this.id;
	 }

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Set getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set privilages) {
		this.privileges = privilages;
	}
	
	public Set getUserFavorites() {
		return userFavorites;
	}

	public void setUserFavorites(Set userFavorites) {
		this.userFavorites = userFavorites;
	}
}
