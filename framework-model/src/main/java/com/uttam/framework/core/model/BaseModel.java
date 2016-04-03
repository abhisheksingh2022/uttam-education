package com.uttam.framework.core.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public abstract class BaseModel implements Serializable, Cloneable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseModel() {
        super();
    }

	@Id
    abstract public String getId();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseModel)) {
			return false;
		}
		BaseModel other = (BaseModel) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

    public String toString() {
        return this.getClass().getName() + " id = " + getId();
    }

   /*
    * TODO
    * convert for Deep Cloning
    */
    @Override
    public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
}