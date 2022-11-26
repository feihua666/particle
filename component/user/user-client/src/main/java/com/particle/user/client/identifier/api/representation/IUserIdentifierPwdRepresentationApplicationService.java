package com.particle.user.client.identifier.api.representation;

import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdQueryDetailForUpdateCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdQueryDetailCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdPageQueryCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdQueryListCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 用户密码 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IUserIdentifierPwdRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param userIdentifierPwdQueryDetailForUpdateCommand
	 * @return
	 */
	SingleResponse<UserIdentifierPwdVO> queryDetailForUpdate(UserIdentifierPwdQueryDetailForUpdateCommand userIdentifierPwdQueryDetailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param userIdentifierPwdQueryDetailCommand
	 * @return
	 */
	SingleResponse<UserIdentifierPwdVO> queryDetail(UserIdentifierPwdQueryDetailCommand userIdentifierPwdQueryDetailCommand);

	/**
	 * 列表查询
	 * @param userIdentifierPwdQueryListCommand
	 * @return
	 */
	MultiResponse<UserIdentifierPwdVO> queryList(UserIdentifierPwdQueryListCommand userIdentifierPwdQueryListCommand);

	/**
	 * 分页查询
	 * @param userIdentifierPwdPageQueryCommand
	 * @return
	 */
	PageResponse<UserIdentifierPwdVO> pageQuery(UserIdentifierPwdPageQueryCommand userIdentifierPwdPageQueryCommand);

}
