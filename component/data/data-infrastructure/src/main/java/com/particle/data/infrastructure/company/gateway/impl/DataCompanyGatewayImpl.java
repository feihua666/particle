package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.data.domain.company.DataCompany;
import com.particle.data.domain.company.DataCompanyId;
import com.particle.data.domain.company.gateway.DataCompanyGateway;
import com.particle.data.infrastructure.company.dos.DataCompanyDO;
import com.particle.data.infrastructure.company.service.IDataCompanyService;
import com.particle.data.infrastructure.company.structmapping.DataCompanyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Component
public class DataCompanyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyId,DataCompany> implements DataCompanyGateway {

    private IDataCompanyService iDataCompanyService;

    @Override
    public DataCompany getById(DataCompanyId dataCompanyId) {
        DataCompanyDO byId = iDataCompanyService.getById(dataCompanyId.getId());
        DataCompany dataCompany = DomainFactory.create(DataCompany.class);
        dataCompany = DataCompanyInfrastructureStructMapping.instance. dataCompanyDOToDataCompany(dataCompany,byId);
        return dataCompany;
    }

    @Override
    public boolean doSave(DataCompany dataCompany) {
        DataCompanyDO dataCompanyDO = DataCompanyInfrastructureStructMapping.instance.dataCompanyToDataCompanyDO(dataCompany);
        if (dataCompanyDO.getId() == null) {
            dataCompanyDO.setAddControl(dataCompany.getAddControl());
            DataCompanyDO add = iDataCompanyService.add(dataCompanyDO);
            dataCompany.setId(DataCompanyId.of(add.getId()));
            return add != null;
        }
        dataCompanyDO.setUpdateControl(dataCompany.getUpdateControl());
        DataCompanyDO update = iDataCompanyService.update(dataCompanyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyId dataCompanyId) {
        return iDataCompanyService.deleteById(dataCompanyId.getId());
    }

    @Override
    public boolean delete(DataCompanyId dataCompanyId, IdCommand idCommand) {
        return iDataCompanyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyService(IDataCompanyService iDataCompanyService) {
        this.iDataCompanyService = iDataCompanyService;
    }
}
