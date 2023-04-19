package com.particle.tenant.infrastructure.createapply.gateway.impl;

import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;
import com.particle.tenant.domain.createapply.gateway.TenantCreateApplyGateway;
import com.particle.tenant.infrastructure.createapply.service.ITenantCreateApplyService;
import com.particle.tenant.infrastructure.createapply.dos.TenantCreateApplyDO;
import com.particle.tenant.infrastructure.createapply.structmapping.TenantCreateApplyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 租户创建申请 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Component
public class TenantCreateApplyGatewayImpl extends AbstractBaseGatewayImpl<TenantCreateApplyId,TenantCreateApply> implements TenantCreateApplyGateway {

	private ITenantCreateApplyService iTenantCreateApplyService;

	@Override
	public TenantCreateApply getById(TenantCreateApplyId tenantCreateApplyId) {
		TenantCreateApplyDO byId = iTenantCreateApplyService.getById(tenantCreateApplyId.getId());
		TenantCreateApply tenantCreateApply = DomainFactory.create(TenantCreateApply.class);
		tenantCreateApply = TenantCreateApplyInfrastructureStructMapping.instance. tenantCreateApplyDOToTenantCreateApply(tenantCreateApply,byId);
		return tenantCreateApply;
	}

	@Override
	public boolean doSave(TenantCreateApply tenantCreateApply) {
		TenantCreateApplyDO tenantCreateApplyDO = TenantCreateApplyInfrastructureStructMapping.instance.tenantCreateApplyToTenantCreateApplyDO(tenantCreateApply);
		if (tenantCreateApplyDO.getId() == null) {
			tenantCreateApplyDO.setAddControl(tenantCreateApply.getAddControl());
			TenantCreateApplyDO add = iTenantCreateApplyService.add(tenantCreateApplyDO);
			tenantCreateApply.setId(TenantCreateApplyId.of(add.getId()));
			return add != null;
		}
		tenantCreateApplyDO.setUpdateControl(tenantCreateApply.getUpdateControl());
		TenantCreateApplyDO update = iTenantCreateApplyService.update(tenantCreateApplyDO);
		return update != null;
	}

	@Override
	public boolean delete(TenantCreateApplyId tenantCreateApplyId) {
		return iTenantCreateApplyService.deleteById(tenantCreateApplyId.getId());
	}


	@Autowired
	public void setITenantCreateApplyService(ITenantCreateApplyService iTenantCreateApplyService) {
		this.iTenantCreateApplyService = iTenantCreateApplyService;
	}
}
