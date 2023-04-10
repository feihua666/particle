package com.particle.global.big.datasource.bigdatasource.impl.http.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.IBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.AbstractBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.http.api.config.HttpBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigContentType;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestMethod;

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

		if (HttpBigDatasourceApiConfigContentType.json == httpBigDatasourceApiConfig.getRequestContentType()) {
			return executePostJson(bigDatasourceApi, command,queryString);
		}
		if (HttpBigDatasourceApiConfigContentType.form_data == httpBigDatasourceApiConfig.getRequestContentType()) {
			return executePostFormData(bigDatasourceApi, command,queryString);
		}
		if (HttpBigDatasourceApiConfigContentType.x_www_form_urlencoded == httpBigDatasourceApiConfig.getRequestContentType()) {
			return executePostXWwwFormUrlencoded(bigDatasourceApi, command,queryString);
		}
		if (HttpBigDatasourceApiConfigContentType.text == httpBigDatasourceApiConfig.getRequestContentType()) {
			return executePostText(bigDatasourceApi, command,queryString);
		}
		if (HttpBigDatasourceApiConfigContentType.xml == httpBigDatasourceApiConfig.getRequestContentType()) {
			return executePostXml(bigDatasourceApi, command,queryString);
		}

		throw new BigDatasourceException("contentType " + httpBigDatasourceApiConfig.getRequestContentType().name() + " for method post not support currently");
	}

	/**
	 * 执行 post json 请求
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public abstract Object executePostJson(BigDatasourceApi bigDatasourceApi, Object command,String queryString);

	/**
	 * 执行 post form-data 请求
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public abstract Object executePostFormData(BigDatasourceApi bigDatasourceApi, Object command,String queryString);

	/**
	 * 执行 post x-www-form-urlencoded 请求
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public abstract Object executePostXWwwFormUrlencoded(BigDatasourceApi bigDatasourceApi, Object command,String queryString);

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

		return doExecuteGet(bigDatasourceApi, command,queryString);
	}

	/**
	 * 执行get请求
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public abstract Object doExecuteGet(BigDatasourceApi bigDatasourceApi, Object command,String queryString);

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
