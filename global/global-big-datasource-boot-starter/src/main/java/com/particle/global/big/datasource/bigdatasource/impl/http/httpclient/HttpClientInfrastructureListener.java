package com.particle.global.big.datasource.bigdatasource.impl.http.httpclient;

import com.particle.global.big.datasource.bigdatasource.infrastructure.InfrastructureListener;

import java.util.Map;

/**
 * <p>
 * http大数据源调用基础监听
 * </p>
 *
 * @author yangwei
 * @since 2023-08-22 14:05
 */
public interface HttpClientInfrastructureListener extends InfrastructureListener {

	/**
	 * 请求前调用
	 * @param requestUrl
	 * @param headers
	 * @param command
	 * @param commandJsonStr
	 * @param queryString
	 * @param contentType
	 * @param method
	 */
	void beforeRequest(String requestUrl, Map<String, String> headers, Object command, String commandJsonStr, String queryString, String contentType,String method);

	/**
	 * 请求返回结果后调用
	 * @param requestUrl
	 * @param headers
	 * @param command
	 * @param commandJsonStr
	 * @param queryString
	 * @param contentType
	 * @param method
	 * @param responseResult
	 */
	void afterResponse(String requestUrl,
					   Map<String, String> headers,
					   Object command,
					   String commandJsonStr,
					   String queryString,
					   String contentType,
					   String method,String responseResult,
					   Integer responseHttpStatus,
					   String responseBusinessStatus,
					   Integer handleDuration);
}
