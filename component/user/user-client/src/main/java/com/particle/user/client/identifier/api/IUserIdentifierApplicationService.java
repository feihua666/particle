package com.particle.user.client.identifier.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.identifier.dto.command.UserIdentifierCreateCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierUpdateCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;

/**
 * <p>
 * 用户登录标识 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IUserIdentifierApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param userIdentifierCreateCommand
	 * @return
	 */
	SingleResponse<UserIdentifierVO> create(UserIdentifierCreateCommand userIdentifierCreateCommand, UserIdentifierPwdCommand userIdentifierPwdCommand);

	/**
	 * 仅绑定登录标识，一般用于绑定手机号和邮箱等三方登录
	 * @param userIdentifierCreateCommand
	 * @return
	 */
	SingleResponse<UserIdentifierVO> createBind(UserIdentifierCreateCommand userIdentifierCreateCommand);

	/**
	 * 删除领域对象
	 * @param userIdentifierDeleteCommand
	 * @return
	 */
	SingleResponse<UserIdentifierVO> delete(IdCommand userIdentifierDeleteCommand);

	/**
	 * 更新领域对象
	 * @param userIdentifierUpdateCommand
	 * @return
	 */
	SingleResponse<UserIdentifierVO> update(UserIdentifierUpdateCommand userIdentifierUpdateCommand);

}
