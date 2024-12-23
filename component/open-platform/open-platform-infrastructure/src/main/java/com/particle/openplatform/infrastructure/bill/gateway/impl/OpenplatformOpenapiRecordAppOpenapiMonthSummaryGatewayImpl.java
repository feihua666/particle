package com.particle.openplatform.infrastructure.bill.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
import com.particle.openplatform.infrastructure.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiMonthSummaryInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台应用开放接口月汇总 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Component
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiRecordAppOpenapiMonthSummaryId,OpenplatformOpenapiRecordAppOpenapiMonthSummary> implements OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway {

    private IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;

    @Override
    public OpenplatformOpenapiRecordAppOpenapiMonthSummary getById(OpenplatformOpenapiRecordAppOpenapiMonthSummaryId openplatformOpenapiRecordAppOpenapiMonthSummaryId) {
        OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO byId = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.getById(openplatformOpenapiRecordAppOpenapiMonthSummaryId.getId());
        OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary = DomainFactory.create(OpenplatformOpenapiRecordAppOpenapiMonthSummary.class);
        openplatformOpenapiRecordAppOpenapiMonthSummary = OpenplatformOpenapiRecordAppOpenapiMonthSummaryInfrastructureStructMapping.instance. openplatformOpenapiRecordAppOpenapiMonthSummaryDOToOpenplatformOpenapiRecordAppOpenapiMonthSummary(openplatformOpenapiRecordAppOpenapiMonthSummary,byId);
        return openplatformOpenapiRecordAppOpenapiMonthSummary;
    }

    @Override
    public boolean doSave(OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary) {
        OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO openplatformOpenapiRecordAppOpenapiMonthSummaryDO = OpenplatformOpenapiRecordAppOpenapiMonthSummaryInfrastructureStructMapping.instance.openplatformOpenapiRecordAppOpenapiMonthSummaryToOpenplatformOpenapiRecordAppOpenapiMonthSummaryDO(openplatformOpenapiRecordAppOpenapiMonthSummary);
        if (openplatformOpenapiRecordAppOpenapiMonthSummaryDO.getId() == null) {
            openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setAddControl(openplatformOpenapiRecordAppOpenapiMonthSummary.getAddControl());
            OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO add = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.add(openplatformOpenapiRecordAppOpenapiMonthSummaryDO);
            openplatformOpenapiRecordAppOpenapiMonthSummary.setId(OpenplatformOpenapiRecordAppOpenapiMonthSummaryId.of(add.getId()));
            return add != null;
        }
        openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setUpdateControl(openplatformOpenapiRecordAppOpenapiMonthSummary.getUpdateControl());
        OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO update = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.update(openplatformOpenapiRecordAppOpenapiMonthSummaryDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformOpenapiRecordAppOpenapiMonthSummaryId openplatformOpenapiRecordAppOpenapiMonthSummaryId) {
        return iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.deleteById(openplatformOpenapiRecordAppOpenapiMonthSummaryId.getId());
    }

    @Override
    public boolean delete(OpenplatformOpenapiRecordAppOpenapiMonthSummaryId openplatformOpenapiRecordAppOpenapiMonthSummaryId, IdCommand idCommand) {
        return iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformOpenapiRecordAppOpenapiMonthSummaryService(IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService) {
        this.iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
    }
}
