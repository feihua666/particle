package com.particle.user.client.api.representation;

import com.particle.user.client.dto.command.representation.UserQueryDetailForUpdateCommand;
import com.particle.user.client.dto.command.representation.UserQueryDetailCommand;
import com.particle.user.client.dto.command.representation.UserPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserQueryListCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 用户 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IUserRepresentationApplicationService extends IBaseApplicationService {

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
