package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentLegalStatus;
import com.particle.data.domain.company.DataCompanyIprPatentLegalStatusId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentLegalStatusGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentLegalStatusService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentLegalStatusDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentLegalStatusInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利法律状态 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Component
public class DataCompanyIprPatentLegalStatusGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentLegalStatusId,DataCompanyIprPatentLegalStatus> implements DataCompanyIprPatentLegalStatusGateway {

    private IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService;

    @Override
    public DataCompanyIprPatentLegalStatus getById(DataCompanyIprPatentLegalStatusId dataCompanyIprPatentLegalStatusId) {
        DataCompanyIprPatentLegalStatusDO byId = iDataCompanyIprPatentLegalStatusService.getById(dataCompanyIprPatentLegalStatusId.getId());
        DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus = DomainFactory.create(DataCompanyIprPatentLegalStatus.class);
        dataCompanyIprPatentLegalStatus = DataCompanyIprPatentLegalStatusInfrastructureStructMapping.instance. dataCompanyIprPatentLegalStatusDOToDataCompanyIprPatentLegalStatus(dataCompanyIprPatentLegalStatus,byId);
        return dataCompanyIprPatentLegalStatus;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentLegalStatus dataCompanyIprPatentLegalStatus) {
        DataCompanyIprPatentLegalStatusDO dataCompanyIprPatentLegalStatusDO = DataCompanyIprPatentLegalStatusInfrastructureStructMapping.instance.dataCompanyIprPatentLegalStatusToDataCompanyIprPatentLegalStatusDO(dataCompanyIprPatentLegalStatus);
        if (dataCompanyIprPatentLegalStatusDO.getId() == null) {
            dataCompanyIprPatentLegalStatusDO.setAddControl(dataCompanyIprPatentLegalStatus.getAddControl());
            DataCompanyIprPatentLegalStatusDO add = iDataCompanyIprPatentLegalStatusService.add(dataCompanyIprPatentLegalStatusDO);
            dataCompanyIprPatentLegalStatus.setId(DataCompanyIprPatentLegalStatusId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentLegalStatusDO.setUpdateControl(dataCompanyIprPatentLegalStatus.getUpdateControl());
        DataCompanyIprPatentLegalStatusDO update = iDataCompanyIprPatentLegalStatusService.update(dataCompanyIprPatentLegalStatusDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentLegalStatusId dataCompanyIprPatentLegalStatusId) {
        return iDataCompanyIprPatentLegalStatusService.deleteById(dataCompanyIprPatentLegalStatusId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentLegalStatusId dataCompanyIprPatentLegalStatusId, IdCommand idCommand) {
        return iDataCompanyIprPatentLegalStatusService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentLegalStatusService(IDataCompanyIprPatentLegalStatusService iDataCompanyIprPatentLegalStatusService) {
        this.iDataCompanyIprPatentLegalStatusService = iDataCompanyIprPatentLegalStatusService;
    }
}
