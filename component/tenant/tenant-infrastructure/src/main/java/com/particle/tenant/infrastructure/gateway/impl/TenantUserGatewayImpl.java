package com.particle.tenant.infrastructure.gateway.impl;

import com.particle.tenant.domain.TenantUser;
import com.particle.tenant.domain.TenantUserId;
import com.particle.tenant.domain.gateway.TenantUserGateway;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.structmapping.TenantUserInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 租户用户 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Component
public class TenantUserGatewayImpl extends AbstractBaseGatewayImpl<TenantUserId,TenantUser> implements TenantUserGateway {

	private ITenantUserService iTenantUserService;

	@Override
	public TenantUser getById(TenantUserId tenantUserId) {
		TenantUserDO byId = iTenantUserService.getById(tenantUserId.getId());
		TenantUser tenantUser = DomainFactory.create(TenantUser.class);
		tenantUser = TenantUserInfrastructureStructMapping.instance. tenantUserDOToTenantUser(tenantUser,byId);
		return tenantUser;
	}

	@Override
	public boolean doSave(TenantUser tenantUser) {
		TenantUserDO tenantUserDO = TenantUserInfrastructureStructMapping.instance.tenantUserToTenantUserDO(tenantUser);
		if (tenantUserDO.getId() == null) {
			tenantUserDO.setAddControl(tenantUser.getAddControl());
			TenantUserDO add = iTenantUserService.add(tenantUserDO);
			tenantUser.setId(TenantUserId.of(add.getId()));
			return add != null;
		}
		tenantUserDO.setUpdateControl(tenantUser.getUpdateControl());
		TenantUserDO update = iTenantUserService.update(tenantUserDO);
		return update != null;
	}

	@Override
	public boolean delete(TenantUserId tenantUserId) {
		return iTenantUserService.deleteById(tenantUserId.getId());
	}


	@Autowired
	public void setITenantUserService(ITenantUserService iTenantUserService) {
		this.iTenantUserService = iTenantUserService;
	}
}
