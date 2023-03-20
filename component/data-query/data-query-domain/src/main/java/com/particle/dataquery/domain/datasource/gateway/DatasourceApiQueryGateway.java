package com.particle.dataquery.domain.datasource.gateway;

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
public interface DatasourceApiQueryGateway {
	/**
	 * 执行查询
	 * @param datasource
	 * @param datasourceApi
	 * @param param
	 * @return
	 */
	Object query(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi,Object param);
}
