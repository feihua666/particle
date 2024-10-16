package com.particle.openplatform.infrastructure.bill.gateway.impl;

import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDayRtSummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO;
import com.particle.openplatform.infrastructure.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台应用开放接口日实时汇总 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Component
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId,OpenplatformOpenapiRecordAppOpenapiDayRtSummary> implements OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway {

    private IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;

    @Override
    public OpenplatformOpenapiRecordAppOpenapiDayRtSummary getById(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId openplatformOpenapiRecordAppOpenapiDayRtSummaryId) {
        OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO byId = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.getById(openplatformOpenapiRecordAppOpenapiDayRtSummaryId.getId());
        OpenplatformOpenapiRecordAppOpenapiDayRtSummary openplatformOpenapiRecordAppOpenapiDayRtSummary = DomainFactory.create(OpenplatformOpenapiRecordAppOpenapiDayRtSummary.class);
        openplatformOpenapiRecordAppOpenapiDayRtSummary = OpenplatformOpenapiRecordAppOpenapiDayRtSummaryInfrastructureStructMapping.instance. openplatformOpenapiRecordAppOpenapiDayRtSummaryDOToOpenplatformOpenapiRecordAppOpenapiDayRtSummary(openplatformOpenapiRecordAppOpenapiDayRtSummary,byId);
        return openplatformOpenapiRecordAppOpenapiDayRtSummary;
    }

    @Override
    public boolean doSave(OpenplatformOpenapiRecordAppOpenapiDayRtSummary openplatformOpenapiRecordAppOpenapiDayRtSummary) {
        OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO openplatformOpenapiRecordAppOpenapiDayRtSummaryDO = OpenplatformOpenapiRecordAppOpenapiDayRtSummaryInfrastructureStructMapping.instance.openplatformOpenapiRecordAppOpenapiDayRtSummaryToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO(openplatformOpenapiRecordAppOpenapiDayRtSummary);
        if (openplatformOpenapiRecordAppOpenapiDayRtSummaryDO.getId() == null) {
            openplatformOpenapiRecordAppOpenapiDayRtSummaryDO.setAddControl(openplatformOpenapiRecordAppOpenapiDayRtSummary.getAddControl());
            OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO add = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.add(openplatformOpenapiRecordAppOpenapiDayRtSummaryDO);
            openplatformOpenapiRecordAppOpenapiDayRtSummary.setId(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId.of(add.getId()));
            return add != null;
        }
        openplatformOpenapiRecordAppOpenapiDayRtSummaryDO.setUpdateControl(openplatformOpenapiRecordAppOpenapiDayRtSummary.getUpdateControl());
        OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO update = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.update(openplatformOpenapiRecordAppOpenapiDayRtSummaryDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId openplatformOpenapiRecordAppOpenapiDayRtSummaryId) {
        return iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.deleteById(openplatformOpenapiRecordAppOpenapiDayRtSummaryId.getId());
    }

    @Override
    public boolean delete(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId openplatformOpenapiRecordAppOpenapiDayRtSummaryId, IdCommand idCommand) {
        return iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService(IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService) {
        this.iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
    }
}
