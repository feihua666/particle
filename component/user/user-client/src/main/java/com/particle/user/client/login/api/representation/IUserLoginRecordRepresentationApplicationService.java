package com.particle.user.client.login.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordPageQueryCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryDetailCommand;
import com.particle.user.client.login.dto.command.representation.UserLoginRecordQueryListCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;

/**
 * <p>
 * 用户登录记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
public interface IUserLoginRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param userLoginRecordQueryDetailCommand
	 * @return
	 */
	SingleResponse<UserLoginRecordVO> queryDetail(UserLoginRecordQueryDetailCommand userLoginRecordQueryDetailCommand);

	/**
	 * 列表查询
	 * @param userLoginRecordQueryListCommand
	 * @return
	 */
	MultiResponse<UserLoginRecordVO> queryList(UserLoginRecordQueryListCommand userLoginRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param userLoginRecordPageQueryCommand
	 * @return
	 */
	PageResponse<UserLoginRecordVO> pageQuery(UserLoginRecordPageQueryCommand userLoginRecordPageQueryCommand);

}
