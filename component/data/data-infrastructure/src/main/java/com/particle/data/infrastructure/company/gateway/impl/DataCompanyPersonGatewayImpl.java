package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyPerson;
import com.particle.data.domain.company.DataCompanyPersonId;
import com.particle.data.domain.company.gateway.DataCompanyPersonGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPersonService;
import com.particle.data.infrastructure.company.dos.DataCompanyPersonDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyPersonInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业个人 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Component
public class DataCompanyPersonGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyPersonId,DataCompanyPerson> implements DataCompanyPersonGateway {

    private IDataCompanyPersonService iDataCompanyPersonService;

    @Override
    public DataCompanyPerson getById(DataCompanyPersonId dataCompanyPersonId) {
        DataCompanyPersonDO byId = iDataCompanyPersonService.getById(dataCompanyPersonId.getId());
        DataCompanyPerson dataCompanyPerson = DomainFactory.create(DataCompanyPerson.class);
        dataCompanyPerson = DataCompanyPersonInfrastructureStructMapping.instance. dataCompanyPersonDOToDataCompanyPerson(dataCompanyPerson,byId);
        return dataCompanyPerson;
    }

    @Override
    public boolean doSave(DataCompanyPerson dataCompanyPerson) {
        DataCompanyPersonDO dataCompanyPersonDO = DataCompanyPersonInfrastructureStructMapping.instance.dataCompanyPersonToDataCompanyPersonDO(dataCompanyPerson);
        if (dataCompanyPersonDO.getId() == null) {
            dataCompanyPersonDO.setAddControl(dataCompanyPerson.getAddControl());
            DataCompanyPersonDO add = iDataCompanyPersonService.add(dataCompanyPersonDO);
            dataCompanyPerson.setId(DataCompanyPersonId.of(add.getId()));
            return add != null;
        }
        dataCompanyPersonDO.setUpdateControl(dataCompanyPerson.getUpdateControl());
        DataCompanyPersonDO update = iDataCompanyPersonService.update(dataCompanyPersonDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyPersonId dataCompanyPersonId) {
        return iDataCompanyPersonService.deleteById(dataCompanyPersonId.getId());
    }

    @Override
    public boolean delete(DataCompanyPersonId dataCompanyPersonId, IdCommand idCommand) {
        return iDataCompanyPersonService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyPersonService(IDataCompanyPersonService iDataCompanyPersonService) {
        this.iDataCompanyPersonService = iDataCompanyPersonService;
    }
}
