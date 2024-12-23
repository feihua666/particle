package com.particle.data.infrastructure.temp.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.data.domain.temp.DataCompanyMd5Ids;
import com.particle.data.domain.temp.DataCompanyMd5IdsId;
import com.particle.data.domain.temp.gateway.DataCompanyMd5IdsGateway;
import com.particle.data.infrastructure.temp.dos.DataCompanyMd5IdsDO;
import com.particle.data.infrastructure.temp.service.IDataCompanyMd5IdsService;
import com.particle.data.infrastructure.temp.structmapping.DataCompanyMd5IdsInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业md5ids 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Component
public class DataCompanyMd5IdsGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyMd5IdsId,DataCompanyMd5Ids> implements DataCompanyMd5IdsGateway {

    private IDataCompanyMd5IdsService iDataCompanyMd5IdsService;

    @Override
    public DataCompanyMd5Ids getById(DataCompanyMd5IdsId dataCompanyMd5IdsId) {
        DataCompanyMd5IdsDO byId = iDataCompanyMd5IdsService.getById(dataCompanyMd5IdsId.getId());
        DataCompanyMd5Ids dataCompanyMd5Ids = DomainFactory.create(DataCompanyMd5Ids.class);
        dataCompanyMd5Ids = DataCompanyMd5IdsInfrastructureStructMapping.instance. dataCompanyMd5IdsDOToDataCompanyMd5Ids(dataCompanyMd5Ids,byId);
        return dataCompanyMd5Ids;
    }

    @Override
    public boolean doSave(DataCompanyMd5Ids dataCompanyMd5Ids) {
        DataCompanyMd5IdsDO dataCompanyMd5IdsDO = DataCompanyMd5IdsInfrastructureStructMapping.instance.dataCompanyMd5IdsToDataCompanyMd5IdsDO(dataCompanyMd5Ids);
        if (dataCompanyMd5IdsDO.getId() == null) {
            dataCompanyMd5IdsDO.setAddControl(dataCompanyMd5Ids.getAddControl());
            DataCompanyMd5IdsDO add = iDataCompanyMd5IdsService.add(dataCompanyMd5IdsDO);
            dataCompanyMd5Ids.setId(DataCompanyMd5IdsId.of(add.getId()));
            return add != null;
        }
        dataCompanyMd5IdsDO.setUpdateControl(dataCompanyMd5Ids.getUpdateControl());
        DataCompanyMd5IdsDO update = iDataCompanyMd5IdsService.update(dataCompanyMd5IdsDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyMd5IdsId dataCompanyMd5IdsId) {
        return iDataCompanyMd5IdsService.deleteById(dataCompanyMd5IdsId.getId());
    }

    @Override
    public boolean delete(DataCompanyMd5IdsId dataCompanyMd5IdsId, IdCommand idCommand) {
        return iDataCompanyMd5IdsService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyMd5IdsService(IDataCompanyMd5IdsService iDataCompanyMd5IdsService) {
        this.iDataCompanyMd5IdsService = iDataCompanyMd5IdsService;
    }
}
