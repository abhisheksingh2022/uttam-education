package com.uttam.framework.setup.model;

import java.util.Set;
import com.uttam.framework.core.model.BaseModel;

public class Resource extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String name;
	
	private String displayName;

	private String description;

	private String action;

	private int displayOrder;

	private boolean showInMenu;

	private String hierarchyId;

	private String icon;
	
	private Resource parentResource;

	private Set resourceModules;

	private Set childResources;

	private Set pageResources;
	
	private Set userFavourites;

	
	public String getId() {
		return this.id;
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

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getHierarchyId() {
		return this.hierarchyId;
	}

	public void setHierarchyId(String hierarchyId) {
		this.hierarchyId = hierarchyId;
	}
	public Resource getParentResource() {
		return this.parentResource;
	}

	public void setParentResource(Resource resource) {
		this.parentResource = resource;
	}

	public Set getResourceModules() {
		return this.resourceModules;
	}

	public void setResourceModules(Set resourceModules) {
		this.resourceModules = resourceModules;
	}

	public Set getChildResources() {
		return this.childResources;
	}

	public void setChildResources(Set resources) {
		this.childResources = resources;
	}

	
	public boolean isShowInMenu() {
		return showInMenu;
	}

	public void setShowInMenu(boolean showInMenu) {
		this.showInMenu = showInMenu;
	}

	
	public Set getPageResources() {
		return pageResources;
	}

	public void setPageResources(Set pageResources) {
		this.pageResources = pageResources;
	}

	
	public Set getUserFavourites() {
		return userFavourites;
	}

	public void setUserFavourites(Set userFavourites) {
		this.userFavourites = userFavourites;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
