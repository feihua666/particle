package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAnnualReportChange;
import com.particle.data.domain.company.DataCompanyAnnualReportChangeId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportChangeGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportChangeService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportChangeDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAnnualReportChangeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业年报变更 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Component
public class DataCompanyAnnualReportChangeGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAnnualReportChangeId,DataCompanyAnnualReportChange> implements DataCompanyAnnualReportChangeGateway {

    private IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService;

    @Override
    public DataCompanyAnnualReportChange getById(DataCompanyAnnualReportChangeId dataCompanyAnnualReportChangeId) {
        DataCompanyAnnualReportChangeDO byId = iDataCompanyAnnualReportChangeService.getById(dataCompanyAnnualReportChangeId.getId());
        DataCompanyAnnualReportChange dataCompanyAnnualReportChange = DomainFactory.create(DataCompanyAnnualReportChange.class);
        dataCompanyAnnualReportChange = DataCompanyAnnualReportChangeInfrastructureStructMapping.instance. dataCompanyAnnualReportChangeDOToDataCompanyAnnualReportChange(dataCompanyAnnualReportChange,byId);
        return dataCompanyAnnualReportChange;
    }

    @Override
    public boolean doSave(DataCompanyAnnualReportChange dataCompanyAnnualReportChange) {
        DataCompanyAnnualReportChangeDO dataCompanyAnnualReportChangeDO = DataCompanyAnnualReportChangeInfrastructureStructMapping.instance.dataCompanyAnnualReportChangeToDataCompanyAnnualReportChangeDO(dataCompanyAnnualReportChange);
        if (dataCompanyAnnualReportChangeDO.getId() == null) {
            dataCompanyAnnualReportChangeDO.setAddControl(dataCompanyAnnualReportChange.getAddControl());
            DataCompanyAnnualReportChangeDO add = iDataCompanyAnnualReportChangeService.add(dataCompanyAnnualReportChangeDO);
            dataCompanyAnnualReportChange.setId(DataCompanyAnnualReportChangeId.of(add.getId()));
            return add != null;
        }
        dataCompanyAnnualReportChangeDO.setUpdateControl(dataCompanyAnnualReportChange.getUpdateControl());
        DataCompanyAnnualReportChangeDO update = iDataCompanyAnnualReportChangeService.update(dataCompanyAnnualReportChangeDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAnnualReportChangeId dataCompanyAnnualReportChangeId) {
        return iDataCompanyAnnualReportChangeService.deleteById(dataCompanyAnnualReportChangeId.getId());
    }

    @Override
    public boolean delete(DataCompanyAnnualReportChangeId dataCompanyAnnualReportChangeId, IdCommand idCommand) {
        return iDataCompanyAnnualReportChangeService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAnnualReportChangeService(IDataCompanyAnnualReportChangeService iDataCompanyAnnualReportChangeService) {
        this.iDataCompanyAnnualReportChangeService = iDataCompanyAnnualReportChangeService;
    }
}
