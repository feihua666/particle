package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAdministrativeLicense;
import com.particle.data.domain.company.DataCompanyAdministrativeLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyAdministrativeLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAdministrativeLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyAdministrativeLicenseDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAdministrativeLicenseInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业行政许可 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Component
public class DataCompanyAdministrativeLicenseGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAdministrativeLicenseId,DataCompanyAdministrativeLicense> implements DataCompanyAdministrativeLicenseGateway {

    private IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService;

    @Override
    public DataCompanyAdministrativeLicense getById(DataCompanyAdministrativeLicenseId dataCompanyAdministrativeLicenseId) {
        DataCompanyAdministrativeLicenseDO byId = iDataCompanyAdministrativeLicenseService.getById(dataCompanyAdministrativeLicenseId.getId());
        DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense = DomainFactory.create(DataCompanyAdministrativeLicense.class);
        dataCompanyAdministrativeLicense = DataCompanyAdministrativeLicenseInfrastructureStructMapping.instance. dataCompanyAdministrativeLicenseDOToDataCompanyAdministrativeLicense(dataCompanyAdministrativeLicense,byId);
        return dataCompanyAdministrativeLicense;
    }

    @Override
    public boolean doSave(DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense) {
        DataCompanyAdministrativeLicenseDO dataCompanyAdministrativeLicenseDO = DataCompanyAdministrativeLicenseInfrastructureStructMapping.instance.dataCompanyAdministrativeLicenseToDataCompanyAdministrativeLicenseDO(dataCompanyAdministrativeLicense);
        if (dataCompanyAdministrativeLicenseDO.getId() == null) {
            dataCompanyAdministrativeLicenseDO.setAddControl(dataCompanyAdministrativeLicense.getAddControl());
            DataCompanyAdministrativeLicenseDO add = iDataCompanyAdministrativeLicenseService.add(dataCompanyAdministrativeLicenseDO);
            dataCompanyAdministrativeLicense.setId(DataCompanyAdministrativeLicenseId.of(add.getId()));
            return add != null;
        }
        dataCompanyAdministrativeLicenseDO.setUpdateControl(dataCompanyAdministrativeLicense.getUpdateControl());
        DataCompanyAdministrativeLicenseDO update = iDataCompanyAdministrativeLicenseService.update(dataCompanyAdministrativeLicenseDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAdministrativeLicenseId dataCompanyAdministrativeLicenseId) {
        return iDataCompanyAdministrativeLicenseService.deleteById(dataCompanyAdministrativeLicenseId.getId());
    }

    @Override
    public boolean delete(DataCompanyAdministrativeLicenseId dataCompanyAdministrativeLicenseId, IdCommand idCommand) {
        return iDataCompanyAdministrativeLicenseService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAdministrativeLicenseService(IDataCompanyAdministrativeLicenseService iDataCompanyAdministrativeLicenseService) {
        this.iDataCompanyAdministrativeLicenseService = iDataCompanyAdministrativeLicenseService;
    }
}
