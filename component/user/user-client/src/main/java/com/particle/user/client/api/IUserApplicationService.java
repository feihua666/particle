package com.particle.user.client.api;

import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.UserQueryDetailForUpdateCommand;
import com.particle.user.client.dto.command.UserQueryDetailCommand;
import com.particle.user.client.dto.command.UserDeleteCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.command.UserPageQueryCommand;
import com.particle.user.client.dto.command.UserQueryListCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 后台管理用户 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface IUserApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param userCreateCommand
	 * @return
	 */
	SingleResponse<UserVO> create(UserCreateCommand userCreateCommand);

	/**
	 * 删除领域对象
	 * @param userDeleteCommand
	 * @return
	 */
	SingleResponse<UserVO> delete(UserDeleteCommand userDeleteCommand);

	/**
	 * 更新领域对象
	 * @param userUpdateCommand
	 * @return
	 */
	SingleResponse<UserVO> update(UserUpdateCommand userUpdateCommand);

	/**
	 * 查询详情，仅更新时使用
	 * @param userQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<UserVO> queryDetailForUpdate(UserQueryDetailForUpdateCommand userQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param userQueryDetailCommand
	 * @return
	 */
	SingleResponse<UserVO> queryDetail(UserQueryDetailCommand userQueryDetailCommand);

	/**
	 * 列表查询
	 * @param userQueryListCommand
	 * @return
	 */
	MultiResponse<UserVO> queryList(UserQueryListCommand userQueryListCommand);

	/**
	 * 分页查询
	 * @param userPageQueryCommand
	 * @return
	 */
	PageResponse<UserVO> pageQuery(UserPageQueryCommand userPageQueryCommand);

}
