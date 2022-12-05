package com.particle.role.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.dto.command.representation.RolePageQueryCommand;
import com.particle.role.client.dto.command.representation.RoleQueryListCommand;
import com.particle.role.client.dto.data.RoleVO;

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
	SingleResponse<RoleVO> queryDetailForUpdate(IdCommand roleQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param roleQueryDetailCommand
	 * @return
	 */
	SingleResponse<RoleVO> queryDetail(IdCommand roleQueryDetailCommand);

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
