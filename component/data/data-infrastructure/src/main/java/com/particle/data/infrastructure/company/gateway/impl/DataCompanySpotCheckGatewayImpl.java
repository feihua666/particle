package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanySpotCheck;
import com.particle.data.domain.company.DataCompanySpotCheckId;
import com.particle.data.domain.company.gateway.DataCompanySpotCheckGateway;
import com.particle.data.infrastructure.company.service.IDataCompanySpotCheckService;
import com.particle.data.infrastructure.company.dos.DataCompanySpotCheckDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanySpotCheckInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业抽查检查 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Component
public class DataCompanySpotCheckGatewayImpl extends AbstractBaseGatewayImpl<DataCompanySpotCheckId,DataCompanySpotCheck> implements DataCompanySpotCheckGateway {

    private IDataCompanySpotCheckService iDataCompanySpotCheckService;

    @Override
    public DataCompanySpotCheck getById(DataCompanySpotCheckId dataCompanySpotCheckId) {
        DataCompanySpotCheckDO byId = iDataCompanySpotCheckService.getById(dataCompanySpotCheckId.getId());
        DataCompanySpotCheck dataCompanySpotCheck = DomainFactory.create(DataCompanySpotCheck.class);
        dataCompanySpotCheck = DataCompanySpotCheckInfrastructureStructMapping.instance. dataCompanySpotCheckDOToDataCompanySpotCheck(dataCompanySpotCheck,byId);
        return dataCompanySpotCheck;
    }

    @Override
    public boolean doSave(DataCompanySpotCheck dataCompanySpotCheck) {
        DataCompanySpotCheckDO dataCompanySpotCheckDO = DataCompanySpotCheckInfrastructureStructMapping.instance.dataCompanySpotCheckToDataCompanySpotCheckDO(dataCompanySpotCheck);
        if (dataCompanySpotCheckDO.getId() == null) {
            dataCompanySpotCheckDO.setAddControl(dataCompanySpotCheck.getAddControl());
            DataCompanySpotCheckDO add = iDataCompanySpotCheckService.add(dataCompanySpotCheckDO);
            dataCompanySpotCheck.setId(DataCompanySpotCheckId.of(add.getId()));
            return add != null;
        }
        dataCompanySpotCheckDO.setUpdateControl(dataCompanySpotCheck.getUpdateControl());
        DataCompanySpotCheckDO update = iDataCompanySpotCheckService.update(dataCompanySpotCheckDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanySpotCheckId dataCompanySpotCheckId) {
        return iDataCompanySpotCheckService.deleteById(dataCompanySpotCheckId.getId());
    }

    @Override
    public boolean delete(DataCompanySpotCheckId dataCompanySpotCheckId, IdCommand idCommand) {
        return iDataCompanySpotCheckService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanySpotCheckService(IDataCompanySpotCheckService iDataCompanySpotCheckService) {
        this.iDataCompanySpotCheckService = iDataCompanySpotCheckService;
    }
}
