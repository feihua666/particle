package com.particle.tenant.client.tenantfuncapplication.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantAssignFuncApplicationCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantFuncApplicationCreateCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.command.TenantFuncApplicationUpdateCommand;
import com.particle.tenant.client.tenantfuncapplication.dto.data.TenantFuncApplicationVO;

/**
 * <p>
 * 租户功能应用 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
public interface ITenantFuncApplicationApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param tenantFuncApplicationCreateCommand
	 * @return
	 */
	SingleResponse<TenantFuncApplicationVO> create(TenantFuncApplicationCreateCommand tenantFuncApplicationCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<TenantFuncApplicationVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param tenantFuncApplicationUpdateCommand
	 * @return
	 */
	SingleResponse<TenantFuncApplicationVO> update(TenantFuncApplicationUpdateCommand tenantFuncApplicationUpdateCommand);

	/**
	 * 租户分配功能应用
	 * @param cf
	 * @return
	 */
	public Response tenantAssignFuncApplication(TenantAssignFuncApplicationCommand cf);

}
