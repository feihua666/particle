package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentPledge;
import com.particle.data.domain.company.DataCompanyIprPatentPledgeId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPledgeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentPledgeService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentPledgeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利质押信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Component
public class DataCompanyIprPatentPledgeGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentPledgeId,DataCompanyIprPatentPledge> implements DataCompanyIprPatentPledgeGateway {

    private IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService;

    @Override
    public DataCompanyIprPatentPledge getById(DataCompanyIprPatentPledgeId dataCompanyIprPatentPledgeId) {
        DataCompanyIprPatentPledgeDO byId = iDataCompanyIprPatentPledgeService.getById(dataCompanyIprPatentPledgeId.getId());
        DataCompanyIprPatentPledge dataCompanyIprPatentPledge = DomainFactory.create(DataCompanyIprPatentPledge.class);
        dataCompanyIprPatentPledge = DataCompanyIprPatentPledgeInfrastructureStructMapping.instance. dataCompanyIprPatentPledgeDOToDataCompanyIprPatentPledge(dataCompanyIprPatentPledge,byId);
        return dataCompanyIprPatentPledge;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentPledge dataCompanyIprPatentPledge) {
        DataCompanyIprPatentPledgeDO dataCompanyIprPatentPledgeDO = DataCompanyIprPatentPledgeInfrastructureStructMapping.instance.dataCompanyIprPatentPledgeToDataCompanyIprPatentPledgeDO(dataCompanyIprPatentPledge);
        if (dataCompanyIprPatentPledgeDO.getId() == null) {
            dataCompanyIprPatentPledgeDO.setAddControl(dataCompanyIprPatentPledge.getAddControl());
            DataCompanyIprPatentPledgeDO add = iDataCompanyIprPatentPledgeService.add(dataCompanyIprPatentPledgeDO);
            dataCompanyIprPatentPledge.setId(DataCompanyIprPatentPledgeId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentPledgeDO.setUpdateControl(dataCompanyIprPatentPledge.getUpdateControl());
        DataCompanyIprPatentPledgeDO update = iDataCompanyIprPatentPledgeService.update(dataCompanyIprPatentPledgeDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentPledgeId dataCompanyIprPatentPledgeId) {
        return iDataCompanyIprPatentPledgeService.deleteById(dataCompanyIprPatentPledgeId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentPledgeId dataCompanyIprPatentPledgeId, IdCommand idCommand) {
        return iDataCompanyIprPatentPledgeService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentPledgeService(IDataCompanyIprPatentPledgeService iDataCompanyIprPatentPledgeService) {
        this.iDataCompanyIprPatentPledgeService = iDataCompanyIprPatentPledgeService;
    }
}
