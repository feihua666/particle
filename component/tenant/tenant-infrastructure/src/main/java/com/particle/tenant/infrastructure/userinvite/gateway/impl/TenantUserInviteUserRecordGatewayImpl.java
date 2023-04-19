package com.particle.tenant.infrastructure.userinvite.gateway.impl;

import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecord;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecordId;
import com.particle.tenant.domain.userinvite.gateway.TenantUserInviteUserRecordGateway;
import com.particle.tenant.infrastructure.userinvite.service.ITenantUserInviteUserRecordService;
import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteUserRecordDO;
import com.particle.tenant.infrastructure.userinvite.structmapping.TenantUserInviteUserRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 租户用户邀请记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Component
public class TenantUserInviteUserRecordGatewayImpl extends AbstractBaseGatewayImpl<TenantUserInviteUserRecordId,TenantUserInviteUserRecord> implements TenantUserInviteUserRecordGateway {

	private ITenantUserInviteUserRecordService iTenantUserInviteUserRecordService;

	@Override
	public TenantUserInviteUserRecord getById(TenantUserInviteUserRecordId tenantUserInviteUserRecordId) {
		TenantUserInviteUserRecordDO byId = iTenantUserInviteUserRecordService.getById(tenantUserInviteUserRecordId.getId());
		TenantUserInviteUserRecord tenantUserInviteUserRecord = DomainFactory.create(TenantUserInviteUserRecord.class);
		tenantUserInviteUserRecord = TenantUserInviteUserRecordInfrastructureStructMapping.instance. tenantUserInviteUserRecordDOToTenantUserInviteUserRecord(tenantUserInviteUserRecord,byId);
		return tenantUserInviteUserRecord;
	}

	@Override
	public boolean doSave(TenantUserInviteUserRecord tenantUserInviteUserRecord) {
		TenantUserInviteUserRecordDO tenantUserInviteUserRecordDO = TenantUserInviteUserRecordInfrastructureStructMapping.instance.tenantUserInviteUserRecordToTenantUserInviteUserRecordDO(tenantUserInviteUserRecord);
		if (tenantUserInviteUserRecordDO.getId() == null) {
			tenantUserInviteUserRecordDO.setAddControl(tenantUserInviteUserRecord.getAddControl());
			TenantUserInviteUserRecordDO add = iTenantUserInviteUserRecordService.add(tenantUserInviteUserRecordDO);
			tenantUserInviteUserRecord.setId(TenantUserInviteUserRecordId.of(add.getId()));
			return add != null;
		}
		tenantUserInviteUserRecordDO.setUpdateControl(tenantUserInviteUserRecord.getUpdateControl());
		TenantUserInviteUserRecordDO update = iTenantUserInviteUserRecordService.update(tenantUserInviteUserRecordDO);
		return update != null;
	}

	@Override
	public boolean delete(TenantUserInviteUserRecordId tenantUserInviteUserRecordId) {
		return iTenantUserInviteUserRecordService.deleteById(tenantUserInviteUserRecordId.getId());
	}


	@Autowired
	public void setITenantUserInviteUserRecordService(ITenantUserInviteUserRecordService iTenantUserInviteUserRecordService) {
		this.iTenantUserInviteUserRecordService = iTenantUserInviteUserRecordService;
	}
}
