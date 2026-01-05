package com.particle.componentadmin.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.componentadmin.client.dto.command.AdminComponentDependencyCreateCommand;
import com.particle.componentadmin.client.dto.command.AdminComponentDependencyUpdateCommand;
import com.particle.componentadmin.client.dto.data.AdminComponentDependencyVO;
import com.particle.componentadmin.client.dto.command.ComponentAssignDependComponentCommand;
import com.particle.componentadmin.client.dto.command.DependComponentAssignComponentCommand;
/**
 * <p>
 * 组件依赖关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
public interface IAdminComponentDependencyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param adminComponentDependencyCreateCommand
	 * @return
	 */
	SingleResponse<AdminComponentDependencyVO> create(AdminComponentDependencyCreateCommand adminComponentDependencyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AdminComponentDependencyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param adminComponentDependencyUpdateCommand
	 * @return
	 */
	SingleResponse<AdminComponentDependencyVO> update(AdminComponentDependencyUpdateCommand adminComponentDependencyUpdateCommand);


	/**
	 * 源组件分配依赖组件
	 * @param cf
	 * @return
	 */
	Response componentAssignDependComponent(ComponentAssignDependComponentCommand cf);

	/**
	 * 依赖组件分配源组件
	 * @param cf
	 * @return
	 */
	Response dependComponentAssignComponent(DependComponentAssignComponentCommand cf);

	/**
	 * 根据源组件id删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByComponentId(IdCommand idCommand);

	/**
	 * 根据依赖组件id删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByDependComponentId(IdCommand idCommand);

}
