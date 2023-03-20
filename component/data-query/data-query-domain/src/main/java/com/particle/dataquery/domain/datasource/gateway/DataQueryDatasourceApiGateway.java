package com.particle.dataquery.domain.datasource.gateway;

import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 数据查询数据源接口 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
public interface DataQueryDatasourceApiGateway extends IBaseGateway<DataQueryDatasourceApiId,DataQueryDatasourceApi> {
}
