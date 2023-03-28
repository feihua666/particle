package com.particle.global.big.datasource.bigdatasource.dynamic.executor;

import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRouter;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceExecutor;

import java.util.Optional;

/**
 * <p>
 * 大数据源接口执行器代理
 * </p>
 *
 * @author yangwei
 * @since 2023-03-12 11:35
 */
public class DelegateBigDatasourceExecutor extends AbstractDelegateBigDatasourceExecutor implements BigDatasourceExecutor {

	private BigDatasourceApi bigDatasourceApi;

	public DelegateBigDatasourceExecutor(DynamicBigDatasourceRouter dynamicBigDatasourceRouter, BigDatasourceApi bigDatasourceApi){
		super(dynamicBigDatasourceRouter);
		this.bigDatasourceApi = bigDatasourceApi;
	}
	/**
	 * 路由大数据源
	 * @return
	 */
	protected BigDatasource routing() {
		DynamicBigDatasourceRoutingKey routingKey = this.bigDatasourceApi.routingKey();
		return super.routing(routingKey);
	}

	/**
	 * 获取真正执行器
	 * @return
	 * @throws BigDatasourceException
	 */
	private BigDatasourceApiExecutor getApiExecutor() throws BigDatasourceException {
		return Optional.of(routing()).map(BigDatasource::getApiExecutor).get();
	}
	@Override
	public Object execute(Object command,String queryString) {
		return getApiExecutor().execute(this.bigDatasourceApi,command,queryString);
	}

}
