package com.particle.user.client.api;

import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;

/**
 * <p>
 * 用户 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IUserApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param userCreateCommand
	 * @return
	 */
	SingleResponse<UserVO> create(UserCreateCommand userCreateCommand, UserIdentifierPwdCommand userIdentifierPwdCommand);

	/**
	 * 删除领域对象
	 * @param userDeleteCommand
	 * @return
	 */
	SingleResponse<UserVO> delete(IdCommand userDeleteCommand);

	/**
	 * 更新领域对象
	 * @param userUpdateCommand
	 * @return
	 */
	SingleResponse<UserVO> update(UserUpdateCommand userUpdateCommand);

}
