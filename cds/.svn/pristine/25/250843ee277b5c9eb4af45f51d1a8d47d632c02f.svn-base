package com.hixapi.web.framework.dao.interceptor;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.hixapi.web.framework.common.APIUtil;
import com.hixapi.web.framework.common.UserIdentity;
import com.hixapi.web.framework.context.ContextKeyEnum;
import com.hixapi.web.framework.context.ContextProvider;

public class AuditLogInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7853971248815989472L;

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,

	Object[] previousState, String[] propertyNames, Type[] types) {
		UserIdentity i = ContextProvider.getContextField(ContextKeyEnum.REQUEST_USER, UserIdentity.class);
		setValue(currentState, propertyNames, "update_uid", i.getLoginId());
		setValue(currentState, propertyNames, "update_ts", APIUtil.getCurrentDate());
		return true;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		UserIdentity i = ContextProvider.getContextField(ContextKeyEnum.REQUEST_USER, UserIdentity.class);
		setValue(state, propertyNames, "create_uid", i.getLoginId());
		setValue(state, propertyNames, "create_ts", APIUtil.getCurrentDate());
		return true;

	}

	private void setValue(Object[] currentState, String[] propertyNames, String propertyToSet, Object value) {

		Integer propertyPosition = 0;
		for (String property : propertyNames) {

			if (StringUtils.equals(property, propertyToSet)) {

				currentState[propertyPosition] = value;
			} else {
				propertyPosition++;
			}

		}
	}

}