package com.particle.global.big.datasource.bigdatasource.impl.jdbc.executor;

import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.IBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.executor.AbstractBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config.JdbcBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigDataType;

/**
 * <p>
 * jdbc大数据源接口执行器抽象父类
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 17:06
 */
public abstract class AbstractJdbcBigDatasourceApiExecutor extends AbstractBigDatasourceApiExecutor {

	/**
	 * 分页查询
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public Object executePage(BigDatasourceApi bigDatasourceApi, Object command) {
		return doExecutePage(bigDatasourceApi,command);
	}

	public abstract Object doExecutePage(BigDatasourceApi bigDatasourceApi, Object command);

	/**
	 * 多条查询
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public Object executeMulti(BigDatasourceApi bigDatasourceApi, Object command) {
		return doExecuteMulti(bigDatasourceApi,command);
	}

	public abstract Object doExecuteMulti(BigDatasourceApi bigDatasourceApi, Object command);

	/**
	 * 单条查询
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	public Object executeSingle(BigDatasourceApi bigDatasourceApi, Object command) {

		return doExecuteSingle(bigDatasourceApi,command);
	}

	public abstract Object doExecuteSingle(BigDatasourceApi bigDatasourceApi, Object command);

	@Override
	public Object doExecute(BigDatasourceApi bigDatasourceApi, Object command) {
		IBigDatasourceApiConfig config = bigDatasourceApi.config();
		JdbcBigDatasourceApiConfig jdbcBigDatasourceApiConfig = (JdbcBigDatasourceApiConfig) config;

		JdbcBigDatasourceApiConfigDataType dataType = jdbcBigDatasourceApiConfig.getDataType();
		if (dataType == null) {
			throw new RuntimeException("dataType can not be null");
		}

		if (dataType == JdbcBigDatasourceApiConfigDataType.single) {
			return executeSingle(bigDatasourceApi, command);
		}
		if (dataType == JdbcBigDatasourceApiConfigDataType.multiple) {
			return executeMulti(bigDatasourceApi, command);
		}
		if (dataType == JdbcBigDatasourceApiConfigDataType.page) {
			return executePage(bigDatasourceApi, command);
		}

		throw new RuntimeException("this is like a bug,because here is unreachable");

	}

}
