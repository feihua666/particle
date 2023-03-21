package com.particle.dataquery.infrastructure.dataapi.gateway.impl;

import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.DataQueryDataApiId;
import com.particle.dataquery.domain.dataapi.gateway.DataQueryDataApiGateway;
import com.particle.dataquery.infrastructure.dataapi.service.IDataQueryDataApiService;
import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.dataquery.infrastructure.dataapi.structmapping.DataQueryDataApiInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据查询数据接口 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Component
public class DataQueryDataApiGatewayImpl extends AbstractBaseGatewayImpl<DataQueryDataApiId,DataQueryDataApi> implements DataQueryDataApiGateway {

	private IDataQueryDataApiService iDataQueryDataApiService;

	@Override
	public DataQueryDataApi getById(DataQueryDataApiId dataQueryDataApiId) {
		DataQueryDataApiDO byId = iDataQueryDataApiService.getById(dataQueryDataApiId.getId());
		DataQueryDataApi dataQueryDataApi = DomainFactory.create(DataQueryDataApi.class);
		dataQueryDataApi = DataQueryDataApiInfrastructureStructMapping.instance. dataQueryDataApiDOToDataQueryDataApi(dataQueryDataApi,byId);
		return dataQueryDataApi;
	}

	@Override
	public boolean doSave(DataQueryDataApi dataQueryDataApi) {
		DataQueryDataApiDO dataQueryDataApiDO = DataQueryDataApiInfrastructureStructMapping.instance.dataQueryDataApiToDataQueryDataApiDO(dataQueryDataApi);
		if (dataQueryDataApiDO.getId() == null) {
			dataQueryDataApiDO.setAddControl(dataQueryDataApi.getAddControl());
			DataQueryDataApiDO add = iDataQueryDataApiService.add(dataQueryDataApiDO);
			dataQueryDataApi.setId(DataQueryDataApiId.of(add.getId()));
			return add != null;
		}
		dataQueryDataApiDO.setUpdateControl(dataQueryDataApi.getUpdateControl());
		DataQueryDataApiDO update = iDataQueryDataApiService.update(dataQueryDataApiDO);
		return update != null;
	}

	@Override
	public boolean delete(DataQueryDataApiId dataQueryDataApiId) {
		return iDataQueryDataApiService.deleteById(dataQueryDataApiId.getId());
	}


	@Autowired
	public void setIDataQueryDataApiService(IDataQueryDataApiService iDataQueryDataApiService) {
		this.iDataQueryDataApiService = iDataQueryDataApiService;
	}
}
