package com.particle.role.client.roledatascoperel.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.roledatascoperel.dto.command.representation.RoleDataScopeRelPageQueryCommand;
import com.particle.role.client.roledatascoperel.dto.command.representation.RoleDataScopeRelQueryListCommand;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;

/**
 * <p>
 * 角色数据范围关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IRoleDataScopeRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<RoleDataScopeRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<RoleDataScopeRelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param roleDataScopeRelQueryListCommand
	 * @return
	 */
	MultiResponse<RoleDataScopeRelVO> queryList(RoleDataScopeRelQueryListCommand roleDataScopeRelQueryListCommand);

	/**
	 * 分页查询
	 * @param roleDataScopeRelPageQueryCommand
	 * @return
	 */
	PageResponse<RoleDataScopeRelVO> pageQuery(RoleDataScopeRelPageQueryCommand roleDataScopeRelPageQueryCommand);

	/**
	 * 查询角色已分配的数据范围id
	 * @param roleIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryDataScopeIdsByRoleId(IdCommand roleIdCommand);

	/**
	 * 查询数据范围已分配的角色id
	 * @param dataScopeIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryRoleIdsByDataScopeId(IdCommand dataScopeIdCommand);
}
