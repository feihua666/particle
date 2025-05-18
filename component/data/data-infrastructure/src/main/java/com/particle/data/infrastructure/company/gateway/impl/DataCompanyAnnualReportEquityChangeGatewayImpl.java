package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAnnualReportEquityChange;
import com.particle.data.domain.company.DataCompanyAnnualReportEquityChangeId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportEquityChangeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportEquityChangeService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportEquityChangeDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAnnualReportEquityChangeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业年报股权变更 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Component
public class DataCompanyAnnualReportEquityChangeGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAnnualReportEquityChangeId,DataCompanyAnnualReportEquityChange> implements DataCompanyAnnualReportEquityChangeGateway {

    private IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService;

    @Override
    public DataCompanyAnnualReportEquityChange getById(DataCompanyAnnualReportEquityChangeId dataCompanyAnnualReportEquityChangeId) {
        DataCompanyAnnualReportEquityChangeDO byId = iDataCompanyAnnualReportEquityChangeService.getById(dataCompanyAnnualReportEquityChangeId.getId());
        DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange = DomainFactory.create(DataCompanyAnnualReportEquityChange.class);
        dataCompanyAnnualReportEquityChange = DataCompanyAnnualReportEquityChangeInfrastructureStructMapping.instance. dataCompanyAnnualReportEquityChangeDOToDataCompanyAnnualReportEquityChange(dataCompanyAnnualReportEquityChange,byId);
        return dataCompanyAnnualReportEquityChange;
    }

    @Override
    public boolean doSave(DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange) {
        DataCompanyAnnualReportEquityChangeDO dataCompanyAnnualReportEquityChangeDO = DataCompanyAnnualReportEquityChangeInfrastructureStructMapping.instance.dataCompanyAnnualReportEquityChangeToDataCompanyAnnualReportEquityChangeDO(dataCompanyAnnualReportEquityChange);
        if (dataCompanyAnnualReportEquityChangeDO.getId() == null) {
            dataCompanyAnnualReportEquityChangeDO.setAddControl(dataCompanyAnnualReportEquityChange.getAddControl());
            DataCompanyAnnualReportEquityChangeDO add = iDataCompanyAnnualReportEquityChangeService.add(dataCompanyAnnualReportEquityChangeDO);
            dataCompanyAnnualReportEquityChange.setId(DataCompanyAnnualReportEquityChangeId.of(add.getId()));
            return add != null;
        }
        dataCompanyAnnualReportEquityChangeDO.setUpdateControl(dataCompanyAnnualReportEquityChange.getUpdateControl());
        DataCompanyAnnualReportEquityChangeDO update = iDataCompanyAnnualReportEquityChangeService.update(dataCompanyAnnualReportEquityChangeDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAnnualReportEquityChangeId dataCompanyAnnualReportEquityChangeId) {
        return iDataCompanyAnnualReportEquityChangeService.deleteById(dataCompanyAnnualReportEquityChangeId.getId());
    }

    @Override
    public boolean delete(DataCompanyAnnualReportEquityChangeId dataCompanyAnnualReportEquityChangeId, IdCommand idCommand) {
        return iDataCompanyAnnualReportEquityChangeService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAnnualReportEquityChangeService(IDataCompanyAnnualReportEquityChangeService iDataCompanyAnnualReportEquityChangeService) {
        this.iDataCompanyAnnualReportEquityChangeService = iDataCompanyAnnualReportEquityChangeService;
    }
}
