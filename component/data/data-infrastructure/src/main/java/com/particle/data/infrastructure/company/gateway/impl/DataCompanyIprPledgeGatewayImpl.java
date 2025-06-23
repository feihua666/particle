package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPledge;
import com.particle.data.domain.company.DataCompanyIprPledgeId;
import com.particle.data.domain.company.gateway.DataCompanyIprPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPledgeDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPledgeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权出质 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Component
public class DataCompanyIprPledgeGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPledgeId,DataCompanyIprPledge> implements DataCompanyIprPledgeGateway {

    private IDataCompanyIprPledgeService iDataCompanyIprPledgeService;

    @Override
    public DataCompanyIprPledge getById(DataCompanyIprPledgeId dataCompanyIprPledgeId) {
        DataCompanyIprPledgeDO byId = iDataCompanyIprPledgeService.getById(dataCompanyIprPledgeId.getId());
        DataCompanyIprPledge dataCompanyIprPledge = DomainFactory.create(DataCompanyIprPledge.class);
        dataCompanyIprPledge = DataCompanyIprPledgeInfrastructureStructMapping.instance. dataCompanyIprPledgeDOToDataCompanyIprPledge(dataCompanyIprPledge,byId);
        return dataCompanyIprPledge;
    }

    @Override
    public boolean doSave(DataCompanyIprPledge dataCompanyIprPledge) {
        DataCompanyIprPledgeDO dataCompanyIprPledgeDO = DataCompanyIprPledgeInfrastructureStructMapping.instance.dataCompanyIprPledgeToDataCompanyIprPledgeDO(dataCompanyIprPledge);
        if (dataCompanyIprPledgeDO.getId() == null) {
            dataCompanyIprPledgeDO.setAddControl(dataCompanyIprPledge.getAddControl());
            DataCompanyIprPledgeDO add = iDataCompanyIprPledgeService.add(dataCompanyIprPledgeDO);
            dataCompanyIprPledge.setId(DataCompanyIprPledgeId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPledgeDO.setUpdateControl(dataCompanyIprPledge.getUpdateControl());
        DataCompanyIprPledgeDO update = iDataCompanyIprPledgeService.update(dataCompanyIprPledgeDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPledgeId dataCompanyIprPledgeId) {
        return iDataCompanyIprPledgeService.deleteById(dataCompanyIprPledgeId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPledgeId dataCompanyIprPledgeId, IdCommand idCommand) {
        return iDataCompanyIprPledgeService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPledgeService(IDataCompanyIprPledgeService iDataCompanyIprPledgeService) {
        this.iDataCompanyIprPledgeService = iDataCompanyIprPledgeService;
    }
}
