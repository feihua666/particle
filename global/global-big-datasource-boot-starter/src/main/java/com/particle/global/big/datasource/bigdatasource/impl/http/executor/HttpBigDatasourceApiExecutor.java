package com.particle.global.big.datasource.bigdatasource.impl.http.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.IBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.api.config.HttpBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.config.HttpBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.BigDatasourceHttpClient;

import java.util.Collections;
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

	@Override
	public Object executePostJson(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		String url = getUrl(bigDatasourceApi, command,queryString);
		Map<String, String> headers = getHeaders(command,queryString);
		return bigDatasourceHttpClient.postJson(url, headers,command,queryString);
	}

	@Override
	public Object executePostFormData(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		String url = getUrl(bigDatasourceApi, command,queryString);
		Map<String, String> headers = getHeaders(command,queryString);
		return bigDatasourceHttpClient.postFormData(url,headers,command,queryString);
	}

	@Override
	public Object executePostXWwwFormUrlencoded(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		String url = getUrl(bigDatasourceApi, command,queryString);
		Map<String, String> headers = getHeaders(command,queryString);
		return bigDatasourceHttpClient.postXWwwFormUrlencoded(url,headers,command,queryString);
	}

	@Override
	public Object executePostText(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		String url = getUrl(bigDatasourceApi, command,queryString);
		Map<String, String> headers = getHeaders(command,queryString);
		return bigDatasourceHttpClient.postText(url,headers,command,queryString);
	}

	@Override
	public Object executePostXml(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		String url = getUrl(bigDatasourceApi, command,queryString);
		Map<String, String> headers = getHeaders(command,queryString);
		return bigDatasourceHttpClient.postXml(url,headers,command,queryString);
	}

	@Override
	public Object doExecuteGet(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		String url = getUrl(bigDatasourceApi, command,queryString);
		Map<String, String> headers = getHeaders(command,queryString);
		return bigDatasourceHttpClient.get(url,headers,command,queryString);
	}

	private Map<String, String> getHeaders(Object command,String queryString) {
		Map<String, String> map = httpBigDatasourceConfig.renderAuthHeaders(command, queryString);
		if (map != null) {
			return map;
		}

		return Collections.EMPTY_MAP;
	}

	/**
	 * 获取请求地址
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	private String getUrl(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {

		IBigDatasourceApiConfig config = bigDatasourceApi.config();
		HttpBigDatasourceApiConfig httpBigDatasourceApiConfig = (HttpBigDatasourceApiConfig) config;

		return httpBigDatasourceConfig.getDomainUrl() + httpBigDatasourceApiConfig.renderRequestUrl(command,queryString);
	}


	public static HttpBigDatasourceApiExecutor create(
			BigDatasourceHttpClient bigDatasourceHttpClient,
			HttpBigDatasourceConfig httpBigDatasourceConfig
	){
		HttpBigDatasourceApiExecutor httpBigDatasourceApiExecutor = new HttpBigDatasourceApiExecutor();
		httpBigDatasourceApiExecutor.setBigDatasourceHttpClient(bigDatasourceHttpClient);
		httpBigDatasourceApiExecutor.setHttpBigDatasourceConfig(httpBigDatasourceConfig);
		return httpBigDatasourceApiExecutor;
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
