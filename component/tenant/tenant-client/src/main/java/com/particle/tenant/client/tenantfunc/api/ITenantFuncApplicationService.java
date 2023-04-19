package com.particle.tenant.client.tenantfunc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.tenantfunc.dto.command.TenantAssignFuncCommand;
import com.particle.tenant.client.tenantfunc.dto.command.TenantFuncCreateCommand;
import com.particle.tenant.client.tenantfunc.dto.command.TenantFuncUpdateCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;

/**
 * <p>
 * 租户功能菜单 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
public interface ITenantFuncApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param tenantFuncCreateCommand
	 * @return
	 */
	SingleResponse<TenantFuncVO> create(TenantFuncCreateCommand tenantFuncCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<TenantFuncVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param tenantFuncUpdateCommand
	 * @return
	 */
	SingleResponse<TenantFuncVO> update(TenantFuncUpdateCommand tenantFuncUpdateCommand);

	/**
	 * 租户分配应用
	 * @param cf
	 * @return
	 */
	public Response tenantAssignFunc(TenantAssignFuncCommand cf);
}
