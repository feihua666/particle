package com.particle.openplatform.infrastructure.bill.gateway.impl;

import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummary;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdApiDaySummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdApiDaySummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiDaySummaryDO;
import com.particle.openplatform.infrastructure.bill.structmapping.OpenplatformProviderRecordPrdApiDaySummaryInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台供应商接口日汇总 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Component
public class OpenplatformProviderRecordPrdApiDaySummaryGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformProviderRecordPrdApiDaySummaryId,OpenplatformProviderRecordPrdApiDaySummary> implements OpenplatformProviderRecordPrdApiDaySummaryGateway {

    private IOpenplatformProviderRecordPrdApiDaySummaryService iOpenplatformProviderRecordPrdApiDaySummaryService;

    @Override
    public OpenplatformProviderRecordPrdApiDaySummary getById(OpenplatformProviderRecordPrdApiDaySummaryId openplatformProviderRecordPrdApiDaySummaryId) {
        OpenplatformProviderRecordPrdApiDaySummaryDO byId = iOpenplatformProviderRecordPrdApiDaySummaryService.getById(openplatformProviderRecordPrdApiDaySummaryId.getId());
        OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary = DomainFactory.create(OpenplatformProviderRecordPrdApiDaySummary.class);
        openplatformProviderRecordPrdApiDaySummary = OpenplatformProviderRecordPrdApiDaySummaryInfrastructureStructMapping.instance. openplatformProviderRecordPrdApiDaySummaryDOToOpenplatformProviderRecordPrdApiDaySummary(openplatformProviderRecordPrdApiDaySummary,byId);
        return openplatformProviderRecordPrdApiDaySummary;
    }

    @Override
    public boolean doSave(OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary) {
        OpenplatformProviderRecordPrdApiDaySummaryDO openplatformProviderRecordPrdApiDaySummaryDO = OpenplatformProviderRecordPrdApiDaySummaryInfrastructureStructMapping.instance.openplatformProviderRecordPrdApiDaySummaryToOpenplatformProviderRecordPrdApiDaySummaryDO(openplatformProviderRecordPrdApiDaySummary);
        if (openplatformProviderRecordPrdApiDaySummaryDO.getId() == null) {
            openplatformProviderRecordPrdApiDaySummaryDO.setAddControl(openplatformProviderRecordPrdApiDaySummary.getAddControl());
            OpenplatformProviderRecordPrdApiDaySummaryDO add = iOpenplatformProviderRecordPrdApiDaySummaryService.add(openplatformProviderRecordPrdApiDaySummaryDO);
            openplatformProviderRecordPrdApiDaySummary.setId(OpenplatformProviderRecordPrdApiDaySummaryId.of(add.getId()));
            return add != null;
        }
        openplatformProviderRecordPrdApiDaySummaryDO.setUpdateControl(openplatformProviderRecordPrdApiDaySummary.getUpdateControl());
        OpenplatformProviderRecordPrdApiDaySummaryDO update = iOpenplatformProviderRecordPrdApiDaySummaryService.update(openplatformProviderRecordPrdApiDaySummaryDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformProviderRecordPrdApiDaySummaryId openplatformProviderRecordPrdApiDaySummaryId) {
        return iOpenplatformProviderRecordPrdApiDaySummaryService.deleteById(openplatformProviderRecordPrdApiDaySummaryId.getId());
    }

    @Override
    public boolean delete(OpenplatformProviderRecordPrdApiDaySummaryId openplatformProviderRecordPrdApiDaySummaryId, IdCommand idCommand) {
        return iOpenplatformProviderRecordPrdApiDaySummaryService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformProviderRecordPrdApiDaySummaryService(IOpenplatformProviderRecordPrdApiDaySummaryService iOpenplatformProviderRecordPrdApiDaySummaryService) {
        this.iOpenplatformProviderRecordPrdApiDaySummaryService = iOpenplatformProviderRecordPrdApiDaySummaryService;
    }
}
