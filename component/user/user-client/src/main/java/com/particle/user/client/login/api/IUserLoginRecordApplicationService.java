package com.particle.user.client.login.api;

import com.particle.user.client.login.dto.command.UserLoginRecordCreateCommand;
import com.particle.user.client.login.dto.command.UserLoginRecordDeleteCommand;
import com.particle.user.client.login.dto.command.UserLoginRecordUpdateCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 用户登录记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
public interface IUserLoginRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param userLoginRecordCreateCommand
	 * @return
	 */
	SingleResponse<UserLoginRecordVO> create(UserLoginRecordCreateCommand userLoginRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param userLoginRecordDeleteCommand
	 * @return
	 */
	SingleResponse<UserLoginRecordVO> delete(UserLoginRecordDeleteCommand userLoginRecordDeleteCommand);

	/**
	 * 更新领域对象
	 * @param userLoginRecordUpdateCommand
	 * @return
	 */
	SingleResponse<UserLoginRecordVO> update(UserLoginRecordUpdateCommand userLoginRecordUpdateCommand);

}
