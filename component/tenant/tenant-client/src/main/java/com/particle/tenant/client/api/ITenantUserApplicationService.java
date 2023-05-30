package com.particle.tenant.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.command.TenantUserUpdateCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;

/**
 * <p>
 * 租户用户 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
public interface ITenantUserApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param tenantUserCreateCommand
	 * @return
	 */
	SingleResponse<TenantUserVO> create(TenantUserCreateCommand tenantUserCreateCommand);
	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<TenantUserVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param tenantUserUpdateCommand
	 * @return
	 */
	SingleResponse<TenantUserVO> update(TenantUserUpdateCommand tenantUserUpdateCommand);

}
