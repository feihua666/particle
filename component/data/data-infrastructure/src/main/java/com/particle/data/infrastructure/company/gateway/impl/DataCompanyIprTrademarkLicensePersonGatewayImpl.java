package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePerson;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePersonId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkLicensePersonGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkLicensePersonService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkLicensePersonDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprTrademarkLicensePersonInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权商标许可人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Component
public class DataCompanyIprTrademarkLicensePersonGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprTrademarkLicensePersonId,DataCompanyIprTrademarkLicensePerson> implements DataCompanyIprTrademarkLicensePersonGateway {

    private IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService;

    @Override
    public DataCompanyIprTrademarkLicensePerson getById(DataCompanyIprTrademarkLicensePersonId dataCompanyIprTrademarkLicensePersonId) {
        DataCompanyIprTrademarkLicensePersonDO byId = iDataCompanyIprTrademarkLicensePersonService.getById(dataCompanyIprTrademarkLicensePersonId.getId());
        DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson = DomainFactory.create(DataCompanyIprTrademarkLicensePerson.class);
        dataCompanyIprTrademarkLicensePerson = DataCompanyIprTrademarkLicensePersonInfrastructureStructMapping.instance. dataCompanyIprTrademarkLicensePersonDOToDataCompanyIprTrademarkLicensePerson(dataCompanyIprTrademarkLicensePerson,byId);
        return dataCompanyIprTrademarkLicensePerson;
    }

    @Override
    public boolean doSave(DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson) {
        DataCompanyIprTrademarkLicensePersonDO dataCompanyIprTrademarkLicensePersonDO = DataCompanyIprTrademarkLicensePersonInfrastructureStructMapping.instance.dataCompanyIprTrademarkLicensePersonToDataCompanyIprTrademarkLicensePersonDO(dataCompanyIprTrademarkLicensePerson);
        if (dataCompanyIprTrademarkLicensePersonDO.getId() == null) {
            dataCompanyIprTrademarkLicensePersonDO.setAddControl(dataCompanyIprTrademarkLicensePerson.getAddControl());
            DataCompanyIprTrademarkLicensePersonDO add = iDataCompanyIprTrademarkLicensePersonService.add(dataCompanyIprTrademarkLicensePersonDO);
            dataCompanyIprTrademarkLicensePerson.setId(DataCompanyIprTrademarkLicensePersonId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprTrademarkLicensePersonDO.setUpdateControl(dataCompanyIprTrademarkLicensePerson.getUpdateControl());
        DataCompanyIprTrademarkLicensePersonDO update = iDataCompanyIprTrademarkLicensePersonService.update(dataCompanyIprTrademarkLicensePersonDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkLicensePersonId dataCompanyIprTrademarkLicensePersonId) {
        return iDataCompanyIprTrademarkLicensePersonService.deleteById(dataCompanyIprTrademarkLicensePersonId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkLicensePersonId dataCompanyIprTrademarkLicensePersonId, IdCommand idCommand) {
        return iDataCompanyIprTrademarkLicensePersonService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprTrademarkLicensePersonService(IDataCompanyIprTrademarkLicensePersonService iDataCompanyIprTrademarkLicensePersonService) {
        this.iDataCompanyIprTrademarkLicensePersonService = iDataCompanyIprTrademarkLicensePersonService;
    }
}
