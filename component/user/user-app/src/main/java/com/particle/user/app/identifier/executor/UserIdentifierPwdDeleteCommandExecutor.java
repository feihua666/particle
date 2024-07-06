package com.particle.user.app.identifier.executor;

import com.particle.user.app.identifier.structmapping.UserIdentifierPwdAppStructMapping;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.user.domain.identifier.UserIdentifierPwd;
import com.particle.user.domain.identifier.UserIdentifierPwdId;
import com.particle.user.domain.identifier.gateway.UserIdentifierPwdGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
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
public class UserIdentifierPwdDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UserIdentifierPwdGateway userIdentifierPwdGateway;

	/**
	 * 执行 用户密码 删除指令
	 * @param userIdentifierPwdDeleteCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierPwdVO> execute(@Valid IdCommand userIdentifierPwdDeleteCommand) {
		UserIdentifierPwdId userIdentifierPwdId = UserIdentifierPwdId.of(userIdentifierPwdDeleteCommand.getId());
		UserIdentifierPwd byId = userIdentifierPwdGateway.getById(userIdentifierPwdId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = userIdentifierPwdGateway.delete(userIdentifierPwdId,userIdentifierPwdDeleteCommand);
		if (delete) {
			return SingleResponse.of(UserIdentifierPwdAppStructMapping.instance.toUserIdentifierPwdVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
