package com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.impl;

import cn.hutool.json.JSONUtil;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.BigDatasourceHttpClient;
import com.particle.global.tool.json.JsonTool;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.util.Collections;
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


	@Override
	public Object get(String url, Map<String, String> headers, Object command, String commandJsonStr, String queryString, String contentType) {
		Map<String, String> queryMap = Collections.emptyMap();
		boolean b = command instanceof Map;
		if (b) {
			queryMap = ((Map<String, String>) command);
		}
		// get请求没有请求内容类型 contentType
		log.info("{}. url={},body={},queryString={},headers={},content-type={}","get",url,commandJsonStr,JsonTool.toJsonStr(headers),contentType);
		long start = System.currentTimeMillis();
		HttpResponse httpResponse = HttpRequest.get(url)
				// queryString 必须在前面，会重置内部query
				.queryString(queryString)
				.query(queryMap)
				.header(headers)
				.send();
		String result = httpResponse.charset("utf-8").bodyText();
		log.info("get result. duration={}ms, result={}",System.currentTimeMillis()-start,result);

		return adaptResult(httpResponse.contentType(), result);
	}

	@Override
	public Object post(String url, Map<String, String> headers, Object command, String commandJsonStr, String queryString, String contentType) {
		HttpRequest httpRequest = HttpRequest.post(url);
		return request(httpRequest, url, headers, command, commandJsonStr, queryString, contentType, "post");
	}

	@Override
	public Object delete(String url, Map<String, String> headers, Object command, String commandJsonStr, String queryString, String contentType) {
		HttpRequest httpRequest = HttpRequest.post(url);
		return request(httpRequest, url, headers, command, commandJsonStr, queryString, contentType, "delete");
	}

	@Override
	public Object put(String url, Map<String, String> headers, Object command, String commandJsonStr, String queryString, String contentType) {
		HttpRequest httpRequest = HttpRequest.post(url);
		return request(httpRequest, url, headers, command, commandJsonStr, queryString, contentType, "put");
	}

	@Override
	public Object patch(String url, Map<String, String> headers, Object command, String commandJsonStr, String queryString, String contentType) {
		HttpRequest httpRequest = HttpRequest.post(url);
		return request(httpRequest, url, headers, command, commandJsonStr, queryString, contentType, "patch");
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
	private Object request(HttpRequest httpRequest,String url, Map<String, String> headers, Object command, String commandJsonStr, String queryString, String contentType,String methodLog) {
		String body = commandJsonStr;
		log.info("{}. url={},body={},queryString={},headers={},content-type={}",methodLog,url,body,queryString,JsonTool.toJsonStr(headers),contentType);

		if (contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
			httpRequest.bodyText(body, contentType);
		} else if (contentType.startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
			httpRequest.form(((Map) command)).multipart(true).contentType(contentType);
		}else if (contentType.startsWith(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
			httpRequest.form(((Map) command)).contentType(contentType);
		}else {
			httpRequest.bodyText(body, contentType);
		}
		long start = System.currentTimeMillis();

		HttpResponse httpResponse = httpRequest
				.queryString(queryString)
				.header(headers)
				.send();
		String result = httpResponse.charset("utf-8").bodyText();
		log.info("{} result. duration={}ms, result={}",methodLog,System.currentTimeMillis() - start,result);

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
	 * 创建实例
	 * @return
	 */
	public static BigDatasourceHttpJoddClientImpl create() {
		BigDatasourceHttpJoddClientImpl bigDatasourceHttpJoddClient = new BigDatasourceHttpJoddClientImpl();
		return bigDatasourceHttpJoddClient;
	}

}
