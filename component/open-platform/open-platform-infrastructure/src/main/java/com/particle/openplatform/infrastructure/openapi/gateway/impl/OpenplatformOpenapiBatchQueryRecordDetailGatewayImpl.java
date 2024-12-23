package com.particle.openplatform.infrastructure.openapi.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetail;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetailId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiBatchQueryRecordDetailGateway;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDetailDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordDetailService;
import com.particle.openplatform.infrastructure.openapi.structmapping.OpenplatformOpenapiBatchQueryRecordDetailInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口批量查询记录明细 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Component
public class OpenplatformOpenapiBatchQueryRecordDetailGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiBatchQueryRecordDetailId,OpenplatformOpenapiBatchQueryRecordDetail> implements OpenplatformOpenapiBatchQueryRecordDetailGateway {

    private IOpenplatformOpenapiBatchQueryRecordDetailService iOpenplatformOpenapiBatchQueryRecordDetailService;

    @Override
    public OpenplatformOpenapiBatchQueryRecordDetail getById(OpenplatformOpenapiBatchQueryRecordDetailId openplatformOpenapiBatchQueryRecordDetailId) {
        OpenplatformOpenapiBatchQueryRecordDetailDO byId = iOpenplatformOpenapiBatchQueryRecordDetailService.getById(openplatformOpenapiBatchQueryRecordDetailId.getId());
        OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail = DomainFactory.create(OpenplatformOpenapiBatchQueryRecordDetail.class);
        openplatformOpenapiBatchQueryRecordDetail = OpenplatformOpenapiBatchQueryRecordDetailInfrastructureStructMapping.instance. openplatformOpenapiBatchQueryRecordDetailDOToOpenplatformOpenapiBatchQueryRecordDetail(openplatformOpenapiBatchQueryRecordDetail,byId);
        return openplatformOpenapiBatchQueryRecordDetail;
    }

    @Override
    public boolean doSave(OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail) {
        OpenplatformOpenapiBatchQueryRecordDetailDO openplatformOpenapiBatchQueryRecordDetailDO = OpenplatformOpenapiBatchQueryRecordDetailInfrastructureStructMapping.instance.openplatformOpenapiBatchQueryRecordDetailToOpenplatformOpenapiBatchQueryRecordDetailDO(openplatformOpenapiBatchQueryRecordDetail);
        if (openplatformOpenapiBatchQueryRecordDetailDO.getId() == null) {
            openplatformOpenapiBatchQueryRecordDetailDO.setAddControl(openplatformOpenapiBatchQueryRecordDetail.getAddControl());
            OpenplatformOpenapiBatchQueryRecordDetailDO add = iOpenplatformOpenapiBatchQueryRecordDetailService.add(openplatformOpenapiBatchQueryRecordDetailDO);
            openplatformOpenapiBatchQueryRecordDetail.setId(OpenplatformOpenapiBatchQueryRecordDetailId.of(add.getId()));
            return add != null;
        }
        openplatformOpenapiBatchQueryRecordDetailDO.setUpdateControl(openplatformOpenapiBatchQueryRecordDetail.getUpdateControl());
        OpenplatformOpenapiBatchQueryRecordDetailDO update = iOpenplatformOpenapiBatchQueryRecordDetailService.update(openplatformOpenapiBatchQueryRecordDetailDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformOpenapiBatchQueryRecordDetailId openplatformOpenapiBatchQueryRecordDetailId) {
        return iOpenplatformOpenapiBatchQueryRecordDetailService.deleteById(openplatformOpenapiBatchQueryRecordDetailId.getId());
    }

    @Override
    public boolean delete(OpenplatformOpenapiBatchQueryRecordDetailId openplatformOpenapiBatchQueryRecordDetailId, IdCommand idCommand) {
        return iOpenplatformOpenapiBatchQueryRecordDetailService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformOpenapiBatchQueryRecordDetailService(IOpenplatformOpenapiBatchQueryRecordDetailService iOpenplatformOpenapiBatchQueryRecordDetailService) {
        this.iOpenplatformOpenapiBatchQueryRecordDetailService = iOpenplatformOpenapiBatchQueryRecordDetailService;
    }
}
