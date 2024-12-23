package com.particle.openplatform.infrastructure.bill.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
import com.particle.openplatform.infrastructure.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiDaySummaryInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台应用开放接口日汇总 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Component
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiRecordAppOpenapiDaySummaryId,OpenplatformOpenapiRecordAppOpenapiDaySummary> implements OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway {

    private IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;

    @Override
    public OpenplatformOpenapiRecordAppOpenapiDaySummary getById(OpenplatformOpenapiRecordAppOpenapiDaySummaryId openplatformOpenapiRecordAppOpenapiDaySummaryId) {
        OpenplatformOpenapiRecordAppOpenapiDaySummaryDO byId = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.getById(openplatformOpenapiRecordAppOpenapiDaySummaryId.getId());
        OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary = DomainFactory.create(OpenplatformOpenapiRecordAppOpenapiDaySummary.class);
        openplatformOpenapiRecordAppOpenapiDaySummary = OpenplatformOpenapiRecordAppOpenapiDaySummaryInfrastructureStructMapping.instance. openplatformOpenapiRecordAppOpenapiDaySummaryDOToOpenplatformOpenapiRecordAppOpenapiDaySummary(openplatformOpenapiRecordAppOpenapiDaySummary,byId);
        return openplatformOpenapiRecordAppOpenapiDaySummary;
    }

    @Override
    public boolean doSave(OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary) {
        OpenplatformOpenapiRecordAppOpenapiDaySummaryDO openplatformOpenapiRecordAppOpenapiDaySummaryDO = OpenplatformOpenapiRecordAppOpenapiDaySummaryInfrastructureStructMapping.instance.openplatformOpenapiRecordAppOpenapiDaySummaryToOpenplatformOpenapiRecordAppOpenapiDaySummaryDO(openplatformOpenapiRecordAppOpenapiDaySummary);
        if (openplatformOpenapiRecordAppOpenapiDaySummaryDO.getId() == null) {
            openplatformOpenapiRecordAppOpenapiDaySummaryDO.setAddControl(openplatformOpenapiRecordAppOpenapiDaySummary.getAddControl());
            OpenplatformOpenapiRecordAppOpenapiDaySummaryDO add = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.add(openplatformOpenapiRecordAppOpenapiDaySummaryDO);
            openplatformOpenapiRecordAppOpenapiDaySummary.setId(OpenplatformOpenapiRecordAppOpenapiDaySummaryId.of(add.getId()));
            return add != null;
        }
        openplatformOpenapiRecordAppOpenapiDaySummaryDO.setUpdateControl(openplatformOpenapiRecordAppOpenapiDaySummary.getUpdateControl());
        OpenplatformOpenapiRecordAppOpenapiDaySummaryDO update = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.update(openplatformOpenapiRecordAppOpenapiDaySummaryDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformOpenapiRecordAppOpenapiDaySummaryId openplatformOpenapiRecordAppOpenapiDaySummaryId) {
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.deleteById(openplatformOpenapiRecordAppOpenapiDaySummaryId.getId());
    }

    @Override
    public boolean delete(OpenplatformOpenapiRecordAppOpenapiDaySummaryId openplatformOpenapiRecordAppOpenapiDaySummaryId, IdCommand idCommand) {
        return iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformOpenapiRecordAppOpenapiDaySummaryService(IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService) {
        this.iOpenplatformOpenapiRecordAppOpenapiDaySummaryService = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
    }
}
