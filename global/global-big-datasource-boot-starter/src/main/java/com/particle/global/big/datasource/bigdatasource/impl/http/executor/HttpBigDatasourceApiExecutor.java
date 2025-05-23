package com.particle.global.big.datasource.bigdatasource.impl.http.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApiContext;
import com.particle.global.big.datasource.bigdatasource.api.config.IBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.http.api.config.HttpBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.config.HttpBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestMethod;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.BigDatasourceHttpClient;
import com.particle.global.tool.proxy.ProxyConfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * http大数据源接口执行器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-27 13:53
 */
public class HttpBigDatasourceApiExecutor extends AbstractHttpBigDatasourceApiExecutor{


	private BigDatasourceHttpClient bigDatasourceHttpClient;
	private HttpBigDatasourceConfig httpBigDatasourceConfig;

	/**
	 * 请求头处理
	 * @param command
	 * @param commandJsonStr
	 * @param queryString
	 * @param authMap 额外的扩展数据
	 * @return
	 */
	private Map<String, String> getHeaders(Object command,String commandJsonStr,String queryString,Map<String, Object> authMap) {
		Map<String, String> map = httpBigDatasourceConfig.renderAuthHeaders(command,commandJsonStr, queryString,authMap);
		if (map != null) {
			return map;
		}

		return Collections.EMPTY_MAP;
	}

	/**
	 * 获取请求地址
	 * @param bigDatasourceApi
	 * @param command
	 * @param queryString
	 * @param authMap
	 * @return
	 */
	private String getUrl(BigDatasourceApi bigDatasourceApi, Object command,String queryString,Map<String, Object> authMap) {

		IBigDatasourceApiConfig config = bigDatasourceApi.config();
		HttpBigDatasourceApiConfig httpBigDatasourceApiConfig = (HttpBigDatasourceApiConfig) config;
		Map<String, Object> objectMap = httpBigDatasourceConfig.shareDataMap();
		objectMap.put("authMap",authMap);
		return httpBigDatasourceConfig.getDomainUrl() + httpBigDatasourceApiConfig.renderRequestUrl(command,queryString,objectMap);
	}


	public static HttpBigDatasourceApiExecutor create(
			BigDatasourceHttpClient bigDatasourceHttpClient,
			HttpBigDatasourceConfig httpBigDatasourceConfig
	){
		HttpBigDatasourceApiExecutor httpBigDatasourceApiExecutor = new HttpBigDatasourceApiExecutor();
		httpBigDatasourceApiExecutor.setBigDatasourceHttpClient(bigDatasourceHttpClient);
		httpBigDatasourceApiExecutor.setHttpBigDatasourceConfig(httpBigDatasourceConfig);
		// 初始化监听
		httpBigDatasourceApiExecutor.executorInfrastructureListenerInitFromSpring();
		httpBigDatasourceApiExecutor.bigDatasourceApiExecutorExeCacheInitFromSpring();
		httpBigDatasourceApiExecutor.bigDatasourceTransSupportServiceInitFromSpring();
		return httpBigDatasourceApiExecutor;
	}


	@Override
	public Object executeHttp(BigDatasourceApi bigDatasourceApi, Object command, String commandString, String queryString) {
		IBigDatasourceApiConfig config = bigDatasourceApi.config();
		HttpBigDatasourceApiConfig httpBigDatasourceApiConfig = (HttpBigDatasourceApiConfig) config;
		ProxyConfig proxyConfig = httpBigDatasourceConfig.getProxyConfig();
		Map<String, Object> authMap = new HashMap<>();
		Map<String, String> headers = getHeaders(command,commandString,queryString,authMap);
		String url = getUrl(bigDatasourceApi, command,queryString,authMap);
		BigDatasourceApiContext bigDatasourceApiContext = bigDatasourceApi.apiContext();

		Integer connectTimeout = bigDatasourceApi.connectTimeout();
		Integer readTimeout = bigDatasourceApi.readTimeout();

		String contentType = httpBigDatasourceApiConfig.getRequestContentType().getContentType();
		if (HttpBigDatasourceApiConfigRequestMethod.get == httpBigDatasourceApiConfig.getRequestMethod()) {
			return bigDatasourceHttpClient.get(url,headers,command, commandString,
					queryString, contentType,proxyConfig,bigDatasourceApiContext,connectTimeout,readTimeout);
		}
		if (HttpBigDatasourceApiConfigRequestMethod.post == httpBigDatasourceApiConfig.getRequestMethod()) {
			return bigDatasourceHttpClient.post(url,headers,command, commandString,
					queryString, contentType,proxyConfig,bigDatasourceApiContext,connectTimeout,readTimeout);
		}
		if (HttpBigDatasourceApiConfigRequestMethod.delete == httpBigDatasourceApiConfig.getRequestMethod()) {
			return bigDatasourceHttpClient.delete(url,headers,command, commandString,
					queryString, contentType,proxyConfig,bigDatasourceApiContext,connectTimeout,readTimeout);
		}
		if (HttpBigDatasourceApiConfigRequestMethod.put == httpBigDatasourceApiConfig.getRequestMethod()) {
			return bigDatasourceHttpClient.put(url,headers,command, commandString,
					queryString, contentType,proxyConfig,bigDatasourceApiContext,connectTimeout,readTimeout);
		}
		if (HttpBigDatasourceApiConfigRequestMethod.patch == httpBigDatasourceApiConfig.getRequestMethod()) {
			return bigDatasourceHttpClient.patch(url,headers,command, commandString,
					queryString, contentType,proxyConfig,bigDatasourceApiContext,connectTimeout,readTimeout);
		}
		throw new BigDatasourceException("request method " + httpBigDatasourceApiConfig.getRequestMethod().name() + " not support currently");
	}



	public BigDatasourceHttpClient getBigDatasourceHttpClient() {
		return bigDatasourceHttpClient;
	}

	public void setBigDatasourceHttpClient(BigDatasourceHttpClient bigDatasourceHttpClient) {
		this.bigDatasourceHttpClient = bigDatasourceHttpClient;
	}

	public HttpBigDatasourceConfig getHttpBigDatasourceConfig() {
		return httpBigDatasourceConfig;
	}

	public void setHttpBigDatasourceConfig(HttpBigDatasourceConfig httpBigDatasourceConfig) {
		this.httpBigDatasourceConfig = httpBigDatasourceConfig;
	}
}
