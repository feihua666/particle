package com.particle.global.big.datasource.bigdatasource.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;

/**
 * <p>
 * 大数据源 接口执行器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-09 11:42
 */
public interface BigDatasourceApiExecutor<R>{

	/**
	 * 执行最原生的数据响应
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	Object execute(BigDatasourceApi bigDatasourceApi, Object command);

}
