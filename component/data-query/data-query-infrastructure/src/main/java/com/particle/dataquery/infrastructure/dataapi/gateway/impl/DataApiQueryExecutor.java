package com.particle.dataquery.infrastructure.dataapi.gateway.impl;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.executor.AbstractBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.trans.IBigDatasourceApiTransSupportService;

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

	private static IBigDatasourceApiTransSupportService iBigDatasourceApiTransSupportService;
	private static boolean hasInitBigDatasourceApiTransSupportService = false;


	private Function executorFunction;

	public DataApiQueryExecutor(Function executorFunction) {
		this.executorFunction = executorFunction;

		if (iBigDatasourceApiTransSupportService == null && !hasInitBigDatasourceApiTransSupportService) {
			bigDatasourceTransSupportServiceInitFromSpring();
			iBigDatasourceApiTransSupportService = super.iBigDatasourceApiTransSupportService;
			hasInitBigDatasourceApiTransSupportService = true;
		}else {
			bigDatasourceTransSupportServiceInit(iBigDatasourceApiTransSupportService);
		}
	}


	@Override
	public Object doExecute(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		return executorFunction.apply(command);
	}

}
