package com.particle.dataquery.infrastructure.datasource.gateway.impl;

import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceApiGateway;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceApiService;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.dataquery.infrastructure.datasource.structmapping.DataQueryDatasourceApiInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据查询数据源接口 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Component
public class DataQueryDatasourceApiGatewayImpl extends AbstractBaseGatewayImpl<DataQueryDatasourceApiId,DataQueryDatasourceApi> implements DataQueryDatasourceApiGateway {

	private IDataQueryDatasourceApiService iDataQueryDatasourceApiService;

	@Override
	public DataQueryDatasourceApi getById(DataQueryDatasourceApiId dataQueryDatasourceApiId) {
		DataQueryDatasourceApiDO byId = iDataQueryDatasourceApiService.getById(dataQueryDatasourceApiId.getId());
		DataQueryDatasourceApi dataQueryDatasourceApi = DomainFactory.create(DataQueryDatasourceApi.class);
		dataQueryDatasourceApi = DataQueryDatasourceApiInfrastructureStructMapping.instance. dataQueryDatasourceApiDOToDataQueryDatasourceApi(dataQueryDatasourceApi,byId);
		return dataQueryDatasourceApi;
	}

	@Override
	public boolean doSave(DataQueryDatasourceApi dataQueryDatasourceApi) {
		DataQueryDatasourceApiDO dataQueryDatasourceApiDO = DataQueryDatasourceApiInfrastructureStructMapping.instance.dataQueryDatasourceApiToDataQueryDatasourceApiDO(dataQueryDatasourceApi);
		if (dataQueryDatasourceApiDO.getId() == null) {
			dataQueryDatasourceApiDO.setAddControl(dataQueryDatasourceApi.getAddControl());
			DataQueryDatasourceApiDO add = iDataQueryDatasourceApiService.add(dataQueryDatasourceApiDO);
			dataQueryDatasourceApi.setId(DataQueryDatasourceApiId.of(add.getId()));
			return add != null;
		}
		dataQueryDatasourceApiDO.setUpdateControl(dataQueryDatasourceApi.getUpdateControl());
		DataQueryDatasourceApiDO update = iDataQueryDatasourceApiService.update(dataQueryDatasourceApiDO);
		return update != null;
	}

	@Override
	public boolean delete(DataQueryDatasourceApiId dataQueryDatasourceApiId) {
		return iDataQueryDatasourceApiService.deleteById(dataQueryDatasourceApiId.getId());
	}


	@Autowired
	public void setIDataQueryDatasourceApiService(IDataQueryDatasourceApiService iDataQueryDatasourceApiService) {
		this.iDataQueryDatasourceApiService = iDataQueryDatasourceApiService;
	}
}
