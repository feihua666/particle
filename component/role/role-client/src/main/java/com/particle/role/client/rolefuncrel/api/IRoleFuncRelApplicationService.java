package com.particle.role.client.rolefuncrel.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelCreateCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.role.client.rolefuncrel.dto.command.FuncAssignRoleCommand;
import com.particle.role.client.rolefuncrel.dto.command.RoleAssignFuncCommand;

/**
 * <p>
 * 角色菜单功能关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IRoleFuncRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param roleFuncRelCreateCommand
	 * @return
	 */
	SingleResponse<RoleFuncRelVO> create(RoleFuncRelCreateCommand roleFuncRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param roleFuncRelDeleteCommand
	 * @return
	 */
	SingleResponse<RoleFuncRelVO> delete(IdCommand roleFuncRelDeleteCommand);


	/**
	 * 角色分配功能
	 * @param cf
	 * @return
	 */
	Response roleAssignFunc(RoleAssignFuncCommand cf);

	/**
	 * 功能分配角色
	 * @param cf
	 * @return
	 */
	Response funcAssignRole(FuncAssignRoleCommand cf);

	/**
	 * 根据角色id删除
	 * @param roleIdCommand
	 * @return
	 */
	public Response deleteByRoleId(IdCommand roleIdCommand);

	/**
	 * 根据功能id删除
	 * @param funcIdCommand
	 * @return
	 */
	public Response deleteByFuncId(IdCommand funcIdCommand);
}
