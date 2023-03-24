package com.particle.dataquery.infrastructure.dataapi.gateway.impl;

import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.executor.AbstractBigDatasourceApiExecutor;

import java.util.function.Function;

/**
 * <p>
 * 由于服务接口相关配置使用和数据源接口一致，这里借用大数据源执行器，自己实现一个主要是用于参数校验和后置处理
 * </p>
 *
 * @author yangwei
 * @since 2023-03-23 23:07
 */
public class DataApiQueryExecutor extends AbstractBigDatasourceApiExecutor {

	private Function executorFunction;

	public DataApiQueryExecutor(Function executorFunction) {
		this.executorFunction = executorFunction;
	}


	@Override
	public Object doExecute(BigDatasourceApi bigDatasourceApi, Object command) {
		return executorFunction.apply(command);
	}

}
