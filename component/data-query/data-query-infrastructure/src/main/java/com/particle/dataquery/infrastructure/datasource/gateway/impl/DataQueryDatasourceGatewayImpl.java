package com.particle.dataquery.infrastructure.datasource.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceId;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceGateway;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceService;
import com.particle.dataquery.infrastructure.datasource.structmapping.DataQueryDatasourceInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据查询数据源 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Component
public class DataQueryDatasourceGatewayImpl extends AbstractBaseGatewayImpl<DataQueryDatasourceId,DataQueryDatasource> implements DataQueryDatasourceGateway {

	private IDataQueryDatasourceService iDataQueryDatasourceService;

	@Override
	public DataQueryDatasource getById(DataQueryDatasourceId dataQueryDatasourceId) {
		DataQueryDatasourceDO byId = iDataQueryDatasourceService.getById(dataQueryDatasourceId.getId());
		DataQueryDatasource dataQueryDatasource = DomainFactory.create(DataQueryDatasource.class);
		dataQueryDatasource = DataQueryDatasourceInfrastructureStructMapping.instance. dataQueryDatasourceDOToDataQueryDatasource(dataQueryDatasource,byId);
		return dataQueryDatasource;
	}

	@Override
	public boolean doSave(DataQueryDatasource dataQueryDatasource) {
		DataQueryDatasourceDO dataQueryDatasourceDO = DataQueryDatasourceInfrastructureStructMapping.instance.dataQueryDatasourceToDataQueryDatasourceDO(dataQueryDatasource);
		if (dataQueryDatasourceDO.getId() == null) {
			dataQueryDatasourceDO.setAddControl(dataQueryDatasource.getAddControl());
			DataQueryDatasourceDO add = iDataQueryDatasourceService.add(dataQueryDatasourceDO);
			dataQueryDatasource.setId(DataQueryDatasourceId.of(add.getId()));
			return add != null;
		}
		dataQueryDatasourceDO.setUpdateControl(dataQueryDatasource.getUpdateControl());
		DataQueryDatasourceDO update = iDataQueryDatasourceService.update(dataQueryDatasourceDO);
		return update != null;
	}

	@Override
	public boolean delete(DataQueryDatasourceId dataQueryDatasourceId) {
		return iDataQueryDatasourceService.deleteById(dataQueryDatasourceId.getId());
	}

	@Override
	public boolean delete(DataQueryDatasourceId id, IdCommand idCommand) {
		return iDataQueryDatasourceService.deleteById(idCommand);
	}

	@Autowired
	public void setIDataQueryDatasourceService(IDataQueryDatasourceService iDataQueryDatasourceService) {
		this.iDataQueryDatasourceService = iDataQueryDatasourceService;
	}
}
