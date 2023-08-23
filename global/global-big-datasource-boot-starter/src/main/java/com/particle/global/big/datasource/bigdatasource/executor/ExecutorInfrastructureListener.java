package com.particle.global.big.datasource.bigdatasource.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.infrastructure.InfrastructureListener;

import java.util.Map;

/**
 * <p>
 * 大数据源执行器调用基础监听
 * </p>
 *
 * @author yangwei
 * @since 2023-08-22 14:05
 */
public interface ExecutorInfrastructureListener extends InfrastructureListener {

	/**
	 * 请求前调用
	 * @param bigDatasourceApi
	 * @param command
	 * @param queryString
	 */
	void beforeRequest(BigDatasourceApi bigDatasourceApi, Object command,String queryString);

	/**
	 * 请求返回结果后调用
	 * @param bigDatasourceApi
	 * @param command
	 * @param queryString
	 * @param success 标识是否返回了成功的结果，或者说有效的数据
	 * @param resultData 返回的数据，可能被修改过
	 * @param resultDataConverted 最终返回的数据，可能被修改过
	 */
	void afterResponse(BigDatasourceApi bigDatasourceApi, Object command, String queryString,boolean success,Object resultData,Object resultDataConverted);
}
