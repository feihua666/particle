package com.particle.tenant.infrastructure.userinvite.gateway.impl;

import com.particle.tenant.domain.userinvite.TenantUserInvite;
import com.particle.tenant.domain.userinvite.TenantUserInviteId;
import com.particle.tenant.domain.userinvite.gateway.TenantUserInviteGateway;
import com.particle.tenant.infrastructure.userinvite.service.ITenantUserInviteService;
import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteDO;
import com.particle.tenant.infrastructure.userinvite.structmapping.TenantUserInviteInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 租户用户邀请 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Component
public class TenantUserInviteGatewayImpl extends AbstractBaseGatewayImpl<TenantUserInviteId,TenantUserInvite> implements TenantUserInviteGateway {

	private ITenantUserInviteService iTenantUserInviteService;

	@Override
	public TenantUserInvite getById(TenantUserInviteId tenantUserInviteId) {
		TenantUserInviteDO byId = iTenantUserInviteService.getById(tenantUserInviteId.getId());
		TenantUserInvite tenantUserInvite = DomainFactory.create(TenantUserInvite.class);
		tenantUserInvite = TenantUserInviteInfrastructureStructMapping.instance. tenantUserInviteDOToTenantUserInvite(tenantUserInvite,byId);
		return tenantUserInvite;
	}

	@Override
	public boolean doSave(TenantUserInvite tenantUserInvite) {
		TenantUserInviteDO tenantUserInviteDO = TenantUserInviteInfrastructureStructMapping.instance.tenantUserInviteToTenantUserInviteDO(tenantUserInvite);
		if (tenantUserInviteDO.getId() == null) {
			tenantUserInviteDO.setAddControl(tenantUserInvite.getAddControl());
			TenantUserInviteDO add = iTenantUserInviteService.add(tenantUserInviteDO);
			tenantUserInvite.setId(TenantUserInviteId.of(add.getId()));
			return add != null;
		}
		tenantUserInviteDO.setUpdateControl(tenantUserInvite.getUpdateControl());
		TenantUserInviteDO update = iTenantUserInviteService.update(tenantUserInviteDO);
		return update != null;
	}

	@Override
	public boolean delete(TenantUserInviteId tenantUserInviteId) {
		return iTenantUserInviteService.deleteById(tenantUserInviteId.getId());
	}


	@Autowired
	public void setITenantUserInviteService(ITenantUserInviteService iTenantUserInviteService) {
		this.iTenantUserInviteService = iTenantUserInviteService;
	}
}
