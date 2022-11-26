package com.particle.role.client.api;

import com.particle.role.client.dto.command.RoleCreateCommand;
import com.particle.role.client.dto.command.RoleDeleteCommand;
import com.particle.role.client.dto.command.RoleUpdateCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 角色 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IRoleApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param roleCreateCommand
	 * @return
	 */
	SingleResponse<RoleVO> create(RoleCreateCommand roleCreateCommand);

	/**
	 * 删除领域对象
	 * @param roleDeleteCommand
	 * @return
	 */
	SingleResponse<RoleVO> delete(RoleDeleteCommand roleDeleteCommand);

	/**
	 * 更新领域对象
	 * @param roleUpdateCommand
	 * @return
	 */
	SingleResponse<RoleVO> update(RoleUpdateCommand roleUpdateCommand);

}
