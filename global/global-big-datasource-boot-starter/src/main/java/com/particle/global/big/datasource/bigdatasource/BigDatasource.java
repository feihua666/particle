package com.particle.global.big.datasource.bigdatasource;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceExecutor;

import java.io.Closeable;

/**
 * <p>
 * 大数据源接口
 * </p>
 *
 * @author yangwei
 * @since 2023-03-09 11:27
 */
public interface BigDatasource extends Closeable {

	/**
	 * 数据源名称
	 * @return
	 */
	String getName();

	/**
	 * 数据源类型
	 * @return
	 */
	BigDatasourceType getType();

	/**
	 * 获取执行器,执行器获取后请立即调用执行方法获取数据
	 * 否则可能导致不必要的路由错误，
	 * @return
	 */
	BigDatasourceApiExecutor getApiExecutor() throws BigDatasourceException;;

	/**
	 * 获取执行器，该执行器可以延迟路由，即在执行方法获取数据时路由数据源
	 * @param bigDatasourceApi
	 * @return
	 * @throws BigDatasourceException
	 */
	BigDatasourceExecutor getExecutor(BigDatasourceApi bigDatasourceApi) throws BigDatasourceException;

}
