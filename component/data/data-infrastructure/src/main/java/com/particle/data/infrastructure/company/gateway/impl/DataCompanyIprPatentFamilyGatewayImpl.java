package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentFamily;
import com.particle.data.domain.company.DataCompanyIprPatentFamilyId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentFamilyGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentFamilyService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentFamilyDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentFamilyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利同族信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Component
public class DataCompanyIprPatentFamilyGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentFamilyId,DataCompanyIprPatentFamily> implements DataCompanyIprPatentFamilyGateway {

    private IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService;

    @Override
    public DataCompanyIprPatentFamily getById(DataCompanyIprPatentFamilyId dataCompanyIprPatentFamilyId) {
        DataCompanyIprPatentFamilyDO byId = iDataCompanyIprPatentFamilyService.getById(dataCompanyIprPatentFamilyId.getId());
        DataCompanyIprPatentFamily dataCompanyIprPatentFamily = DomainFactory.create(DataCompanyIprPatentFamily.class);
        dataCompanyIprPatentFamily = DataCompanyIprPatentFamilyInfrastructureStructMapping.instance. dataCompanyIprPatentFamilyDOToDataCompanyIprPatentFamily(dataCompanyIprPatentFamily,byId);
        return dataCompanyIprPatentFamily;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentFamily dataCompanyIprPatentFamily) {
        DataCompanyIprPatentFamilyDO dataCompanyIprPatentFamilyDO = DataCompanyIprPatentFamilyInfrastructureStructMapping.instance.dataCompanyIprPatentFamilyToDataCompanyIprPatentFamilyDO(dataCompanyIprPatentFamily);
        if (dataCompanyIprPatentFamilyDO.getId() == null) {
            dataCompanyIprPatentFamilyDO.setAddControl(dataCompanyIprPatentFamily.getAddControl());
            DataCompanyIprPatentFamilyDO add = iDataCompanyIprPatentFamilyService.add(dataCompanyIprPatentFamilyDO);
            dataCompanyIprPatentFamily.setId(DataCompanyIprPatentFamilyId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentFamilyDO.setUpdateControl(dataCompanyIprPatentFamily.getUpdateControl());
        DataCompanyIprPatentFamilyDO update = iDataCompanyIprPatentFamilyService.update(dataCompanyIprPatentFamilyDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentFamilyId dataCompanyIprPatentFamilyId) {
        return iDataCompanyIprPatentFamilyService.deleteById(dataCompanyIprPatentFamilyId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentFamilyId dataCompanyIprPatentFamilyId, IdCommand idCommand) {
        return iDataCompanyIprPatentFamilyService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentFamilyService(IDataCompanyIprPatentFamilyService iDataCompanyIprPatentFamilyService) {
        this.iDataCompanyIprPatentFamilyService = iDataCompanyIprPatentFamilyService;
    }
}
