package com.particle.user.app.identifier.executor;

import com.particle.user.app.identifier.structmapping.UserIdentifierPwdAppStructMapping;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCreateCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.user.domain.identifier.UserIdentifierPwd;
import com.particle.user.domain.identifier.gateway.UserIdentifierPwdGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 用户密码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserIdentifierPwdCreateCommandExecutor  extends AbstractBaseExecutor {

	private UserIdentifierPwdGateway userIdentifierPwdGateway;

	/**
	 * 执行用户密码添加指令
	 * @param userIdentifierPwdCreateCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierPwdVO> execute(@Valid UserIdentifierPwdCreateCommand userIdentifierPwdCreateCommand,@Valid UserIdentifierPwdCommand userIdentifierPwdCommand) {

		UserIdentifierPwd userIdentifierPwd = UserIdentifierPwd.create(userIdentifierPwdCreateCommand.getUserId(), userIdentifierPwdCreateCommand.getIdentifierId(),
				userIdentifierPwdCommand.getPwdEncoded(),
				userIdentifierPwdCommand.getPwdEncryptFlag(),
				userIdentifierPwdCommand.getPwdComplexity(),
				userIdentifierPwdCommand.getIsPwdExpired(), userIdentifierPwdCommand.getPwdExpiredReason(), userIdentifierPwdCommand.getPwdExpireAt(),
				userIdentifierPwdCommand.getIsPwdNeedUpdate(), userIdentifierPwdCommand.getPwdNeedUpdateMessage());
		boolean save = userIdentifierPwdGateway.save(userIdentifierPwd);
		if (save) {
			return SingleResponse.of(UserIdentifierPwdAppStructMapping.instance.toUserIdentifierPwdVO(userIdentifierPwd));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param userIdentifierPwdGateway
	 */
	@Autowired
	public void setUserIdentifierPwdGateway(UserIdentifierPwdGateway userIdentifierPwdGateway) {
		this.userIdentifierPwdGateway = userIdentifierPwdGateway;
	}
}
