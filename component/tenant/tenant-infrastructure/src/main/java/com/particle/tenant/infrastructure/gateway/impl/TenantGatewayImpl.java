package com.particle.tenant.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.tenant.domain.Tenant;
import com.particle.tenant.domain.TenantId;
import com.particle.tenant.domain.gateway.TenantGateway;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import com.particle.tenant.infrastructure.structmapping.TenantInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 租户 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Component
public class TenantGatewayImpl extends AbstractBaseGatewayImpl<TenantId,Tenant> implements TenantGateway {

	private ITenantService iTenantService;

	@Override
	public Tenant getById(TenantId tenantId) {
		TenantDO byId = iTenantService.getById(tenantId.getId());
		Tenant tenant = DomainFactory.create(Tenant.class);
		tenant = TenantInfrastructureStructMapping.instance. tenantDOToTenant(tenant,byId);
		return tenant;
	}

	@Override
	public boolean doSave(Tenant tenant) {
		TenantDO tenantDO = TenantInfrastructureStructMapping.instance.tenantToTenantDO(tenant);
		if (tenantDO.getId() == null) {
			tenantDO.setAddControl(tenant.getAddControl());
			TenantDO add = iTenantService.add(tenantDO);
			tenant.setId(TenantId.of(add.getId()));
			return add != null;
		}
		tenantDO.setUpdateControl(tenant.getUpdateControl());
		TenantDO update = iTenantService.update(tenantDO);
		return update != null;
	}

	@Override
	public boolean delete(TenantId tenantId) {
		return iTenantService.deleteById(tenantId.getId());
	}

	@Override
	public boolean delete(TenantId id, IdCommand idCommand) {
		return iTenantService.deleteById(idCommand);
	}

	@Autowired
	public void setITenantService(ITenantService iTenantService) {
		this.iTenantService = iTenantService;
	}
}
