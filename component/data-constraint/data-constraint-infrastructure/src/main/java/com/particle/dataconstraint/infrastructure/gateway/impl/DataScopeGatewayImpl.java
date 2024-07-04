package com.particle.dataconstraint.infrastructure.gateway.impl;

import com.particle.dataconstraint.domain.DataScope;
import com.particle.dataconstraint.domain.DataScopeId;
import com.particle.dataconstraint.domain.gateway.DataScopeGateway;
import com.particle.dataconstraint.infrastructure.service.IDataScopeService;
import com.particle.dataconstraint.infrastructure.dos.DataScopeDO;
import com.particle.dataconstraint.infrastructure.structmapping.DataScopeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据范围 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Component
public class DataScopeGatewayImpl extends AbstractBaseGatewayImpl<DataScopeId,DataScope> implements DataScopeGateway {

	private IDataScopeService iDataScopeService;

	@Override
	public DataScope getById(DataScopeId dataScopeId) {
		DataScopeDO byId = iDataScopeService.getById(dataScopeId.getId());
		DataScope dataScope = DomainFactory.create(DataScope.class);
		dataScope = DataScopeInfrastructureStructMapping.instance. dataScopeDOToDataScope(dataScope,byId);
		return dataScope;
	}

	@Override
	public boolean doSave(DataScope dataScope) {
		DataScopeDO dataScopeDO = DataScopeInfrastructureStructMapping.instance.dataScopeToDataScopeDO(dataScope);
		if (dataScopeDO.getId() == null) {
			dataScopeDO.setAddControl(dataScope.getAddControl());
			DataScopeDO add = iDataScopeService.add(dataScopeDO);
			dataScope.setId(DataScopeId.of(add.getId()));
			return add != null;
		}
		dataScopeDO.setUpdateControl(dataScope.getUpdateControl());
		DataScopeDO update = iDataScopeService.update(dataScopeDO);
		return update != null;
	}

	@Override
	public boolean delete(DataScopeId dataScopeId) {
		return iDataScopeService.deleteById(dataScopeId.getId());
	}


	@Autowired
	public void setIDataScopeService(IDataScopeService iDataScopeService) {
		this.iDataScopeService = iDataScopeService;
	}
}
