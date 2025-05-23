package com.particle.user.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.dto.command.representation.UserPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserQueryListCommand;
import com.particle.user.client.dto.data.UserVO;

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
	SingleResponse<UserVO> queryDetailForUpdate(IdCommand userQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param userQueryDetailCommand
	 * @return
	 */
	SingleResponse<UserVO> queryDetail(IdCommand userQueryDetailCommand);

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
