package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyEquityPledge;
import com.particle.data.domain.company.DataCompanyEquityPledgeId;
import com.particle.data.domain.company.gateway.DataCompanyEquityPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyEquityPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyEquityPledgeDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyEquityPledgeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业股权出质 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Component
public class DataCompanyEquityPledgeGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyEquityPledgeId,DataCompanyEquityPledge> implements DataCompanyEquityPledgeGateway {

    private IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService;

    @Override
    public DataCompanyEquityPledge getById(DataCompanyEquityPledgeId dataCompanyEquityPledgeId) {
        DataCompanyEquityPledgeDO byId = iDataCompanyEquityPledgeService.getById(dataCompanyEquityPledgeId.getId());
        DataCompanyEquityPledge dataCompanyEquityPledge = DomainFactory.create(DataCompanyEquityPledge.class);
        dataCompanyEquityPledge = DataCompanyEquityPledgeInfrastructureStructMapping.instance. dataCompanyEquityPledgeDOToDataCompanyEquityPledge(dataCompanyEquityPledge,byId);
        return dataCompanyEquityPledge;
    }

    @Override
    public boolean doSave(DataCompanyEquityPledge dataCompanyEquityPledge) {
        DataCompanyEquityPledgeDO dataCompanyEquityPledgeDO = DataCompanyEquityPledgeInfrastructureStructMapping.instance.dataCompanyEquityPledgeToDataCompanyEquityPledgeDO(dataCompanyEquityPledge);
        if (dataCompanyEquityPledgeDO.getId() == null) {
            dataCompanyEquityPledgeDO.setAddControl(dataCompanyEquityPledge.getAddControl());
            DataCompanyEquityPledgeDO add = iDataCompanyEquityPledgeService.add(dataCompanyEquityPledgeDO);
            dataCompanyEquityPledge.setId(DataCompanyEquityPledgeId.of(add.getId()));
            return add != null;
        }
        dataCompanyEquityPledgeDO.setUpdateControl(dataCompanyEquityPledge.getUpdateControl());
        DataCompanyEquityPledgeDO update = iDataCompanyEquityPledgeService.update(dataCompanyEquityPledgeDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyEquityPledgeId dataCompanyEquityPledgeId) {
        return iDataCompanyEquityPledgeService.deleteById(dataCompanyEquityPledgeId.getId());
    }

    @Override
    public boolean delete(DataCompanyEquityPledgeId dataCompanyEquityPledgeId, IdCommand idCommand) {
        return iDataCompanyEquityPledgeService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyEquityPledgeService(IDataCompanyEquityPledgeService iDataCompanyEquityPledgeService) {
        this.iDataCompanyEquityPledgeService = iDataCompanyEquityPledgeService;
    }
}
