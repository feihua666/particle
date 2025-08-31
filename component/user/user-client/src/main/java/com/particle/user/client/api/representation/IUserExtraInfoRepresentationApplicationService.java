package com.particle.user.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.dto.command.representation.UserExtraInfoPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserExtraInfoQueryListCommand;
import com.particle.user.client.dto.data.UserExtraInfoVO;

/**
 * <p>
 * 用户扩展信息 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IUserExtraInfoRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<UserExtraInfoVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<UserExtraInfoVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param userExtraInfoQueryListCommand
	 * @return
	 */
	MultiResponse<UserExtraInfoVO> queryList(UserExtraInfoQueryListCommand userExtraInfoQueryListCommand);

	/**
	 * 分页查询
	 * @param userExtraInfoPageQueryCommand
	 * @return
	 */
	PageResponse<UserExtraInfoVO> pageQuery(UserExtraInfoPageQueryCommand userExtraInfoPageQueryCommand);

}
