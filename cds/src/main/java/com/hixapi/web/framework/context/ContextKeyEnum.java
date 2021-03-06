package com.hixapi.web.framework.context;

public class ContextKeyEnum {
	
	private String code;

	protected ContextKeyEnum(String code) {
		this.code = code;
	}


	public static final ContextKeyEnum REQUEST_USER = new ContextKeyEnum("REQUEST_USER");
	public static final ContextKeyEnum REQUEST_UUID = new ContextKeyEnum("REQUEST_UUID");
	public static final ContextKeyEnum COOKIE_CONTEXT = new ContextKeyEnum("COOKIE_CONTEXT");
	public static final ContextKeyEnum SERVICE_PROVIDER_ID = new ContextKeyEnum("SERVICE_PROVIDER_ID");
	public static final ContextKeyEnum DATE_FORMAT_MM_DD_YYYY = new ContextKeyEnum("DATE_FORMAT_MM_DD_YYYY");
	public static final ContextKeyEnum DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = new ContextKeyEnum("DATE_FORMAT_YYYY_MM_DD_HH_MM_SS");
	public static final ContextKeyEnum CLIENT_IP = new ContextKeyEnum("CLIENT_IP");
	public static final ContextKeyEnum CURRENT_LOCALE = new ContextKeyEnum("CURRENT_LOCALE");
	public static final ContextKeyEnum CURRENT_DATETIME = new ContextKeyEnum("CURRENT_DATETIME");
	public static final ContextKeyEnum REQUEST_JSON = new ContextKeyEnum("REQUEST_JSON");
	public static final ContextKeyEnum DATE_FORMAT_YYYY_MM_DD = new ContextKeyEnum("DATE_FORMAT_YYYY_MM_DD");

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContextKeyEnum other = (ContextKeyEnum) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
	

}
