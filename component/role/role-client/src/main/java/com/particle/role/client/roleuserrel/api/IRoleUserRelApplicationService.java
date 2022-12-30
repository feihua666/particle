package com.particle.role.client.roleuserrel.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.roleuserrel.dto.command.UserAssignRoleCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleAssignUserCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelCreateCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;

/**
 * <p>
 * 角色用户关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IRoleUserRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param roleUserRelCreateCommand
	 * @return
	 */
	SingleResponse<RoleUserRelVO> create(RoleUserRelCreateCommand roleUserRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param roleUserRelDeleteCommand
	 * @return
	 */
	SingleResponse<RoleUserRelVO> delete(IdCommand roleUserRelDeleteCommand);
	

	/**
	 * 角色分配用户
	 * @param cf
	 * @return
	 */
	Response roleAssignUser(RoleAssignUserCommand cf);

	/**
	 * 用户分配角色
	 * @param cf
	 * @return
	 */
	Response userAssignRole(UserAssignRoleCommand cf);

	/**
	 * 根据角色id删除
	 * @param roleIdCommand
	 * @return
	 */
	public Response deleteByRoleId(IdCommand roleIdCommand);

	/**
	 * 根据用户id删除
	 * @param userIdCommand
	 * @return
	 */
	public Response deleteByUserId(IdCommand userIdCommand);
}
