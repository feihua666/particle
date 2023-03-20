package com.particle.global.big.datasource.bigdatasource.dynamic.executor;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRouter;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;

import java.util.Optional;

/**
 * <p>
 * 大数据源接口执行器代理
 * </p>
 *
 * @author yangwei
 * @since 2023-03-12 22:01
 */
public class DelegateBigDatasourceApiExecutor extends AbstractDelegateBigDatasourceExecutor implements BigDatasourceApiExecutor {

	public DelegateBigDatasourceApiExecutor(DynamicBigDatasourceRouter dynamicBigDatasourceRouter){
		super(dynamicBigDatasourceRouter);
	}

	/**
	 * 获取真正执行器
	 * @return
	 * @throws BigDatasourceException
	 */
	private BigDatasourceApiExecutor getApiExecutor(BigDatasourceApi bigDatasourceApi) throws BigDatasourceException {
		return Optional.of(routing(bigDatasourceApi.routingKey())).map(BigDatasource::getApiExecutor).get();
	}
	@Override
	public Object execute(BigDatasourceApi bigDatasourceApi, Object command) {
		return getApiExecutor(bigDatasourceApi).execute(bigDatasourceApi,command);
	}

}
