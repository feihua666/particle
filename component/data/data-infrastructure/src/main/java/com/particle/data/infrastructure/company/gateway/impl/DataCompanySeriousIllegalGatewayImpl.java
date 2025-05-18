package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanySeriousIllegal;
import com.particle.data.domain.company.DataCompanySeriousIllegalId;
import com.particle.data.domain.company.gateway.DataCompanySeriousIllegalGateway;
import com.particle.data.infrastructure.company.service.IDataCompanySeriousIllegalService;
import com.particle.data.infrastructure.company.dos.DataCompanySeriousIllegalDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanySeriousIllegalInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业严重违法 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Component
public class DataCompanySeriousIllegalGatewayImpl extends AbstractBaseGatewayImpl<DataCompanySeriousIllegalId,DataCompanySeriousIllegal> implements DataCompanySeriousIllegalGateway {

    private IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService;

    @Override
    public DataCompanySeriousIllegal getById(DataCompanySeriousIllegalId dataCompanySeriousIllegalId) {
        DataCompanySeriousIllegalDO byId = iDataCompanySeriousIllegalService.getById(dataCompanySeriousIllegalId.getId());
        DataCompanySeriousIllegal dataCompanySeriousIllegal = DomainFactory.create(DataCompanySeriousIllegal.class);
        dataCompanySeriousIllegal = DataCompanySeriousIllegalInfrastructureStructMapping.instance. dataCompanySeriousIllegalDOToDataCompanySeriousIllegal(dataCompanySeriousIllegal,byId);
        return dataCompanySeriousIllegal;
    }

    @Override
    public boolean doSave(DataCompanySeriousIllegal dataCompanySeriousIllegal) {
        DataCompanySeriousIllegalDO dataCompanySeriousIllegalDO = DataCompanySeriousIllegalInfrastructureStructMapping.instance.dataCompanySeriousIllegalToDataCompanySeriousIllegalDO(dataCompanySeriousIllegal);
        if (dataCompanySeriousIllegalDO.getId() == null) {
            dataCompanySeriousIllegalDO.setAddControl(dataCompanySeriousIllegal.getAddControl());
            DataCompanySeriousIllegalDO add = iDataCompanySeriousIllegalService.add(dataCompanySeriousIllegalDO);
            dataCompanySeriousIllegal.setId(DataCompanySeriousIllegalId.of(add.getId()));
            return add != null;
        }
        dataCompanySeriousIllegalDO.setUpdateControl(dataCompanySeriousIllegal.getUpdateControl());
        DataCompanySeriousIllegalDO update = iDataCompanySeriousIllegalService.update(dataCompanySeriousIllegalDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanySeriousIllegalId dataCompanySeriousIllegalId) {
        return iDataCompanySeriousIllegalService.deleteById(dataCompanySeriousIllegalId.getId());
    }

    @Override
    public boolean delete(DataCompanySeriousIllegalId dataCompanySeriousIllegalId, IdCommand idCommand) {
        return iDataCompanySeriousIllegalService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanySeriousIllegalService(IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService) {
        this.iDataCompanySeriousIllegalService = iDataCompanySeriousIllegalService;
    }
}
