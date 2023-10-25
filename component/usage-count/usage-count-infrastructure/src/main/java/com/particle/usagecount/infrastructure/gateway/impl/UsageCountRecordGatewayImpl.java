package com.particle.usagecount.infrastructure.gateway.impl;

import com.particle.usagecount.domain.UsageCountRecord;
import com.particle.usagecount.domain.UsageCountRecordId;
import com.particle.usagecount.domain.gateway.UsageCountRecordGateway;
import com.particle.usagecount.infrastructure.service.IUsageCountRecordService;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDO;
import com.particle.usagecount.infrastructure.structmapping.UsageCountRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 使用次数记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Component
public class UsageCountRecordGatewayImpl extends AbstractBaseGatewayImpl<UsageCountRecordId,UsageCountRecord> implements UsageCountRecordGateway {

	private IUsageCountRecordService iUsageCountRecordService;

	@Override
	public UsageCountRecord getById(UsageCountRecordId usageCountRecordId) {
		UsageCountRecordDO byId = iUsageCountRecordService.getById(usageCountRecordId.getId());
		UsageCountRecord usageCountRecord = DomainFactory.create(UsageCountRecord.class);
		usageCountRecord = UsageCountRecordInfrastructureStructMapping.instance. usageCountRecordDOToUsageCountRecord(usageCountRecord,byId);
		return usageCountRecord;
	}

	@Override
	public boolean doSave(UsageCountRecord usageCountRecord) {
		UsageCountRecordDO usageCountRecordDO = UsageCountRecordInfrastructureStructMapping.instance.usageCountRecordToUsageCountRecordDO(usageCountRecord);
		if (usageCountRecordDO.getId() == null) {
			usageCountRecordDO.setAddControl(usageCountRecord.getAddControl());
			UsageCountRecordDO add = iUsageCountRecordService.add(usageCountRecordDO);
			usageCountRecord.setId(UsageCountRecordId.of(add.getId()));
			return add != null;
		}
		usageCountRecordDO.setUpdateControl(usageCountRecord.getUpdateControl());
		UsageCountRecordDO update = iUsageCountRecordService.update(usageCountRecordDO);
		return update != null;
	}

	@Override
	public boolean delete(UsageCountRecordId usageCountRecordId) {
		return iUsageCountRecordService.deleteById(usageCountRecordId.getId());
	}


	@Autowired
	public void setIUsageCountRecordService(IUsageCountRecordService iUsageCountRecordService) {
		this.iUsageCountRecordService = iUsageCountRecordService;
	}
}
