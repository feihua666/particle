package com.particle.user.app.executor;

import com.particle.user.app.structmapping.UserAppStructMapping;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.domain.User;
import com.particle.user.domain.UserId;
import com.particle.user.domain.gateway.UserGateway;
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
 * 用户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
@Validated
public class UserDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UserGateway userGateway;

	/**
	 * 执行 用户 删除指令
	 * @param userDeleteCommand
	 * @return
	 */
	public SingleResponse<UserVO> execute(@Valid IdCommand userDeleteCommand) {
		UserId userId = UserId.of(userDeleteCommand.getId());
		User byId = userGateway.getById(userId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = userGateway.delete(userId);
		if (delete) {
			return SingleResponse.of(UserAppStructMapping.instance.toUserVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param userGateway
	 */
	@Autowired
	public void setUserGateway(UserGateway userGateway) {
		this.userGateway = userGateway;
	}
}
