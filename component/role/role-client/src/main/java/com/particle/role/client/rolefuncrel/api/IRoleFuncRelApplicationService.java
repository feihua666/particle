package com.particle.role.client.rolefuncrel.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelCreateCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelUpdateCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;

/**
 * <p>
 * 角色菜单功能关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IRoleFuncRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param roleFuncRelCreateCommand
	 * @return
	 */
	SingleResponse<RoleFuncRelVO> create(RoleFuncRelCreateCommand roleFuncRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param roleFuncRelDeleteCommand
	 * @return
	 */
	SingleResponse<RoleFuncRelVO> delete(IdCommand roleFuncRelDeleteCommand);

	/**
	 * 更新领域对象
	 * @param roleFuncRelUpdateCommand
	 * @return
	 */
	SingleResponse<RoleFuncRelVO> update(RoleFuncRelUpdateCommand roleFuncRelUpdateCommand);

}
