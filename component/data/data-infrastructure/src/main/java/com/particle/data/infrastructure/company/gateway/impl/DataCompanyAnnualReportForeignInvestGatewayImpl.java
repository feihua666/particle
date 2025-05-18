package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvest;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvestId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportForeignInvestGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignInvestService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignInvestDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAnnualReportForeignInvestInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业年报对外投资 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Component
public class DataCompanyAnnualReportForeignInvestGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAnnualReportForeignInvestId,DataCompanyAnnualReportForeignInvest> implements DataCompanyAnnualReportForeignInvestGateway {

    private IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService;

    @Override
    public DataCompanyAnnualReportForeignInvest getById(DataCompanyAnnualReportForeignInvestId dataCompanyAnnualReportForeignInvestId) {
        DataCompanyAnnualReportForeignInvestDO byId = iDataCompanyAnnualReportForeignInvestService.getById(dataCompanyAnnualReportForeignInvestId.getId());
        DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest = DomainFactory.create(DataCompanyAnnualReportForeignInvest.class);
        dataCompanyAnnualReportForeignInvest = DataCompanyAnnualReportForeignInvestInfrastructureStructMapping.instance. dataCompanyAnnualReportForeignInvestDOToDataCompanyAnnualReportForeignInvest(dataCompanyAnnualReportForeignInvest,byId);
        return dataCompanyAnnualReportForeignInvest;
    }

    @Override
    public boolean doSave(DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest) {
        DataCompanyAnnualReportForeignInvestDO dataCompanyAnnualReportForeignInvestDO = DataCompanyAnnualReportForeignInvestInfrastructureStructMapping.instance.dataCompanyAnnualReportForeignInvestToDataCompanyAnnualReportForeignInvestDO(dataCompanyAnnualReportForeignInvest);
        if (dataCompanyAnnualReportForeignInvestDO.getId() == null) {
            dataCompanyAnnualReportForeignInvestDO.setAddControl(dataCompanyAnnualReportForeignInvest.getAddControl());
            DataCompanyAnnualReportForeignInvestDO add = iDataCompanyAnnualReportForeignInvestService.add(dataCompanyAnnualReportForeignInvestDO);
            dataCompanyAnnualReportForeignInvest.setId(DataCompanyAnnualReportForeignInvestId.of(add.getId()));
            return add != null;
        }
        dataCompanyAnnualReportForeignInvestDO.setUpdateControl(dataCompanyAnnualReportForeignInvest.getUpdateControl());
        DataCompanyAnnualReportForeignInvestDO update = iDataCompanyAnnualReportForeignInvestService.update(dataCompanyAnnualReportForeignInvestDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAnnualReportForeignInvestId dataCompanyAnnualReportForeignInvestId) {
        return iDataCompanyAnnualReportForeignInvestService.deleteById(dataCompanyAnnualReportForeignInvestId.getId());
    }

    @Override
    public boolean delete(DataCompanyAnnualReportForeignInvestId dataCompanyAnnualReportForeignInvestId, IdCommand idCommand) {
        return iDataCompanyAnnualReportForeignInvestService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAnnualReportForeignInvestService(IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService) {
        this.iDataCompanyAnnualReportForeignInvestService = iDataCompanyAnnualReportForeignInvestService;
    }
}
