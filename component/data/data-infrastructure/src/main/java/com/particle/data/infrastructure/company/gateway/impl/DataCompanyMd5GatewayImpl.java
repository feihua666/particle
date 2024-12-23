package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.data.domain.company.DataCompanyMd5;
import com.particle.data.domain.company.DataCompanyMd5Id;
import com.particle.data.domain.company.gateway.DataCompanyMd5Gateway;
import com.particle.data.infrastructure.company.dos.DataCompanyMd5DO;
import com.particle.data.infrastructure.company.service.IDataCompanyMd5Service;
import com.particle.data.infrastructure.company.structmapping.DataCompanyMd5InfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业md5 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Component
public class DataCompanyMd5GatewayImpl extends AbstractBaseGatewayImpl<DataCompanyMd5Id,DataCompanyMd5> implements DataCompanyMd5Gateway {

    private IDataCompanyMd5Service iDataCompanyMd5Service;

    @Override
    public DataCompanyMd5 getById(DataCompanyMd5Id dataCompanyMd5Id) {
        DataCompanyMd5DO byId = iDataCompanyMd5Service.getById(dataCompanyMd5Id.getId());
        DataCompanyMd5 dataCompanyMd5 = DomainFactory.create(DataCompanyMd5.class);
        dataCompanyMd5 = DataCompanyMd5InfrastructureStructMapping.instance. dataCompanyMd5DOToDataCompanyMd5(dataCompanyMd5,byId);
        return dataCompanyMd5;
    }

    @Override
    public boolean doSave(DataCompanyMd5 dataCompanyMd5) {
        DataCompanyMd5DO dataCompanyMd5DO = DataCompanyMd5InfrastructureStructMapping.instance.dataCompanyMd5ToDataCompanyMd5DO(dataCompanyMd5);
        if (dataCompanyMd5DO.getId() == null) {
            dataCompanyMd5DO.setAddControl(dataCompanyMd5.getAddControl());
            DataCompanyMd5DO add = iDataCompanyMd5Service.add(dataCompanyMd5DO);
            dataCompanyMd5.setId(DataCompanyMd5Id.of(add.getId()));
            return add != null;
        }
        dataCompanyMd5DO.setUpdateControl(dataCompanyMd5.getUpdateControl());
        DataCompanyMd5DO update = iDataCompanyMd5Service.update(dataCompanyMd5DO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyMd5Id dataCompanyMd5Id) {
        return iDataCompanyMd5Service.deleteById(dataCompanyMd5Id.getId());
    }

    @Override
    public boolean delete(DataCompanyMd5Id dataCompanyMd5Id, IdCommand idCommand) {
        return iDataCompanyMd5Service.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyMd5Service(IDataCompanyMd5Service iDataCompanyMd5Service) {
        this.iDataCompanyMd5Service = iDataCompanyMd5Service;
    }
}
