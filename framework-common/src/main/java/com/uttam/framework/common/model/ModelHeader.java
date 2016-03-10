/**
 * 
 */
package com.uttam.framework.common.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.uttam.framework.common.APIUtil;

/**
 * @author admin
 *
 */
public class ModelHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5553101542992878700L;

	private Date createTimestamp = APIUtil.getCurrentDate();
	private APIExceptionMessage apiException;
	private String status;
	private String statusDescription;

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public APIExceptionMessage getApiException() {
		return apiException;
	}

	public void setApiException(APIExceptionMessage apiException) {
		this.apiException = apiException;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
