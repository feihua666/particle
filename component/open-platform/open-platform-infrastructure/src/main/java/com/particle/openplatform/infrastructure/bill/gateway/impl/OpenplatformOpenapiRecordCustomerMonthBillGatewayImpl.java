package com.particle.openplatform.infrastructure.bill.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBillId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordCustomerMonthBillGateway;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordCustomerMonthBillDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordCustomerMonthBillService;
import com.particle.openplatform.infrastructure.bill.structmapping.OpenplatformOpenapiRecordCustomerMonthBillInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台客户月账单 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Component
public class OpenplatformOpenapiRecordCustomerMonthBillGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiRecordCustomerMonthBillId,OpenplatformOpenapiRecordCustomerMonthBill> implements OpenplatformOpenapiRecordCustomerMonthBillGateway {

    private IOpenplatformOpenapiRecordCustomerMonthBillService iOpenplatformOpenapiRecordCustomerMonthBillService;

    @Override
    public OpenplatformOpenapiRecordCustomerMonthBill getById(OpenplatformOpenapiRecordCustomerMonthBillId openplatformOpenapiRecordCustomerMonthBillId) {
        OpenplatformOpenapiRecordCustomerMonthBillDO byId = iOpenplatformOpenapiRecordCustomerMonthBillService.getById(openplatformOpenapiRecordCustomerMonthBillId.getId());
        OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill = DomainFactory.create(OpenplatformOpenapiRecordCustomerMonthBill.class);
        openplatformOpenapiRecordCustomerMonthBill = OpenplatformOpenapiRecordCustomerMonthBillInfrastructureStructMapping.instance. openplatformOpenapiRecordCustomerMonthBillDOToOpenplatformOpenapiRecordCustomerMonthBill(openplatformOpenapiRecordCustomerMonthBill,byId);
        return openplatformOpenapiRecordCustomerMonthBill;
    }

    @Override
    public boolean doSave(OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill) {
        OpenplatformOpenapiRecordCustomerMonthBillDO openplatformOpenapiRecordCustomerMonthBillDO = OpenplatformOpenapiRecordCustomerMonthBillInfrastructureStructMapping.instance.openplatformOpenapiRecordCustomerMonthBillToOpenplatformOpenapiRecordCustomerMonthBillDO(openplatformOpenapiRecordCustomerMonthBill);
        if (openplatformOpenapiRecordCustomerMonthBillDO.getId() == null) {
            openplatformOpenapiRecordCustomerMonthBillDO.setAddControl(openplatformOpenapiRecordCustomerMonthBill.getAddControl());
            OpenplatformOpenapiRecordCustomerMonthBillDO add = iOpenplatformOpenapiRecordCustomerMonthBillService.add(openplatformOpenapiRecordCustomerMonthBillDO);
            openplatformOpenapiRecordCustomerMonthBill.setId(OpenplatformOpenapiRecordCustomerMonthBillId.of(add.getId()));
            return add != null;
        }
        openplatformOpenapiRecordCustomerMonthBillDO.setUpdateControl(openplatformOpenapiRecordCustomerMonthBill.getUpdateControl());
        OpenplatformOpenapiRecordCustomerMonthBillDO update = iOpenplatformOpenapiRecordCustomerMonthBillService.update(openplatformOpenapiRecordCustomerMonthBillDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformOpenapiRecordCustomerMonthBillId openplatformOpenapiRecordCustomerMonthBillId) {
        return iOpenplatformOpenapiRecordCustomerMonthBillService.deleteById(openplatformOpenapiRecordCustomerMonthBillId.getId());
    }

    @Override
    public boolean delete(OpenplatformOpenapiRecordCustomerMonthBillId openplatformOpenapiRecordCustomerMonthBillId, IdCommand idCommand) {
        return iOpenplatformOpenapiRecordCustomerMonthBillService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformOpenapiRecordCustomerMonthBillService(IOpenplatformOpenapiRecordCustomerMonthBillService iOpenplatformOpenapiRecordCustomerMonthBillService) {
        this.iOpenplatformOpenapiRecordCustomerMonthBillService = iOpenplatformOpenapiRecordCustomerMonthBillService;
    }
}
