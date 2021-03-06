package com.uttam.education.api.model;

import org.apache.commons.lang3.StringUtils;

public class APICodeConstants {
	
	
	public enum Severity {
		ERROR(1, "Error"), WARNING(2, "Warning"), INFO(3, "Info");

		private Integer code;
		private String description;

		Severity(Integer code, String description) {
			this.code = code;
			this.description = description;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String toString() {
			return code + ":" + StringUtils.trimToEmpty(description);
		}

		public static Severity getEnum(Integer code) {
			for (Severity type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(Integer code){
			if(code == null){
				return false;
			}
			Severity type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
		
		
	}
	public enum PreferenceType {
		DATE_FORMAT("DF", "Date Format"), TIMEZONE("TZ", "Time Zone"), EMAIL_NOTIFICATION("EN", "N");

		private String code;
		private String description;

		PreferenceType(String code, String description) {
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

		public static PreferenceType getEnum(String code) {
			for (PreferenceType type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			PreferenceType type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}

	public enum PreferenceValue {
		DATE_FORMAT_MMDDYYYY("DF1", "MM/dd/yyyy"), DATE_FORMAT_YYYYMMDD("DF2", "yyyy/MM/dd"), DATE_FORMAT_YYYYMONDD("DF3",
				"yyyy-Mon-dd"), DATE_FORMAT_MONDDYYYY("DF4", "Mon-dd-yyyy"),

		TIMEZONE_ET("ET", "U.S. Eastern Time"), TIMEZONE_CT("CT", "U.S. Central Time"), TIMEZONE_MT("MT",
				"U.S. Mountain Time"), TIMEZONE_PT("PT", "U.S. Pacific Time"), ;

		private String code;
		private String description;

		PreferenceValue(String code, String description) {
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

		public static PreferenceValue getEnum(String code) {
			for (PreferenceValue type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			PreferenceValue type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}

	public enum PlanStatus {
		INCOMPLETE("IC", "Incomplete"), 
		VALIDATION_IN_PROGRESS("VP", "Validation Under Progress"), 
		VALIDATED("VA", "Validated"), 
		UNDER_CARRIER_REVIEW("UCR", "Under Carrier Review"), 
		CARRIER_SIGNOFF("CSO", "Carrier Sign Off"),
		PENDING_ACTIVATION("PE",
				"Pending Activation"),
		ACTIVATED("AC", "Activated"),
		DEACTIVATED("DAC", "Deactivated"),
		WITHDRAWN("WIT",
				"Withdraw");

		private String code;
		private String description;

		PlanStatus(String code, String description) {
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

		public static PlanStatus getEnum(String code) {
			for (PlanStatus type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			PlanStatus type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}

	public enum PlanVariant {
		VAR_00("00", "Off Exchange Plan"),
		VAR_01("01", "Variant 1"),
		VAR_02("02", "Variant 2"),
		VAR_03("03", "Variant 3"),
		VAR_04("04", "Variant 4"),
		VAR_05("05", "Variant 5"),
		VAR_06("06", "Variant 6"),
		;

		private String code;
		private String description;

		PlanVariant(String code, String description) {
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

		public static PlanVariant getEnum(String code) {
			for (PlanVariant type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			PlanVariant type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}

	public enum Language {
		ENGLISH("en_US", "English"), SPANISH("es_US", "Spanish");

		private String code;
		private String description;

		Language(String code, String description) {
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

		public static Language getEnum(String code) {
			for (Language type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			Language type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}

	public enum PlanUploadDocType {
		ENGLISH("EN", "English"), SPANISH("ES", "Spanish");

		private String code;
		private String description;

		PlanUploadDocType(String code, String description) {
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

		public static PlanUploadDocType getEnum(String code) {
			for (PlanUploadDocType type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			PlanUploadDocType type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}

	public enum RecordMode {
		EDIT("edit", "Edit"), VIEW("view", "View"), ADD("add", "Add");

		private String code;
		private String description;

		RecordMode(String code, String description) {
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

		public static RecordMode getEnum(String code) {
			for (RecordMode type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			RecordMode type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}
	
	public enum UserActivationStatus {
		ACTIVE("A", "Active"), DEACTIVATED("D", "Deactivated");

		private String code;
		private String description;

		UserActivationStatus(String code, String description) {
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

		public static UserActivationStatus getEnum(String code) {
			for (UserActivationStatus type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			UserActivationStatus type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}

	public enum AccessCheckMode {
		USER_ACTION("A", "Specific function in the system"), WEB_ACTION("S", "Web Action Path"), WEB_URL("W", "Web URL");

		private String code;
		private String description;

		AccessCheckMode(String code, String description) {
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

		public static AccessCheckMode getEnum(String code) {
			for (AccessCheckMode type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			AccessCheckMode type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}

	public enum UserRole {
		CARRIER("HIX_Carriers", "Carrier User"), 
		PLAN_MANAGER("HIX_PlanManager", "Plan Manager"), 
		PLAN_SPECIALIST("HIX_PlanSpecialist", "Plan Specialist");

		private String code;
		private String description;

		UserRole(String code, String description) {
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

		public static UserRole getEnum(String code) {
			for (UserRole type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			UserRole type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}

	public enum UserFunction {
		CARRIER_ADD("CARRIER_ADD", "Add Carrier"), CARRIER_EDIT("CARRIER_EDIT", "Edit Carrier"), 
		CARRIER_VIEW("CARRIER_VIEW", "View Carrier"), CARRIERUSER_ADD("CARRIERUSER_ADD", "Add Carrier User"), 
		CARRIERUSER_EDIT("CARRIERUSER_EDIT", "Edit Carrier User"),
		CARRIERUSER_VIEW("CARRIERUSER_VIEW", "View Carrier User"),
		PLAN_EDIT("PLAN_EDIT", "Edit Plan"), PLAN_DOWNLOAD("PLAN_DOWNLOAD", "Download Plan"), PLAN_TRANSLATED_UPLOAD("PLAN_TRANSLATED_UPLOAD", "Plan Translation Upload");

		private String code;
		private String description;

		UserFunction(String code, String description) {
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

		public static UserFunction getEnum(String code) {
			for (UserFunction type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			UserFunction type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}
	
	/**
	 * 
	 * @author ujjalb
	 *
	 */
	public enum UploadTypes {
		
		ADMIN_TEMP("AT", "Admin Template"), NETWORK_TEMP("NT", "Network Template"), PBT_TEMP("PBT", "Plan Benefits Template"), 
		DRUG_TEMP("PDT", "Rx / Drugs Template"), RATE_TEMP("RDT", "Rates Template");
		 
		private String uploadType;
		private String uploadDescription;
	 
		private UploadTypes(String s, String description) {
			uploadType = s;
			uploadDescription = description;
		}
		
		public String getUploadType() {
			return uploadType;
		}
		
		public String getUploadDescription() {
			return uploadDescription;
		}
		
		public static UploadTypes getEnum(String uploadType) {
			for (UploadTypes type : values()) {
				if (type.uploadType.equals(uploadType)) {
					return type;
				}
			}
			return null;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return StringUtils.trimToEmpty(uploadType) + ":" + StringUtils.trimToEmpty(uploadDescription);
		}
		
		
	}

	public enum YesNo {
		YES("Y", "Yes"), NO("N", "No");

		private String code;
		private String description;

		YesNo(String code, String description) {
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
			return code + ":" + StringUtils.trimToEmpty(description);
		}

		public static YesNo getEnum(String code) {
			for (YesNo type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			if(code == null){
				return false;
			}
			YesNo type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
	}
	
	
	public enum PlanMetalLevel {
		SILVER("S", "Silver"), GOLD("G", "Gold"), PLATINUM("P", "Platinum"), BRONZE("B", "Bronze"), CATASTROPHIC("C", "Catastrophic");

		private String code;
		private String description;

		PlanMetalLevel(String code, String description) {
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
			return code + ":" + StringUtils.trimToEmpty(description);
		}

		public static PlanMetalLevel getEnum(String code) {
			for (PlanMetalLevel type : values()) {
				if (type.code.equals(code)) {
					return type;
				}
			}
			return null;
		}
		
		public boolean equals(String code){
			if(code == null){
				return false;
			}
			PlanMetalLevel type = getEnum(code);
			if(type == null){
				return false;
			}
			return type == this;
		}
		
		
	}
	
}
