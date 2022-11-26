package com.particle.role.client.api.representation;

import com.particle.role.client.dto.command.representation.RoleQueryDetailForUpdateCommand;
import com.particle.role.client.dto.command.representation.RoleQueryDetailCommand;
import com.particle.role.client.dto.command.representation.RolePageQueryCommand;
import com.particle.role.client.dto.command.representation.RoleQueryListCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 角色 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IRoleRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param roleQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<RoleVO> queryDetailForUpdate(RoleQueryDetailForUpdateCommand roleQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param roleQueryDetailCommand
	 * @return
	 */
	SingleResponse<RoleVO> queryDetail(RoleQueryDetailCommand roleQueryDetailCommand);

	/**
	 * 列表查询
	 * @param roleQueryListCommand
	 * @return
	 */
	MultiResponse<RoleVO> queryList(RoleQueryListCommand roleQueryListCommand);

	/**
	 * 分页查询
	 * @param rolePageQueryCommand
	 * @return
	 */
	PageResponse<RoleVO> pageQuery(RolePageQueryCommand rolePageQueryCommand);

}
