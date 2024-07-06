package com.particle.tenant.infrastructure.tenantfunc.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.tenant.domain.tenantfunc.TenantFunc;
import com.particle.tenant.domain.tenantfunc.TenantFuncId;
import com.particle.tenant.domain.tenantfunc.gateway.TenantFuncGateway;
import com.particle.tenant.infrastructure.tenantfunc.service.ITenantFuncService;
import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import com.particle.tenant.infrastructure.tenantfunc.structmapping.TenantFuncInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 租户功能菜单 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Component
public class TenantFuncGatewayImpl extends AbstractBaseGatewayImpl<TenantFuncId,TenantFunc> implements TenantFuncGateway {

	private ITenantFuncService iTenantFuncService;

	@Override
	public TenantFunc getById(TenantFuncId tenantFuncId) {
		TenantFuncDO byId = iTenantFuncService.getById(tenantFuncId.getId());
		TenantFunc tenantFunc = DomainFactory.create(TenantFunc.class);
		tenantFunc = TenantFuncInfrastructureStructMapping.instance. tenantFuncDOToTenantFunc(tenantFunc,byId);
		return tenantFunc;
	}

	@Override
	public boolean doSave(TenantFunc tenantFunc) {
		TenantFuncDO tenantFuncDO = TenantFuncInfrastructureStructMapping.instance.tenantFuncToTenantFuncDO(tenantFunc);
		if (tenantFuncDO.getId() == null) {
			tenantFuncDO.setAddControl(tenantFunc.getAddControl());
			TenantFuncDO add = iTenantFuncService.add(tenantFuncDO);
			tenantFunc.setId(TenantFuncId.of(add.getId()));
			return add != null;
		}
		tenantFuncDO.setUpdateControl(tenantFunc.getUpdateControl());
		TenantFuncDO update = iTenantFuncService.update(tenantFuncDO);
		return update != null;
	}

	@Override
	public boolean delete(TenantFuncId tenantFuncId) {
		return iTenantFuncService.deleteById(tenantFuncId.getId());
	}

	@Override
	public boolean delete(TenantFuncId id, IdCommand idCommand) {
		return iTenantFuncService.deleteById(idCommand);
	}

	@Autowired
	public void setITenantFuncService(ITenantFuncService iTenantFuncService) {
		this.iTenantFuncService = iTenantFuncService;
	}
}
