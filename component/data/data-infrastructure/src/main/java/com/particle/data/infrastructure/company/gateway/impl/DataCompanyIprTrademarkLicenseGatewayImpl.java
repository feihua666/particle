package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprTrademarkLicense;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkLicenseGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicenseService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicenseDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprTrademarkLicenseInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权商标许可信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Component
public class DataCompanyIprTrademarkLicenseGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprTrademarkLicenseId,DataCompanyIprTrademarkLicense> implements DataCompanyIprTrademarkLicenseGateway {

    private IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService;

    @Override
    public DataCompanyIprTrademarkLicense getById(DataCompanyIprTrademarkLicenseId dataCompanyIprTrademarkLicenseId) {
        DataCompanyIprTrademarkLicenseDO byId = iDataCompanyIprTrademarkLicenseService.getById(dataCompanyIprTrademarkLicenseId.getId());
        DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense = DomainFactory.create(DataCompanyIprTrademarkLicense.class);
        dataCompanyIprTrademarkLicense = DataCompanyIprTrademarkLicenseInfrastructureStructMapping.instance. dataCompanyIprTrademarkLicenseDOToDataCompanyIprTrademarkLicense(dataCompanyIprTrademarkLicense,byId);
        return dataCompanyIprTrademarkLicense;
    }

    @Override
    public boolean doSave(DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense) {
        DataCompanyIprTrademarkLicenseDO dataCompanyIprTrademarkLicenseDO = DataCompanyIprTrademarkLicenseInfrastructureStructMapping.instance.dataCompanyIprTrademarkLicenseToDataCompanyIprTrademarkLicenseDO(dataCompanyIprTrademarkLicense);
        if (dataCompanyIprTrademarkLicenseDO.getId() == null) {
            dataCompanyIprTrademarkLicenseDO.setAddControl(dataCompanyIprTrademarkLicense.getAddControl());
            DataCompanyIprTrademarkLicenseDO add = iDataCompanyIprTrademarkLicenseService.add(dataCompanyIprTrademarkLicenseDO);
            dataCompanyIprTrademarkLicense.setId(DataCompanyIprTrademarkLicenseId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprTrademarkLicenseDO.setUpdateControl(dataCompanyIprTrademarkLicense.getUpdateControl());
        DataCompanyIprTrademarkLicenseDO update = iDataCompanyIprTrademarkLicenseService.update(dataCompanyIprTrademarkLicenseDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkLicenseId dataCompanyIprTrademarkLicenseId) {
        return iDataCompanyIprTrademarkLicenseService.deleteById(dataCompanyIprTrademarkLicenseId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkLicenseId dataCompanyIprTrademarkLicenseId, IdCommand idCommand) {
        return iDataCompanyIprTrademarkLicenseService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprTrademarkLicenseService(IDataCompanyIprTrademarkLicenseService iDataCompanyIprTrademarkLicenseService) {
        this.iDataCompanyIprTrademarkLicenseService = iDataCompanyIprTrademarkLicenseService;
    }
}
