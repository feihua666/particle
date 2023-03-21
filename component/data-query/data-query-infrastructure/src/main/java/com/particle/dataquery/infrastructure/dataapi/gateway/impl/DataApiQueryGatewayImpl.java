package com.particle.dataquery.infrastructure.dataapi.gateway.impl;

import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiAdaptType;
import com.particle.dataquery.domain.dataapi.gateway.DataApiQueryGateway;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceId;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceApiGateway;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceGateway;
import com.particle.dataquery.domain.datasource.gateway.DatasourceApiQueryGateway;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据接口查询网关 实现
 * </p>
 *
 * @author yangwei
 * @since 2023-03-21 22:49
 */
@Component
public class DataApiQueryGatewayImpl implements DataApiQueryGateway {
	@Autowired
	private DatasourceApiQueryGateway datasourceApiQueryGateway;
	@Autowired
	private DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway;

	@Autowired
	private DataQueryDictGateway dataQueryDictGateway;
	@Autowired
	private DataQueryDatasourceGateway dataQueryDatasourceGateway;
	@Override
	public Object query(DataQueryDataApi dataQueryDataApi, Object param) {
		// todo 缓存性能
		return queryRealtime(dataQueryDataApi,param);
	}

	@Override
	public Object queryRealtime(DataQueryDataApi dataQueryDataApi, Object param) {
		Long adaptTypeDictId = dataQueryDataApi.getAdaptTypeDictId();
		String adaptTypeDictValue = dataQueryDictGateway.getDictValueById(adaptTypeDictId);
		DataQueryDataApiAdaptType dataQueryDataApiAdaptType = DataQueryDataApiAdaptType.valueOf(adaptTypeDictValue);
		if (DataQueryDataApiAdaptType.single_direct == dataQueryDataApiAdaptType) {
			DataQueryDatasourceApi dataQueryDatasourceApi = dataQueryDatasourceApiGateway.getById(DataQueryDatasourceApiId.of(dataQueryDataApi.getDataQueryDatasourceApiId()));
			DataQueryDatasource dataQueryDatasource = dataQueryDatasourceGateway.getById(DataQueryDatasourceId.of(dataQueryDatasourceApi.getDataQueryDatasourceId()));
			return datasourceApiQueryGateway.queryRealtime(dataQueryDatasource, dataQueryDatasourceApi, param);
		}

		throw new RuntimeException("adaptType " + dataQueryDataApiAdaptType.itemValue() + " not support currently");
	}
}
