package com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.impl;

import cn.hutool.json.JSONUtil;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.BigDatasourceHttpClient;
import com.particle.global.tool.json.JsonTool;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

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
public class BigDatasourceHttpJoddClientImpl implements BigDatasourceHttpClient {
	@Override
	public Object postJson(String url, Map<String,String> headers, Object command,String queryString) {
		HttpResponse httpResponse = HttpRequest.post(url)
				.bodyText(JsonTool.toJsonStr(command), "application/json")
				.header(headers)
				.send();
		String result = httpResponse.bodyText();
		return adapteResult(httpResponse.contentType(), result);

	}

	@Override
	public Object postFormData(String url,Map<String,String> headers, Object command,String queryString) {
		HttpResponse httpResponse = HttpRequest.post(url)
				.bodyText(JsonTool.toJsonStr(command), "multipart/form-data")
				.header(headers)
				.send();

		String result = httpResponse.bodyText();
		return adapteResult(httpResponse.contentType(), result);

	}

	@Override
	public Object postXWwwFormUrlencoded(String url, Map<String,String> headers,Object command,String queryString) {
		HttpResponse httpResponse = HttpRequest.post(url)
				.bodyText(JsonTool.toJsonStr(command), "application/x-www-form-urlencoded")
				.header(headers)
				.send();
		String result = httpResponse.bodyText();
		return adapteResult(httpResponse.contentType(), result);

	}

	@Override
	public Object postText(String url, Map<String,String> headers,Object command,String queryString) {
		throw new BigDatasourceException(" post text not support currently");
	}

	@Override
	public Object postXml(String url,Map<String,String> headers, Object command,String queryString) {
		throw new BigDatasourceException(" post xml not support currently");
	}

	@Override
	public Object get(String url, Map<String,String> headers,Object command,String queryString) {
		Map<String, String> queryMap = Collections.emptyMap();
		boolean b = command instanceof Map;
		if (b) {
			queryMap = ((Map<String, String>) command);
		}
				HttpResponse httpResponse = HttpRequest.get(url)
				.query(queryMap)
				.header(headers)
				.send();
		String result = httpResponse.bodyText();
		return adapteResult(httpResponse.contentType(), result);
	}



	private Object adapteResult(String responseContentType, Object result) {
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


	public static BigDatasourceHttpJoddClientImpl create() {
		BigDatasourceHttpJoddClientImpl bigDatasourceHttpJoddClient = new BigDatasourceHttpJoddClientImpl();
		return bigDatasourceHttpJoddClient;
	}
}
