package com.particle.dataconstraint.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.dataconstraint.domain.DataScopeCustomDataRel;
import com.particle.dataconstraint.domain.DataScopeCustomDataRelId;
import com.particle.dataconstraint.domain.gateway.DataScopeCustomDataRelGateway;
import com.particle.dataconstraint.infrastructure.dos.DataScopeCustomDataRelDO;
import com.particle.dataconstraint.infrastructure.service.IDataScopeCustomDataRelService;
import com.particle.dataconstraint.infrastructure.structmapping.DataScopeCustomDataRelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据范围自定义数据关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Component
public class DataScopeCustomDataRelGatewayImpl extends AbstractBaseGatewayImpl<DataScopeCustomDataRelId,DataScopeCustomDataRel> implements DataScopeCustomDataRelGateway {

	private IDataScopeCustomDataRelService iDataScopeCustomDataRelService;

	@Override
	public DataScopeCustomDataRel getById(DataScopeCustomDataRelId dataScopeCustomDataRelId) {
		DataScopeCustomDataRelDO byId = iDataScopeCustomDataRelService.getById(dataScopeCustomDataRelId.getId());
		DataScopeCustomDataRel dataScopeCustomDataRel = DomainFactory.create(DataScopeCustomDataRel.class);
		dataScopeCustomDataRel = DataScopeCustomDataRelInfrastructureStructMapping.instance. dataScopeCustomDataRelDOToDataScopeCustomDataRel(dataScopeCustomDataRel,byId);
		return dataScopeCustomDataRel;
	}

	@Override
	public boolean doSave(DataScopeCustomDataRel dataScopeCustomDataRel) {
		DataScopeCustomDataRelDO dataScopeCustomDataRelDO = DataScopeCustomDataRelInfrastructureStructMapping.instance.dataScopeCustomDataRelToDataScopeCustomDataRelDO(dataScopeCustomDataRel);
		if (dataScopeCustomDataRelDO.getId() == null) {
			dataScopeCustomDataRelDO.setAddControl(dataScopeCustomDataRel.getAddControl());
			DataScopeCustomDataRelDO add = iDataScopeCustomDataRelService.add(dataScopeCustomDataRelDO);
			dataScopeCustomDataRel.setId(DataScopeCustomDataRelId.of(add.getId()));
			return add != null;
		}
		dataScopeCustomDataRelDO.setUpdateControl(dataScopeCustomDataRel.getUpdateControl());
		DataScopeCustomDataRelDO update = iDataScopeCustomDataRelService.update(dataScopeCustomDataRelDO);
		return update != null;
	}

	@Override
	public boolean delete(DataScopeCustomDataRelId dataScopeCustomDataRelId) {
		return iDataScopeCustomDataRelService.deleteById(dataScopeCustomDataRelId.getId());
	}

	@Override
	public boolean delete(DataScopeCustomDataRelId id, IdCommand idCommand) {
		return iDataScopeCustomDataRelService.deleteById(idCommand);
	}

	@Autowired
	public void setIDataScopeCustomDataRelService(IDataScopeCustomDataRelService iDataScopeCustomDataRelService) {
		this.iDataScopeCustomDataRelService = iDataScopeCustomDataRelService;
	}
}
