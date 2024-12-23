package com.particle.openplatform.infrastructure.bill.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummary;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdApiMonthSummaryGateway;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdApiMonthSummaryService;
import com.particle.openplatform.infrastructure.bill.structmapping.OpenplatformProviderRecordPrdApiMonthSummaryInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台供应商接口月汇总 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Component
public class OpenplatformProviderRecordPrdApiMonthSummaryGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformProviderRecordPrdApiMonthSummaryId,OpenplatformProviderRecordPrdApiMonthSummary> implements OpenplatformProviderRecordPrdApiMonthSummaryGateway {

    private IOpenplatformProviderRecordPrdApiMonthSummaryService iOpenplatformProviderRecordPrdApiMonthSummaryService;

    @Override
    public OpenplatformProviderRecordPrdApiMonthSummary getById(OpenplatformProviderRecordPrdApiMonthSummaryId openplatformProviderRecordPrdApiMonthSummaryId) {
        OpenplatformProviderRecordPrdApiMonthSummaryDO byId = iOpenplatformProviderRecordPrdApiMonthSummaryService.getById(openplatformProviderRecordPrdApiMonthSummaryId.getId());
        OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary = DomainFactory.create(OpenplatformProviderRecordPrdApiMonthSummary.class);
        openplatformProviderRecordPrdApiMonthSummary = OpenplatformProviderRecordPrdApiMonthSummaryInfrastructureStructMapping.instance. openplatformProviderRecordPrdApiMonthSummaryDOToOpenplatformProviderRecordPrdApiMonthSummary(openplatformProviderRecordPrdApiMonthSummary,byId);
        return openplatformProviderRecordPrdApiMonthSummary;
    }

    @Override
    public boolean doSave(OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary) {
        OpenplatformProviderRecordPrdApiMonthSummaryDO openplatformProviderRecordPrdApiMonthSummaryDO = OpenplatformProviderRecordPrdApiMonthSummaryInfrastructureStructMapping.instance.openplatformProviderRecordPrdApiMonthSummaryToOpenplatformProviderRecordPrdApiMonthSummaryDO(openplatformProviderRecordPrdApiMonthSummary);
        if (openplatformProviderRecordPrdApiMonthSummaryDO.getId() == null) {
            openplatformProviderRecordPrdApiMonthSummaryDO.setAddControl(openplatformProviderRecordPrdApiMonthSummary.getAddControl());
            OpenplatformProviderRecordPrdApiMonthSummaryDO add = iOpenplatformProviderRecordPrdApiMonthSummaryService.add(openplatformProviderRecordPrdApiMonthSummaryDO);
            openplatformProviderRecordPrdApiMonthSummary.setId(OpenplatformProviderRecordPrdApiMonthSummaryId.of(add.getId()));
            return add != null;
        }
        openplatformProviderRecordPrdApiMonthSummaryDO.setUpdateControl(openplatformProviderRecordPrdApiMonthSummary.getUpdateControl());
        OpenplatformProviderRecordPrdApiMonthSummaryDO update = iOpenplatformProviderRecordPrdApiMonthSummaryService.update(openplatformProviderRecordPrdApiMonthSummaryDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformProviderRecordPrdApiMonthSummaryId openplatformProviderRecordPrdApiMonthSummaryId) {
        return iOpenplatformProviderRecordPrdApiMonthSummaryService.deleteById(openplatformProviderRecordPrdApiMonthSummaryId.getId());
    }

    @Override
    public boolean delete(OpenplatformProviderRecordPrdApiMonthSummaryId openplatformProviderRecordPrdApiMonthSummaryId, IdCommand idCommand) {
        return iOpenplatformProviderRecordPrdApiMonthSummaryService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformProviderRecordPrdApiMonthSummaryService(IOpenplatformProviderRecordPrdApiMonthSummaryService iOpenplatformProviderRecordPrdApiMonthSummaryService) {
        this.iOpenplatformProviderRecordPrdApiMonthSummaryService = iOpenplatformProviderRecordPrdApiMonthSummaryService;
    }
}
