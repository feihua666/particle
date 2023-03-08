package com.particle.dataquery.domain.datasource.gateway;

import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 数据查询数据源 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
public interface DataQueryDatasourceGateway extends IBaseGateway<DataQueryDatasourceId,DataQueryDatasource> {
}
