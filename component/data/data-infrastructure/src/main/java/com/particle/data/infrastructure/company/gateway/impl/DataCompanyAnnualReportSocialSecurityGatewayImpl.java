package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurity;
import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurityId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportSocialSecurityGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportSocialSecurityService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportSocialSecurityDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAnnualReportSocialSecurityInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业年报社保 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Component
public class DataCompanyAnnualReportSocialSecurityGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAnnualReportSocialSecurityId,DataCompanyAnnualReportSocialSecurity> implements DataCompanyAnnualReportSocialSecurityGateway {

    private IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService;

    @Override
    public DataCompanyAnnualReportSocialSecurity getById(DataCompanyAnnualReportSocialSecurityId dataCompanyAnnualReportSocialSecurityId) {
        DataCompanyAnnualReportSocialSecurityDO byId = iDataCompanyAnnualReportSocialSecurityService.getById(dataCompanyAnnualReportSocialSecurityId.getId());
        DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity = DomainFactory.create(DataCompanyAnnualReportSocialSecurity.class);
        dataCompanyAnnualReportSocialSecurity = DataCompanyAnnualReportSocialSecurityInfrastructureStructMapping.instance. dataCompanyAnnualReportSocialSecurityDOToDataCompanyAnnualReportSocialSecurity(dataCompanyAnnualReportSocialSecurity,byId);
        return dataCompanyAnnualReportSocialSecurity;
    }

    @Override
    public boolean doSave(DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity) {
        DataCompanyAnnualReportSocialSecurityDO dataCompanyAnnualReportSocialSecurityDO = DataCompanyAnnualReportSocialSecurityInfrastructureStructMapping.instance.dataCompanyAnnualReportSocialSecurityToDataCompanyAnnualReportSocialSecurityDO(dataCompanyAnnualReportSocialSecurity);
        if (dataCompanyAnnualReportSocialSecurityDO.getId() == null) {
            dataCompanyAnnualReportSocialSecurityDO.setAddControl(dataCompanyAnnualReportSocialSecurity.getAddControl());
            DataCompanyAnnualReportSocialSecurityDO add = iDataCompanyAnnualReportSocialSecurityService.add(dataCompanyAnnualReportSocialSecurityDO);
            dataCompanyAnnualReportSocialSecurity.setId(DataCompanyAnnualReportSocialSecurityId.of(add.getId()));
            return add != null;
        }
        dataCompanyAnnualReportSocialSecurityDO.setUpdateControl(dataCompanyAnnualReportSocialSecurity.getUpdateControl());
        DataCompanyAnnualReportSocialSecurityDO update = iDataCompanyAnnualReportSocialSecurityService.update(dataCompanyAnnualReportSocialSecurityDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAnnualReportSocialSecurityId dataCompanyAnnualReportSocialSecurityId) {
        return iDataCompanyAnnualReportSocialSecurityService.deleteById(dataCompanyAnnualReportSocialSecurityId.getId());
    }

    @Override
    public boolean delete(DataCompanyAnnualReportSocialSecurityId dataCompanyAnnualReportSocialSecurityId, IdCommand idCommand) {
        return iDataCompanyAnnualReportSocialSecurityService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAnnualReportSocialSecurityService(IDataCompanyAnnualReportSocialSecurityService iDataCompanyAnnualReportSocialSecurityService) {
        this.iDataCompanyAnnualReportSocialSecurityService = iDataCompanyAnnualReportSocialSecurityService;
    }
}
