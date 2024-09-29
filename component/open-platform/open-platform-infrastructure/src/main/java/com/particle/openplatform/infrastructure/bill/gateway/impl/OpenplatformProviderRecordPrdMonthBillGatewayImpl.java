package com.particle.openplatform.infrastructure.bill.gateway.impl;

import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBillId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdMonthBillGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdMonthBillService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdMonthBillDO;
import com.particle.openplatform.infrastructure.bill.structmapping.OpenplatformProviderRecordPrdMonthBillInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台供应商月账单 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Component
public class OpenplatformProviderRecordPrdMonthBillGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformProviderRecordPrdMonthBillId,OpenplatformProviderRecordPrdMonthBill> implements OpenplatformProviderRecordPrdMonthBillGateway {

    private IOpenplatformProviderRecordPrdMonthBillService iOpenplatformProviderRecordPrdMonthBillService;

    @Override
    public OpenplatformProviderRecordPrdMonthBill getById(OpenplatformProviderRecordPrdMonthBillId openplatformProviderRecordPrdMonthBillId) {
        OpenplatformProviderRecordPrdMonthBillDO byId = iOpenplatformProviderRecordPrdMonthBillService.getById(openplatformProviderRecordPrdMonthBillId.getId());
        OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill = DomainFactory.create(OpenplatformProviderRecordPrdMonthBill.class);
        openplatformProviderRecordPrdMonthBill = OpenplatformProviderRecordPrdMonthBillInfrastructureStructMapping.instance. openplatformProviderRecordPrdMonthBillDOToOpenplatformProviderRecordPrdMonthBill(openplatformProviderRecordPrdMonthBill,byId);
        return openplatformProviderRecordPrdMonthBill;
    }

    @Override
    public boolean doSave(OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill) {
        OpenplatformProviderRecordPrdMonthBillDO openplatformProviderRecordPrdMonthBillDO = OpenplatformProviderRecordPrdMonthBillInfrastructureStructMapping.instance.openplatformProviderRecordPrdMonthBillToOpenplatformProviderRecordPrdMonthBillDO(openplatformProviderRecordPrdMonthBill);
        if (openplatformProviderRecordPrdMonthBillDO.getId() == null) {
            openplatformProviderRecordPrdMonthBillDO.setAddControl(openplatformProviderRecordPrdMonthBill.getAddControl());
            OpenplatformProviderRecordPrdMonthBillDO add = iOpenplatformProviderRecordPrdMonthBillService.add(openplatformProviderRecordPrdMonthBillDO);
            openplatformProviderRecordPrdMonthBill.setId(OpenplatformProviderRecordPrdMonthBillId.of(add.getId()));
            return add != null;
        }
        openplatformProviderRecordPrdMonthBillDO.setUpdateControl(openplatformProviderRecordPrdMonthBill.getUpdateControl());
        OpenplatformProviderRecordPrdMonthBillDO update = iOpenplatformProviderRecordPrdMonthBillService.update(openplatformProviderRecordPrdMonthBillDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformProviderRecordPrdMonthBillId openplatformProviderRecordPrdMonthBillId) {
        return iOpenplatformProviderRecordPrdMonthBillService.deleteById(openplatformProviderRecordPrdMonthBillId.getId());
    }

    @Override
    public boolean delete(OpenplatformProviderRecordPrdMonthBillId openplatformProviderRecordPrdMonthBillId, IdCommand idCommand) {
        return iOpenplatformProviderRecordPrdMonthBillService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformProviderRecordPrdMonthBillService(IOpenplatformProviderRecordPrdMonthBillService iOpenplatformProviderRecordPrdMonthBillService) {
        this.iOpenplatformProviderRecordPrdMonthBillService = iOpenplatformProviderRecordPrdMonthBillService;
    }
}
