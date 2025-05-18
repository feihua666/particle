package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAnnualReport;
import com.particle.data.domain.company.DataCompanyAnnualReportId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAnnualReportInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业年报 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Component
public class DataCompanyAnnualReportGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAnnualReportId,DataCompanyAnnualReport> implements DataCompanyAnnualReportGateway {

    private IDataCompanyAnnualReportService iDataCompanyAnnualReportService;

    @Override
    public DataCompanyAnnualReport getById(DataCompanyAnnualReportId dataCompanyAnnualReportId) {
        DataCompanyAnnualReportDO byId = iDataCompanyAnnualReportService.getById(dataCompanyAnnualReportId.getId());
        DataCompanyAnnualReport dataCompanyAnnualReport = DomainFactory.create(DataCompanyAnnualReport.class);
        dataCompanyAnnualReport = DataCompanyAnnualReportInfrastructureStructMapping.instance. dataCompanyAnnualReportDOToDataCompanyAnnualReport(dataCompanyAnnualReport,byId);
        return dataCompanyAnnualReport;
    }

    @Override
    public boolean doSave(DataCompanyAnnualReport dataCompanyAnnualReport) {
        DataCompanyAnnualReportDO dataCompanyAnnualReportDO = DataCompanyAnnualReportInfrastructureStructMapping.instance.dataCompanyAnnualReportToDataCompanyAnnualReportDO(dataCompanyAnnualReport);
        if (dataCompanyAnnualReportDO.getId() == null) {
            dataCompanyAnnualReportDO.setAddControl(dataCompanyAnnualReport.getAddControl());
            DataCompanyAnnualReportDO add = iDataCompanyAnnualReportService.add(dataCompanyAnnualReportDO);
            dataCompanyAnnualReport.setId(DataCompanyAnnualReportId.of(add.getId()));
            return add != null;
        }
        dataCompanyAnnualReportDO.setUpdateControl(dataCompanyAnnualReport.getUpdateControl());
        DataCompanyAnnualReportDO update = iDataCompanyAnnualReportService.update(dataCompanyAnnualReportDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAnnualReportId dataCompanyAnnualReportId) {
        return iDataCompanyAnnualReportService.deleteById(dataCompanyAnnualReportId.getId());
    }

    @Override
    public boolean delete(DataCompanyAnnualReportId dataCompanyAnnualReportId, IdCommand idCommand) {
        return iDataCompanyAnnualReportService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAnnualReportService(IDataCompanyAnnualReportService iDataCompanyAnnualReportService) {
        this.iDataCompanyAnnualReportService = iDataCompanyAnnualReportService;
    }
}
