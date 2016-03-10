/*
 * Author - HIXAPI.
 * v1.0
 * 2009
 */
package com.uttam.framework.common.model;

import java.io.Serializable;

/**
 * The Class LookupBean.
 */

public class LookupBean  implements Serializable{
	
	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = -960707800336414317L;

	/** The id. */
	private LookupId id;

	/** The label. */
	private String label;
	
	/** The sort order. */
	private Integer sortOrder;

	/**
         * Equals.
         * 
         * @param obj
         *                the obj
         * 
         * @return true, if equals
         * 
         * @author admin Feb 20, 2009
         */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final LookupBean other = (LookupBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
         * Gets the id.
         * 
         * @return the id
         * 
         * @author admin Feb 20, 2009
         */
	public LookupId getId() {
		return id;
	}

	/**
         * Gets the label.
         * 
         * @return the label
         */
	public String getLabel() {
		return label;
	}

	/**
         * Hash code.
         * 
         * @return the int
         * 
         * @author admin Feb 20, 2009
         */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
         * Sets the id.
         * 
         * @param id
         *                the id to set
         * 
         * @author admin Feb 20, 2009
         */
	public void setId(LookupId id) {
		this.id = id;
	}

	/**
         * Sets the label.
         * 
         * @param label
         *                the new label
         */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return
	 * @author admin
	 * Nov 1, 2009
	 */
	public String toString() {

		return "[" + id.getValue() + "," + label + "," + id.getLangCode() + "]";
	}

	/**
         * Gets the sort order.
         * 
         * @return the sortOrder
         * 
         * @author admin Feb 20, 2009
         */
	public Integer getSortOrder() {
		return sortOrder;
	}

	/**
         * Sets the sort order.
         * 
         * @param sortOrder
         *                the sortOrder to set
         * 
         * @author admin Feb 20, 2009
         */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	/**
         * Gets the value.
         * 
         * @return the value
         */
	public String getValue(){
		return this.id.getValue();
	}

}
