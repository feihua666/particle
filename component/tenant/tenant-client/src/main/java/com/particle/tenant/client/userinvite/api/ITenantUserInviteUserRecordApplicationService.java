package com.particle.tenant.client.userinvite.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUserRecordCreateCommand;
import com.particle.tenant.client.userinvite.dto.command.TenantUserInviteUserRecordUpdateCommand;
import com.particle.tenant.client.userinvite.dto.data.TenantUserInviteUserRecordVO;

/**
 * <p>
 * 租户用户邀请记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
public interface ITenantUserInviteUserRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param tenantUserInviteUserRecordCreateCommand
	 * @return
	 */
	SingleResponse<TenantUserInviteUserRecordVO> create(TenantUserInviteUserRecordCreateCommand tenantUserInviteUserRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<TenantUserInviteUserRecordVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param tenantUserInviteUserRecordUpdateCommand
	 * @return
	 */
	SingleResponse<TenantUserInviteUserRecordVO> update(TenantUserInviteUserRecordUpdateCommand tenantUserInviteUserRecordUpdateCommand);

}
