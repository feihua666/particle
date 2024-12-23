package com.particle.user.app.login.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.user.app.login.structmapping.UserLoginRecordAppStructMapping;
import com.particle.user.client.login.dto.command.UserLoginRecordDeleteCommand;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import com.particle.user.domain.login.UserLoginRecord;
import com.particle.user.domain.login.UserLoginRecordId;
import com.particle.user.domain.login.gateway.UserLoginRecordGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 用户登录记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Component
@Validated
public class UserLoginRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UserLoginRecordGateway userLoginRecordGateway;

	/**
	 * 执行 用户登录记录 删除指令
	 * @param userLoginRecordDeleteCommand
	 * @return
	 */
	public SingleResponse<UserLoginRecordVO> execute(@Valid UserLoginRecordDeleteCommand userLoginRecordDeleteCommand) {
		UserLoginRecordId userLoginRecordId = UserLoginRecordId.of(userLoginRecordDeleteCommand.getId());
		UserLoginRecord byId = userLoginRecordGateway.getById(userLoginRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = userLoginRecordGateway.delete(userLoginRecordId,userLoginRecordDeleteCommand);
		if (delete) {
			return SingleResponse.of(UserLoginRecordAppStructMapping.instance.toUserLoginRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param userLoginRecordGateway
	 */
	@Autowired
	public void setUserLoginRecordGateway(UserLoginRecordGateway userLoginRecordGateway) {
		this.userLoginRecordGateway = userLoginRecordGateway;
	}
}
