package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyShareholder;
import com.particle.data.domain.company.DataCompanyShareholderId;
import com.particle.data.domain.company.gateway.DataCompanyShareholderGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyShareholderService;
import com.particle.data.infrastructure.company.dos.DataCompanyShareholderDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyShareholderInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业股东 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Component
public class DataCompanyShareholderGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyShareholderId,DataCompanyShareholder> implements DataCompanyShareholderGateway {

    private IDataCompanyShareholderService iDataCompanyShareholderService;

    @Override
    public DataCompanyShareholder getById(DataCompanyShareholderId dataCompanyShareholderId) {
        DataCompanyShareholderDO byId = iDataCompanyShareholderService.getById(dataCompanyShareholderId.getId());
        DataCompanyShareholder dataCompanyShareholder = DomainFactory.create(DataCompanyShareholder.class);
        dataCompanyShareholder = DataCompanyShareholderInfrastructureStructMapping.instance. dataCompanyShareholderDOToDataCompanyShareholder(dataCompanyShareholder,byId);
        return dataCompanyShareholder;
    }

    @Override
    public boolean doSave(DataCompanyShareholder dataCompanyShareholder) {
        DataCompanyShareholderDO dataCompanyShareholderDO = DataCompanyShareholderInfrastructureStructMapping.instance.dataCompanyShareholderToDataCompanyShareholderDO(dataCompanyShareholder);
        if (dataCompanyShareholderDO.getId() == null) {
            dataCompanyShareholderDO.setAddControl(dataCompanyShareholder.getAddControl());
            DataCompanyShareholderDO add = iDataCompanyShareholderService.add(dataCompanyShareholderDO);
            dataCompanyShareholder.setId(DataCompanyShareholderId.of(add.getId()));
            return add != null;
        }
        dataCompanyShareholderDO.setUpdateControl(dataCompanyShareholder.getUpdateControl());
        DataCompanyShareholderDO update = iDataCompanyShareholderService.update(dataCompanyShareholderDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyShareholderId dataCompanyShareholderId) {
        return iDataCompanyShareholderService.deleteById(dataCompanyShareholderId.getId());
    }

    @Override
    public boolean delete(DataCompanyShareholderId dataCompanyShareholderId, IdCommand idCommand) {
        return iDataCompanyShareholderService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyShareholderService(IDataCompanyShareholderService iDataCompanyShareholderService) {
        this.iDataCompanyShareholderService = iDataCompanyShareholderService;
    }
}
