package com.particle.user.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.user.client.dto.command.UserExtraInfoCreateCommand;
import com.particle.user.client.dto.command.UserExtraInfoUpdateCommand;
import com.particle.user.client.dto.data.UserExtraInfoVO;
/**
 * <p>
 * 用户扩展信息 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
public interface IUserExtraInfoApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param userExtraInfoCreateCommand
	 * @return
	 */
	SingleResponse<UserExtraInfoVO> create(UserExtraInfoCreateCommand userExtraInfoCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<UserExtraInfoVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param userExtraInfoUpdateCommand
	 * @return
	 */
	SingleResponse<UserExtraInfoVO> update(UserExtraInfoUpdateCommand userExtraInfoUpdateCommand);
}
