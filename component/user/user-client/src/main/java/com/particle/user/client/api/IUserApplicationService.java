package com.particle.user.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.command.UserUpdateInfoCommand;
import com.particle.user.client.dto.data.UserVO;
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

	/**
	 * 单纯更新用户信息
	 * 一般用户自己更新头像、昵称、姓名、性别等统一入口
	 * @param userUpdateInfoCommand
	 * @return
	 */
	Response updateUserInfo(UserUpdateInfoCommand userUpdateInfoCommand);

}
