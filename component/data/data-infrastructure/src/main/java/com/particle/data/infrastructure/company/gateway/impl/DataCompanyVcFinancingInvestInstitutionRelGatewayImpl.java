package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRel;
import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRelId;
import com.particle.data.domain.company.gateway.DataCompanyVcFinancingInvestInstitutionRelGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcFinancingInvestInstitutionRelService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcFinancingInvestInstitutionRelDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyVcFinancingInvestInstitutionRelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业融资历史投资机构关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Component
public class DataCompanyVcFinancingInvestInstitutionRelGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyVcFinancingInvestInstitutionRelId,DataCompanyVcFinancingInvestInstitutionRel> implements DataCompanyVcFinancingInvestInstitutionRelGateway {

    private IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService;

    @Override
    public DataCompanyVcFinancingInvestInstitutionRel getById(DataCompanyVcFinancingInvestInstitutionRelId dataCompanyVcFinancingInvestInstitutionRelId) {
        DataCompanyVcFinancingInvestInstitutionRelDO byId = iDataCompanyVcFinancingInvestInstitutionRelService.getById(dataCompanyVcFinancingInvestInstitutionRelId.getId());
        DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel = DomainFactory.create(DataCompanyVcFinancingInvestInstitutionRel.class);
        dataCompanyVcFinancingInvestInstitutionRel = DataCompanyVcFinancingInvestInstitutionRelInfrastructureStructMapping.instance. dataCompanyVcFinancingInvestInstitutionRelDOToDataCompanyVcFinancingInvestInstitutionRel(dataCompanyVcFinancingInvestInstitutionRel,byId);
        return dataCompanyVcFinancingInvestInstitutionRel;
    }

    @Override
    public boolean doSave(DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel) {
        DataCompanyVcFinancingInvestInstitutionRelDO dataCompanyVcFinancingInvestInstitutionRelDO = DataCompanyVcFinancingInvestInstitutionRelInfrastructureStructMapping.instance.dataCompanyVcFinancingInvestInstitutionRelToDataCompanyVcFinancingInvestInstitutionRelDO(dataCompanyVcFinancingInvestInstitutionRel);
        if (dataCompanyVcFinancingInvestInstitutionRelDO.getId() == null) {
            dataCompanyVcFinancingInvestInstitutionRelDO.setAddControl(dataCompanyVcFinancingInvestInstitutionRel.getAddControl());
            DataCompanyVcFinancingInvestInstitutionRelDO add = iDataCompanyVcFinancingInvestInstitutionRelService.add(dataCompanyVcFinancingInvestInstitutionRelDO);
            dataCompanyVcFinancingInvestInstitutionRel.setId(DataCompanyVcFinancingInvestInstitutionRelId.of(add.getId()));
            return add != null;
        }
        dataCompanyVcFinancingInvestInstitutionRelDO.setUpdateControl(dataCompanyVcFinancingInvestInstitutionRel.getUpdateControl());
        DataCompanyVcFinancingInvestInstitutionRelDO update = iDataCompanyVcFinancingInvestInstitutionRelService.update(dataCompanyVcFinancingInvestInstitutionRelDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyVcFinancingInvestInstitutionRelId dataCompanyVcFinancingInvestInstitutionRelId) {
        return iDataCompanyVcFinancingInvestInstitutionRelService.deleteById(dataCompanyVcFinancingInvestInstitutionRelId.getId());
    }

    @Override
    public boolean delete(DataCompanyVcFinancingInvestInstitutionRelId dataCompanyVcFinancingInvestInstitutionRelId, IdCommand idCommand) {
        return iDataCompanyVcFinancingInvestInstitutionRelService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyVcFinancingInvestInstitutionRelService(IDataCompanyVcFinancingInvestInstitutionRelService iDataCompanyVcFinancingInvestInstitutionRelService) {
        this.iDataCompanyVcFinancingInvestInstitutionRelService = iDataCompanyVcFinancingInvestInstitutionRelService;
    }
}
