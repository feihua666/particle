package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRel;
import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRelId;
import com.particle.data.domain.company.gateway.DataCompanyVcProductCompetitiveProductRelGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyVcProductCompetitiveProductRelService;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductCompetitiveProductRelDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyVcProductCompetitiveProductRelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业融资产品竞品关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Component
public class DataCompanyVcProductCompetitiveProductRelGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyVcProductCompetitiveProductRelId,DataCompanyVcProductCompetitiveProductRel> implements DataCompanyVcProductCompetitiveProductRelGateway {

    private IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService;

    @Override
    public DataCompanyVcProductCompetitiveProductRel getById(DataCompanyVcProductCompetitiveProductRelId dataCompanyVcProductCompetitiveProductRelId) {
        DataCompanyVcProductCompetitiveProductRelDO byId = iDataCompanyVcProductCompetitiveProductRelService.getById(dataCompanyVcProductCompetitiveProductRelId.getId());
        DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel = DomainFactory.create(DataCompanyVcProductCompetitiveProductRel.class);
        dataCompanyVcProductCompetitiveProductRel = DataCompanyVcProductCompetitiveProductRelInfrastructureStructMapping.instance. dataCompanyVcProductCompetitiveProductRelDOToDataCompanyVcProductCompetitiveProductRel(dataCompanyVcProductCompetitiveProductRel,byId);
        return dataCompanyVcProductCompetitiveProductRel;
    }

    @Override
    public boolean doSave(DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel) {
        DataCompanyVcProductCompetitiveProductRelDO dataCompanyVcProductCompetitiveProductRelDO = DataCompanyVcProductCompetitiveProductRelInfrastructureStructMapping.instance.dataCompanyVcProductCompetitiveProductRelToDataCompanyVcProductCompetitiveProductRelDO(dataCompanyVcProductCompetitiveProductRel);
        if (dataCompanyVcProductCompetitiveProductRelDO.getId() == null) {
            dataCompanyVcProductCompetitiveProductRelDO.setAddControl(dataCompanyVcProductCompetitiveProductRel.getAddControl());
            DataCompanyVcProductCompetitiveProductRelDO add = iDataCompanyVcProductCompetitiveProductRelService.add(dataCompanyVcProductCompetitiveProductRelDO);
            dataCompanyVcProductCompetitiveProductRel.setId(DataCompanyVcProductCompetitiveProductRelId.of(add.getId()));
            return add != null;
        }
        dataCompanyVcProductCompetitiveProductRelDO.setUpdateControl(dataCompanyVcProductCompetitiveProductRel.getUpdateControl());
        DataCompanyVcProductCompetitiveProductRelDO update = iDataCompanyVcProductCompetitiveProductRelService.update(dataCompanyVcProductCompetitiveProductRelDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyVcProductCompetitiveProductRelId dataCompanyVcProductCompetitiveProductRelId) {
        return iDataCompanyVcProductCompetitiveProductRelService.deleteById(dataCompanyVcProductCompetitiveProductRelId.getId());
    }

    @Override
    public boolean delete(DataCompanyVcProductCompetitiveProductRelId dataCompanyVcProductCompetitiveProductRelId, IdCommand idCommand) {
        return iDataCompanyVcProductCompetitiveProductRelService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyVcProductCompetitiveProductRelService(IDataCompanyVcProductCompetitiveProductRelService iDataCompanyVcProductCompetitiveProductRelService) {
        this.iDataCompanyVcProductCompetitiveProductRelService = iDataCompanyVcProductCompetitiveProductRelService;
    }
}
