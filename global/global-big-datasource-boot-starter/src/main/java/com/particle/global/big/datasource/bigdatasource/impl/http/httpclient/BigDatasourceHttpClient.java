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
	 * get请求
	 * @param url
	 * @param headers
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @param contentType 请求内容类型
	 * @return
	 */
	Object get(String url,Map<String,String> headers,Object command, String commandJsonStr,String queryString,String contentType);

	/**
	 * post请求
	 * @param url
	 * @param headers
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @param contentType 请求内容类型
	 * @return
	 */
	Object post(String url,Map<String,String> headers,Object command, String commandJsonStr,String queryString,String contentType);


	/**
	 * delete请求
	 * @param url
	 * @param headers
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @param contentType 请求内容类型
	 * @return
	 */
	Object delete(String url,Map<String,String> headers,Object command, String commandJsonStr,String queryString,String contentType);


	/**
	 * put请求
	 * @param url
	 * @param headers
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @param contentType 请求内容类型
	 * @return
	 */
	Object put(String url,Map<String,String> headers,Object command, String commandJsonStr,String queryString,String contentType);


	/**
	 * patch请求
	 * @param url
	 * @param headers
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @param contentType 请求内容类型
	 * @return
	 */
	Object patch(String url,Map<String,String> headers,Object command, String commandJsonStr,String queryString,String contentType);
}
