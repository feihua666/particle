package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyPrimeStaffPosition;
import com.particle.data.domain.company.DataCompanyPrimeStaffPositionId;
import com.particle.data.domain.company.gateway.DataCompanyPrimeStaffPositionGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffPositionService;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffPositionDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyPrimeStaffPositionInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业主要人员职位 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Component
public class DataCompanyPrimeStaffPositionGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyPrimeStaffPositionId,DataCompanyPrimeStaffPosition> implements DataCompanyPrimeStaffPositionGateway {

    private IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService;

    @Override
    public DataCompanyPrimeStaffPosition getById(DataCompanyPrimeStaffPositionId dataCompanyPrimeStaffPositionId) {
        DataCompanyPrimeStaffPositionDO byId = iDataCompanyPrimeStaffPositionService.getById(dataCompanyPrimeStaffPositionId.getId());
        DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition = DomainFactory.create(DataCompanyPrimeStaffPosition.class);
        dataCompanyPrimeStaffPosition = DataCompanyPrimeStaffPositionInfrastructureStructMapping.instance. dataCompanyPrimeStaffPositionDOToDataCompanyPrimeStaffPosition(dataCompanyPrimeStaffPosition,byId);
        return dataCompanyPrimeStaffPosition;
    }

    @Override
    public boolean doSave(DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition) {
        DataCompanyPrimeStaffPositionDO dataCompanyPrimeStaffPositionDO = DataCompanyPrimeStaffPositionInfrastructureStructMapping.instance.dataCompanyPrimeStaffPositionToDataCompanyPrimeStaffPositionDO(dataCompanyPrimeStaffPosition);
        if (dataCompanyPrimeStaffPositionDO.getId() == null) {
            dataCompanyPrimeStaffPositionDO.setAddControl(dataCompanyPrimeStaffPosition.getAddControl());
            DataCompanyPrimeStaffPositionDO add = iDataCompanyPrimeStaffPositionService.add(dataCompanyPrimeStaffPositionDO);
            dataCompanyPrimeStaffPosition.setId(DataCompanyPrimeStaffPositionId.of(add.getId()));
            return add != null;
        }
        dataCompanyPrimeStaffPositionDO.setUpdateControl(dataCompanyPrimeStaffPosition.getUpdateControl());
        DataCompanyPrimeStaffPositionDO update = iDataCompanyPrimeStaffPositionService.update(dataCompanyPrimeStaffPositionDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyPrimeStaffPositionId dataCompanyPrimeStaffPositionId) {
        return iDataCompanyPrimeStaffPositionService.deleteById(dataCompanyPrimeStaffPositionId.getId());
    }

    @Override
    public boolean delete(DataCompanyPrimeStaffPositionId dataCompanyPrimeStaffPositionId, IdCommand idCommand) {
        return iDataCompanyPrimeStaffPositionService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyPrimeStaffPositionService(IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService) {
        this.iDataCompanyPrimeStaffPositionService = iDataCompanyPrimeStaffPositionService;
    }
}
