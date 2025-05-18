package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyBasic;
import com.particle.data.domain.company.DataCompanyBasicId;
import com.particle.data.domain.company.gateway.DataCompanyBasicGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyBasicService;
import com.particle.data.infrastructure.company.dos.DataCompanyBasicDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyBasicInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业基本信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Component
public class DataCompanyBasicGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyBasicId,DataCompanyBasic> implements DataCompanyBasicGateway {

    private IDataCompanyBasicService iDataCompanyBasicService;

    @Override
    public DataCompanyBasic getById(DataCompanyBasicId dataCompanyBasicId) {
        DataCompanyBasicDO byId = iDataCompanyBasicService.getById(dataCompanyBasicId.getId());
        DataCompanyBasic dataCompanyBasic = DomainFactory.create(DataCompanyBasic.class);
        dataCompanyBasic = DataCompanyBasicInfrastructureStructMapping.instance. dataCompanyBasicDOToDataCompanyBasic(dataCompanyBasic,byId);
        return dataCompanyBasic;
    }

    @Override
    public boolean doSave(DataCompanyBasic dataCompanyBasic) {
        DataCompanyBasicDO dataCompanyBasicDO = DataCompanyBasicInfrastructureStructMapping.instance.dataCompanyBasicToDataCompanyBasicDO(dataCompanyBasic);
        if (dataCompanyBasicDO.getId() == null) {
            dataCompanyBasicDO.setAddControl(dataCompanyBasic.getAddControl());
            DataCompanyBasicDO add = iDataCompanyBasicService.add(dataCompanyBasicDO);
            dataCompanyBasic.setId(DataCompanyBasicId.of(add.getId()));
            return add != null;
        }
        dataCompanyBasicDO.setUpdateControl(dataCompanyBasic.getUpdateControl());
        DataCompanyBasicDO update = iDataCompanyBasicService.update(dataCompanyBasicDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyBasicId dataCompanyBasicId) {
        return iDataCompanyBasicService.deleteById(dataCompanyBasicId.getId());
    }

    @Override
    public boolean delete(DataCompanyBasicId dataCompanyBasicId, IdCommand idCommand) {
        return iDataCompanyBasicService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyBasicService(IDataCompanyBasicService iDataCompanyBasicService) {
        this.iDataCompanyBasicService = iDataCompanyBasicService;
    }
}
