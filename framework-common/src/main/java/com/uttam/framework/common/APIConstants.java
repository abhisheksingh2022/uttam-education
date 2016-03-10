package com.uttam.framework.common;

import java.util.Locale;

public class APIConstants {

	public static final Locale LOCALE_EN = APIUtil.getLocale("en_US");
	public static final Locale LOCALE_ES = APIUtil.getLocale("es_US");

	public static final String REQUEST_UUID = "REQUEST_UUID";
	public static final String STARTUP_REQ_ID = "SYSSTART";

	public static final String LOG4JCONTEXT_CLIENT_IP = "CLIENT_IP";
	public static final String LOG4JCONTEXT_EVENT_NM = "EVENT_NM";
	public static final String LOG4JCONTEXT_EVENT_SUCCESS_IND = "EVENT_SUCCESS_IND";
	public static final String LOG4JCONTEXT_EVENT_DETAIL = "EVENT_DETAIL";

	/** The Constant ENV_PROD. */
	public static final String ENV_PROD = "PROD";

	/** The Constant ENV_DEV. */
	public static final String ENV_DEV = "DEV";

	/** The Constant ENV_SIT. */
	public static final String ENV_SIT = "SIT";

	/** The Constant ENV_UAT. */
	public static final String ENV_UAT = "UAT";

	/** The Constant ENV_STAGING. */
	public static final String ENV_STAGING = "STAGING";

	/** The Constant ENV_TRNG. */
	public static final String ENV_TRNG = "TRNG";

	/** The Property Groups. */
	public static final String PROPGROUP_GLOBAL = "GLOBAL";
	public static final String PROPGROUP_WEB_SERVICES = "WEBSERVICES";
	public static final String PROPGROUP_PLAN_UPLOAD = "PLAN_UPLOAD";
	public static final String PROPGROUP_NAS = "NAS";
	
	/** The Property Keys. */
	public static final String PROP_KEY_PREFIX_PLAN_QLTY_RATING = "plan.default_qlty_rating.";
	public static final String PROPKEY_ENVIRONMENT = "global.environment";
	public static final String PROPKEY_BUILD_ID = "global.build.id";
	public static final String PROPKEY_AUTH_REQUIRED = "global.authrequired";
	public static final String PROPKEY_ENABLE_ISIM = "global.security.enable_isim";
	public static final String PROPKEY_TEST_USER_FNAME = "global.security.test_user_firstname";
	public static final String PROPKEY_TEST_USER_LNAME = "global.security.test_user_lastname";
	public static final String PROPKEY_TEST_USER_EMAIL = "global.security.test_user_email";
	public static final String PROPKEY_ISIM_PRINCIPAL = "isim.services.credential.principal";
	public static final String PROPKEY_ISIM_ENC_PASSWORD = "isim.services.credential.encpassword";
	public static final String PROPKEY_ISIM_RETRY_COUNTER = "isim.services.retry_counter";
	public static final String PROPKEY_ISIM_SLEEP_DURATION = "isim.services.sleep_duration";
	public static final String PROPKEY_ISIM_CARRIER_CONTAINER = "isim.services.container.carrier";
	public static final String PROP_KEY_ISIM_EXTERNAL_USER_PROFILE_NAME = "isim.services.profile.carrier";
	public static final String PROP_KEY_ISIM_CARRIER_ROLE_NAME = "isim.services.role.carrier";
	public static final String PROP_KEY_ISIM_PASSWORD_VALIDATOR_ID = "isim.services.credential.passwordvalidator";
	public static final String PROP_KEY_NAS_ROOT_FOLDER = "nas.planpdf.rootfolder";
	public static final String PROP_KEY_NAS_PLAN_PDF_DRAFT_FOLDER = "nas.planpdf.drafts.folder";
	public static final String PROP_KEY_NAS_PLAN_PDF_FINAL_FOLDER = "nas.planpdf.final.folder";
	
	public static final String PROP_KEY_OESTART_DATE = "global.oestart.2016";

	//rrError Code keys
	public static final String ERRORCODE_SESSION_INVALID = "error.session.invalid";
	public static final String ERRORCODE_GENERAL_ERROR = "error.unknown";
	public static final String ERRORCODE_ACCESS_DENIED = "error.accessdenied";
	//public static final String ERRORCODE_USER_NOT_FOUND = "error.accessdenied";

	/*HTTP Client properties*/
	public static final String KEY_HTTPCLIENT_INT_HTTP_MAX_CONN_PER_ROUTE = "apachehttp.max_conn_per_route";
	public static final String KEY_HTTPCLIENT_INT_HTTP_MAX_CONN_TOTAL = "apachehttp.max_conn_total";
	public static final String KEY_HTTPCLIENT_BOOL_HTTP_USE_PROXY = "apachehttp.use_proxy";
	public static final String KEY_HTTPCLIENT_STRING_PROXY_NAME = "apachehttp.proxy_server";
	public static final String KEY_HTTPCLIENT_STRING_PROXY_PORT = "apachehttp.proxy_port";
	public static final String KEY_HTTPCLIENT_STRING_PROXY_TRUST_STORE = "apachehttp.trust_store_path";
	public static final String KEY_HTTPCLIENT_BOOL_USE_TRUST_STORE = "apachehttp.use_trust_store";
	public static final String KEY_HTTPCLIENT_STRING_TRUST_STORE_PWD = "apachehttp.trust_store_pwd";
	public static final String KEY_HTTPCLIENT_INT_KEEP_ALIVE_DURATION = "apachehttp.keep_alive_duration";
	public static final String KEY_HTTPCLIENT_USER_AGENT = "apachehttp.user_agent";
	public static final String REST_SERVICE = "REST_CLIENT";
	public static final String FLASH_REQUEST_INDICATOR = "prgRequest";

