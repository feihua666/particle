package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyVcInvestInstitution;
import com.particle.data.domain.company.DataCompanyVcInvestInstitutionId;
import com.particle.data.domain.company.gateway.DataCompanyVcInvestInstitutionGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcInvestInstitutionService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcInvestInstitutionDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyVcInvestInstitutionInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业投资机构 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Component
public class DataCompanyVcInvestInstitutionGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyVcInvestInstitutionId,DataCompanyVcInvestInstitution> implements DataCompanyVcInvestInstitutionGateway {

    private IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService;

    @Override
    public DataCompanyVcInvestInstitution getById(DataCompanyVcInvestInstitutionId dataCompanyVcInvestInstitutionId) {
        DataCompanyVcInvestInstitutionDO byId = iDataCompanyVcInvestInstitutionService.getById(dataCompanyVcInvestInstitutionId.getId());
        DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution = DomainFactory.create(DataCompanyVcInvestInstitution.class);
        dataCompanyVcInvestInstitution = DataCompanyVcInvestInstitutionInfrastructureStructMapping.instance. dataCompanyVcInvestInstitutionDOToDataCompanyVcInvestInstitution(dataCompanyVcInvestInstitution,byId);
        return dataCompanyVcInvestInstitution;
    }

    @Override
    public boolean doSave(DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution) {
        DataCompanyVcInvestInstitutionDO dataCompanyVcInvestInstitutionDO = DataCompanyVcInvestInstitutionInfrastructureStructMapping.instance.dataCompanyVcInvestInstitutionToDataCompanyVcInvestInstitutionDO(dataCompanyVcInvestInstitution);
        if (dataCompanyVcInvestInstitutionDO.getId() == null) {
            dataCompanyVcInvestInstitutionDO.setAddControl(dataCompanyVcInvestInstitution.getAddControl());
            DataCompanyVcInvestInstitutionDO add = iDataCompanyVcInvestInstitutionService.add(dataCompanyVcInvestInstitutionDO);
            dataCompanyVcInvestInstitution.setId(DataCompanyVcInvestInstitutionId.of(add.getId()));
            return add != null;
        }
        dataCompanyVcInvestInstitutionDO.setUpdateControl(dataCompanyVcInvestInstitution.getUpdateControl());
        DataCompanyVcInvestInstitutionDO update = iDataCompanyVcInvestInstitutionService.update(dataCompanyVcInvestInstitutionDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyVcInvestInstitutionId dataCompanyVcInvestInstitutionId) {
        return iDataCompanyVcInvestInstitutionService.deleteById(dataCompanyVcInvestInstitutionId.getId());
    }

    @Override
    public boolean delete(DataCompanyVcInvestInstitutionId dataCompanyVcInvestInstitutionId, IdCommand idCommand) {
        return iDataCompanyVcInvestInstitutionService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyVcInvestInstitutionService(IDataCompanyVcInvestInstitutionService iDataCompanyVcInvestInstitutionService) {
        this.iDataCompanyVcInvestInstitutionService = iDataCompanyVcInvestInstitutionService;
    }
}
