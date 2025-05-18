package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyCaseFiling;
import com.particle.data.domain.company.DataCompanyCaseFilingId;
import com.particle.data.domain.company.gateway.DataCompanyCaseFilingGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingService;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyCaseFilingInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业立案信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Component
public class DataCompanyCaseFilingGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyCaseFilingId,DataCompanyCaseFiling> implements DataCompanyCaseFilingGateway {

    private IDataCompanyCaseFilingService iDataCompanyCaseFilingService;

    @Override
    public DataCompanyCaseFiling getById(DataCompanyCaseFilingId dataCompanyCaseFilingId) {
        DataCompanyCaseFilingDO byId = iDataCompanyCaseFilingService.getById(dataCompanyCaseFilingId.getId());
        DataCompanyCaseFiling dataCompanyCaseFiling = DomainFactory.create(DataCompanyCaseFiling.class);
        dataCompanyCaseFiling = DataCompanyCaseFilingInfrastructureStructMapping.instance. dataCompanyCaseFilingDOToDataCompanyCaseFiling(dataCompanyCaseFiling,byId);
        return dataCompanyCaseFiling;
    }

    @Override
    public boolean doSave(DataCompanyCaseFiling dataCompanyCaseFiling) {
        DataCompanyCaseFilingDO dataCompanyCaseFilingDO = DataCompanyCaseFilingInfrastructureStructMapping.instance.dataCompanyCaseFilingToDataCompanyCaseFilingDO(dataCompanyCaseFiling);
        if (dataCompanyCaseFilingDO.getId() == null) {
            dataCompanyCaseFilingDO.setAddControl(dataCompanyCaseFiling.getAddControl());
            DataCompanyCaseFilingDO add = iDataCompanyCaseFilingService.add(dataCompanyCaseFilingDO);
            dataCompanyCaseFiling.setId(DataCompanyCaseFilingId.of(add.getId()));
            return add != null;
        }
        dataCompanyCaseFilingDO.setUpdateControl(dataCompanyCaseFiling.getUpdateControl());
        DataCompanyCaseFilingDO update = iDataCompanyCaseFilingService.update(dataCompanyCaseFilingDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyCaseFilingId dataCompanyCaseFilingId) {
        return iDataCompanyCaseFilingService.deleteById(dataCompanyCaseFilingId.getId());
    }

    @Override
    public boolean delete(DataCompanyCaseFilingId dataCompanyCaseFilingId, IdCommand idCommand) {
        return iDataCompanyCaseFilingService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyCaseFilingService(IDataCompanyCaseFilingService iDataCompanyCaseFilingService) {
        this.iDataCompanyCaseFilingService = iDataCompanyCaseFilingService;
    }
}
