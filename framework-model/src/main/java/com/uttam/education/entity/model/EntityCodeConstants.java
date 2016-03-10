package com.uttam.education.entity.model;

import org.apache.commons.lang3.StringUtils;

public class EntityCodeConstants {

	public enum RoleType {
		ALL("AL", "All Roles"), PLAN_MANAGER("PM", "Plan Manager"), PLAN_SPECIALIST("PS", "Plan"), CARRIER("CA", "Carrier");

		private String code;
		private String description;

		RoleType(String code, String description) {
			this.code = code;
			this.description = description;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String toString() {
			return StringUtils.trimToEmpty(code) + ":" + StringUtils.trimToEmpty(description);
		}
	}

}
