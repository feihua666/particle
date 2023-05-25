package com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.impl;

import cn.hutool.json.JSONUtil;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.BigDatasourceHttpClient;
import com.particle.global.tool.json.JsonTool;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

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
	public Object postJson(String url, Map<String,String> headers, Object command,String queryString) {
		String body = JsonTool.toJsonStr(command);
		log.info("postJson. url={},body={},headers={}",url,body,JsonTool.toJsonStr(headers));
		HttpResponse httpResponse = HttpRequest.post(url)
				.bodyText(body, "application/json")
				.header(headers)
				.send();
		String result = httpResponse.charset("utf-8").bodyText();
		log.info("postJson result. result={}",result);

		return adapteResult(httpResponse.contentType(), result);

	}

	@Override
	public Object postFormData(String url,Map<String,String> headers, Object command,String queryString) {
		String form = JsonTool.toJsonStr(command);
		log.info("postFormData. url={},form={},headers={}",url,form,JsonTool.toJsonStr(headers));
		HttpResponse httpResponse = HttpRequest.post(url)
				//.form(JsonTool.toJsonStr(command), "multipart/form-data")
				.form(((Map) command))
				.header(headers)
				.send();

		String result = httpResponse.charset("utf-8").bodyText();
		log.info("postFormData result. result={}",result);

		return adapteResult(httpResponse.contentType(), result);

	}

	@Override
	public Object postXWwwFormUrlencoded(String url, Map<String,String> headers,Object command,String queryString) {
		String form = JsonTool.toJsonStr(command);
		log.info("postXWwwFormUrlencoded. url={},form={},headers={}",url,form,JsonTool.toJsonStr(headers));
		HttpResponse httpResponse = HttpRequest.post(url)
				//.bodyText(JsonTool.toJsonStr(command), "application/x-www-form-urlencoded")
				.form(((Map) command))
				.header(headers)
				.send();
		String result = httpResponse.charset("utf-8").bodyText();
		log.info("postXWwwFormUrlencoded result. result={}",result);

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
		log.info("get. url={},form={},headers={}",url,JsonTool.toJsonStr(queryMap),JsonTool.toJsonStr(headers));
		HttpResponse httpResponse = HttpRequest.get(url)
				.query(queryMap)
				.header(headers)
				.send();
		String result = httpResponse.charset("utf-8").bodyText();
		log.info("get result. result={}",result);

		return adapteResult(httpResponse.contentType(), result);
	}


	/**
	 * 适配结果
	 * @param responseContentType
	 * @param result
	 * @return
	 */
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


	/**
	 * 创建实例
	 * @return
	 */
	public static BigDatasourceHttpJoddClientImpl create() {
		BigDatasourceHttpJoddClientImpl bigDatasourceHttpJoddClient = new BigDatasourceHttpJoddClientImpl();
		return bigDatasourceHttpJoddClient;
	}
}
