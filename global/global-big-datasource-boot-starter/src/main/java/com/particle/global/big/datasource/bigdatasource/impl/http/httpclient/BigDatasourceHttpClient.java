package com.particle.global.big.datasource.bigdatasource.impl.http.httpclient;

import java.util.Map;

/**
 * <p>
 * 大数据源 http 客户端
 * </p>
 *
 * @author yangwei
 * @since 2023-03-27 17:39
 */
public interface BigDatasourceHttpClient {

	/**
	 * post请求json
	 * @param url
	 * @param headers
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @return
	 */
	Object postJson(String url, Map<String,String> headers, Object command, String commandJsonStr,String queryString);

	/**
	 * post请求form-data
	 * @param url
	 * @param headers
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @return
	 */
	Object postFormData(String url,Map<String,String> headers,Object command, String commandJsonStr,String queryString);

	/**
	 * post请求 x-www-form-urlencoded
	 * @param url
	 * @param headers
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @return
	 */
	Object postXWwwFormUrlencoded(String url,Map<String,String> headers,Object command, String commandJsonStr,String queryString);

	/**
	 * post请求 文本
	 * @param url
	 * @param headers
	 * @param command
	 * @return
	 */
	Object postText(String url,Map<String,String> headers,Object command,String queryString);

	/**
	 * post请求 xml
	 * @param url
	 * @param headers
	 * @param command
	 * @return
	 */
	Object postXml(String url,Map<String,String> headers,Object command,String queryString);

	/**
	 * get请求
	 * @param url
	 * @param headers
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @return
	 */
	Object get(String url,Map<String,String> headers,Object command, String commandJsonStr,String queryString);
}
