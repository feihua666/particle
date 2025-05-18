package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicense;
import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportAdministrativeLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAdministrativeLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAdministrativeLicenseDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAnnualReportAdministrativeLicenseInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业年报行政许可 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Component
public class DataCompanyAnnualReportAdministrativeLicenseGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAnnualReportAdministrativeLicenseId,DataCompanyAnnualReportAdministrativeLicense> implements DataCompanyAnnualReportAdministrativeLicenseGateway {

    private IDataCompanyAnnualReportAdministrativeLicenseService iDataCompanyAnnualReportAdministrativeLicenseService;

    @Override
    public DataCompanyAnnualReportAdministrativeLicense getById(DataCompanyAnnualReportAdministrativeLicenseId dataCompanyAnnualReportAdministrativeLicenseId) {
        DataCompanyAnnualReportAdministrativeLicenseDO byId = iDataCompanyAnnualReportAdministrativeLicenseService.getById(dataCompanyAnnualReportAdministrativeLicenseId.getId());
        DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense = DomainFactory.create(DataCompanyAnnualReportAdministrativeLicense.class);
        dataCompanyAnnualReportAdministrativeLicense = DataCompanyAnnualReportAdministrativeLicenseInfrastructureStructMapping.instance. dataCompanyAnnualReportAdministrativeLicenseDOToDataCompanyAnnualReportAdministrativeLicense(dataCompanyAnnualReportAdministrativeLicense,byId);
        return dataCompanyAnnualReportAdministrativeLicense;
    }

    @Override
    public boolean doSave(DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense) {
        DataCompanyAnnualReportAdministrativeLicenseDO dataCompanyAnnualReportAdministrativeLicenseDO = DataCompanyAnnualReportAdministrativeLicenseInfrastructureStructMapping.instance.dataCompanyAnnualReportAdministrativeLicenseToDataCompanyAnnualReportAdministrativeLicenseDO(dataCompanyAnnualReportAdministrativeLicense);
        if (dataCompanyAnnualReportAdministrativeLicenseDO.getId() == null) {
            dataCompanyAnnualReportAdministrativeLicenseDO.setAddControl(dataCompanyAnnualReportAdministrativeLicense.getAddControl());
            DataCompanyAnnualReportAdministrativeLicenseDO add = iDataCompanyAnnualReportAdministrativeLicenseService.add(dataCompanyAnnualReportAdministrativeLicenseDO);
            dataCompanyAnnualReportAdministrativeLicense.setId(DataCompanyAnnualReportAdministrativeLicenseId.of(add.getId()));
            return add != null;
        }
        dataCompanyAnnualReportAdministrativeLicenseDO.setUpdateControl(dataCompanyAnnualReportAdministrativeLicense.getUpdateControl());
        DataCompanyAnnualReportAdministrativeLicenseDO update = iDataCompanyAnnualReportAdministrativeLicenseService.update(dataCompanyAnnualReportAdministrativeLicenseDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAnnualReportAdministrativeLicenseId dataCompanyAnnualReportAdministrativeLicenseId) {
        return iDataCompanyAnnualReportAdministrativeLicenseService.deleteById(dataCompanyAnnualReportAdministrativeLicenseId.getId());
    }

    @Override
    public boolean delete(DataCompanyAnnualReportAdministrativeLicenseId dataCompanyAnnualReportAdministrativeLicenseId, IdCommand idCommand) {
        return iDataCompanyAnnualReportAdministrativeLicenseService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAnnualReportAdministrativeLicenseService(IDataCompanyAnnualReportAdministrativeLicenseService iDataCompanyAnnualReportAdministrativeLicenseService) {
        this.iDataCompanyAnnualReportAdministrativeLicenseService = iDataCompanyAnnualReportAdministrativeLicenseService;
    }
}
