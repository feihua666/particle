package com.particle.user.client.login.api.representation;

import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryDetailForUpdateCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryDetailCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDevicePageQueryCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginDeviceQueryListCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 用户登录设备 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
public interface IUserLoginDeviceRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param userLoginDeviceQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<UserLoginDeviceVO> queryDetailForUpdate(UserLoginDeviceQueryDetailForUpdateCommand userLoginDeviceQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param userLoginDeviceQueryDetailCommand
	 * @return
	 */
	SingleResponse<UserLoginDeviceVO> queryDetail(UserLoginDeviceQueryDetailCommand userLoginDeviceQueryDetailCommand);

	/**
	 * 列表查询
	 * @param userLoginDeviceQueryListCommand
	 * @return
	 */
	MultiResponse<UserLoginDeviceVO> queryList(UserLoginDeviceQueryListCommand userLoginDeviceQueryListCommand);

	/**
	 * 分页查询
	 * @param userLoginDevicePageQueryCommand
	 * @return
	 */
	PageResponse<UserLoginDeviceVO> pageQuery(UserLoginDevicePageQueryCommand userLoginDevicePageQueryCommand);

}
