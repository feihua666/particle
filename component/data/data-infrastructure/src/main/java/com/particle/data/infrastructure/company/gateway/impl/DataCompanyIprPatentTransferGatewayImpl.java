package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyIprPatentTransfer;
import com.particle.data.domain.company.DataCompanyIprPatentTransferId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentTransferGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentTransferService;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentTransferDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyIprPatentTransferInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业知识产权专利转让信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Component
public class DataCompanyIprPatentTransferGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyIprPatentTransferId,DataCompanyIprPatentTransfer> implements DataCompanyIprPatentTransferGateway {

    private IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService;

    @Override
    public DataCompanyIprPatentTransfer getById(DataCompanyIprPatentTransferId dataCompanyIprPatentTransferId) {
        DataCompanyIprPatentTransferDO byId = iDataCompanyIprPatentTransferService.getById(dataCompanyIprPatentTransferId.getId());
        DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer = DomainFactory.create(DataCompanyIprPatentTransfer.class);
        dataCompanyIprPatentTransfer = DataCompanyIprPatentTransferInfrastructureStructMapping.instance. dataCompanyIprPatentTransferDOToDataCompanyIprPatentTransfer(dataCompanyIprPatentTransfer,byId);
        return dataCompanyIprPatentTransfer;
    }

    @Override
    public boolean doSave(DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer) {
        DataCompanyIprPatentTransferDO dataCompanyIprPatentTransferDO = DataCompanyIprPatentTransferInfrastructureStructMapping.instance.dataCompanyIprPatentTransferToDataCompanyIprPatentTransferDO(dataCompanyIprPatentTransfer);
        if (dataCompanyIprPatentTransferDO.getId() == null) {
            dataCompanyIprPatentTransferDO.setAddControl(dataCompanyIprPatentTransfer.getAddControl());
            DataCompanyIprPatentTransferDO add = iDataCompanyIprPatentTransferService.add(dataCompanyIprPatentTransferDO);
            dataCompanyIprPatentTransfer.setId(DataCompanyIprPatentTransferId.of(add.getId()));
            return add != null;
        }
        dataCompanyIprPatentTransferDO.setUpdateControl(dataCompanyIprPatentTransfer.getUpdateControl());
        DataCompanyIprPatentTransferDO update = iDataCompanyIprPatentTransferService.update(dataCompanyIprPatentTransferDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyIprPatentTransferId dataCompanyIprPatentTransferId) {
        return iDataCompanyIprPatentTransferService.deleteById(dataCompanyIprPatentTransferId.getId());
    }

    @Override
    public boolean delete(DataCompanyIprPatentTransferId dataCompanyIprPatentTransferId, IdCommand idCommand) {
        return iDataCompanyIprPatentTransferService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyIprPatentTransferService(IDataCompanyIprPatentTransferService iDataCompanyIprPatentTransferService) {
        this.iDataCompanyIprPatentTransferService = iDataCompanyIprPatentTransferService;
    }
}
