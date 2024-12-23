package com.particle.openplatform.infrastructure.bill.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBillId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppMonthBillGateway;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppMonthBillDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppMonthBillService;
import com.particle.openplatform.infrastructure.bill.structmapping.OpenplatformOpenapiRecordAppMonthBillInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台应用月账单 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Component
public class OpenplatformOpenapiRecordAppMonthBillGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiRecordAppMonthBillId,OpenplatformOpenapiRecordAppMonthBill> implements OpenplatformOpenapiRecordAppMonthBillGateway {

    private IOpenplatformOpenapiRecordAppMonthBillService iOpenplatformOpenapiRecordAppMonthBillService;

    @Override
    public OpenplatformOpenapiRecordAppMonthBill getById(OpenplatformOpenapiRecordAppMonthBillId openplatformOpenapiRecordAppMonthBillId) {
        OpenplatformOpenapiRecordAppMonthBillDO byId = iOpenplatformOpenapiRecordAppMonthBillService.getById(openplatformOpenapiRecordAppMonthBillId.getId());
        OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill = DomainFactory.create(OpenplatformOpenapiRecordAppMonthBill.class);
        openplatformOpenapiRecordAppMonthBill = OpenplatformOpenapiRecordAppMonthBillInfrastructureStructMapping.instance. openplatformOpenapiRecordAppMonthBillDOToOpenplatformOpenapiRecordAppMonthBill(openplatformOpenapiRecordAppMonthBill,byId);
        return openplatformOpenapiRecordAppMonthBill;
    }

    @Override
    public boolean doSave(OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill) {
        OpenplatformOpenapiRecordAppMonthBillDO openplatformOpenapiRecordAppMonthBillDO = OpenplatformOpenapiRecordAppMonthBillInfrastructureStructMapping.instance.openplatformOpenapiRecordAppMonthBillToOpenplatformOpenapiRecordAppMonthBillDO(openplatformOpenapiRecordAppMonthBill);
        if (openplatformOpenapiRecordAppMonthBillDO.getId() == null) {
            openplatformOpenapiRecordAppMonthBillDO.setAddControl(openplatformOpenapiRecordAppMonthBill.getAddControl());
            OpenplatformOpenapiRecordAppMonthBillDO add = iOpenplatformOpenapiRecordAppMonthBillService.add(openplatformOpenapiRecordAppMonthBillDO);
            openplatformOpenapiRecordAppMonthBill.setId(OpenplatformOpenapiRecordAppMonthBillId.of(add.getId()));
            return add != null;
        }
        openplatformOpenapiRecordAppMonthBillDO.setUpdateControl(openplatformOpenapiRecordAppMonthBill.getUpdateControl());
        OpenplatformOpenapiRecordAppMonthBillDO update = iOpenplatformOpenapiRecordAppMonthBillService.update(openplatformOpenapiRecordAppMonthBillDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformOpenapiRecordAppMonthBillId openplatformOpenapiRecordAppMonthBillId) {
        return iOpenplatformOpenapiRecordAppMonthBillService.deleteById(openplatformOpenapiRecordAppMonthBillId.getId());
    }

    @Override
    public boolean delete(OpenplatformOpenapiRecordAppMonthBillId openplatformOpenapiRecordAppMonthBillId, IdCommand idCommand) {
        return iOpenplatformOpenapiRecordAppMonthBillService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformOpenapiRecordAppMonthBillService(IOpenplatformOpenapiRecordAppMonthBillService iOpenplatformOpenapiRecordAppMonthBillService) {
        this.iOpenplatformOpenapiRecordAppMonthBillService = iOpenplatformOpenapiRecordAppMonthBillService;
    }
}
