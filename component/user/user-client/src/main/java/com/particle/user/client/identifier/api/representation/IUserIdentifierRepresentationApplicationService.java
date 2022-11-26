package com.particle.user.client.identifier.api.representation;

import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryDetailForUpdateCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryDetailCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPageQueryCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierQueryListCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 用户登录标识 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IUserIdentifierRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param userIdentifierQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<UserIdentifierVO> queryDetailForUpdate(UserIdentifierQueryDetailForUpdateCommand userIdentifierQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param userIdentifierQueryDetailCommand
	 * @return
	 */
	SingleResponse<UserIdentifierVO> queryDetail(UserIdentifierQueryDetailCommand userIdentifierQueryDetailCommand);

	/**
	 * 列表查询
	 * @param userIdentifierQueryListCommand
	 * @return
	 */
	MultiResponse<UserIdentifierVO> queryList(UserIdentifierQueryListCommand userIdentifierQueryListCommand);

	/**
	 * 分页查询
	 * @param userIdentifierPageQueryCommand
	 * @return
	 */
	PageResponse<UserIdentifierVO> pageQuery(UserIdentifierPageQueryCommand userIdentifierPageQueryCommand);

}