	//Indicators
	public static final String INDICATOR_YES = "Y";
	public static final String INDICATOR_NO = "N";

	//Web Services
	public static final String JAX_WS_SERVICE_TIMEOUT = "timeout";

	//Web Services - ISIM
	public static final String WS_ISIM_SESSSION_SERVICE = "isim.WSSessionService";
	public static final String WS_ISIM_PERSON_SERVICE = "isim.WSPersonService";
	public static final String WS_ISIM_ACCOUNT_SERVICE = "isim.WSAccountService";
	public static final String WS_ISIM_SHARED_ACCESS_SERVICE = "isim.WSSharedAccessService";
	public static final String WS_ISIM_GROUP_SERVICE = "isim.WSGroupService";
	public static final String WS_ISIM_ORGANIZATIONAL_CONTAINER_SERVICE = "isim.WSOrganizationalContainerService";
	public static final String WS_ISIM_PASSWORD_SERVICE = "isim.WSPasswordService";
	public static final String WS_ISIM_PROVISIONING_POLICY_SERVICE = "isim.WSProvisioningPolicyService";
	public static final String WS_ISIM_ROLE_SERVICE = "isim.WSRoleService";
	public static final String WS_ISIM_SERVICE_SERVICE = "isim.WSServiceService";
	public static final String WS_ISIM_SYSTEM_USER_SERVICE = "isim.WSSystemUserService";
	public static final String WS_ISIM_SEARCH_DATA_SERVICE = "isim.WSSearchDataService";
	public static final String WS_ISIM_TODO_SERVICE = "isim.WSToDoService";
	public static final String WS_ISIM_ACCESS_SERVICE = "isim.WSAccessService";
	public static final String WS_ISIM_REQUEST_SERVICE = "isim.WSRequestService";
	public static final String WS_ISIM_CODE_OPEN_BRACES = "(";
	public static final String WS_ISIM_CODE_CLOSE_BRACES = ")";
	public static final String WS_ISIM_CODE_EQUAL_STRING = "=";
	public static final String WS_ISIM_CODE_SPACE_STRING = " ";
	public static final String WS_ISIM_CODE_GIVEN_NAME = "givenname";
	public static final String WS_ISIM_CODE_LAST_NAME = "sn";
	public static final String WS_ISIM_CODE_COMPLETE_NAME = "cn";
	public static final String WS_ISIM_CODE_USER_ID = "uid";
	public static final String WS_ISIM_CODE_PASSWORD = "erpersonpassword";
	public static final String WS_ISIM_CODE_ERROLES = "erroles";
	public static final String WS_ISIM_CODE_EMAIL = "mail";
	public static final String WS_ISIM_CODE_PHONE = "telephoneNumber";
	public static final String WS_ISIM_CODE_MOBILE = "mobile";
	public static final String WS_ISIM_CODE_ERROLE_NAME = "errolename";
	public static final String WS_ISIM_CODE_CRED_ID = "ctmfacredid";
	public static final String WS_ISIM_ATTR_ERROLE_NAME = "errolename";
	public static final String WS_ISIM_UNAUTH_SERVICE = "isim.WSUnauthService";
	public static final String WS_ISIM_EXTENSION_SERVICE = "isim.WSExtensionService";
	public static final String WS_ISIM_ERRORCODE_CONNECTIVITY = "exception.isim.connectivity";
	public static final String WS_ISIM_ERRORCODE_USER_EXISTS = "exception.isim.user_id_exists";
	public static final String WS_ISIM_ERRORCODE_EMAIL_EXISTS = "exception.isim.email_id_exists";
	public static final String WS_ISIM_ERRORCODE_INVALID_PASSWORD = "exception.isim_invalid_password";
	public static final String WS_ISIM_ERRORCODE_USER_NOT_FOUND = "exception.isim_user_not_found";

	public static final String WS_ISIM_REQUEST_STATUS_INPROCESS = "In Process";
	public static final String WS_ISIM_REQUEST_STATUS_WARNING = "Warning";
	public static final String WS_ISIM_REQUEST_STATUS_SUCCEEDED = "Succeeded";
	public static final String WS_ISIM_REQUEST_STATUS_FAILED = "Failed";
	public static final String WS_ISIM_REQUEST_STATUS_COMPLETE = "C";

	public static final String WS_ISIM_USER_CREATE_STATUS_FAILED = "Failed";
	public static final String WS_ISIM_USER_CREATE_STATUS_SUCCESS = "Success";

	public static final String LKUP_TYPE_PLAN_TEMPLATES = "PlanTemplateTypes";

	public static final String MAPPING_ADMIN_CEO = "OrganizationCEOContact";
	public static final String MAPPING_ADMIN_CFO = "OrganizationCFOContact";
	public static final String MAPPING_ADMIN_SMALL_GROUP_MARKET = "SmallGroupMarketContact";
	//	public static final String MAPPING_ADMIN_CFO = "OrganizationCFOContact";
	//	public static final String MAPPING_ADMIN_CFO = "OrganizationCFOContact";
	//	public static final String MAPPING_ADMIN_CFO = "OrganizationCFOContact";

	public static final String SYSTEM_USER_ID = "system";
	public static final String NOT_APPLICABLE = "Not Applicable";
	
	public static final String COPAY_IN = "COPAY-IN";
	public static final String COPAY_OUT = "COPAY-OUT";
	public static final String COINS_IN = "COINS-IN";
	public static final String COINS_OUT = "COINS-OUT";

}
