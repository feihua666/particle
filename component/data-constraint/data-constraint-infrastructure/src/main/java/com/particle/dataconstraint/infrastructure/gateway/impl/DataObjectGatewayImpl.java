package com.particle.dataconstraint.infrastructure.gateway.impl;

import com.particle.dataconstraint.domain.DataObject;
import com.particle.dataconstraint.domain.DataObjectId;
import com.particle.dataconstraint.domain.gateway.DataObjectGateway;
import com.particle.dataconstraint.infrastructure.service.IDataObjectService;
import com.particle.dataconstraint.infrastructure.dos.DataObjectDO;
import com.particle.dataconstraint.infrastructure.structmapping.DataObjectInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据对象 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Component
public class DataObjectGatewayImpl extends AbstractBaseGatewayImpl<DataObjectId,DataObject> implements DataObjectGateway {

	private IDataObjectService iDataObjectService;

	@Override
	public DataObject getById(DataObjectId dataObjectId) {
		DataObjectDO byId = iDataObjectService.getById(dataObjectId.getId());
		DataObject dataObject = DomainFactory.create(DataObject.class);
		dataObject = DataObjectInfrastructureStructMapping.instance. dataObjectDOToDataObject(dataObject,byId);
		return dataObject;
	}

	@Override
	public boolean doSave(DataObject dataObject) {
		DataObjectDO dataObjectDO = DataObjectInfrastructureStructMapping.instance.dataObjectToDataObjectDO(dataObject);
		if (dataObjectDO.getId() == null) {
			dataObjectDO.setAddControl(dataObject.getAddControl());
			DataObjectDO add = iDataObjectService.add(dataObjectDO);
			dataObject.setId(DataObjectId.of(add.getId()));
			return add != null;
		}
		dataObjectDO.setUpdateControl(dataObject.getUpdateControl());
		DataObjectDO update = iDataObjectService.update(dataObjectDO);
		return update != null;
	}

	@Override
	public boolean delete(DataObjectId dataObjectId) {
		return iDataObjectService.deleteById(dataObjectId.getId());
	}


	@Autowired
	public void setIDataObjectService(IDataObjectService iDataObjectService) {
		this.iDataObjectService = iDataObjectService;
	}
}
