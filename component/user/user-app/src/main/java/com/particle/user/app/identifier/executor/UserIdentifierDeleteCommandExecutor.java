package com.particle.user.app.identifier.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.user.app.identifier.structmapping.UserIdentifierAppStructMapping;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.user.domain.identifier.UserIdentifier;
import com.particle.user.domain.identifier.UserIdentifierId;
import com.particle.user.domain.identifier.gateway.UserIdentifierGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 用户登录标识 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserIdentifierDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UserIdentifierGateway userIdentifierGateway;

	/**
	 * 执行 用户登录标识 删除指令
	 * @param userIdentifierDeleteCommand
	 * @return
	 */
	public SingleResponse<UserIdentifierVO> execute(@Valid IdCommand userIdentifierDeleteCommand) {
		UserIdentifierId userIdentifierId = UserIdentifierId.of(userIdentifierDeleteCommand.getId());
		UserIdentifier byId = userIdentifierGateway.getById(userIdentifierId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = userIdentifierGateway.delete(userIdentifierId,userIdentifierDeleteCommand);
		if (delete) {
			return SingleResponse.of(UserIdentifierAppStructMapping.instance.toUserIdentifierVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param userIdentifierGateway
	 */
	@Autowired
	public void setUserIdentifierGateway(UserIdentifierGateway userIdentifierGateway) {
		this.userIdentifierGateway = userIdentifierGateway;
	}
}
