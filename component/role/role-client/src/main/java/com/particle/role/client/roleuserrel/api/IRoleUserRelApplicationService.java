package com.particle.role.client.roleuserrel.api;

import com.particle.role.client.roleuserrel.dto.command.RoleUserRelCreateCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelDeleteCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelUpdateCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

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
	SingleResponse<RoleUserRelVO> delete(RoleUserRelDeleteCommand roleUserRelDeleteCommand);

	/**
	 * 更新领域对象
	 * @param roleUserRelUpdateCommand
	 * @return
	 */
	SingleResponse<RoleUserRelVO> update(RoleUserRelUpdateCommand roleUserRelUpdateCommand);

}
