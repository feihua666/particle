package com.particle.global.big.datasource.bigdatasource.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;

/**
 * <p>
 * 大数据源接口执行器,默认实现
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 17:06
 */
public class DefaultBigDatasourceExecutor implements BigDatasourceExecutor{

	protected BigDatasourceApi bigDatasourceApi;

	protected BigDatasourceApiExecutor bigDatasourceApiExecutor;

	public DefaultBigDatasourceExecutor(BigDatasourceApi bigDatasourceApi, BigDatasourceApiExecutor bigDatasourceApiExecutor) {
		this.bigDatasourceApi = bigDatasourceApi;
		this.bigDatasourceApiExecutor = bigDatasourceApiExecutor;
	}

	@Override
	public Object execute(Object command) {
		return bigDatasourceApiExecutor.execute(bigDatasourceApi,command);
	}

}
