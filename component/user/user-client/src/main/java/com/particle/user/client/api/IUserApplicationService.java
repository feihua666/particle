package com.particle.user.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.UserDeleteCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.data.UserVO;

/**
 * <p>
 * 后台管理用户 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface IUserApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param userCreateCommand
	 * @return
	 */
	SingleResponse<UserVO> create(UserCreateCommand userCreateCommand);

	/**
	 * 删除领域对象
	 * @param userDeleteCommand
	 * @return
	 */
	SingleResponse<UserVO> delete(UserDeleteCommand userDeleteCommand);

	/**
	 * 更新领域对象
	 * @param userUpdateCommand
	 * @return
	 */
	SingleResponse<UserVO> update(UserUpdateCommand userUpdateCommand);

}
