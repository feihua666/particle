package com.particle.tenant.app.userinvite.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.userinvite.structmapping.TenantUserInviteUserRecordAppStructMapping;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteUserRecordVO;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecord;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecordId;
import com.particle.tenant.domain.userinvite.gateway.TenantUserInviteUserRecordGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 租户用户邀请记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Component
@Validated
public class TenantUserInviteUserRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private TenantUserInviteUserRecordGateway tenantUserInviteUserRecordGateway;

	/**
	 * 执行 租户用户邀请记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<TenantUserInviteUserRecordVO> execute(@Valid IdCommand deleteCommand) {
		TenantUserInviteUserRecordId tenantUserInviteUserRecordId = TenantUserInviteUserRecordId.of(deleteCommand.getId());
		TenantUserInviteUserRecord byId = tenantUserInviteUserRecordGateway.getById(tenantUserInviteUserRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = tenantUserInviteUserRecordGateway.delete(tenantUserInviteUserRecordId);
		if (delete) {
			return SingleResponse.of(TenantUserInviteUserRecordAppStructMapping.instance.toTenantUserInviteUserRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param tenantUserInviteUserRecordGateway
	 */
	@Autowired
	public void setTenantUserInviteUserRecordGateway(TenantUserInviteUserRecordGateway tenantUserInviteUserRecordGateway) {
		this.tenantUserInviteUserRecordGateway = tenantUserInviteUserRecordGateway;
	}
}
