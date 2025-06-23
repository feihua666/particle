package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprTrademark;
import com.particle.data.domain.company.DataCompanyIprTrademarkId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprTrademarkInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权商标 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Component
public class DataCompanyIprTrademarkGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprTrademarkId,DataCompanyIprTrademark> implements DataCompanyIprTrademarkGateway {

    private IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService;

    @Override
    public DataCompanyIprTrademark getById(DataCompanyIprTrademarkId dataCompanyIprTrademarkId) {
        DataCompanyIprTrademarkDO byId = iDataCompanyIprTrademarkService.getById(dataCompanyIprTrademarkId.getId());
        DataCompanyIprTrademark dataCompanyIprTrademark = DomainFactory.create(DataCompanyIprTrademark.class);
        dataCompanyIprTrademark = DataCompanyIprTrademarkInfrastructureStructMapping.instance. dataCompanyIprTrademarkDOToDataCompanyIprTrademark(dataCompanyIprTrademark,byId);
        return dataCompanyIprTrademark;
    }

    @Override
    public boolean doSave(DataCompanyIprTrademark dataCompanyIprTrademark) {
        DataCompanyIprTrademarkDO dataCompanyIprTrademarkDO = DataCompanyIprTrademarkInfrastructureStructMapping.instance.dataCompanyIprTrademarkToDataCompanyIprTrademarkDO(dataCompanyIprTrademark);
        if (dataCompanyIprTrademarkDO.getId() == null) {
            dataCompanyIprTrademarkDO.setAddControl(dataCompanyIprTrademark.getAddControl());
            DataCompanyIprTrademarkDO add = iDataCompanyIprTrademarkService.add(dataCompanyIprTrademarkDO);
            dataCompanyIprTrademark.setId(DataCompanyIprTrademarkId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprTrademarkDO.setUpdateControl(dataCompanyIprTrademark.getUpdateControl());
        DataCompanyIprTrademarkDO update = iDataCompanyIprTrademarkService.update(dataCompanyIprTrademarkDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkId dataCompanyIprTrademarkId) {
        return iDataCompanyIprTrademarkService.deleteById(dataCompanyIprTrademarkId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkId dataCompanyIprTrademarkId, IdCommand idCommand) {
        return iDataCompanyIprTrademarkService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprTrademarkService(IDataCompanyIprTrademarkService iDataCompanyIprTrademarkService) {
        this.iDataCompanyIprTrademarkService = iDataCompanyIprTrademarkService;
    }
}
