package com.particle.role.client.rolefuncrel.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelPageQueryCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;

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

	/**
	 * 查询角色已分配的功能id
	 * @param roleIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryFuncIdsByRoleId(IdCommand roleIdCommand);

	/**
	 * 查询功能已分配的角色id
	 * @param funcIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryRoleIdsByFuncId(IdCommand funcIdCommand);
}
