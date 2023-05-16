package com.particle.user.client.identifier.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.identifier.dto.command.*;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;

/**
 * <p>
 * 用户密码 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IUserIdentifierPwdApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param userIdentifierPwdCreateCommand
	 * @return
	 */
	SingleResponse<UserIdentifierPwdVO> create(UserIdentifierPwdCreateCommand userIdentifierPwdCreateCommand, UserIdentifierPwdCommand userIdentifierPwdCommand);

	/**
	 * 重置用户密码，根据 identifier重置
	 * @param userIdentifierResetPasswordCommand
	 * @return
	 */
	Response identifierResetPassword(UserIdentifierResetPasswordCommand userIdentifierResetPasswordCommand);

	/**
	 * 重置用户密码，根据用户id重置，将用户所以账号密码重置成一样的
	 * @param userResetPasswordCommand
	 * @return
	 */
	Response userResetPassword(UserResetPwdCommand userResetPasswordCommand);

	/**
	 * 删除领域对象
	 * @param userIdentifierPwdDeleteCommand
	 * @return
	 */
	SingleResponse<UserIdentifierPwdVO> delete(IdCommand userIdentifierPwdDeleteCommand);

	/**
	 * 更新领域对象
	 * @param userIdentifierPwdUpdateCommand
	 * @return
	 */
	SingleResponse<UserIdentifierPwdVO> update(UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand);

}
