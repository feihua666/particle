package com.particle.user.app.login.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.user.app.login.structmapping.UserLoginDeviceAppStructMapping;
import com.particle.user.client.login.dto.command.UserLoginDeviceDeleteCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.user.domain.login.UserLoginDevice;
import com.particle.user.domain.login.UserLoginDeviceId;
import com.particle.user.domain.login.gateway.UserLoginDeviceGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 用户登录设备 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Component
@Validated
public class UserLoginDeviceDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UserLoginDeviceGateway userLoginDeviceGateway;

	/**
	 * 执行 用户登录设备 删除指令
	 * @param userLoginDeviceDeleteCommand
	 * @return
	 */
	public SingleResponse<UserLoginDeviceVO> execute(@Valid UserLoginDeviceDeleteCommand userLoginDeviceDeleteCommand) {
		UserLoginDeviceId userLoginDeviceId = UserLoginDeviceId.of(userLoginDeviceDeleteCommand.getId());
		UserLoginDevice byId = userLoginDeviceGateway.getById(userLoginDeviceId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = userLoginDeviceGateway.delete(userLoginDeviceId,userLoginDeviceDeleteCommand);
		if (delete) {
			return SingleResponse.of(UserLoginDeviceAppStructMapping.instance.toUserLoginDeviceVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param userLoginDeviceGateway
	 */
	@Autowired
	public void setUserLoginDeviceGateway(UserLoginDeviceGateway userLoginDeviceGateway) {
		this.userLoginDeviceGateway = userLoginDeviceGateway;
	}
}
