package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyRestrictHighConsume;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumeId;
import com.particle.data.domain.company.gateway.DataCompanyRestrictHighConsumeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumeService;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumeDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyRestrictHighConsumeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业限制高消费 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Component
public class DataCompanyRestrictHighConsumeGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyRestrictHighConsumeId,DataCompanyRestrictHighConsume> implements DataCompanyRestrictHighConsumeGateway {

    private IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService;

    @Override
    public DataCompanyRestrictHighConsume getById(DataCompanyRestrictHighConsumeId dataCompanyRestrictHighConsumeId) {
        DataCompanyRestrictHighConsumeDO byId = iDataCompanyRestrictHighConsumeService.getById(dataCompanyRestrictHighConsumeId.getId());
        DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume = DomainFactory.create(DataCompanyRestrictHighConsume.class);
        dataCompanyRestrictHighConsume = DataCompanyRestrictHighConsumeInfrastructureStructMapping.instance. dataCompanyRestrictHighConsumeDOToDataCompanyRestrictHighConsume(dataCompanyRestrictHighConsume,byId);
        return dataCompanyRestrictHighConsume;
    }

    @Override
    public boolean doSave(DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume) {
        DataCompanyRestrictHighConsumeDO dataCompanyRestrictHighConsumeDO = DataCompanyRestrictHighConsumeInfrastructureStructMapping.instance.dataCompanyRestrictHighConsumeToDataCompanyRestrictHighConsumeDO(dataCompanyRestrictHighConsume);
        if (dataCompanyRestrictHighConsumeDO.getId() == null) {
            dataCompanyRestrictHighConsumeDO.setAddControl(dataCompanyRestrictHighConsume.getAddControl());
            DataCompanyRestrictHighConsumeDO add = iDataCompanyRestrictHighConsumeService.add(dataCompanyRestrictHighConsumeDO);
            dataCompanyRestrictHighConsume.setId(DataCompanyRestrictHighConsumeId.of(add.getId()));
            return add != null;
        }
        dataCompanyRestrictHighConsumeDO.setUpdateControl(dataCompanyRestrictHighConsume.getUpdateControl());
        DataCompanyRestrictHighConsumeDO update = iDataCompanyRestrictHighConsumeService.update(dataCompanyRestrictHighConsumeDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyRestrictHighConsumeId dataCompanyRestrictHighConsumeId) {
        return iDataCompanyRestrictHighConsumeService.deleteById(dataCompanyRestrictHighConsumeId.getId());
    }

    @Override
    public boolean delete(DataCompanyRestrictHighConsumeId dataCompanyRestrictHighConsumeId, IdCommand idCommand) {
        return iDataCompanyRestrictHighConsumeService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyRestrictHighConsumeService(IDataCompanyRestrictHighConsumeService iDataCompanyRestrictHighConsumeService) {
        this.iDataCompanyRestrictHighConsumeService = iDataCompanyRestrictHighConsumeService;
    }
}
