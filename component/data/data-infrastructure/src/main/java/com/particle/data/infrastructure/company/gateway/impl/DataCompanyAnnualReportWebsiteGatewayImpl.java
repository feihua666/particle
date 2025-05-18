package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAnnualReportWebsite;
import com.particle.data.domain.company.DataCompanyAnnualReportWebsiteId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportWebsiteGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportWebsiteService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportWebsiteDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAnnualReportWebsiteInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业年报网站网店 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Component
public class DataCompanyAnnualReportWebsiteGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAnnualReportWebsiteId,DataCompanyAnnualReportWebsite> implements DataCompanyAnnualReportWebsiteGateway {

    private IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService;

    @Override
    public DataCompanyAnnualReportWebsite getById(DataCompanyAnnualReportWebsiteId dataCompanyAnnualReportWebsiteId) {
        DataCompanyAnnualReportWebsiteDO byId = iDataCompanyAnnualReportWebsiteService.getById(dataCompanyAnnualReportWebsiteId.getId());
        DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite = DomainFactory.create(DataCompanyAnnualReportWebsite.class);
        dataCompanyAnnualReportWebsite = DataCompanyAnnualReportWebsiteInfrastructureStructMapping.instance. dataCompanyAnnualReportWebsiteDOToDataCompanyAnnualReportWebsite(dataCompanyAnnualReportWebsite,byId);
        return dataCompanyAnnualReportWebsite;
    }

    @Override
    public boolean doSave(DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite) {
        DataCompanyAnnualReportWebsiteDO dataCompanyAnnualReportWebsiteDO = DataCompanyAnnualReportWebsiteInfrastructureStructMapping.instance.dataCompanyAnnualReportWebsiteToDataCompanyAnnualReportWebsiteDO(dataCompanyAnnualReportWebsite);
        if (dataCompanyAnnualReportWebsiteDO.getId() == null) {
            dataCompanyAnnualReportWebsiteDO.setAddControl(dataCompanyAnnualReportWebsite.getAddControl());
            DataCompanyAnnualReportWebsiteDO add = iDataCompanyAnnualReportWebsiteService.add(dataCompanyAnnualReportWebsiteDO);
            dataCompanyAnnualReportWebsite.setId(DataCompanyAnnualReportWebsiteId.of(add.getId()));
            return add != null;
        }
        dataCompanyAnnualReportWebsiteDO.setUpdateControl(dataCompanyAnnualReportWebsite.getUpdateControl());
        DataCompanyAnnualReportWebsiteDO update = iDataCompanyAnnualReportWebsiteService.update(dataCompanyAnnualReportWebsiteDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAnnualReportWebsiteId dataCompanyAnnualReportWebsiteId) {
        return iDataCompanyAnnualReportWebsiteService.deleteById(dataCompanyAnnualReportWebsiteId.getId());
    }

    @Override
    public boolean delete(DataCompanyAnnualReportWebsiteId dataCompanyAnnualReportWebsiteId, IdCommand idCommand) {
        return iDataCompanyAnnualReportWebsiteService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAnnualReportWebsiteService(IDataCompanyAnnualReportWebsiteService iDataCompanyAnnualReportWebsiteService) {
        this.iDataCompanyAnnualReportWebsiteService = iDataCompanyAnnualReportWebsiteService;
    }
}
