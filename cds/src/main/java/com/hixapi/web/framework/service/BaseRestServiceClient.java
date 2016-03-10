package com.hixapi.web.framework.service;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.hixapi.web.framework.common.APIConstants;
import com.hixapi.web.framework.env.IEnvironmentProvider;
import com.hixapi.web.framework.exception.ConfigurationException;

/**
 * 
 * @author admin
 *
 */
public class BaseRestServiceClient {

	private CloseableHttpClient httpClient;
	private boolean initialized;

	private static final Logger log = LogManager.getLogger(BaseRestServiceClient.class);

	private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;

	private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;
	private static final int DEFAULT_KEEPALIVE_DURATION = 5;

	private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (30 * 1000);

	private static final List<MediaType> json = Collections.singletonList(new MediaType("application", "json"));

	public BaseRestServiceClient() {
		
	}

	private ClientHttpRequestFactory httpRequestFactory() {
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

	public RestTemplate getRestClient() {
		log.entry();
		initialize();
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
		// List<HttpMessageConverter<?>> converters = restTemplate
		// .getMessageConverters();
		//
		// for (HttpMessageConverter<?> converter : converters) {
		// if (converter instanceof MappingJackson2HttpMessageConverter) {
		// MappingJackson2HttpMessageConverter jsonConverter =
		// (MappingJackson2HttpMessageConverter) converter;
		// jsonConverter.setObjectMapper(objectMapper);
		// }
		// }

		return log.exit(restTemplate);
	}

	protected <T> HttpEntity<T> getRestPostEntity(T body) {
		log.entry();
		initialize();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(json);
		requestHeaders.setContentType(new MediaType("application", "json"));
		HttpEntity<T> entity = new HttpEntity<T>(body);

		return log.exit(entity);

	}

	protected String getEndpoint(String serviceCode) {
		log.entry();
		initialize();
		String value = ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
				.getProperty(serviceCode, String.class, "");
		if (StringUtils.isBlank(value)) {
			throw new ConfigurationException("Service Endpoint Not Found for " + serviceCode, APIConstants.REST_SERVICE);
		}
		return log.exit(value.trim());

	}
	
	private void initialize(){
		if(!initialized){
			initializeHttpClient();
			initialized = true;
		}
	}

	private synchronized void initializeHttpClient() {
		log.entry();
		final LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
		final PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		final int maxPerRouteStr = ServiceLocator
				.getInstance()
				.getService(IEnvironmentProvider.class)
				.getProperty(APIConstants.KEY_HTTPCLIENT_INT_HTTP_MAX_CONN_PER_ROUTE, Integer.class,
						DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
		connManager.setDefaultMaxPerRoute(maxPerRouteStr);

		final int maxTotalStr = ServiceLocator
				.getInstance()
				.getService(IEnvironmentProvider.class)
				.getProperty(APIConstants.KEY_HTTPCLIENT_INT_HTTP_MAX_CONN_TOTAL, Integer.class,
						DEFAULT_MAX_TOTAL_CONNECTIONS);
		connManager.setMaxTotal(maxTotalStr);

		final int keepAliveStr = ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
				.getProperty(APIConstants.KEY_HTTPCLIENT_INT_KEEP_ALIVE_DURATION, Integer.class, DEFAULT_KEEPALIVE_DURATION);
		// Keep-Alive strategy
		ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {

			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {

				HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						return Long.parseLong(value) * 1000;
					}
				}
				return keepAliveStr * 1000;

			}
		};

		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(DEFAULT_READ_TIMEOUT_MILLISECONDS)
				.setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();

		boolean useProxy = ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
				.getProperty(APIConstants.KEY_HTTPCLIENT_BOOL_HTTP_USE_PROXY, Boolean.class, false);
		if (useProxy) {

			boolean useTrustStore = ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
					.getProperty(APIConstants.KEY_HTTPCLIENT_BOOL_USE_TRUST_STORE, Boolean.class, false);
			HttpHost proxy = new HttpHost(Validate.notBlank(ServiceLocator.getInstance()
					.getService(IEnvironmentProvider.class)
					.getProperty(APIConstants.KEY_HTTPCLIENT_STRING_PROXY_NAME, String.class, "")),
					Integer.parseInt(ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
							.getProperty(APIConstants.KEY_HTTPCLIENT_STRING_PROXY_PORT, String.class, "")));
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
			SSLConnectionSocketFactory sslSF = null;
			if (useTrustStore) {
				String password = Validate.notBlank(ServiceLocator.getInstance().getService(IEnvironmentProvider.class)
						.getProperty(APIConstants.KEY_HTTPCLIENT_STRING_TRUST_STORE_PWD, String.class, ""));

				FileInputStream inStream = null;
				try {
					KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
					inStream = new FileInputStream(new File(Validate.notBlank(ServiceLocator.getInstance()
							.getService(IEnvironmentProvider.class)
							.getProperty(APIConstants.KEY_HTTPCLIENT_STRING_PROXY_TRUST_STORE, String.class, ""))));
					trustStore.load(inStream, password.toCharArray());
					SSLContext sslContext = SSLContexts.custom().useProtocol("TLS")
							.loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
					sslSF = new SSLConnectionSocketFactory(sslContext);
				} catch (Exception ex) {
					log.throwing(ex);
					throw new RuntimeException("Failed to create Http Client", ex);
				} finally {
					try {
						inStream.close();
					} catch (Exception ex) {
						log.throwing(ex);
					}
				}

			}
			HttpClientBuilder httpClientBuilder = HttpClients.custom().setRoutePlanner(routePlanner)
					.setConnectionManager(connManager).setKeepAliveStrategy(keepAliveStrategy)
					.setRedirectStrategy(redirectStrategy);
			if (sslSF != null) {
				httpClientBuilder.setSSLSocketFactory(sslSF);
			}
			httpClient = httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig).build();

		} else {
			httpClient = HttpClients.custom().setConnectionManager(connManager).setKeepAliveStrategy(keepAliveStrategy)
					.setRedirectStrategy(redirectStrategy).setDefaultRequestConfig(defaultRequestConfig).build();
		}

		log.debug("Http Client initialized");
		log.exit();

	}
}
