package com.particle.tenant.infrastructure.tenantfuncapplication.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplication;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplicationId;
import com.particle.tenant.domain.tenantfuncapplication.gateway.TenantFuncApplicationGateway;
import com.particle.tenant.infrastructure.tenantfuncapplication.dos.TenantFuncApplicationDO;
import com.particle.tenant.infrastructure.tenantfuncapplication.service.ITenantFuncApplicationService;
import com.particle.tenant.infrastructure.tenantfuncapplication.structmapping.TenantFuncApplicationInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 租户功能应用 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Component
public class TenantFuncApplicationGatewayImpl extends AbstractBaseGatewayImpl<TenantFuncApplicationId,TenantFuncApplication> implements TenantFuncApplicationGateway {

	private ITenantFuncApplicationService iTenantFuncApplicationService;

	@Override
	public TenantFuncApplication getById(TenantFuncApplicationId tenantFuncApplicationId) {
		TenantFuncApplicationDO byId = iTenantFuncApplicationService.getById(tenantFuncApplicationId.getId());
		TenantFuncApplication tenantFuncApplication = DomainFactory.create(TenantFuncApplication.class);
		tenantFuncApplication = TenantFuncApplicationInfrastructureStructMapping.instance. tenantFuncApplicationDOToTenantFuncApplication(tenantFuncApplication,byId);
		return tenantFuncApplication;
	}

	@Override
	public boolean doSave(TenantFuncApplication tenantFuncApplication) {
		TenantFuncApplicationDO tenantFuncApplicationDO = TenantFuncApplicationInfrastructureStructMapping.instance.tenantFuncApplicationToTenantFuncApplicationDO(tenantFuncApplication);
		if (tenantFuncApplicationDO.getId() == null) {
			tenantFuncApplicationDO.setAddControl(tenantFuncApplication.getAddControl());
			TenantFuncApplicationDO add = iTenantFuncApplicationService.add(tenantFuncApplicationDO);
			tenantFuncApplication.setId(TenantFuncApplicationId.of(add.getId()));
			return add != null;
		}
		tenantFuncApplicationDO.setUpdateControl(tenantFuncApplication.getUpdateControl());
		TenantFuncApplicationDO update = iTenantFuncApplicationService.update(tenantFuncApplicationDO);
		return update != null;
	}

	@Override
	public boolean delete(TenantFuncApplicationId tenantFuncApplicationId) {
		return iTenantFuncApplicationService.deleteById(tenantFuncApplicationId.getId());
	}

	@Override
	public boolean delete(TenantFuncApplicationId id, IdCommand idCommand) {
		return iTenantFuncApplicationService.deleteById(idCommand);
	}

	@Autowired
	public void setITenantFuncApplicationService(ITenantFuncApplicationService iTenantFuncApplicationService) {
		this.iTenantFuncApplicationService = iTenantFuncApplicationService;
	}
}
