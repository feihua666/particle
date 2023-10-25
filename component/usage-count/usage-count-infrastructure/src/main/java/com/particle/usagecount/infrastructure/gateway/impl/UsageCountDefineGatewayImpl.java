package com.particle.usagecount.infrastructure.gateway.impl;

import com.particle.usagecount.domain.UsageCountDefine;
import com.particle.usagecount.domain.UsageCountDefineId;
import com.particle.usagecount.domain.gateway.UsageCountDefineGateway;
import com.particle.usagecount.infrastructure.service.IUsageCountDefineService;
import com.particle.usagecount.infrastructure.dos.UsageCountDefineDO;
import com.particle.usagecount.infrastructure.structmapping.UsageCountDefineInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 使用次数定义 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Component
public class UsageCountDefineGatewayImpl extends AbstractBaseGatewayImpl<UsageCountDefineId,UsageCountDefine> implements UsageCountDefineGateway {

	private IUsageCountDefineService iUsageCountDefineService;

	@Override
	public UsageCountDefine getById(UsageCountDefineId usageCountDefineId) {
		UsageCountDefineDO byId = iUsageCountDefineService.getById(usageCountDefineId.getId());
		UsageCountDefine usageCountDefine = DomainFactory.create(UsageCountDefine.class);
		usageCountDefine = UsageCountDefineInfrastructureStructMapping.instance. usageCountDefineDOToUsageCountDefine(usageCountDefine,byId);
		return usageCountDefine;
	}

	@Override
	public boolean doSave(UsageCountDefine usageCountDefine) {
		UsageCountDefineDO usageCountDefineDO = UsageCountDefineInfrastructureStructMapping.instance.usageCountDefineToUsageCountDefineDO(usageCountDefine);
		if (usageCountDefineDO.getId() == null) {
			usageCountDefineDO.setAddControl(usageCountDefine.getAddControl());
			UsageCountDefineDO add = iUsageCountDefineService.add(usageCountDefineDO);
			usageCountDefine.setId(UsageCountDefineId.of(add.getId()));
			return add != null;
		}
		usageCountDefineDO.setUpdateControl(usageCountDefine.getUpdateControl());
		UsageCountDefineDO update = iUsageCountDefineService.update(usageCountDefineDO);
		return update != null;
	}

	@Override
	public boolean delete(UsageCountDefineId usageCountDefineId) {
		return iUsageCountDefineService.deleteById(usageCountDefineId.getId());
	}


	@Autowired
	public void setIUsageCountDefineService(IUsageCountDefineService iUsageCountDefineService) {
		this.iUsageCountDefineService = iUsageCountDefineService;
	}
}
