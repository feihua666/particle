package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAnnualReportShareholder;
import com.particle.data.domain.company.DataCompanyAnnualReportShareholderId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportShareholderGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportShareholderService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportShareholderDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAnnualReportShareholderInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业年报股东 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Component
public class DataCompanyAnnualReportShareholderGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAnnualReportShareholderId,DataCompanyAnnualReportShareholder> implements DataCompanyAnnualReportShareholderGateway {

    private IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService;

    @Override
    public DataCompanyAnnualReportShareholder getById(DataCompanyAnnualReportShareholderId dataCompanyAnnualReportShareholderId) {
        DataCompanyAnnualReportShareholderDO byId = iDataCompanyAnnualReportShareholderService.getById(dataCompanyAnnualReportShareholderId.getId());
        DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder = DomainFactory.create(DataCompanyAnnualReportShareholder.class);
        dataCompanyAnnualReportShareholder = DataCompanyAnnualReportShareholderInfrastructureStructMapping.instance. dataCompanyAnnualReportShareholderDOToDataCompanyAnnualReportShareholder(dataCompanyAnnualReportShareholder,byId);
        return dataCompanyAnnualReportShareholder;
    }

    @Override
    public boolean doSave(DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder) {
        DataCompanyAnnualReportShareholderDO dataCompanyAnnualReportShareholderDO = DataCompanyAnnualReportShareholderInfrastructureStructMapping.instance.dataCompanyAnnualReportShareholderToDataCompanyAnnualReportShareholderDO(dataCompanyAnnualReportShareholder);
        if (dataCompanyAnnualReportShareholderDO.getId() == null) {
            dataCompanyAnnualReportShareholderDO.setAddControl(dataCompanyAnnualReportShareholder.getAddControl());
            DataCompanyAnnualReportShareholderDO add = iDataCompanyAnnualReportShareholderService.add(dataCompanyAnnualReportShareholderDO);
            dataCompanyAnnualReportShareholder.setId(DataCompanyAnnualReportShareholderId.of(add.getId()));
            return add != null;
        }
        dataCompanyAnnualReportShareholderDO.setUpdateControl(dataCompanyAnnualReportShareholder.getUpdateControl());
        DataCompanyAnnualReportShareholderDO update = iDataCompanyAnnualReportShareholderService.update(dataCompanyAnnualReportShareholderDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAnnualReportShareholderId dataCompanyAnnualReportShareholderId) {
        return iDataCompanyAnnualReportShareholderService.deleteById(dataCompanyAnnualReportShareholderId.getId());
    }

    @Override
    public boolean delete(DataCompanyAnnualReportShareholderId dataCompanyAnnualReportShareholderId, IdCommand idCommand) {
        return iDataCompanyAnnualReportShareholderService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAnnualReportShareholderService(IDataCompanyAnnualReportShareholderService iDataCompanyAnnualReportShareholderService) {
        this.iDataCompanyAnnualReportShareholderService = iDataCompanyAnnualReportShareholderService;
    }
}
