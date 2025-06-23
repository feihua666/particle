package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprSoftwareCopyright;
import com.particle.data.domain.company.DataCompanyIprSoftwareCopyrightId;
import com.particle.data.domain.company.gateway.DataCompanyIprSoftwareCopyrightGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprSoftwareCopyrightService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprSoftwareCopyrightDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprSoftwareCopyrightInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权软件著作 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Component
public class DataCompanyIprSoftwareCopyrightGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprSoftwareCopyrightId,DataCompanyIprSoftwareCopyright> implements DataCompanyIprSoftwareCopyrightGateway {

    private IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService;

    @Override
    public DataCompanyIprSoftwareCopyright getById(DataCompanyIprSoftwareCopyrightId dataCompanyIprSoftwareCopyrightId) {
        DataCompanyIprSoftwareCopyrightDO byId = iDataCompanyIprSoftwareCopyrightService.getById(dataCompanyIprSoftwareCopyrightId.getId());
        DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright = DomainFactory.create(DataCompanyIprSoftwareCopyright.class);
        dataCompanyIprSoftwareCopyright = DataCompanyIprSoftwareCopyrightInfrastructureStructMapping.instance. dataCompanyIprSoftwareCopyrightDOToDataCompanyIprSoftwareCopyright(dataCompanyIprSoftwareCopyright,byId);
        return dataCompanyIprSoftwareCopyright;
    }

    @Override
    public boolean doSave(DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright) {
        DataCompanyIprSoftwareCopyrightDO dataCompanyIprSoftwareCopyrightDO = DataCompanyIprSoftwareCopyrightInfrastructureStructMapping.instance.dataCompanyIprSoftwareCopyrightToDataCompanyIprSoftwareCopyrightDO(dataCompanyIprSoftwareCopyright);
        if (dataCompanyIprSoftwareCopyrightDO.getId() == null) {
            dataCompanyIprSoftwareCopyrightDO.setAddControl(dataCompanyIprSoftwareCopyright.getAddControl());
            DataCompanyIprSoftwareCopyrightDO add = iDataCompanyIprSoftwareCopyrightService.add(dataCompanyIprSoftwareCopyrightDO);
            dataCompanyIprSoftwareCopyright.setId(DataCompanyIprSoftwareCopyrightId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprSoftwareCopyrightDO.setUpdateControl(dataCompanyIprSoftwareCopyright.getUpdateControl());
        DataCompanyIprSoftwareCopyrightDO update = iDataCompanyIprSoftwareCopyrightService.update(dataCompanyIprSoftwareCopyrightDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprSoftwareCopyrightId dataCompanyIprSoftwareCopyrightId) {
        return iDataCompanyIprSoftwareCopyrightService.deleteById(dataCompanyIprSoftwareCopyrightId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprSoftwareCopyrightId dataCompanyIprSoftwareCopyrightId, IdCommand idCommand) {
        return iDataCompanyIprSoftwareCopyrightService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprSoftwareCopyrightService(IDataCompanyIprSoftwareCopyrightService iDataCompanyIprSoftwareCopyrightService) {
        this.iDataCompanyIprSoftwareCopyrightService = iDataCompanyIprSoftwareCopyrightService;
    }
}
