package com.particle.role.client.rolefuncrel.api.representation;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelPageQueryCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 角色菜单功能关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IRoleFuncRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param roleFuncRelQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<RoleFuncRelVO> queryDetailForUpdate(IdCommand roleFuncRelQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param roleFuncRelQueryDetailCommand
	 * @return
	 */
	SingleResponse<RoleFuncRelVO> queryDetail(IdCommand roleFuncRelQueryDetailCommand);

	/**
	 * 列表查询
	 * @param roleFuncRelQueryListCommand
	 * @return
	 */
	MultiResponse<RoleFuncRelVO> queryList(RoleFuncRelQueryListCommand roleFuncRelQueryListCommand);

	/**
	 * 分页查询
	 * @param roleFuncRelPageQueryCommand
	 * @return
	 */
	PageResponse<RoleFuncRelVO> pageQuery(RoleFuncRelPageQueryCommand roleFuncRelPageQueryCommand);

}
