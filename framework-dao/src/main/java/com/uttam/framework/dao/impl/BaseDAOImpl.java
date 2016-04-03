package com.uttam.framework.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;
import com.uttam.framework.core.model.BaseModel;
import com.uttam.framework.dao.BaseDAO;

public class BaseDAOImpl<T> implements BaseDAO<T> {

	private MongoOperations mongoOps;

	private Logger log = LogManager.getLogger(getClass());

	public BaseDAOImpl() {
		log.debug("BaseDao Instantiated");
	}
	
	public MongoOperations getMongoOps() {
		return mongoOps;
	}

	public void setMongoOps(MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}
	
	@Override
	public void create(BaseModel model) {
		this.mongoOps.insert(model);
	}

	@Override
	public void update(BaseModel model) {
		this.mongoOps.save(model, model.getClass().getName());
	}
	
	@Override
	public BaseModel readById(Class<T> clazz, String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return (BaseModel) this.mongoOps.findOne(query, clazz);
	}
	
	@Override
	public int deleteById(Class<T> clazz, String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, clazz);
		return result.getN();
	}
}
