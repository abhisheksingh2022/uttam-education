package com.uttam.framework.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "display_text")
public class DisplayText extends BaseModel{
	
		private static final long serialVersionUID = 1L;
		@Id
		private String id;
		private String textKey;
		private String textValue;
		private String textLangCode;
		private String serviceProviderId;

		@Override
		public String getId() {
			return id;
		}

		public String getTextKey() {
			return textKey;
		}

		public void setTextKey(String textKey) {
			this.textKey = textKey;
		}

		public String getTextValue() {
			return textValue;
		}

		public void setTextValue(String textValue) {
			this.textValue = textValue;
		}

		public String getTextLangCode() {
			return textLangCode;
		}

		public void setTextLangCode(String textLangCode) {
			this.textLangCode = textLangCode;
		}

		public String getServiceProviderId() {
			return serviceProviderId;
		}

		public void setServiceProviderId(String serviceProviderId) {
			this.serviceProviderId = serviceProviderId;
		}

		public void setId(String id) {
			this.id = id;
		}
}
