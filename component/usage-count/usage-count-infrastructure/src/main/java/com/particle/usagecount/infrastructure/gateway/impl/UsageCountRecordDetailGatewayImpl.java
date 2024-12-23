package com.particle.usagecount.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.usagecount.domain.UsageCountRecordDetail;
import com.particle.usagecount.domain.UsageCountRecordDetailId;
import com.particle.usagecount.domain.gateway.UsageCountRecordDetailGateway;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDetailDO;
import com.particle.usagecount.infrastructure.service.IUsageCountRecordDetailService;
import com.particle.usagecount.infrastructure.structmapping.UsageCountRecordDetailInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 使用次数记录明细 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Component
public class UsageCountRecordDetailGatewayImpl extends AbstractBaseGatewayImpl<UsageCountRecordDetailId,UsageCountRecordDetail> implements UsageCountRecordDetailGateway {

	private IUsageCountRecordDetailService iUsageCountRecordDetailService;

	@Override
	public UsageCountRecordDetail getById(UsageCountRecordDetailId usageCountRecordDetailId) {
		UsageCountRecordDetailDO byId = iUsageCountRecordDetailService.getById(usageCountRecordDetailId.getId());
		UsageCountRecordDetail usageCountRecordDetail = DomainFactory.create(UsageCountRecordDetail.class);
		usageCountRecordDetail = UsageCountRecordDetailInfrastructureStructMapping.instance. usageCountRecordDetailDOToUsageCountRecordDetail(usageCountRecordDetail,byId);
		return usageCountRecordDetail;
	}

	@Override
	public boolean doSave(UsageCountRecordDetail usageCountRecordDetail) {
		UsageCountRecordDetailDO usageCountRecordDetailDO = UsageCountRecordDetailInfrastructureStructMapping.instance.usageCountRecordDetailToUsageCountRecordDetailDO(usageCountRecordDetail);
		if (usageCountRecordDetailDO.getId() == null) {
			usageCountRecordDetailDO.setAddControl(usageCountRecordDetail.getAddControl());
			UsageCountRecordDetailDO add = iUsageCountRecordDetailService.add(usageCountRecordDetailDO);
			usageCountRecordDetail.setId(UsageCountRecordDetailId.of(add.getId()));
			return add != null;
		}
		usageCountRecordDetailDO.setUpdateControl(usageCountRecordDetail.getUpdateControl());
		UsageCountRecordDetailDO update = iUsageCountRecordDetailService.update(usageCountRecordDetailDO);
		return update != null;
	}

	@Override
	public boolean delete(UsageCountRecordDetailId usageCountRecordDetailId) {
		return iUsageCountRecordDetailService.deleteById(usageCountRecordDetailId.getId());
	}

	@Override
	public boolean delete(UsageCountRecordDetailId id, IdCommand idCommand) {
		return iUsageCountRecordDetailService.deleteById(idCommand);
	}

	@Autowired
	public void setIUsageCountRecordDetailService(IUsageCountRecordDetailService iUsageCountRecordDetailService) {
		this.iUsageCountRecordDetailService = iUsageCountRecordDetailService;
	}
}
