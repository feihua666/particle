package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatent;
import com.particle.data.domain.company.DataCompanyIprPatentId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Component
public class DataCompanyIprPatentGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentId,DataCompanyIprPatent> implements DataCompanyIprPatentGateway {

    private IDataCompanyIprPatentService iDataCompanyIprPatentService;

    @Override
    public DataCompanyIprPatent getById(DataCompanyIprPatentId dataCompanyIprPatentId) {
        DataCompanyIprPatentDO byId = iDataCompanyIprPatentService.getById(dataCompanyIprPatentId.getId());
        DataCompanyIprPatent dataCompanyIprPatent = DomainFactory.create(DataCompanyIprPatent.class);
        dataCompanyIprPatent = DataCompanyIprPatentInfrastructureStructMapping.instance. dataCompanyIprPatentDOToDataCompanyIprPatent(dataCompanyIprPatent,byId);
        return dataCompanyIprPatent;
    }

    @Override
    public boolean doSave(DataCompanyIprPatent dataCompanyIprPatent) {
        DataCompanyIprPatentDO dataCompanyIprPatentDO = DataCompanyIprPatentInfrastructureStructMapping.instance.dataCompanyIprPatentToDataCompanyIprPatentDO(dataCompanyIprPatent);
        if (dataCompanyIprPatentDO.getId() == null) {
            dataCompanyIprPatentDO.setAddControl(dataCompanyIprPatent.getAddControl());
            DataCompanyIprPatentDO add = iDataCompanyIprPatentService.add(dataCompanyIprPatentDO);
            dataCompanyIprPatent.setId(DataCompanyIprPatentId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentDO.setUpdateControl(dataCompanyIprPatent.getUpdateControl());
        DataCompanyIprPatentDO update = iDataCompanyIprPatentService.update(dataCompanyIprPatentDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentId dataCompanyIprPatentId) {
        return iDataCompanyIprPatentService.deleteById(dataCompanyIprPatentId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentId dataCompanyIprPatentId, IdCommand idCommand) {
        return iDataCompanyIprPatentService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentService(IDataCompanyIprPatentService iDataCompanyIprPatentService) {
        this.iDataCompanyIprPatentService = iDataCompanyIprPatentService;
    }
}
