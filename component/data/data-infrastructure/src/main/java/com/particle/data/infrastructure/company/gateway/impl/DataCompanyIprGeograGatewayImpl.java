package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprGeogra;
import com.particle.data.domain.company.DataCompanyIprGeograId;
import com.particle.data.domain.company.gateway.DataCompanyIprGeograGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprGeograInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权地理标识 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Component
public class DataCompanyIprGeograGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprGeograId,DataCompanyIprGeogra> implements DataCompanyIprGeograGateway {

    private IDataCompanyIprGeograService iDataCompanyIprGeograService;

    @Override
    public DataCompanyIprGeogra getById(DataCompanyIprGeograId dataCompanyIprGeograId) {
        DataCompanyIprGeograDO byId = iDataCompanyIprGeograService.getById(dataCompanyIprGeograId.getId());
        DataCompanyIprGeogra dataCompanyIprGeogra = DomainFactory.create(DataCompanyIprGeogra.class);
        dataCompanyIprGeogra = DataCompanyIprGeograInfrastructureStructMapping.instance. dataCompanyIprGeograDOToDataCompanyIprGeogra(dataCompanyIprGeogra,byId);
        return dataCompanyIprGeogra;
    }

    @Override
    public boolean doSave(DataCompanyIprGeogra dataCompanyIprGeogra) {
        DataCompanyIprGeograDO dataCompanyIprGeograDO = DataCompanyIprGeograInfrastructureStructMapping.instance.dataCompanyIprGeograToDataCompanyIprGeograDO(dataCompanyIprGeogra);
        if (dataCompanyIprGeograDO.getId() == null) {
            dataCompanyIprGeograDO.setAddControl(dataCompanyIprGeogra.getAddControl());
            DataCompanyIprGeograDO add = iDataCompanyIprGeograService.add(dataCompanyIprGeograDO);
            dataCompanyIprGeogra.setId(DataCompanyIprGeograId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprGeograDO.setUpdateControl(dataCompanyIprGeogra.getUpdateControl());
        DataCompanyIprGeograDO update = iDataCompanyIprGeograService.update(dataCompanyIprGeograDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprGeograId dataCompanyIprGeograId) {
        return iDataCompanyIprGeograService.deleteById(dataCompanyIprGeograId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprGeograId dataCompanyIprGeograId, IdCommand idCommand) {
        return iDataCompanyIprGeograService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprGeograService(IDataCompanyIprGeograService iDataCompanyIprGeograService) {
        this.iDataCompanyIprGeograService = iDataCompanyIprGeograService;
    }
}
