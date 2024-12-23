package com.particle.dataquery.infrastructure.provider.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.dataquery.domain.provider.DataQueryProvider;
import com.particle.dataquery.domain.provider.DataQueryProviderId;
import com.particle.dataquery.domain.provider.gateway.DataQueryProviderGateway;
import com.particle.dataquery.infrastructure.provider.dos.DataQueryProviderDO;
import com.particle.dataquery.infrastructure.provider.service.IDataQueryProviderService;
import com.particle.dataquery.infrastructure.provider.structmapping.DataQueryProviderInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据查询供应商 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Component
public class DataQueryProviderGatewayImpl extends AbstractBaseGatewayImpl<DataQueryProviderId,DataQueryProvider> implements DataQueryProviderGateway {

	private IDataQueryProviderService iDataQueryProviderService;

	@Override
	public DataQueryProvider getById(DataQueryProviderId dataQueryProviderId) {
		DataQueryProviderDO byId = iDataQueryProviderService.getById(dataQueryProviderId.getId());
		DataQueryProvider dataQueryProvider = DomainFactory.create(DataQueryProvider.class);
		dataQueryProvider = DataQueryProviderInfrastructureStructMapping.instance. dataQueryProviderDOToDataQueryProvider(dataQueryProvider,byId);
		return dataQueryProvider;
	}

	@Override
	public boolean doSave(DataQueryProvider dataQueryProvider) {
		DataQueryProviderDO dataQueryProviderDO = DataQueryProviderInfrastructureStructMapping.instance.dataQueryProviderToDataQueryProviderDO(dataQueryProvider);
		if (dataQueryProviderDO.getId() == null) {
			dataQueryProviderDO.setAddControl(dataQueryProvider.getAddControl());
			DataQueryProviderDO add = iDataQueryProviderService.add(dataQueryProviderDO);
			dataQueryProvider.setId(DataQueryProviderId.of(add.getId()));
			return add != null;
		}
		dataQueryProviderDO.setUpdateControl(dataQueryProvider.getUpdateControl());
		DataQueryProviderDO update = iDataQueryProviderService.update(dataQueryProviderDO);
		return update != null;
	}

	@Override
	public boolean delete(DataQueryProviderId dataQueryProviderId) {
		return iDataQueryProviderService.deleteById(dataQueryProviderId.getId());
	}


	@Override
	public boolean delete(DataQueryProviderId id, IdCommand idCommand) {
		return iDataQueryProviderService.deleteById(idCommand);
	}

	@Autowired
	public void setIDataQueryProviderService(IDataQueryProviderService iDataQueryProviderService) {
		this.iDataQueryProviderService = iDataQueryProviderService;
	}
}
