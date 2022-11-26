package com.particle.user.client.login.api;

import com.particle.user.client.login.dto.command.UserLoginDeviceCreateCommand;
import com.particle.user.client.login.dto.command.UserLoginDeviceDeleteCommand;
import com.particle.user.client.login.dto.command.UserLoginDeviceUpdateCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

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
	 * 添加/创建一个领域对象
	 * @param userLoginDeviceCreateCommand
	 * @return
	 */
	SingleResponse<UserLoginDeviceVO> create(UserLoginDeviceCreateCommand userLoginDeviceCreateCommand);

	/**
	 * 删除领域对象
	 * @param userLoginDeviceDeleteCommand
	 * @return
	 */
	SingleResponse<UserLoginDeviceVO> delete(UserLoginDeviceDeleteCommand userLoginDeviceDeleteCommand);

	/**
	 * 更新领域对象
	 * @param userLoginDeviceUpdateCommand
	 * @return
	 */
	SingleResponse<UserLoginDeviceVO> update(UserLoginDeviceUpdateCommand userLoginDeviceUpdateCommand);

}
