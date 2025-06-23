package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprTrademarkTransfer;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkTransferGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprTrademarkTransferService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprTrademarkTransferInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权商标转让信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Component
public class DataCompanyIprTrademarkTransferGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprTrademarkTransferId,DataCompanyIprTrademarkTransfer> implements DataCompanyIprTrademarkTransferGateway {

    private IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService;

    @Override
    public DataCompanyIprTrademarkTransfer getById(DataCompanyIprTrademarkTransferId dataCompanyIprTrademarkTransferId) {
        DataCompanyIprTrademarkTransferDO byId = iDataCompanyIprTrademarkTransferService.getById(dataCompanyIprTrademarkTransferId.getId());
        DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer = DomainFactory.create(DataCompanyIprTrademarkTransfer.class);
        dataCompanyIprTrademarkTransfer = DataCompanyIprTrademarkTransferInfrastructureStructMapping.instance. dataCompanyIprTrademarkTransferDOToDataCompanyIprTrademarkTransfer(dataCompanyIprTrademarkTransfer,byId);
        return dataCompanyIprTrademarkTransfer;
    }

    @Override
    public boolean doSave(DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer) {
        DataCompanyIprTrademarkTransferDO dataCompanyIprTrademarkTransferDO = DataCompanyIprTrademarkTransferInfrastructureStructMapping.instance.dataCompanyIprTrademarkTransferToDataCompanyIprTrademarkTransferDO(dataCompanyIprTrademarkTransfer);
        if (dataCompanyIprTrademarkTransferDO.getId() == null) {
            dataCompanyIprTrademarkTransferDO.setAddControl(dataCompanyIprTrademarkTransfer.getAddControl());
            DataCompanyIprTrademarkTransferDO add = iDataCompanyIprTrademarkTransferService.add(dataCompanyIprTrademarkTransferDO);
            dataCompanyIprTrademarkTransfer.setId(DataCompanyIprTrademarkTransferId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprTrademarkTransferDO.setUpdateControl(dataCompanyIprTrademarkTransfer.getUpdateControl());
        DataCompanyIprTrademarkTransferDO update = iDataCompanyIprTrademarkTransferService.update(dataCompanyIprTrademarkTransferDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkTransferId dataCompanyIprTrademarkTransferId) {
        return iDataCompanyIprTrademarkTransferService.deleteById(dataCompanyIprTrademarkTransferId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprTrademarkTransferId dataCompanyIprTrademarkTransferId, IdCommand idCommand) {
        return iDataCompanyIprTrademarkTransferService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprTrademarkTransferService(IDataCompanyIprTrademarkTransferService iDataCompanyIprTrademarkTransferService) {
        this.iDataCompanyIprTrademarkTransferService = iDataCompanyIprTrademarkTransferService;
    }
}
