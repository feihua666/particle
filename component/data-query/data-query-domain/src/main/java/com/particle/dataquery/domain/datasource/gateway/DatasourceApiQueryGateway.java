package com.particle.dataquery.domain.datasource.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;

/**
 * <p>
 * 数据源接口查询网关，隔离底层逻辑
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 12:56
 */
public interface DatasourceApiQueryGateway extends IGateway {
	/**
	 * 执行查询，实时从数据库中取数据
	 * @param datasource
	 * @param datasourceApi
	 * @param param
	 * @return
	 */
	Object queryRealtime(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param);

	/**
	 * 执行查询，带有缓存逻辑，提高性能
	 * @param datasource
	 * @param datasourceApi
	 * @param param
	 * @return
	 */
	Object query(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param);
}
