package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyPunishment;
import com.particle.data.domain.company.DataCompanyPunishmentId;
import com.particle.data.domain.company.gateway.DataCompanyPunishmentGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyPunishmentService;
import com.particle.data.infrastructure.company.dos.DataCompanyPunishmentDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyPunishmentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业行政处罚 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Component
public class DataCompanyPunishmentGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyPunishmentId,DataCompanyPunishment> implements DataCompanyPunishmentGateway {

    private IDataCompanyPunishmentService iDataCompanyPunishmentService;

    @Override
    public DataCompanyPunishment getById(DataCompanyPunishmentId dataCompanyPunishmentId) {
        DataCompanyPunishmentDO byId = iDataCompanyPunishmentService.getById(dataCompanyPunishmentId.getId());
        DataCompanyPunishment dataCompanyPunishment = DomainFactory.create(DataCompanyPunishment.class);
        dataCompanyPunishment = DataCompanyPunishmentInfrastructureStructMapping.instance. dataCompanyPunishmentDOToDataCompanyPunishment(dataCompanyPunishment,byId);
        return dataCompanyPunishment;
    }

    @Override
    public boolean doSave(DataCompanyPunishment dataCompanyPunishment) {
        DataCompanyPunishmentDO dataCompanyPunishmentDO = DataCompanyPunishmentInfrastructureStructMapping.instance.dataCompanyPunishmentToDataCompanyPunishmentDO(dataCompanyPunishment);
        if (dataCompanyPunishmentDO.getId() == null) {
            dataCompanyPunishmentDO.setAddControl(dataCompanyPunishment.getAddControl());
            DataCompanyPunishmentDO add = iDataCompanyPunishmentService.add(dataCompanyPunishmentDO);
            dataCompanyPunishment.setId(DataCompanyPunishmentId.of(add.getId()));
            return add != null;
        }
        dataCompanyPunishmentDO.setUpdateControl(dataCompanyPunishment.getUpdateControl());
        DataCompanyPunishmentDO update = iDataCompanyPunishmentService.update(dataCompanyPunishmentDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyPunishmentId dataCompanyPunishmentId) {
        return iDataCompanyPunishmentService.deleteById(dataCompanyPunishmentId.getId());
    }

    @Override
    public boolean delete(DataCompanyPunishmentId dataCompanyPunishmentId, IdCommand idCommand) {
        return iDataCompanyPunishmentService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyPunishmentService(IDataCompanyPunishmentService iDataCompanyPunishmentService) {
        this.iDataCompanyPunishmentService = iDataCompanyPunishmentService;
    }
}
