package com.particle.role.client.roledatascoperel.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.role.client.roledatascoperel.dto.command.RoleDataScopeRelCreateCommand;
import com.particle.role.client.roledatascoperel.dto.command.RoleDataScopeRelUpdateCommand;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;
import com.particle.role.client.roledatascoperel.dto.command.RoleAssignDataScopeCommand;
import com.particle.role.client.roledatascoperel.dto.command.DataScopeAssignRoleCommand;
/**
 * <p>
 * 角色数据范围关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
public interface IRoleDataScopeRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param roleDataScopeRelCreateCommand
	 * @return
	 */
	SingleResponse<RoleDataScopeRelVO> create(RoleDataScopeRelCreateCommand roleDataScopeRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<RoleDataScopeRelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param roleDataScopeRelUpdateCommand
	 * @return
	 */
	SingleResponse<RoleDataScopeRelVO> update(RoleDataScopeRelUpdateCommand roleDataScopeRelUpdateCommand);


	/**
	 * 角色分配数据范围
	 * @param cf
	 * @return
	 */
	Response roleAssignDataScope(RoleAssignDataScopeCommand cf);

	/**
	 * 数据范围分配角色
	 * @param cf
	 * @return
	 */
	Response dataScopeAssignRole(DataScopeAssignRoleCommand cf);

	/**
	 * 根据角色id删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByRoleId(IdCommand idCommand);

	/**
	 * 根据数据范围id删除
	 * @param idCommand
	 * @return
	 */
	public Response deleteByDataScopeId(IdCommand idCommand);

}
