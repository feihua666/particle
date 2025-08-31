package com.particle.role.client.roleuserrel.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelPageQueryCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryListCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;

/**
 * <p>
 * 角色用户关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IRoleUserRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param roleUserRelQueryDetailCommand
	 * @return
	 */
	SingleResponse<RoleUserRelVO> queryDetail(IdCommand roleUserRelQueryDetailCommand);

	/**
	 * 列表查询
	 * @param roleUserRelQueryListCommand
	 * @return
	 */
	MultiResponse<RoleUserRelVO> queryList(RoleUserRelQueryListCommand roleUserRelQueryListCommand);

	/**
	 * 分页查询
	 * @param roleUserRelPageQueryCommand
	 * @return
	 */
	PageResponse<RoleUserRelVO> pageQuery(RoleUserRelPageQueryCommand roleUserRelPageQueryCommand);


	/**
	 * 查询角色已分配的用户id
	 * @param roleIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryUserIdsByRoleId(IdCommand roleIdCommand);
	/**
	 * 角色类型字典id查询角色已分配的用户id
	 * @param roleTypeDictIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryUserIdsByRoleTypeDictId(IdCommand roleTypeDictIdCommand);

	/**
	 * 查询用户已分配的角色id
	 * @param userIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryRoleIdsByUserId(IdCommand userIdCommand);
}
