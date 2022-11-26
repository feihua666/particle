package com.particle.user.client.identifier.api;

import com.particle.user.client.identifier.dto.command.UserIdentifierCreateCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierDeleteCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierUpdateCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

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
	SingleResponse<UserIdentifierVO> create(UserIdentifierCreateCommand userIdentifierCreateCommand);

	/**
	 * 删除领域对象
	 * @param userIdentifierDeleteCommand
	 * @return
	 */
	SingleResponse<UserIdentifierVO> delete(UserIdentifierDeleteCommand userIdentifierDeleteCommand);

	/**
	 * 更新领域对象
	 * @param userIdentifierUpdateCommand
	 * @return
	 */
	SingleResponse<UserIdentifierVO> update(UserIdentifierUpdateCommand userIdentifierUpdateCommand);

}
