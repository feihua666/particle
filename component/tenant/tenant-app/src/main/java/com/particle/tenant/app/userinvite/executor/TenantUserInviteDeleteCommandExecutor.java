package com.particle.tenant.app.userinvite.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.userinvite.structmapping.TenantUserInviteAppStructMapping;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteVO;
import com.particle.tenant.domain.userinvite.TenantUserInvite;
import com.particle.tenant.domain.userinvite.TenantUserInviteId;
import com.particle.tenant.domain.userinvite.gateway.TenantUserInviteGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 租户用户邀请 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Component
@Validated
public class TenantUserInviteDeleteCommandExecutor  extends AbstractBaseExecutor {

	private TenantUserInviteGateway tenantUserInviteGateway;

	/**
	 * 执行 租户用户邀请 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<TenantUserInviteVO> execute(@Valid IdCommand deleteCommand) {
		TenantUserInviteId tenantUserInviteId = TenantUserInviteId.of(deleteCommand.getId());
		TenantUserInvite byId = tenantUserInviteGateway.getById(tenantUserInviteId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = tenantUserInviteGateway.delete(tenantUserInviteId,deleteCommand);
		if (delete) {
			return SingleResponse.of(TenantUserInviteAppStructMapping.instance.toTenantUserInviteVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param tenantUserInviteGateway
	 */
	@Autowired
	public void setTenantUserInviteGateway(TenantUserInviteGateway tenantUserInviteGateway) {
		this.tenantUserInviteGateway = tenantUserInviteGateway;
	}
}
