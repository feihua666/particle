package com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.impl;

import cn.hutool.json.JSONUtil;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApiContext;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.BigDatasourceHttpClient;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.HttpClientInfrastructureListener;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.proxy.ProxyConfig;
import com.particle.global.tool.spring.SpringContextHolder;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.http.net.SocketHttpConnectionProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeansException;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基于 Jodd http 的实现
 * </p>
 *
 * @author yangwei
 * @since 2023-03-28 11:04
 */
@Slf4j
public class BigDatasourceHttpJoddClientImpl implements BigDatasourceHttpClient {


	public static final String apiContext_root_http = "http";

	public static final String apiContext_requestUrl = "requestUrl";
	public static final String apiContext_headers = "headers";
	public static final String apiContext_command = "command";
	public static final String apiContext_commandJsonStr = "commandJsonStr";
	public static final String apiContext_queryString = "queryString";
	public static final String apiContext_contentType = "contentType";
	public static final String apiContext_httpMethod = "httpMethod";


	public static final String apiContext_responseResult = "responseResult";
	public static final String apiContext_responseStatus = "responseStatus";
	public static final String apiContext_responseBusinessStatus = "responseBusinessStatus";
	public static final String apiContext_handleDuration = "handleDuration";

	private List<HttpClientInfrastructureListener> httpClientInfrastructureListeners;

	@Override
	public Object get(String url, Map<String, String> headers, Object command, String commandJsonStr,
					  String queryString, String contentType, ProxyConfig proxyConfig, BigDatasourceApiContext apiContext,Integer connectTimeout,
					  Integer readTimeout) {
		HttpRequest httpRequest = HttpRequest.get(url);
		return request(httpRequest, url, headers, command, commandJsonStr, queryString, contentType,proxyConfig, "get",apiContext,connectTimeout,readTimeout);
	}

	@Override
	public Object post(String url, Map<String, String> headers, Object command, String commandJsonStr,
					   String queryString, String contentType, ProxyConfig proxyConfig, BigDatasourceApiContext apiContext,Integer connectTimeout,
					   Integer readTimeout) {
		HttpRequest httpRequest = HttpRequest.post(url);
		return request(httpRequest, url, headers, command, commandJsonStr, queryString, contentType,proxyConfig, "post",apiContext,connectTimeout,readTimeout);
	}

	@Override
	public Object delete(String url, Map<String, String> headers, Object command, String commandJsonStr,
						 String queryString, String contentType, ProxyConfig proxyConfig, BigDatasourceApiContext apiContext,Integer connectTimeout,
						 Integer readTimeout) {
		HttpRequest httpRequest = HttpRequest.delete(url);
		return request(httpRequest, url, headers, command, commandJsonStr, queryString, contentType,proxyConfig, "delete",apiContext,connectTimeout,readTimeout);
	}

	@Override
	public Object put(String url, Map<String, String> headers, Object command, String commandJsonStr,
					  String queryString, String contentType, ProxyConfig proxyConfig, BigDatasourceApiContext apiContext,Integer connectTimeout,
					  Integer readTimeout) {
		HttpRequest httpRequest = HttpRequest.put(url);
		return request(httpRequest, url, headers, command, commandJsonStr, queryString, contentType,proxyConfig, "put",apiContext,connectTimeout,readTimeout);
	}

	@Override
	public Object patch(String url, Map<String, String> headers, Object command, String commandJsonStr,
						String queryString, String contentType, ProxyConfig proxyConfig, BigDatasourceApiContext apiContext,Integer connectTimeout,
						Integer readTimeout) {
		HttpRequest httpRequest = HttpRequest.patch(url);
		return request(httpRequest, url, headers, command, commandJsonStr, queryString, contentType,proxyConfig, "patch",apiContext,connectTimeout,readTimeout);
	}

