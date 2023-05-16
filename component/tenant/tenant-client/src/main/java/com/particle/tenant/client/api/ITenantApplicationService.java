package com.particle.tenant.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyAuditCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyAuditPassCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyCreateCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyUpdateCommand;
import com.particle.tenant.client.dto.command.TenantCreateCommand;
import com.particle.tenant.client.dto.command.TenantUpdateCommand;
import com.particle.tenant.client.dto.data.TenantVO;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 租户 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
public interface ITenantApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param tenantCreateCommand
	 * @return
	 */
	SingleResponse<TenantVO> create(TenantCreateCommand tenantCreateCommand);

	/**
	 * 一键添加租户及用户
	 * @param tenantCreateApplyCreateCommand 申请信息
	 * @param tenantCreateApplyAuditPassCommand 审核信息
	 * @return
	 */
	SingleResponse<TenantVO> oneClickCreate(TenantCreateApplyCreateCommand tenantCreateApplyCreateCommand, TenantCreateApplyAuditPassCommand tenantCreateApplyAuditPassCommand);
	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<TenantVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param tenantUpdateCommand
	 * @return
	 */
	SingleResponse<TenantVO> update(TenantUpdateCommand tenantUpdateCommand);

}
