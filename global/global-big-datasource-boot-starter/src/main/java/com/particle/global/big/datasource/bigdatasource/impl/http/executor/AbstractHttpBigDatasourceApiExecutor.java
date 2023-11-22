package com.particle.global.big.datasource.bigdatasource.impl.http.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.IBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.executor.AbstractBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.http.api.config.HttpBigDatasourceApiConfig;

/**
 * <p>
 * http大数据源接口执行器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-27 13:54
 */
public abstract class AbstractHttpBigDatasourceApiExecutor extends AbstractBigDatasourceApiExecutor {

	/**
	 * 执行 http 请求
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public abstract Object executeHttp(BigDatasourceApi bigDatasourceApi, Object command,String commandString,String queryString);


	@Override
	public Object doExecute(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		IBigDatasourceApiConfig config = bigDatasourceApi.config();
		HttpBigDatasourceApiConfig httpBigDatasourceApiConfig = (HttpBigDatasourceApiConfig) config;

		String commandString = httpBigDatasourceApiConfig.getRequestContentType().getCommandString(command);
		return executeHttp(bigDatasourceApi, command, commandString, queryString);
	}
}
