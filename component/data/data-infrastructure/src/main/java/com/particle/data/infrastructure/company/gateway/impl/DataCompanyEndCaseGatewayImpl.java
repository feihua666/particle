package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyEndCase;
import com.particle.data.domain.company.DataCompanyEndCaseId;
import com.particle.data.domain.company.gateway.DataCompanyEndCaseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyEndCaseService;
import com.particle.data.infrastructure.company.dos.DataCompanyEndCaseDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyEndCaseInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业终本案件 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Component
public class DataCompanyEndCaseGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyEndCaseId,DataCompanyEndCase> implements DataCompanyEndCaseGateway {

    private IDataCompanyEndCaseService iDataCompanyEndCaseService;

    @Override
    public DataCompanyEndCase getById(DataCompanyEndCaseId dataCompanyEndCaseId) {
        DataCompanyEndCaseDO byId = iDataCompanyEndCaseService.getById(dataCompanyEndCaseId.getId());
        DataCompanyEndCase dataCompanyEndCase = DomainFactory.create(DataCompanyEndCase.class);
        dataCompanyEndCase = DataCompanyEndCaseInfrastructureStructMapping.instance. dataCompanyEndCaseDOToDataCompanyEndCase(dataCompanyEndCase,byId);
        return dataCompanyEndCase;
    }

    @Override
    public boolean doSave(DataCompanyEndCase dataCompanyEndCase) {
        DataCompanyEndCaseDO dataCompanyEndCaseDO = DataCompanyEndCaseInfrastructureStructMapping.instance.dataCompanyEndCaseToDataCompanyEndCaseDO(dataCompanyEndCase);
        if (dataCompanyEndCaseDO.getId() == null) {
            dataCompanyEndCaseDO.setAddControl(dataCompanyEndCase.getAddControl());
            DataCompanyEndCaseDO add = iDataCompanyEndCaseService.add(dataCompanyEndCaseDO);
            dataCompanyEndCase.setId(DataCompanyEndCaseId.of(add.getId()));
            return add != null;
        }
        dataCompanyEndCaseDO.setUpdateControl(dataCompanyEndCase.getUpdateControl());
        DataCompanyEndCaseDO update = iDataCompanyEndCaseService.update(dataCompanyEndCaseDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyEndCaseId dataCompanyEndCaseId) {
        return iDataCompanyEndCaseService.deleteById(dataCompanyEndCaseId.getId());
    }

    @Override
    public boolean delete(DataCompanyEndCaseId dataCompanyEndCaseId, IdCommand idCommand) {
        return iDataCompanyEndCaseService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyEndCaseService(IDataCompanyEndCaseService iDataCompanyEndCaseService) {
        this.iDataCompanyEndCaseService = iDataCompanyEndCaseService;
    }
}
