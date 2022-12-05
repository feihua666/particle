package com.particle.role.client.roleuserrel.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelCreateCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelUpdateCommand;
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
	 * 更新领域对象
	 * @param roleUserRelUpdateCommand
	 * @return
	 */
	SingleResponse<RoleUserRelVO> update(RoleUserRelUpdateCommand roleUserRelUpdateCommand);

}
