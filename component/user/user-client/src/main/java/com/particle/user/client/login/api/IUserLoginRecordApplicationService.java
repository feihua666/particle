package com.particle.user.client.login.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.login.dto.command.UserLoginRecordDeleteCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;

/**
 * <p>
 * 用户登录记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
public interface IUserLoginRecordApplicationService extends IBaseApplicationService {
	/**
	 * 删除领域对象
	 * @param userLoginRecordDeleteCommand
	 * @return
	 */
	SingleResponse<UserLoginRecordVO> delete(UserLoginRecordDeleteCommand userLoginRecordDeleteCommand);
}
