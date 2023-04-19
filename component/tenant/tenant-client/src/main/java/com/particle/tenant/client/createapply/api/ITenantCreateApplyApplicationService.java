package com.particle.tenant.client.createapply.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyCreateCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyUpdateCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;

/**
 * <p>
 * 租户创建申请 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
public interface ITenantCreateApplyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param tenantCreateApplyCreateCommand
	 * @return
	 */
	SingleResponse<TenantCreateApplyVO> create(TenantCreateApplyCreateCommand tenantCreateApplyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<TenantCreateApplyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	SingleResponse<TenantCreateApplyVO> update(TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand);

}
