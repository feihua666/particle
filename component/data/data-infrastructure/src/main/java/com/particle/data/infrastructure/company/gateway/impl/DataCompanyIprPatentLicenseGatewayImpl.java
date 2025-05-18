package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentLicense;
import com.particle.data.domain.company.DataCompanyIprPatentLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLicenseDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentLicenseInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利许可信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Component
public class DataCompanyIprPatentLicenseGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentLicenseId,DataCompanyIprPatentLicense> implements DataCompanyIprPatentLicenseGateway {

    private IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService;

    @Override
    public DataCompanyIprPatentLicense getById(DataCompanyIprPatentLicenseId dataCompanyIprPatentLicenseId) {
        DataCompanyIprPatentLicenseDO byId = iDataCompanyIprPatentLicenseService.getById(dataCompanyIprPatentLicenseId.getId());
        DataCompanyIprPatentLicense dataCompanyIprPatentLicense = DomainFactory.create(DataCompanyIprPatentLicense.class);
        dataCompanyIprPatentLicense = DataCompanyIprPatentLicenseInfrastructureStructMapping.instance. dataCompanyIprPatentLicenseDOToDataCompanyIprPatentLicense(dataCompanyIprPatentLicense,byId);
        return dataCompanyIprPatentLicense;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentLicense dataCompanyIprPatentLicense) {
        DataCompanyIprPatentLicenseDO dataCompanyIprPatentLicenseDO = DataCompanyIprPatentLicenseInfrastructureStructMapping.instance.dataCompanyIprPatentLicenseToDataCompanyIprPatentLicenseDO(dataCompanyIprPatentLicense);
        if (dataCompanyIprPatentLicenseDO.getId() == null) {
            dataCompanyIprPatentLicenseDO.setAddControl(dataCompanyIprPatentLicense.getAddControl());
            DataCompanyIprPatentLicenseDO add = iDataCompanyIprPatentLicenseService.add(dataCompanyIprPatentLicenseDO);
            dataCompanyIprPatentLicense.setId(DataCompanyIprPatentLicenseId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentLicenseDO.setUpdateControl(dataCompanyIprPatentLicense.getUpdateControl());
        DataCompanyIprPatentLicenseDO update = iDataCompanyIprPatentLicenseService.update(dataCompanyIprPatentLicenseDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentLicenseId dataCompanyIprPatentLicenseId) {
        return iDataCompanyIprPatentLicenseService.deleteById(dataCompanyIprPatentLicenseId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentLicenseId dataCompanyIprPatentLicenseId, IdCommand idCommand) {
        return iDataCompanyIprPatentLicenseService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentLicenseService(IDataCompanyIprPatentLicenseService iDataCompanyIprPatentLicenseService) {
        this.iDataCompanyIprPatentLicenseService = iDataCompanyIprPatentLicenseService;
    }
}
