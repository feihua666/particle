package com.particle.global.big.datasource.bigdatasource.impl.http.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.IBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.AbstractBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.http.api.config.HttpBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigContentType;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestMethod;
import com.particle.global.tool.json.JsonTool;

/**
 * <p>
 * http大数据源接口执行器抽象父类
 * </p>
 *
 * @author yangwei
 * @since 2023-03-27 13:54
 */
public abstract class AbstractHttpBigDatasourceApiExecutor extends AbstractBigDatasourceApiExecutor {

	/**
	 * 执行post请求
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public Object executePost(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {

		IBigDatasourceApiConfig config = bigDatasourceApi.config();
		HttpBigDatasourceApiConfig httpBigDatasourceApiConfig = (HttpBigDatasourceApiConfig) config;

		String commandJsonStr = null;
		if (HttpBigDatasourceApiConfigContentType.application_json == httpBigDatasourceApiConfig.getRequestContentType()) {
			if (command != null) {
				commandJsonStr = JsonTool.toJsonStr(command);	
			}
			
			return executePostJson(bigDatasourceApi, command,commandJsonStr,queryString);
		}
		if (HttpBigDatasourceApiConfigContentType.multipart_form_data == httpBigDatasourceApiConfig.getRequestContentType()) {
			if (command != null) {
				commandJsonStr = JsonTool.toJsonStr(command);
			}
			return executePostFormData(bigDatasourceApi, command,commandJsonStr,queryString);
		}
		if (HttpBigDatasourceApiConfigContentType.application_x_www_form_urlencoded == httpBigDatasourceApiConfig.getRequestContentType()) {
			if (command != null) {
				commandJsonStr = JsonTool.toJsonStr(command);
			}
			return executePostXWwwFormUrlencoded(bigDatasourceApi, command,commandJsonStr,queryString);
		}
		if (HttpBigDatasourceApiConfigContentType.text_plain == httpBigDatasourceApiConfig.getRequestContentType()) {
			return executePostText(bigDatasourceApi, command,queryString);
		}
		if (HttpBigDatasourceApiConfigContentType.text_xml == httpBigDatasourceApiConfig.getRequestContentType()) {
			return executePostXml(bigDatasourceApi, command,queryString);
		}

		throw new BigDatasourceException("contentType " + httpBigDatasourceApiConfig.getRequestContentType().name() + " for method post not support currently");
	}

	/**
	 * 执行 post json 请求
	 * @param bigDatasourceApi
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @return
	 */
	public abstract Object executePostJson(BigDatasourceApi bigDatasourceApi, Object command,String commandJsonStr,String queryString);

	/**
	 * 执行 post form-data 请求
	 * @param bigDatasourceApi
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @return
	 */
	public abstract Object executePostFormData(BigDatasourceApi bigDatasourceApi, Object command,String commandJsonStr,String queryString);

	/**
	 * 执行 post x-www-form-urlencoded 请求
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public abstract Object executePostXWwwFormUrlencoded(BigDatasourceApi bigDatasourceApi, Object command,String commandJsonStr,String queryString);

	/**
	 * 执行 post text 请求
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public abstract Object executePostText(BigDatasourceApi bigDatasourceApi, Object command,String queryString);

	/**
	 * 执行 post xml 请求
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public abstract Object executePostXml(BigDatasourceApi bigDatasourceApi, Object command,String queryString);
	/**
	 * 执行get请求
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public Object executeGet(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		String commandJsonStr = null;
		if (command != null) {
			commandJsonStr = JsonTool.toJsonStr(command);
		}
		return doExecuteGet(bigDatasourceApi, command,commandJsonStr,queryString);
	}

	/**
	 * 执行get请求
	 * @param bigDatasourceApi
	 * @param command
	 * @param commandJsonStr command参数对应的json字符串
	 * @return
	 */
	public abstract Object doExecuteGet(BigDatasourceApi bigDatasourceApi, Object command,String commandJsonStr,String queryString);

	@Override
	public Object doExecute(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		IBigDatasourceApiConfig config = bigDatasourceApi.config();
		HttpBigDatasourceApiConfig httpBigDatasourceApiConfig = (HttpBigDatasourceApiConfig) config;
		if (HttpBigDatasourceApiConfigRequestMethod.post == httpBigDatasourceApiConfig.getRequestMethod()) {
			return executePost(bigDatasourceApi,command,queryString);
		}
		if (HttpBigDatasourceApiConfigRequestMethod.get == httpBigDatasourceApiConfig.getRequestMethod()) {
			return executeGet(bigDatasourceApi,command,queryString);
		}
		throw new BigDatasourceException("request method " + httpBigDatasourceApiConfig.getRequestMethod().name() + " not support currently");
	}
}
