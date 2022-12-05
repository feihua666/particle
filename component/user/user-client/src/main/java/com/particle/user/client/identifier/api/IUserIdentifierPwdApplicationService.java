package com.particle.user.client.identifier.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCreateCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdUpdateCommand;
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
	SingleResponse<UserIdentifierPwdVO> create(UserIdentifierPwdCreateCommand userIdentifierPwdCreateCommand);

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
