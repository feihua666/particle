package com.particle.tenant.client.userinvite.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteCreateCommand;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUpdateCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteVO;

/**
 * <p>
 * 租户用户邀请 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
public interface ITenantUserInviteApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param tenantUserInviteCreateCommand
	 * @return
	 */
	SingleResponse<TenantUserInviteVO> create(TenantUserInviteCreateCommand tenantUserInviteCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<TenantUserInviteVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param tenantUserInviteUpdateCommand
	 * @return
	 */
	SingleResponse<TenantUserInviteVO> update(TenantUserInviteUpdateCommand tenantUserInviteUpdateCommand);

}
