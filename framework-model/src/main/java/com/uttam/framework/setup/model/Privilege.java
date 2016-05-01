package com.uttam.framework.setup.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.uttam.framework.core.model.BaseModel;

@Document(collection = "privilege")
public class Privilege extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
    private String name;
    private Role role;
    private ResourceModule resourceModule;
    private int priority;

    
    public String getId() {
    	return this.id;
    }
    
    public void setId(String id) {
		this.id = id;
	}
    
    public String getName() {
    	return this.name;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public int getPriority() {
    	return this.priority;
    }

    public void setPriority(int priority) {
    	this.priority = priority;
    }

    public Role getRole() {
    	return role;
    }

    public void setRole(Role role) {
    	this.role = role;
    }

    public ResourceModule getResourceModule() {
    	return resourceModule;
    }

    public void setResourceModule(ResourceModule resourceModule) {
    	this.resourceModule = resourceModule;
    }
}
