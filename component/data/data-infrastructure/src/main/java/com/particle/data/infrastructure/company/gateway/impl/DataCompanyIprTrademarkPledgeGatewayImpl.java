package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprTrademarkPledge;
import com.particle.data.domain.company.DataCompanyIprTrademarkPledgeId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkPledgeDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprTrademarkPledgeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权商标质押信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Component
public class DataCompanyIprTrademarkPledgeGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprTrademarkPledgeId,DataCompanyIprTrademarkPledge> implements DataCompanyIprTrademarkPledgeGateway {

    private IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService;

    @Override
    public DataCompanyIprTrademarkPledge getById(DataCompanyIprTrademarkPledgeId dataCompanyIprTrademarkPledgeId) {
        DataCompanyIprTrademarkPledgeDO byId = iDataCompanyIprTrademarkPledgeService.getById(dataCompanyIprTrademarkPledgeId.getId());
        DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge = DomainFactory.create(DataCompanyIprTrademarkPledge.class);
        dataCompanyIprTrademarkPledge = DataCompanyIprTrademarkPledgeInfrastructureStructMapping.instance. dataCompanyIprTrademarkPledgeDOToDataCompanyIprTrademarkPledge(dataCompanyIprTrademarkPledge,byId);
        return dataCompanyIprTrademarkPledge;
    }

    @Override
    public boolean doSave(DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge) {
        DataCompanyIprTrademarkPledgeDO dataCompanyIprTrademarkPledgeDO = DataCompanyIprTrademarkPledgeInfrastructureStructMapping.instance.dataCompanyIprTrademarkPledgeToDataCompanyIprTrademarkPledgeDO(dataCompanyIprTrademarkPledge);
        if (dataCompanyIprTrademarkPledgeDO.getId() == null) {
            dataCompanyIprTrademarkPledgeDO.setAddControl(dataCompanyIprTrademarkPledge.getAddControl());
            DataCompanyIprTrademarkPledgeDO add = iDataCompanyIprTrademarkPledgeService.add(dataCompanyIprTrademarkPledgeDO);
            dataCompanyIprTrademarkPledge.setId(DataCompanyIprTrademarkPledgeId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprTrademarkPledgeDO.setUpdateControl(dataCompanyIprTrademarkPledge.getUpdateControl());
        DataCompanyIprTrademarkPledgeDO update = iDataCompanyIprTrademarkPledgeService.update(dataCompanyIprTrademarkPledgeDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkPledgeId dataCompanyIprTrademarkPledgeId) {
        return iDataCompanyIprTrademarkPledgeService.deleteById(dataCompanyIprTrademarkPledgeId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkPledgeId dataCompanyIprTrademarkPledgeId, IdCommand idCommand) {
        return iDataCompanyIprTrademarkPledgeService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprTrademarkPledgeService(IDataCompanyIprTrademarkPledgeService iDataCompanyIprTrademarkPledgeService) {
        this.iDataCompanyIprTrademarkPledgeService = iDataCompanyIprTrademarkPledgeService;
    }
}
