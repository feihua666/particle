package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentCited;
import com.particle.data.domain.company.DataCompanyIprPatentCitedId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentCitedGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCitedService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCitedDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentCitedInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利被引证信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Component
public class DataCompanyIprPatentCitedGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentCitedId,DataCompanyIprPatentCited> implements DataCompanyIprPatentCitedGateway {

    private IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService;

    @Override
    public DataCompanyIprPatentCited getById(DataCompanyIprPatentCitedId dataCompanyIprPatentCitedId) {
        DataCompanyIprPatentCitedDO byId = iDataCompanyIprPatentCitedService.getById(dataCompanyIprPatentCitedId.getId());
        DataCompanyIprPatentCited dataCompanyIprPatentCited = DomainFactory.create(DataCompanyIprPatentCited.class);
        dataCompanyIprPatentCited = DataCompanyIprPatentCitedInfrastructureStructMapping.instance. dataCompanyIprPatentCitedDOToDataCompanyIprPatentCited(dataCompanyIprPatentCited,byId);
        return dataCompanyIprPatentCited;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentCited dataCompanyIprPatentCited) {
        DataCompanyIprPatentCitedDO dataCompanyIprPatentCitedDO = DataCompanyIprPatentCitedInfrastructureStructMapping.instance.dataCompanyIprPatentCitedToDataCompanyIprPatentCitedDO(dataCompanyIprPatentCited);
        if (dataCompanyIprPatentCitedDO.getId() == null) {
            dataCompanyIprPatentCitedDO.setAddControl(dataCompanyIprPatentCited.getAddControl());
            DataCompanyIprPatentCitedDO add = iDataCompanyIprPatentCitedService.add(dataCompanyIprPatentCitedDO);
            dataCompanyIprPatentCited.setId(DataCompanyIprPatentCitedId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentCitedDO.setUpdateControl(dataCompanyIprPatentCited.getUpdateControl());
        DataCompanyIprPatentCitedDO update = iDataCompanyIprPatentCitedService.update(dataCompanyIprPatentCitedDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentCitedId dataCompanyIprPatentCitedId) {
        return iDataCompanyIprPatentCitedService.deleteById(dataCompanyIprPatentCitedId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentCitedId dataCompanyIprPatentCitedId, IdCommand idCommand) {
        return iDataCompanyIprPatentCitedService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentCitedService(IDataCompanyIprPatentCitedService iDataCompanyIprPatentCitedService) {
        this.iDataCompanyIprPatentCitedService = iDataCompanyIprPatentCitedService;
    }
}
