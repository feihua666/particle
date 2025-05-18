package com.particle.data.infrastructure.company.gateway.impl;

import com.particle.data.domain.company.DataCompanyAnnualReportAssets;
import com.particle.data.domain.company.DataCompanyAnnualReportAssetsId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportAssetsGateway;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAssetsService;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAssetsDO;
import com.particle.data.infrastructure.company.structmapping.DataCompanyAnnualReportAssetsInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业资产状况信息 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Component
public class DataCompanyAnnualReportAssetsGatewayImpl extends AbstractBaseGatewayImpl<DataCompanyAnnualReportAssetsId,DataCompanyAnnualReportAssets> implements DataCompanyAnnualReportAssetsGateway {

    private IDataCompanyAnnualReportAssetsService iDataCompanyAnnualReportAssetsService;

    @Override
    public DataCompanyAnnualReportAssets getById(DataCompanyAnnualReportAssetsId dataCompanyAnnualReportAssetsId) {
        DataCompanyAnnualReportAssetsDO byId = iDataCompanyAnnualReportAssetsService.getById(dataCompanyAnnualReportAssetsId.getId());
        DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets = DomainFactory.create(DataCompanyAnnualReportAssets.class);
        dataCompanyAnnualReportAssets = DataCompanyAnnualReportAssetsInfrastructureStructMapping.instance. dataCompanyAnnualReportAssetsDOToDataCompanyAnnualReportAssets(dataCompanyAnnualReportAssets,byId);
        return dataCompanyAnnualReportAssets;
    }

    @Override
    public boolean doSave(DataCompanyAnnualReportAssets dataCompanyAnnualReportAssets) {
        DataCompanyAnnualReportAssetsDO dataCompanyAnnualReportAssetsDO = DataCompanyAnnualReportAssetsInfrastructureStructMapping.instance.dataCompanyAnnualReportAssetsToDataCompanyAnnualReportAssetsDO(dataCompanyAnnualReportAssets);
        if (dataCompanyAnnualReportAssetsDO.getId() == null) {
            dataCompanyAnnualReportAssetsDO.setAddControl(dataCompanyAnnualReportAssets.getAddControl());
            DataCompanyAnnualReportAssetsDO add = iDataCompanyAnnualReportAssetsService.add(dataCompanyAnnualReportAssetsDO);
            dataCompanyAnnualReportAssets.setId(DataCompanyAnnualReportAssetsId.of(add.getId()));
            return add != null;
        }
        dataCompanyAnnualReportAssetsDO.setUpdateControl(dataCompanyAnnualReportAssets.getUpdateControl());
        DataCompanyAnnualReportAssetsDO update = iDataCompanyAnnualReportAssetsService.update(dataCompanyAnnualReportAssetsDO);
        return update != null;
    }

    @Override
    public boolean delete(DataCompanyAnnualReportAssetsId dataCompanyAnnualReportAssetsId) {
        return iDataCompanyAnnualReportAssetsService.deleteById(dataCompanyAnnualReportAssetsId.getId());
    }

    @Override
    public boolean delete(DataCompanyAnnualReportAssetsId dataCompanyAnnualReportAssetsId, IdCommand idCommand) {
        return iDataCompanyAnnualReportAssetsService.deleteById(idCommand);
    }

    @Autowired
    public void setIDataCompanyAnnualReportAssetsService(IDataCompanyAnnualReportAssetsService iDataCompanyAnnualReportAssetsService) {
        this.iDataCompanyAnnualReportAssetsService = iDataCompanyAnnualReportAssetsService;
    }
}
