package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyVcFinancing;
import com.particle.data.domain.company.DataCompanyVcFinancingId;
import com.particle.data.domain.company.gateway.DataCompanyVcFinancingGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyVcFinancingInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业融资 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Component
public class DataCompanyVcFinancingGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyVcFinancingId,DataCompanyVcFinancing> implements DataCompanyVcFinancingGateway {

    private IDataCompanyVcFinancingService iDataCompanyVcFinancingService;

    @Override
    public DataCompanyVcFinancing getById(DataCompanyVcFinancingId dataCompanyVcFinancingId) {
        DataCompanyVcFinancingDO byId = iDataCompanyVcFinancingService.getById(dataCompanyVcFinancingId.getId());
        DataCompanyVcFinancing dataCompanyVcFinancing = DomainFactory.create(DataCompanyVcFinancing.class);
        dataCompanyVcFinancing = DataCompanyVcFinancingInfrastructureStructMapping.instance. dataCompanyVcFinancingDOToDataCompanyVcFinancing(dataCompanyVcFinancing,byId);
        return dataCompanyVcFinancing;
    }

    @Override
    public boolean doSave(DataCompanyVcFinancing dataCompanyVcFinancing) {
        DataCompanyVcFinancingDO dataCompanyVcFinancingDO = DataCompanyVcFinancingInfrastructureStructMapping.instance.dataCompanyVcFinancingToDataCompanyVcFinancingDO(dataCompanyVcFinancing);
        if (dataCompanyVcFinancingDO.getId() == null) {
            dataCompanyVcFinancingDO.setAddControl(dataCompanyVcFinancing.getAddControl());
            DataCompanyVcFinancingDO add = iDataCompanyVcFinancingService.add(dataCompanyVcFinancingDO);
            dataCompanyVcFinancing.setId(DataCompanyVcFinancingId.of(add.getId()));
            return add != null;
        }
        dataCompanyVcFinancingDO.setUpdateControl(dataCompanyVcFinancing.getUpdateControl());
        DataCompanyVcFinancingDO update = iDataCompanyVcFinancingService.update(dataCompanyVcFinancingDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyVcFinancingId dataCompanyVcFinancingId) {
        return iDataCompanyVcFinancingService.deleteById(dataCompanyVcFinancingId.getId());
    }

    @Override
    public boolean delete(DataCompanyVcFinancingId dataCompanyVcFinancingId, IdCommand idCommand) {
        return iDataCompanyVcFinancingService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyVcFinancingService(IDataCompanyVcFinancingService iDataCompanyVcFinancingService) {
        this.iDataCompanyVcFinancingService = iDataCompanyVcFinancingService;
    }
}
