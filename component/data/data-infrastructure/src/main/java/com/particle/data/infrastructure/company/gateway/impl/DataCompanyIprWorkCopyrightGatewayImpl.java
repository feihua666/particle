package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprWorkCopyright;
import com.particle.data.domain.company.DataCompanyIprWorkCopyrightId;
import com.particle.data.domain.company.gateway.DataCompanyIprWorkCopyrightGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprWorkCopyrightService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprWorkCopyrightDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprWorkCopyrightInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权作品著作 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Component
public class DataCompanyIprWorkCopyrightGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprWorkCopyrightId,DataCompanyIprWorkCopyright> implements DataCompanyIprWorkCopyrightGateway {

    private IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService;

    @Override
    public DataCompanyIprWorkCopyright getById(DataCompanyIprWorkCopyrightId dataCompanyIprWorkCopyrightId) {
        DataCompanyIprWorkCopyrightDO byId = iDataCompanyIprWorkCopyrightService.getById(dataCompanyIprWorkCopyrightId.getId());
        DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright = DomainFactory.create(DataCompanyIprWorkCopyright.class);
        dataCompanyIprWorkCopyright = DataCompanyIprWorkCopyrightInfrastructureStructMapping.instance. dataCompanyIprWorkCopyrightDOToDataCompanyIprWorkCopyright(dataCompanyIprWorkCopyright,byId);
        return dataCompanyIprWorkCopyright;
    }

    @Override
    public boolean doSave(DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright) {
        DataCompanyIprWorkCopyrightDO dataCompanyIprWorkCopyrightDO = DataCompanyIprWorkCopyrightInfrastructureStructMapping.instance.dataCompanyIprWorkCopyrightToDataCompanyIprWorkCopyrightDO(dataCompanyIprWorkCopyright);
        if (dataCompanyIprWorkCopyrightDO.getId() == null) {
            dataCompanyIprWorkCopyrightDO.setAddControl(dataCompanyIprWorkCopyright.getAddControl());
            DataCompanyIprWorkCopyrightDO add = iDataCompanyIprWorkCopyrightService.add(dataCompanyIprWorkCopyrightDO);
            dataCompanyIprWorkCopyright.setId(DataCompanyIprWorkCopyrightId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprWorkCopyrightDO.setUpdateControl(dataCompanyIprWorkCopyright.getUpdateControl());
        DataCompanyIprWorkCopyrightDO update = iDataCompanyIprWorkCopyrightService.update(dataCompanyIprWorkCopyrightDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprWorkCopyrightId dataCompanyIprWorkCopyrightId) {
        return iDataCompanyIprWorkCopyrightService.deleteById(dataCompanyIprWorkCopyrightId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprWorkCopyrightId dataCompanyIprWorkCopyrightId, IdCommand idCommand) {
        return iDataCompanyIprWorkCopyrightService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprWorkCopyrightService(IDataCompanyIprWorkCopyrightService iDataCompanyIprWorkCopyrightService) {
        this.iDataCompanyIprWorkCopyrightService = iDataCompanyIprWorkCopyrightService;
    }
}
