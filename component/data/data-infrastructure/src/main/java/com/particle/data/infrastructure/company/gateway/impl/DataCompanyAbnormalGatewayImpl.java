package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAbnormal;
import com.particle.data.domain.company.DataCompanyAbnormalId;
import com.particle.data.domain.company.gateway.DataCompanyAbnormalGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAbnormalService;
import com.particle.data.infrastructure.company.dos.DataCompanyAbnormalDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAbnormalInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业经营异常 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Component
public class DataCompanyAbnormalGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAbnormalId,DataCompanyAbnormal> implements DataCompanyAbnormalGateway {

    private IDataCompanyAbnormalService iDataCompanyAbnormalService;

    @Override
    public DataCompanyAbnormal getById(DataCompanyAbnormalId dataCompanyAbnormalId) {
        DataCompanyAbnormalDO byId = iDataCompanyAbnormalService.getById(dataCompanyAbnormalId.getId());
        DataCompanyAbnormal dataCompanyAbnormal = DomainFactory.create(DataCompanyAbnormal.class);
        dataCompanyAbnormal = DataCompanyAbnormalInfrastructureStructMapping.instance. dataCompanyAbnormalDOToDataCompanyAbnormal(dataCompanyAbnormal,byId);
        return dataCompanyAbnormal;
    }

    @Override
    public boolean doSave(DataCompanyAbnormal dataCompanyAbnormal) {
        DataCompanyAbnormalDO dataCompanyAbnormalDO = DataCompanyAbnormalInfrastructureStructMapping.instance.dataCompanyAbnormalToDataCompanyAbnormalDO(dataCompanyAbnormal);
        if (dataCompanyAbnormalDO.getId() == null) {
            dataCompanyAbnormalDO.setAddControl(dataCompanyAbnormal.getAddControl());
            DataCompanyAbnormalDO add = iDataCompanyAbnormalService.add(dataCompanyAbnormalDO);
            dataCompanyAbnormal.setId(DataCompanyAbnormalId.of(add.getId()));
            return add != null;
        }
        dataCompanyAbnormalDO.setUpdateControl(dataCompanyAbnormal.getUpdateControl());
        DataCompanyAbnormalDO update = iDataCompanyAbnormalService.update(dataCompanyAbnormalDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAbnormalId dataCompanyAbnormalId) {
        return iDataCompanyAbnormalService.deleteById(dataCompanyAbnormalId.getId());
    }

    @Override
    public boolean delete(DataCompanyAbnormalId dataCompanyAbnormalId, IdCommand idCommand) {
        return iDataCompanyAbnormalService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAbnormalService(IDataCompanyAbnormalService iDataCompanyAbnormalService) {
        this.iDataCompanyAbnormalService = iDataCompanyAbnormalService;
    }
}
