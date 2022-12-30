package com.particle.user.client.login.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.login.dto.command.UserLoginDeviceDeleteCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;

/**
 * <p>
 * 用户登录设备 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
public interface IUserLoginDeviceApplicationService extends IBaseApplicationService {

	/**
	 * 删除领域对象
	 * @param userLoginDeviceDeleteCommand
	 * @return
	 */
	SingleResponse<UserLoginDeviceVO> delete(UserLoginDeviceDeleteCommand userLoginDeviceDeleteCommand);

}
