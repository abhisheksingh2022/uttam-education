package com.uttam.framework.dao;

import com.uttam.framework.core.model.BaseModel;

public interface BaseDAO<T> {

	public void create(BaseModel model);
	
	public void update(BaseModel model);

	public BaseModel readById(Class<T> clazz, String id);
	
	public int deleteById(Class<T> clazz, String id);
	
//	public Collection<T> findListByCondition(Class<T> clazz, String[] propertyNames, Object[] propertyValue);
}