	/**
	 * 发起请求
	 * @param httpRequest
	 * @param url
	 * @param headers
	 * @param command
	 * @param commandJsonStr
	 * @param queryString
	 * @param contentType
	 * @param methodLog
	 * @return
	 */
	private Object request(HttpRequest httpRequest,
						   String url,
						   Map<String, String> headers,
						   Object command,
						   String commandJsonStr,
						   String queryString,
						   String contentType,
						   ProxyConfig proxyConfig,
						   String methodLog,
						   BigDatasourceApiContext apiContext,
						   Integer connectTimeout,
						   Integer readTimeout) {
		String body = commandJsonStr;
		log.info("{}. url={},body={},queryString={},headers={},content-type={}",methodLog,url,body,queryString,JsonTool.toJsonStr(headers),contentType);

		/**
		 * 添加基础设施的监听
		 */
		if (httpClientInfrastructureListeners != null) {
			for (HttpClientInfrastructureListener httpClientInfrastructureListener : httpClientInfrastructureListeners) {
				httpClientInfrastructureListener.beforeRequest(url,headers, command, commandJsonStr, queryString, contentType,httpRequest.method());
			}
		}

		Map<String, Object> httpData = new HashMap<>();
		httpData.put(apiContext_requestUrl,url);
		httpData.put(apiContext_headers,headers);
		httpData.put(apiContext_command,command);
		httpData.put(apiContext_commandJsonStr,commandJsonStr);
		httpData.put(apiContext_queryString,queryString);
		httpData.put(apiContext_contentType,contentType);
		httpData.put(apiContext_httpMethod,httpRequest.method());

		apiContext.putData("http",httpData);

		if (command != null) {
			if (contentType != null && contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
				httpRequest.bodyText(body, contentType);
			} else if (contentType != null && contentType.startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
				httpRequest.form(((Map) command)).multipart(true).contentType(contentType);
			}else if (contentType != null && contentType.startsWith(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
				httpRequest.form(((Map) command)).contentType(contentType);
			}else {
				httpRequest.bodyText(body, contentType);
			}
		}

		long start = System.currentTimeMillis();

		httpRequest = httpRequest
				.queryString(queryString)
				.header(headers)
				.connectionTimeout(connectTimeout)
				.timeout(readTimeout)
		;
		ProxyConfig proxy = ProxyConfig.finalProxyConfig(proxyConfig);
		if (proxy != null) {
			HttpConnectionProvider connectionProvider=new SocketHttpConnectionProvider();
			ProxyInfo proxyInfo = null;
			if (Strings.isEmpty(proxy.getProxyType()) || ProxyConfig.ProxyType.http.name().equals(proxy.getProxyType())) {
				proxyInfo = ProxyInfo.httpProxy(proxy.getProxyAddress(), Integer.parseInt(proxy.getProxyPort()), proxy.getProxyUsername(), proxy.getProxyPassword());
			} else if(ProxyConfig.ProxyType.socks4.name().equals(proxy.getProxyType())) {
				proxyInfo = ProxyInfo.socks4Proxy(proxy.getProxyAddress(), Integer.parseInt(proxy.getProxyPort()), proxy.getProxyUsername());
			} else if(ProxyConfig.ProxyType.socks5.name().equals(proxy.getProxyType())) {
				proxyInfo = ProxyInfo.socks5Proxy(proxy.getProxyAddress(), Integer.parseInt(proxy.getProxyPort()), proxy.getProxyUsername(),proxy.getProxyPassword());
			}
			connectionProvider.useProxy(proxyInfo);
			httpRequest = httpRequest.withConnectionProvider(connectionProvider);
		}

		HttpResponse httpResponse = httpRequest.send();
		String result = httpResponse.charset("utf-8").bodyText();
		int statusCode = httpResponse.statusCode();
		long duration = System.currentTimeMillis() - start;
		log.info("{} result. duration={}ms, result={}",methodLog, duration,result);

		/**
		 * 添加基础设施的监听
		 */
		if (httpClientInfrastructureListeners != null) {
			for (HttpClientInfrastructureListener httpClientInfrastructureListener : httpClientInfrastructureListeners) {
				httpClientInfrastructureListener.afterResponse(url,headers, command, commandJsonStr, queryString, contentType,httpRequest.method(), result,
						statusCode,null, ((int) duration));
			}
		}

		httpData.put(apiContext_responseResult,result);
		httpData.put(apiContext_responseStatus,statusCode);
		httpData.put(apiContext_responseBusinessStatus,null);
		httpData.put(apiContext_handleDuration,(int) duration);

		return adaptResult(httpResponse.contentType(), result);

	}

	/**
	 * 适配结果
	 * @param responseContentType
	 * @param result
	 * @return
	 */
	private Object adaptResult(String responseContentType, Object result) {
		boolean b = responseContentType != null && responseContentType.startsWith("application/json");
		if (result != null && (result instanceof String) && b) {
			String string = result.toString().trim();
			if (string.startsWith("{")) {
				Map map = JSONUtil.toBean(string, Map.class);
				return map;
			}
			if (string.startsWith("[")) {
				List<Map> maps = JSONUtil.toList(string, Map.class);
				return maps;
			}
			return result;
		}
		return result;
	}

	/**
	 * 从spring窗口初始化
	 */
	public BigDatasourceHttpJoddClientImpl httpClientInfrastructureListenersInitFromSpring() {
		try {
			this.httpClientInfrastructureListeners = SpringContextHolder.getBeans(HttpClientInfrastructureListener.class);
		} catch (BeansException e) {
			log.warn("can not init httpClientInfrastructureListeners from spring because of exception",e);
		}
		return this;
	}
	/**
	 * 创建实例
	 * @return
	 */
	public static BigDatasourceHttpJoddClientImpl create() {
		BigDatasourceHttpJoddClientImpl bigDatasourceHttpJoddClient = new BigDatasourceHttpJoddClientImpl();
		return bigDatasourceHttpJoddClient;
	}


}
